/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metierImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.tools.LocalisationFormFilter;
import sn.gandal.gesimmo.service.ILocalisationDao;

/**
 *
 * @author msall
 */
@Service
public class LocalisationMetierImpl implements ILocalisationMetier {

    @Autowired
    ILocalisationDao localisation;

    @Override
    public Localisation getLocalisationByName(String nomLocalisation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Localisation getLocalisationById(Long idLocalisation) {
        return localisation.getLocalisationById(idLocalisation);
    }

    @Override
    public void updateLocalisation(Localisation loc) {
        localisation.updateLocalisation(loc);
    }

    @Override
    public Localisation saveLocalisation(Localisation loc) {
        return localisation.save(loc);
    }

    @Override
    public List<BatimentLocalite> findAllTLocaliteByTypeAndEtat(BatimentLocalite.TYPE type, Localisation.ETAT etat) {
        return localisation.findAllTLocaliteByTypeAndEtat(type, etat);
    }

    @Override
    public List<Localisation> findAllLocalisationByTypeAndEtat(String type, Localisation.ETAT etat) {
        return localisation.findAllLocalisationByTypeAndEtat(type, etat);
    }
    
    

    @Override
    public List<BatimentLocalite> findAllTLocaliteByEtat(Localisation.ETAT etat) {
        return localisation.findAllTLocaliteByEtat(etat);
    }

    @Override
    public List<Localisation> findAllLocalisationByEtat(Localisation.ETAT etat) {
        return localisation.findAllLocalisationByEtat(etat);
    }  
    
    @Override
    public String[][] findAllTLocaliteByTypeAndEtatArray(BatimentLocalite.TYPE type, Localisation.ETAT etat) {

        return getLocalitesInArray(findAllTLocaliteByTypeAndEtat(type, etat));
    }

    @Override
    public String[][] findAllTLocalisationByTypeAndEtatArray(String type, Localisation.ETAT etat) {
        return getLocalitesInArray(findAllLocalisationByTypeAndEtat(type, etat));
    }
    

    @Override
    public String[][] findAllTLocaliteByEtatArray( Localisation.ETAT etat) {

        return getLocalitesInArray(findAllTLocaliteByEtat(etat));
    }

    @Override
    public String[][] findAllLocalisationByEtatArray(Localisation.ETAT etat) {
        return getLocalitesInArray(findAllLocalisationByEtat(etat));
    }

    
    
    
    
    public   String[][] getLocalitesInArray(List<? extends Localisation> localites) {

        String[][] locations = new String[localites.size()][11];

        for (int i = 0; i < localites.size(); i++) {
            Localisation ligne = localites.get(i);
            // locations[0]= new String[]{Nom Quartier, longitude, lattitude,
            // alt,"COMMUNE"};
            locations[i] = new String[]{
                // nomLocalite 0
                ligne.getNomLocalisable(),
                // latitude 1
                String.valueOf(ligne.getLatitude()),
                // longitude 2
                String.valueOf(ligne.getLongitude()),
                // Type Localite 3
                ligne.getTypeToString(),
                // Type ID 4
                String.valueOf(ligne.getIdLocalisation()),
                //Responsable  5 
                ligne.getUser() != null ? ligne.getUser().getUserName() + " " + ligne.getUser().getUserPrenom() : "No Responsable",
                
                // Type Localite 3
                ligne.getDType()
            };

        }
        return locations;
    }
    
    public   String[][] getIncidentsInArray(List<ObjetIncident> localites) {

        String[][] locations = new String[localites.size()][11];

        for (int i = 0; i < localites.size(); i++) {
            ObjetIncident ligne = localites.get(i);
            // locations[0]= new String[]{Nom Quartier, longitude, lattitude,
            // alt,"COMMUNE"};
            locations[i] = new String[]{
                // nomLocalite 0
                ligne.getNomLocalisable(),
                // latitude 1
                String.valueOf(ligne.getLatitude()),
                // longitude 2
                String.valueOf(ligne.getLongitude()),
                // Type Localite 3
                ligne.getTypeToString(),
                // Type ID 3
                String.valueOf(ligne.getIdLocalisation()),
                //Responsable  5 
                ligne.getUser() != null ? ligne.getUser().getUserName() + " " + ligne.getUser().getUserPrenom() : "No Responsable"
            };

        }
        return locations;
    }
 
    @Override
    public BatimentLocalite findLocaliteByName(String nom) {
        return localisation.findLocaliteByName(nom);
    }
    @Override
    public User getResponsableLocalisation(Long idLocalisation) {
         return localisation.getResponsableLocalisation(idLocalisation);
    }

    @Override
    public List<Localisation> findLocalisationByEtat(Localisation.ETAT etat) {
        return localisation.findLocalisationByEtat(etat);
    }

    @Override
    public List<Localisation> findLocalisationByCriteres(LocalisationFormFilter localisationFormFilter) {
        return localisation.findLocalisationByCriteres(localisationFormFilter);
    }

    @Override
    public String[][] findAllTLocaliteFilterArray(List<BatimentLocalite> list) {
        return  getLocalitesInArray(list);
    }

    @Override
    public String[][] findAllTIncidentFilterArray(List<ObjetIncident> list) {
                return  getIncidentsInArray(list);

    }

    @Override
    public List<BatimentLocalite> findLocaliteByCriteres(LocalisationFormFilter localisationFormFilter) {
        return localisation.findLocaliteByCriteres(localisationFormFilter);
    }

    @Override
    public List<ObjetIncident> findIncidentByCriteres(LocalisationFormFilter localisationFormFilter) {
       return localisation.findIncidentByCriteres(localisationFormFilter);
    }

    @Override
    public boolean isKeyExist(String key) {
       return localisation.isKeyExist(key);
    }
    
    
 
}
