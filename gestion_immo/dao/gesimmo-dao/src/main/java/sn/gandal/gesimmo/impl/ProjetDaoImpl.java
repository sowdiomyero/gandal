/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.Projet;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;
import sn.gandal.gesimmo.service.IProjetDao;

/**
 *
 * @author MSALL
 */
@Transactional
@Repository
public class ProjetDaoImpl implements IProjetDao {

    @PersistenceContext
    EntityManager em;

    public List<Projet> getAllProjets() {
        return em.createNamedQuery(Projet.FIND_ALL_PROJETS).setParameter("etat", ParamEntity.ETAT_DESACTIVE).getResultList();
    }

    public Projet getProjetByName(String nomProjet) {
        return (Projet) em.createNamedQuery(Projet.FIND_PROJET_BY_NOM).setParameter("nomProjet", nomProjet).getSingleResult();
    }

    public List<Projet> getProjetByEtat(int etat) {
        return em.createNamedQuery(Projet.FIND_PROJETS_BY_ETAT).setParameter("etat", etat).getResultList();
    }

    public void updateProjet(Projet projet) {
        em.merge(projet);
    }

    public boolean isProjetNameExist(String nomProjet, Long idProjet) {
        List<Projet> projets = em.createNamedQuery(Projet.IF_PROJET_EXIST_WITH_NAME).setParameter("nomProjet", nomProjet).setParameter("idProjet", idProjet).getResultList();
        if ((projets != null) && (!projets.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    public Projet getProjetById(Long idProjet) {
        return (Projet) em.createNamedQuery(Projet.FIND_PROJET_BY_ID).setParameter("idProjet", idProjet).getSingleResult();
    }

    public List<Projet> getProjetsByRechercheAvancee(int etat, Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        List<Projet> projets = em.createNamedQuery(Projet.FIND_PROJETS_BY_RECHERCHE_AVANCEE).setParameter("etat", etat).setParameter("dateDebPrevu", dateDebPrevu).setParameter("dateFinPrevu", dateFinPrevu).setParameter("nomProjet", nomProjet).setParameter("budgetPrevu", budgetPrevu).getResultList();
        if (projets != null) {
            return projets;
        } else {
            return new ArrayList<Projet>();
        }
    }

    public List<Projet> getProjetsByRechercheAvanceeAllEtat(Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        List<Projet> projets = em.createNamedQuery(Projet.FIND_PROJETS_BY_RECHERCHE_AVANCEE_ALL_ETAT).setParameter("etat", ParamEntity.ETAT_DESACTIVE).setParameter("dateDebPrevu", dateDebPrevu).setParameter("dateFinPrevu", dateFinPrevu).setParameter("nomProjet", nomProjet).setParameter("budgetPrevu", budgetPrevu).getResultList();
        if (projets != null) {
            return projets;
        } else {
            return new ArrayList<Projet>();
        }
    }
}
