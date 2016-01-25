package sn.gandal.gesimmo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.service.IIndicateurDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-spring-config.xml"})
public class IndicateurDaoTest {

    @Autowired
    IIndicateurDao dao;

    @Before
    public void setup() {

    }

    @Test
    public void find_indicateur_by_name() {
        Indicateur indicateur = new Indicateur();
        indicateur = dao.findIndicateurByName("populqation");
        if (indicateur != null) {
            System.out.println("+++++++###########################===========" + indicateur.getLibelle());
        }
        System.out.println("+++++++####################nulllllllllllllll#######===========");
    }

}
