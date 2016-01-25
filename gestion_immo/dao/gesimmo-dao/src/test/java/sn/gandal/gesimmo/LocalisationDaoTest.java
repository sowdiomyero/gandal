package sn.gandal.gesimmo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;

import sn.gandal.gesimmo.service.ILocalisationDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-spring-config.xml"})
public class LocalisationDaoTest {

    @Autowired
    ILocalisationDao dao;

    @Before
    public void setup() {

    }

    // @Test()
    public void list_localite_par_etat() {

        List<BatimentLocalite> list = dao.findAllTLocaliteByEtat(Localisation.ETAT.FONCTIONNEL);
        System.out.println("+++++++++++++++++++++++++++++++findAllTLocaliteByEtat++++++++++++++++++++++++++++");

        for (BatimentLocalite o : list) {
            System.out.println("++++++++++++++++++++" + o.getNomLocalisableGoogle());
        }

    }

   // @Test()
    public void list_localite_par_etat_type() {

        List<BatimentLocalite> list = dao.findAllTLocaliteByTypeAndEtat(BatimentLocalite.TYPE.LOCATION, Localisation.ETAT.FONCTIONNEL);

        System.out.println("+++++++++++++++++++++++++++++++findAllTLocaliteByTypeAndEtat++++++++++++++++++++++++++++");
        for (BatimentLocalite o : list) {
            System.out.println("++++++++++++++++++++" + o.getNomLocalisableGoogle());
        }

    }
    
   @Test(expected = Exception.class)
    public void save_localisation() {
        Localisation loc = new BatimentLocalite();
        dao.save(loc);
    }
}
