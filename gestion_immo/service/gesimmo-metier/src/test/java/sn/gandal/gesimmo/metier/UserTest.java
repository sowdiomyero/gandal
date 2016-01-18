/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier;

import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import java.util.logging.Logger;
import org.junit.*;
import sn.gandal.gesimmo.modele.client.entities.Role;
import sn.gandal.gesimmo.modele.client.entities.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sn.gandal.gesimmo.mail.sender.EmailSenderImpl;

/**
 *
 * @author SNIANG
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class UserTest {

    public UserTest() {
    }

    @Autowired
    private IGesimmoMetier metier;

    private User userTest = null;
    private Role roleTest = null;

    Logger log = Logger.getLogger(UserTest.class.getName());
    


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //Creation d'un role pour les test
    @Before
    public void setUp() {

        System.out.println("Creation d'un role pour les test");
        roleTest = createAndSaveRole();
        System.out.println("Creation d'un user pour les test");
        userTest = createAndSaveUser();

    }

    @After
    public void tearDown() {

        //Suppression du user cree 
        userTest = deleteUser();

        //Suppression du role cree 
        roleTest = deleteRole();

    }

    //Test si un user avec ce mail existe
    @Test
    public void is_user_with_email_exist() {
        boolean isOk = metier.isUserWithEmailExist(userTest.getUserMail());
        System.out.println("Valeur de la methode isUserWithEmailExist " + isOk);
        Assert.assertTrue(isOk);

    }

    //Test si un autre user different du user en argument  possede  ce mail existe
    @Test
    public void is_other_user_with_email_exist() {
        boolean isOk = metier.isUserWithMailExist(userTest.getUserMail(), userTest.getIdUser());
        System.out.println("Valeur de la methode is_other_user_with_email_exist " + isOk);
        Assert.assertFalse(isOk);

    }

    //Test si un autre user different du user en argument  possede  ce numero de telephone existe
    @Test
    public void is_other_user_with_phone_exist() {
        boolean isOk = metier.isUserWithTelExist(userTest.getUserPhone(), userTest.getIdUser());
        System.out.println("Valeur de la methode is_other_user_with_phone_exist " + isOk);
        Assert.assertFalse(isOk);

    }

   //Test si un user avec ce numero de telephone existe
    @Test
    public void is_user_with_phone_exist() {
        boolean isOk = metier.isUserWithTelExist(userTest.getUserPhone());
        System.out.println("Valeur de la methode isUserWithTelExist " + isOk);
        Assert.assertTrue(isOk);

    }

    
      @Test
    public void testUpdateUser() {
         userTest.setUserPhone("33669932494");
        metier.updateUser(userTest,EmailSenderImpl.TYPE_MAIL.ACCOUNT_UPDATE);
        Assert.assertTrue(metier.isUserWithTelExist("33669932494"));
    }
 
    
    
    

   
     @Test
    public void testLoginUser() {
         Assert.assertTrue(metier.isAccountWithLoginExist("admin2"));
    }

      @Test
    public void deleteUserSuperAdmin2() {
         Assert.assertNull(metier.delete(userTest.getIdUser(), User.class));

    }

    //Fonction qui supprime le role cree
    private Role deleteRole() {
        if (roleTest.getNameRole().equalsIgnoreCase("ROLE_SUPER_ADMIN")) {
            roleTest.setNameRole("ROLE_RESPONSABLE");
            metier.updateRole(roleTest);
        }
        metier.delete(roleTest.getIdRole(), Role.class);
        System.out.println("Suppression du role cree ");
        return null;

    }

    //Fonction qui supprime le user cree
    private User deleteUser() {
        if (userTest.isUserInRole("ROLE_SUPER_ADMIN")) {
            userTest.getRoles().clear();
            metier.updateUser(userTest,EmailSenderImpl.TYPE_MAIL.ACCOUNT_UPDATE);
        }
        metier.delete(userTest.getIdUser(), User.class);
        System.out.println("Suppression du user cree ");
        return null;

    }

    //Methode qui cree un user de role    ROLE_SUPER_ADMIN dans la base  
    private User createAndSaveUser() {

        User newUser2 = new User("admin2@gandal.com", 1, "Diom Yero", "SOW", "33669932494");
        newUser2.addRole(roleTest);
        newUser2.setUserLogged(0);
        newUser2 = (User) metier.createNewUserAccount(newUser2);
        userTest = newUser2;

        return userTest;
    }

    //Methode qui cree un role de  nom ROLE_SUPER_ADMIN dans la base si il n'existe pas ..
    private Role createAndSaveRole() {
        String nomRole = "ROLE_SUPER_ADMIN";
        Role roleFind = metier.findRoleByName(nomRole);
        if (roleFind == null) {
            roleFind = new Role();
            roleFind.setNameRole(nomRole);
            roleFind.setRoleDesc("Le role du super admin");
            roleTest = roleFind;
            roleFind = (Role) metier.saveObject(roleFind);
            return roleFind;
        }
        return roleFind;
    }
}
