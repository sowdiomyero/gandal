/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metierImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IIndicateurMetier;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.modele.client.entities.LocalisationIndicateur;
import sn.gandal.gesimmo.service.IIndicateurDao;

/**
 *
 * @author ddiaw
 */
@Service
public class IndicateurMetierImpl implements IIndicateurMetier {

    @Autowired
    IIndicateurDao indicateurDao;

    @Override
    public Indicateur getIndicateurById(Long idIndicateur) {
        return indicateurDao.getIndicateurById(idIndicateur);
    }

    @Override
    public List<Indicateur> findAllIndicateurByEtat(int etat) {
        return indicateurDao.findAllIndicateurByEtat(etat);
    }

    @Override
    public void updateIndicateur(Indicateur indicateur) {
        indicateurDao.updateIndicateur(indicateur);
    }

    @Override
    public Indicateur findIndicateurByLibelle(String libelle) {
        return indicateurDao.findIndicateurByLibelle(libelle);
    }

    @Override
    public LocalisationIndicateur saveLocalisationIndicateur(LocalisationIndicateur locInd) {
        return indicateurDao.saveLocalisationIndicateur(locInd);
    }

    @Override
    public void updateLocalisationIndicateur(LocalisationIndicateur locInd) {
        indicateurDao.updateLocalisationIndicateur(locInd);
    }

    @Override
    public void deleteLocalisationIndicateur(LocalisationIndicateur locInd) {
        indicateurDao.deleteLocalisationIndicateur(locInd);
    }

    @Override
    public List<LocalisationIndicateur> findLocalisationIndicateurByIdLoc(Long idLocalisation) {
        return indicateurDao.findLocalisationIndicateurByIdLoc(idLocalisation);
    }

    @Override
    public Indicateur findIndicateurByName(String nom) {
        return indicateurDao.findIndicateurByName(nom);
    }

    @Override
    public Indicateur saveIndicateur(Indicateur indicateur) {
        return indicateurDao.save(indicateur);
    }

    public boolean ifListContainElement(List<Indicateur> list1, Indicateur indicateur) {
        boolean res = false;
        for (Indicateur l1 : list1) {
            if((l1.getNomIndicateur().equalsIgnoreCase((indicateur.getNomIndicateur())))) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public List<Indicateur> supprimerIndicateurDansList(List<Indicateur> list1, List<Indicateur> list2) {
        List<Indicateur> results = new ArrayList<Indicateur>();
        if (list2.isEmpty()) {
            results = list1;
        } else {
            for (Indicateur l1 : list1) {
                if (!ifListContainElement(list2, l1)) {
                    results.add(l1);
                }
            }
        }

        return results;
    }

    @Override
    public LocalisationIndicateur findLocalisationIndicateurById(Long idLocalisationIndicateur) {
        return indicateurDao.findLocalisationIndicateurById(idLocalisationIndicateur);
    }

    @Override
    public boolean isOthersIndicateursWithNameExist(String nom, Long id) {
        Indicateur i = indicateurDao.findIndicateurByNameExceptMe(nom, id);
        if (i != null) {
            return true;
        } else {
            return false;
        }
    }

}
