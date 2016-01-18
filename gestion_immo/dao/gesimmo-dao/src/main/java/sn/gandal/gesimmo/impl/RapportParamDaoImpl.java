/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.impl;

import sn.gandal.gesimmo.service.IRapportParamDao;
import sn.gandal.gesimmo.modele.client.entities.RapportParam;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sniang
 */
@Transactional
@Repository
public class RapportParamDaoImpl implements IRapportParamDao {
@PersistenceContext
 EntityManager em;
    public <S extends RapportParam> S save(S s) {
    return  em.merge(s);
    
    }

    public <S extends RapportParam> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RapportParam findOne(Long id) {
        return em.find(RapportParam.class, id);
    }

    public boolean exists(Long id) {
        return (em.find(RapportParam.class, id).getIdParamRapport()>0);
    }

    public Iterable<RapportParam> findAll() {
        return em.createQuery("Select * from RapportParam").getResultList();
    }

    public Iterable<RapportParam> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        
        em.remove(em.find(RapportParam.class, id));
    }

    public void delete(RapportParam t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Iterable<? extends RapportParam> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RapportParam findRapportParam() {
         List<RapportParam> rapportsParam = (List<RapportParam>) em.createNamedQuery(RapportParam.FIND_PARAM_RAPPORT).getResultList();
        if(rapportsParam != null && rapportsParam.size()>0){
            return rapportsParam.get(0);
        }
        return null;
    }
    
}
