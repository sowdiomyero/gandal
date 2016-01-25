/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier;

import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import org.junit.*;
import sn.gandal.gesimmo.modele.client.entities.Role;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author MSOW
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class RoleTest {

    public RoleTest() {
    }

    @Autowired
    private IGesimmoMetier metier;
    private Long idRoleCreated = 0L;

    private Role roleTest = null;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //Creation d'un role pour les test
    @Before
    public void setUp() {
        roleTest = createAndSaveRole();
        System.out.println("Creation d'un role pour les test");
    }

    //Suppression du role cree 
    @After
    public void tearDown() {
        if (roleTest.getNameRole().equalsIgnoreCase("ROLE_SUPER_ADMIN")) {
            roleTest.setNameRole("ROLE");
            metier.updateRole(roleTest);
        }
        metier.delete(roleTest.getIdRole(), Role.class);
        System.out.println("Suppression du role cree ");
    }

   

    //Methode qui cree un role de  nom ROLE_RESPONSABLE dans la base si il n'existe pas
    private Role createAndSaveRole() {
        String nomRole = "ROLE_RESPONSABLE";
        Role roleFind = metier.findRoleByName(nomRole);
        if (roleFind == null) {
            roleFind = new Role();
            roleFind.setNameRole(nomRole);
            roleFind.setRoleDesc("Le role du responsable");
            roleTest = roleFind;
            idRoleCreated = roleFind.getIdRole();
            roleFind = (Role) metier.saveObject(roleFind);
            return roleFind;
        }
        return roleFind;
    }

    //Verifie si un autre role de meme nom existe dans la base 
    @Test
    public void is_other_role_with_name_exist() {

        boolean isOk = metier.isRoleWithNameExist(roleTest.getNameRole(), roleTest.getIdRole());
        System.out.println("Valeur de la methode is_other_role_with_name_exist " + isOk);
        Assert.assertFalse(isOk);

    }

    //Verifie si un   role de meme nom existe dans la base 
    @Test
    public void is_role_with_name_exist() {

        boolean isOk = metier.isRoleWithNameExist(roleTest.getNameRole());
        System.out.println("Valeur de la methode is_role_with_name_exist " + isOk);
        Assert.assertTrue(isOk);
    }

    //Test si la modification d'un role est prise en compte
    @Test
    public void testUpdateRole() {
        roleTest.setNameRole("ROLE_RESPO");
        metier.updateRole(roleTest);
        Assert.assertTrue(metier.isRoleWithNameExist("ROLE_RESPO"));
    }

        //Test si la suppression d'un role Super Admin est interdit  
    @Test
    public void deleteRoleSuperAdmin() {
        roleTest.setNameRole("ROLE_SUPER_ADMIN");
        metier.updateRole(roleTest);
        Assert.assertNull(metier.delete(roleTest.getIdRole(), Role.class));

    }

}
