/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static sn.gandal.gesimmo.impl.ActiviteDaoImpl.log;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.TableConfig;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.tools.LocalisationFormFilter;
import sn.gandal.gesimmo.service.ILocalisationDao;

/**
 *
 * @author msall
 */
@Transactional
@Repository
public class LocalisationDaoImpl implements ILocalisationDao {

    @PersistenceContext
    EntityManager em;

    public Localisation getLocalisationByName(String nomLocalisation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <S extends Localisation> S save(S s) {
        return em.merge(s);
    }

    public <S extends Localisation> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Localisation findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Localisation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Localisation> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Localisation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Iterable<? extends Localisation> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Localisation getLocalisationById(Long idLocalisation) {
        return (Localisation) em.createNamedQuery(Localisation.FIND_LOCALISABLE_BY_ID).setParameter("idLocalisation", idLocalisation).getSingleResult();
    }

    public void updateLocalisation(Localisation localisation) {
        Localisation localisation1 = em.merge(localisation);
    }

    public List<BatimentLocalite> findAllTLocaliteByTypeAndEtat(BatimentLocalite.TYPE type, Localisation.ETAT etat) {
        return (List<BatimentLocalite>) em.createNamedQuery(BatimentLocalite.FIND_ALL_BATIMENT_BY_TYPE_AND_ETAT).setParameter("etat", etat).setParameter("type", type).getResultList();
    }

    public List<Localisation> findAllLocalisationByTypeAndEtat(String type, Localisation.ETAT etat) {
        //String query = "Select * from Localisation where "
        return (List<Localisation>) em.createNamedQuery(Localisation.FIND_ALL_LOCALITE_BY_ETAT_AND_TYPE).setParameter("etat", etat).setParameter("type", type).getResultList();
    }

//     public List<Localisation> findAllLocalisationByTypeAndEtat(String type, int etat) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public List<BatimentLocalite> findAllTLocaliteByEtat(Localisation.ETAT etat) {

        return (List<BatimentLocalite>) em.createNamedQuery(BatimentLocalite.FIND_ALL_BATIMENT_BY_ETAT).setParameter("etat", etat).getResultList();
    }

    public List<Localisation> findAllLocalisationByEtat(Localisation.ETAT etat) {
        return (List<Localisation>) em.createNamedQuery(Localisation.FIND_LOCALISABLES_BY_ETAT).setParameter("etat", etat).getResultList();
      }

   

    
    
    public BatimentLocalite findLocaliteByName(String nom) {
        return (BatimentLocalite) em.createNamedQuery(BatimentLocalite.FIND_BATIMENT_BY_NAME).setParameter("nom", nom).getSingleResult();
    }

    public User getResponsableLocalisation(Long idLocalisation) {
        return (User) em.createNamedQuery(Localisation.FIND_RESPONSABLE_FROM_LOCALISATION).setParameter("idLocalisation", idLocalisation).getSingleResult();
    }

    public List<Localisation> findLocalisationByEtat(Localisation.ETAT etat) {
        return em.createNamedQuery(Localisation.FIND_LOCALISABLES_BY_ETAT).setParameter("etat", etat).getResultList();
    }

    @Override
    public List<Localisation> findLocalisationByCriteres(LocalisationFormFilter localisationFormFilter) {
        try {
            String query = null;
            if (localisationFormFilter.getdT().equalsIgnoreCase(TableConfig.DTYPE_LOCALITE)) {
                query = " SELECT l FROM ObjetLocalite l where l.nomLocalisable LIKE '%" + localisationFormFilter.getNom() + "%'";
                query += " AND  l.description LIKE '%" + localisationFormFilter.getDescription() + "%'";
                if (!localisationFormFilter.getType().equalsIgnoreCase("ALL")) {
                    query += "  AND  l.type  = '" + localisationFormFilter.getType() + "'";
                }
                log.info("=======> " + query);
            }
            if (localisationFormFilter.getdT().equalsIgnoreCase(TableConfig.DTYPE_INCIDENT)) {
                query = " SELECT l FROM ObjetIncident l where l.nomLocalisable LIKE '%" + localisationFormFilter.getNom() + "%'";
                query += " AND  l.description LIKE '%" + localisationFormFilter.getDescription() + "%'";
                if (!localisationFormFilter.getType().equalsIgnoreCase("ALL")) {
                    query += "  AND  l.type  = '" + localisationFormFilter.getType() + "'";
                }
                log.info("=======> " + query);

            }
            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            return new ArrayList<Localisation>();
        }

    }

    @Override
    public List<BatimentLocalite> findLocaliteByCriteres(LocalisationFormFilter localisationFormFilter) {
        try {

            String query = " SELECT l FROM ObjetLocalite l where l.nomLocalisable LIKE '%" + localisationFormFilter.getNom() + "%'";
            query += " AND  l.description LIKE '%" + localisationFormFilter.getDescription() + "%'";
            if (!localisationFormFilter.getType().equalsIgnoreCase("ALL")) {
                query += "  AND  l.type  = '" + localisationFormFilter.getType() + "'";
            }
            log.info("=======> " + query);

            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            return new ArrayList<BatimentLocalite>();
        }

    }

    @Override
    public List<ObjetIncident> findIncidentByCriteres(LocalisationFormFilter localisationFormFilter) {
        try {

            String query = " SELECT l FROM ObjetLocalite l where l.nomLocalisable LIKE '%" + localisationFormFilter.getNom() + "%'";
            query += " AND  l.description LIKE '%" + localisationFormFilter.getDescription() + "%'";
            if (!localisationFormFilter.getType().equalsIgnoreCase("ALL")) {
                query += "  AND  l.type  = '" + localisationFormFilter.getType() + "'";
            }
            log.info("=======> " + query);

            return em.createQuery(query).getResultList();
        } catch (Exception e) {
            return new ArrayList<ObjetIncident>();
        }

    }

   
}
