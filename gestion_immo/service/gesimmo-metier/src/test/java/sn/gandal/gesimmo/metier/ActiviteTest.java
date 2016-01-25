/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier;

import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.entities.Projet;

/**
 *
 * @author kcisse
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class ActiviteTest {

    public ActiviteTest() {
    }
    @Autowired
    private IActiviteMetier activite;

    private Projet projetTest = null;
    private String randomProjectName="";

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("Creation d'un projet pour les test");
        Projet projet = new Projet();
        randomProjectName = "projet_ID_"+UUID.randomUUID();
        projet.setNomActivite(randomProjectName);
        projet.setType("PROJET");
        projetTest = (Projet) activite.addActivite(projet);
        System.out.println("--------------" + projetTest.getNomActivite());

    }

    @After
    public void tearDown() {
        activite.deleteActivite(projetTest);
    }

    @Test
    public void is_projet_with_nom_exist() {
        Activite act = activite.findActivitesByTypeAndByNom("PROJET", randomProjectName);
        if ((projetTest != null) && (act != null)) {
            Assert.assertTrue(act.getNomActivite().equals(projetTest.getNomActivite()));
        }
    }

}
