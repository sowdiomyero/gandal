/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.modele.client.entities.LocalisationIndicateur;
import sn.gandal.gesimmo.service.IIndicateurDao;

/**
 *
 * @author ddiaw
 */
@Transactional
@Repository
public class IndicateurDaoImpl implements IIndicateurDao {

    @PersistenceContext
    EntityManager em;

    public Indicateur getIndicateurById(Long idIndicateur) {
        return (Indicateur) em.createNamedQuery(Indicateur.FIND_INDICATEUR_BY_ID).setParameter("idIndicateur", idIndicateur).getSingleResult();
    }

    public List<Indicateur> findAllIndicateurByEtat(int etat) {
        return em.createNamedQuery(Indicateur.FIND_INDICATEUR_BY_ETAT).setParameter("etat", etat).getResultList();
    }

    public void updateIndicateur(Indicateur indicateur) {
        em.merge(indicateur);
    }

    public <S extends Indicateur> S save(S s) {
        return em.merge(s);
    }

    public <S extends Indicateur> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Indicateur findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Indicateur> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Indicateur> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Indicateur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Iterable<? extends Indicateur> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Indicateur findIndicateurByLibelle(String libelle) {
        return (Indicateur) em.createNamedQuery(Indicateur.FIND_INDICATEUR_BY_LIBELLE).setParameter("libelle", libelle).getSingleResult();
    }

    public LocalisationIndicateur saveLocalisationIndicateur(LocalisationIndicateur locInd) {
        return em.merge(locInd);
    }

    public void updateLocalisationIndicateur(LocalisationIndicateur locInd) {
        em.merge(locInd);
    }

    public void deleteLocalisationIndicateur(LocalisationIndicateur locInd) {

        em.createQuery("Delete  from LocalisationIndicateur li where li.idLocalisationIndicateur = :idLocalisationIndicateur")
                .setParameter("idLocalisationIndicateur", locInd.getIdLocalisationIndicateur())
                .executeUpdate();

    }

    public List<LocalisationIndicateur> findLocalisationIndicateurByIdLoc(Long idLocalisation) {
        return em.createNamedQuery(LocalisationIndicateur.FIND_LOCALISATION_INDICATEUR_BY_ID_LOC).setParameter("idLocalisation", idLocalisation).getResultList();
    }

    public Indicateur findIndicateurByName(String nom) {
        Indicateur ind = null;
        try {
            ind = (Indicateur) em.createNamedQuery(Indicateur.FIND_INDICATEUR_BY_NAME).setParameter("nomIndicateur", nom).getSingleResult();
        } catch (Exception ex) {
            ind = null;
        } finally {
            return ind;
        }
    }

    public LocalisationIndicateur findLocalisationIndicateurById(Long idLocalisationIndicateur) {
        return (LocalisationIndicateur) em.createNamedQuery(LocalisationIndicateur.FIND_LOCALISATION_INDICATEUR_BY_ID).setParameter("idLocalisationIndicateur", idLocalisationIndicateur).getSingleResult();
    }

    public Indicateur findIndicateurByNameExceptMe(String nom, Long id) {
        Indicateur ind = null;
        try {
            ind = (Indicateur) em.createNamedQuery(Indicateur.FIND_INDICATEUR_BY_NAME_EXCEPT_ME).setParameter("nomIndicateur", nom).setParameter("idIndicateur", id).getSingleResult();
        } catch (Exception ex) {
            ind = null;
        } finally {
            return ind;
        }
    }
}
