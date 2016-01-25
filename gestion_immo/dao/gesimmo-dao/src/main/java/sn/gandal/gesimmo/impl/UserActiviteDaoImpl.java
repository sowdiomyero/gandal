/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.UserActivite;
import sn.gandal.gesimmo.service.IUserActiviteDao;

/**
 *
 * @author msall
 */
@Transactional
@Repository
public class UserActiviteDaoImpl implements IUserActiviteDao {

    @PersistenceContext
    EntityManager em;

    public void addUserActivite(UserActivite userActivite) {
       if(userActivite != null){
           em.persist(userActivite);
       }
    }

    public void deleteUserActivite(UserActivite userActivite) {
         if(userActivite != null){
           em.remove(em.merge(userActivite));
       }
    }

    public void updateUserActivite(UserActivite userActivite) {
        if(userActivite != null){
           em.merge(userActivite);
       }
    }

}
