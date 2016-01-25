
package sn.gandal.gesimmo;

import sn.gandal.gesimmo.modele.client.entities.Compte;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sn.gandal.gesimmo.service.ICompteDao;

 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-spring-config.xml"})
public class CompteDaoTest {

    @Autowired
    ICompteDao dao;

    

    @Before
    public void setup() {

    }

  @Test (expected = Exception.class)
   public void login_can_not_null() {
        Compte compte = new Compte();
        compte = dao.save(compte);

  }

    @Test(expected = Exception.class)
    public void password_can_not_null() {
        Compte compte = new Compte();
        compte = dao.save(compte);

    }



}

