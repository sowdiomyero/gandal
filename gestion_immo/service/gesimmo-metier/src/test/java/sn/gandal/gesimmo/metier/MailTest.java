/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier;

import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import java.util.logging.Logger;
import org.junit.*; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 
/**
 *
 * @author SNIANG
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-metier-config.xml"})
public class MailTest {

    public MailTest() {
    }

    @Autowired
    private IGesimmoMetier metier;
    
    @Autowired
    private org.springframework.core.env.Environment env;

    Logger log = Logger.getLogger(MailTest.class.getName());

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    //Creation d'un role pour les test
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void parametre() {
 
        log.info("++++env+++++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.host"));
        log.info("+++++env++++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.username"));
        log.info("++++++env+++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.password"));
        log.info("++++++env+++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.port"));

    }

}
