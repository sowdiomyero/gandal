package sn.gandal.gesimmo.metierImpl;

import org.springframework.beans.factory.annotation.Autowired;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;import sn.gandal.gesimmo.metier.services.IProjetMetier;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 07/12/15
 * Time: 10:59
 * To change this template use File | Settings | File Templates.
 */
public class ServiceManager {

    @Autowired
    static ILocalisationMetier localisationMetier;

    @Autowired
    IProjetMetier projetMetier;

    @Autowired
    IGesimmoMetier gesimmoMetier;


    public static ILocalisationMetier getLocalisationMetier() {
        return localisationMetier;
    }

    public IProjetMetier getProjetMetier() {
        return projetMetier;
    }

    public IGesimmoMetier getgesimmoMetier() {
        return gesimmoMetier;
    }
}
