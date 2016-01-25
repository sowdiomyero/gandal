/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metierImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IProjetMetier;
import sn.gandal.gesimmo.modele.client.entities.Projet;
import sn.gandal.gesimmo.service.IProjetDao;

/**
 *
 * @author MSALL
 */
@Service
public class ProjetMetierImpl implements IProjetMetier{
    
    @Autowired
    IProjetDao projetDao;

    @Override
    public List<Projet> getAllProjets() {
        return projetDao.getAllProjets();
    }

    @Override
    public Projet getProjetById(Long idProjet) {
        return projetDao.getProjetById(idProjet);
    }

    @Override
    public Projet getProjetByName(String nomProjet) {
        return projetDao.getProjetByName(nomProjet);
    }

    @Override
    public boolean isProjetNameExist(String nomProjet, Long idProjet) {
        return projetDao.isProjetNameExist(nomProjet, idProjet);
    }

    @Override
    public List<Projet> getProjetByEtat(int etat) {
        return projetDao.getProjetByEtat(etat);
    }

    @Override
    public List<Projet> getProjetsByRechercheAvancee(int etat, Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        return projetDao.getProjetsByRechercheAvancee(etat, dateDebPrevu, dateFinPrevu, nomProjet, budgetPrevu);
    }

    @Override
    public List<Projet> getProjetsByRechercheAvanceeAllEtat(Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        return projetDao.getProjetsByRechercheAvanceeAllEtat(dateDebPrevu, dateFinPrevu, nomProjet, budgetPrevu);
    }
    
}
