/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.Etat;
import sn.gandal.gesimmo.service.IEtatDao;

/**
 *
 * @author msall
 */
@Transactional
@Repository
public class EtatDaoImpl implements IEtatDao, Serializable {

    @PersistenceContext
    EntityManager em;

    public Etat findEtatByNom(String nom) {
        try {
            return (Etat) em.createNamedQuery(Etat.ETAT_FIND_BY_NOM).setParameter("nom", nom).getSingleResult();
        } catch (Exception e) {
            return new Etat();
        }
    }

    public <S extends Etat> S save(S s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <S extends Etat> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Etat findOne(Long id) { 
        try {
          return  em.find(Etat.class, id);
        } catch (Exception e) {
            return new Etat();
        }
    }

    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Etat> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {

    }

    public void delete(Etat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Iterable<? extends Etat> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Etat> findAllEtat() {
        try {
            return em.createNamedQuery(Etat.ETAT_FIND_ALL).getResultList();
        } catch (Exception e) {
            return new ArrayList<Etat>();
        }
    }

    public Iterable<Etat> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Etat updateEtat(Etat etat) {
        try {
            return em.merge(etat);
        } catch (Exception e) {
            return etat;
        }

    }

    public boolean deleteEtat(Long id) {
        try {
            em.remove(em.find(Etat.class, id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
