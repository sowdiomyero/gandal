/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier;

import sn.gandal.gesimmo.metier.services.IJournalMetier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sn.gandal.gesimmo.modele.client.entities.Journal;

/**
 *
 * @author kcisse
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class JournalTest {

    public JournalTest() {
    }
    @Autowired
    private IJournalMetier metier;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    @Rollback
    public void testUpdate() {
       Journal j= metier.createJournal(1l, "Libelle test", "descrioption test");
       j.setEtat(0);
       metier.updateJournal(j);
    }

}
