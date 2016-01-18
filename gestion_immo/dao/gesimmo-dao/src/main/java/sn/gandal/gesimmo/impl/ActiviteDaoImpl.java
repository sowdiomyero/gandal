/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;
import sn.gandal.gesimmo.modele.client.tools.ProjetFormFilter;
import sn.gandal.gesimmo.service.IActiviteDao;

/**
 *
 * @author msall
 */
@Transactional
@Repository
public class ActiviteDaoImpl implements IActiviteDao, Serializable {

    @PersistenceContext
    EntityManager em;

    static final Logger log = Logger.getLogger(ActiviteDaoImpl.class.getName());

    public void addActivite(Activite activite) {
        if (activite != null) {
            em.persist(activite);
        }
    }

    public List<Activite> getAllActivite() {
        List<Activite> activites = em.createNamedQuery(Activite.FIND_ALL_ACTIVITES).getResultList();
        return activites;
    }

    public Activite getActivitesByName(String nomActivite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Activite> getActiviteByTypeAndEtat(String type, int etat) {
        List<Activite> activites;
        Query q = em.createNativeQuery("SELECT * FROM activite WHERE activite.type_activite = '" + type + "' and activite.etat = " + etat);
        activites = q.getResultList();
        if (activites != null) {
            return activites;
        } else {
            return new ArrayList<Activite>();
        }
    }

    public Activite findActivitesByTypeAndByNom(String type, String name) {
        List<Activite> activites;
        Query nq = em.createNamedQuery(Activite.FIND_ACTIVITE_BY_TYPE_AND_BY_NOM).setParameter("type", type).setParameter("nomActivite", name).setParameter("etat", ParamEntity.ETAT_DESACTIVE);
//        activites = nq.getResultList();
//        if ((activites == null) || (activites.isEmpty())) {
//            return null;
//        } else if (activites.size() == 1) {
//            return activites.get(0);
//        }
        return (Activite) nq.getSingleResult();
        // throw new NonUniqueResultException();
    }

    public Activite updateActivite(Activite activite) {
        if (activite != null) {
            return em.merge(activite);
        }
        return activite;
    }

    public void deleteActivite(Activite activite) {
        if (activite != null) {
            em.remove(em.merge(activite));
        }
    }

    public Activite getActiviteById(Long idActivite) {
        return (Activite) em.createNamedQuery(Activite.FIND_ACTIVITE_BY_ID).setParameter("idActivite", idActivite).getSingleResult();
    }

    public List<Activite> findActivitesByCriteres(ProjetFormFilter projetFormFilter) {
        try {
            String query = " SELECT a FROM Activite a where a.nomActivite LIKE '%" + projetFormFilter.getNomProjet() + "%'";
            query += " AND  a.description LIKE '%" + projetFormFilter.getDescription() + "%'";
            query += " AND  a.tauxRealisation BETWEEN " + projetFormFilter.getTauxMin() + " AND " + projetFormFilter.getTauxMax();
            query += " AND  a.ponderation BETWEEN " + projetFormFilter.getPonderationMin() + " AND " + projetFormFilter.getPonderationMax();

            if (!projetFormFilter.getEtatActuel().equalsIgnoreCase("TOUT")) {
                query += "  AND  a.etatActuel.nom  = '" + projetFormFilter.getEtatActuel() + "'";
            }

            if (projetFormFilter.getNbreEmploiPrevuMax() != 0) {
                if (projetFormFilter.getNbreEmploiPrevuMax() >= projetFormFilter.getNbreEmploiPrevuMin()) {
                    query += " AND  a.nbreEmploiPrevu BETWEEN " + projetFormFilter.getNbreEmploiPrevuMin() + " AND " + projetFormFilter.getNbreEmploiPrevuMax();
                }
            }

            if (projetFormFilter.getBudgetPrevuMax() != 0) {
                if (projetFormFilter.getBudgetPrevuMax() >= projetFormFilter.getBudgetPrevuMin()) {
                    query += " AND  a.budgetPrevu BETWEEN " + projetFormFilter.getBudgetPrevuMin() + " AND " + projetFormFilter.getBudgetPrevuMax();
                }
            }

            //*****************filtre sur la date de debut
            if (!projetFormFilter.getDateDebutMax().equalsIgnoreCase("")) {
                if (!projetFormFilter.getDateDebutMin().equalsIgnoreCase("")) {
                    query += " AND  a.dateDebPrevu >= '" + projetFormFilter.getDateDebutMin() + "' AND a.dateDebPrevu <= '" + projetFormFilter.getDateDebutMax() + "'";
                }
            }

            //*****************filtre sur la date de fin
            if (!projetFormFilter.getDateFinMax().equalsIgnoreCase("")) {
                if (!projetFormFilter.getDateFinMin().equalsIgnoreCase("")) {
                    query += " AND  a.dateFinPrevu >= '" + projetFormFilter.getDateFinMin() + "' AND a.dateFinPrevu <= '" + projetFormFilter.getDateFinMax() + "'";
                }
            }

            
            log.log(Level.INFO, "=======> {0}", query);
            return em.createQuery(query).getResultList();
        } catch (Exception e) {

            log.log(Level.INFO, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ ex ", e.getMessage());
            return new ArrayList<Activite>();
        }

    }

    public List<Activite> findActivitesByCriteresExacts(ProjetFormFilter projetFormFilter) {
        try {
            String query = " SELECT a FROM Activite a where a.nomActivite LIKE '%" + projetFormFilter.getNomProjet() + "%'";
//        query += " AND  p.localite =";
            query += "  AND  p.etatActuel  = '" + projetFormFilter.getEtatActuel() + "'";
            log.info("=======> " + query);
            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            return new ArrayList<Activite>();
        }
    }

}
