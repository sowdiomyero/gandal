/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.modele.client.entities.LocalisationIndicateur;

/**
 *
 * @author ddiaw
 */
public interface IIndicateurDao extends CrudRepository<Indicateur, Long> {

    public Indicateur getIndicateurById(Long idIndicateur);

    public List<Indicateur> findAllIndicateurByEtat(int etat);

    public void updateIndicateur(Indicateur indicateur);

    public Indicateur findIndicateurByLibelle(String libelle);

    public LocalisationIndicateur saveLocalisationIndicateur(LocalisationIndicateur locInd);

    public void updateLocalisationIndicateur(LocalisationIndicateur locInd);

    public void deleteLocalisationIndicateur(LocalisationIndicateur locInd);


    public List<LocalisationIndicateur> findLocalisationIndicateurByIdLoc(Long idLocalisation);
    
    public Indicateur findIndicateurByName(String nom);
    
    public Indicateur findIndicateurByNameExceptMe(String nom, Long id);
    

    public LocalisationIndicateur findLocalisationIndicateurById(Long idLocalisationIndicateur);

}
