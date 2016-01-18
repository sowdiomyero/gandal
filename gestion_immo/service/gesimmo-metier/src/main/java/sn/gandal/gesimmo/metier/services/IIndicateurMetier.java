/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.modele.client.entities.LocalisationIndicateur;

/**
 *
 * @author ddiaw
 */
public interface IIndicateurMetier {

    public Indicateur getIndicateurById(Long idIndicateur);

    public List<Indicateur> findAllIndicateurByEtat(int etat);

    public void updateIndicateur(Indicateur indicateur);

    public Indicateur findIndicateurByLibelle(String libelle);

    public LocalisationIndicateur saveLocalisationIndicateur(LocalisationIndicateur locInd);

    public void updateLocalisationIndicateur(LocalisationIndicateur locInd);

    public void deleteLocalisationIndicateur(LocalisationIndicateur locInd);

    public List<LocalisationIndicateur> findLocalisationIndicateurByIdLoc(Long idLocalisation);

    public Indicateur findIndicateurByName(String nom);

    public Indicateur saveIndicateur(Indicateur indicateur);

    public List<Indicateur> supprimerIndicateurDansList(List<Indicateur> list1, List<Indicateur> list2);

    public LocalisationIndicateur findLocalisationIndicateurById(Long idLocalisationIndicateur);
    
    public boolean isOthersIndicateursWithNameExist(String nom, Long id);

}

