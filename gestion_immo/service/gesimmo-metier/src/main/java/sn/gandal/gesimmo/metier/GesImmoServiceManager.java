/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.metier.services.IIndicateurMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import sn.gandal.gesimmo.metier.services.IProgrammeMetier;
import sn.gandal.gesimmo.metier.services.IProjetMetier;
import sn.gandal.gesimmo.metier.services.IUserActiviteMetier;

/**
 *
 * @author DYSOW
 */
@Service
public class GesImmoServiceManager {
    
    @Autowired
    private IGesimmoMetier metier;
    
    @Autowired
    private IProgrammeMetier programmeService;
    
    @Autowired
    private  IIndicateurMetier indicateurService;
    
    @Autowired
    private IGesimmoMetier gesimmoMetierService;
    
    @Autowired
    private  ILocalisationMetier localisationService;
    
    @Autowired
    private  IProjetMetier projetService;
    
    @Autowired
    private  IUserActiviteMetier userService;
    
    @Autowired
    private  IActiviteMetier activiteService;

    public GesImmoServiceManager() {
    }
    
    
   
   private static final Logger LOG = Logger.getLogger(GesImmoServiceManager.class.getName());

    public  IGesimmoMetier getMetier() {
        return metier;
    }

    public  IProgrammeMetier getProgrammeService() {
        return programmeService;
    }

    public  IIndicateurMetier getIndicateurService() {
        return indicateurService;
    }

    public IGesimmoMetier getGesimmoMetierService() {
        return gesimmoMetierService;
    }

    public ILocalisationMetier getLocalisationService() {
        if(localisationService == null ){
        LOG.log(Level.WARNING, "Recuperation d'une instance de localisationService impossible : Objet Null ");
        }else{
        LOG.log(Level.INFO, "Recuperation d'une instance de localisationService OK ");
        }
        return localisationService;
    }

    public IProjetMetier getProjetService() {
        return projetService;
    }

    public IUserActiviteMetier getUserService() {
        return userService;
    }

    public IActiviteMetier getActiviteService() {
        return activiteService;
    }
    
   
   
   
}
