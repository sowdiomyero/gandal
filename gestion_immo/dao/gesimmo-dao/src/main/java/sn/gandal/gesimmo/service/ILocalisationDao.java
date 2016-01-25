/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.tools.LocalisationFormFilter;

/**
 *
 * @author msall
 */
public interface ILocalisationDao extends CrudRepository<Localisation, Long> {

    public Localisation getLocalisationByName(String nomLocalisation);

    public Localisation getLocalisationById(Long idLocalisation);

    public void updateLocalisation(Localisation localisation);

    public List<BatimentLocalite> findAllTLocaliteByTypeAndEtat(BatimentLocalite.TYPE type, Localisation.ETAT etat);
    
    public List<Localisation> findAllLocalisationByTypeAndEtat(String type, Localisation.ETAT etat);

    public List<BatimentLocalite> findAllTLocaliteByEtat(Localisation.ETAT etat);
    
    public List<Localisation> findAllLocalisationByEtat(Localisation.ETAT etat);

    public BatimentLocalite findLocaliteByName(String nom);

    public User getResponsableLocalisation(Long idLocalisation);

    public List<Localisation> findLocalisationByEtat(Localisation.ETAT etat);

    public List<Localisation> findLocalisationByCriteres(LocalisationFormFilter localisationFormFilter);

    public List<BatimentLocalite> findLocaliteByCriteres(LocalisationFormFilter localisationFormFilter);
    
    public List<ObjetIncident> findIncidentByCriteres(LocalisationFormFilter localisationFormFilter); 

    public boolean isKeyExist(String key);

}
