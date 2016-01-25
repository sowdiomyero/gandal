/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.ActiviteEtat;
import sn.gandal.gesimmo.service.IActiviteEtatDao;

/**
 *
 * @author msall
 */
@Transactional
@Repository
public class ActiviteEtatDaoImpl implements IActiviteEtatDao, Serializable {

    @PersistenceContext
    EntityManager em;

    public List<ActiviteEtat> getActiviteEtatByIdActivite(Long idActivite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ActiviteEtat saveActiviteEtat(ActiviteEtat activiteEtat) {
        try {
            em.merge(activiteEtat);
            return activiteEtat;
        } catch (Exception e) {
          return   activiteEtat;
        }
    }

   

    
}
