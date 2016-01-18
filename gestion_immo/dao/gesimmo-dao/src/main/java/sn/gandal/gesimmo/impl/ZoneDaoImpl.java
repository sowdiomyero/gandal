/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.entities.Zone;
import sn.gandal.gesimmo.service.IUsersDao;
import sn.gandal.gesimmo.service.IZoneDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author sniang
 */
@Transactional
@Repository
public class ZoneDaoImpl implements IZoneDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public <S extends Zone> S save(S s) {
        return em.merge(s);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <S extends Zone> Iterable<S> save(Iterable<S> ses) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Zone findOne(Long aLong) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean exists(Long aLong) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterable<Zone> findAll() {
        return em.createQuery(Zone.FIND_ALL_ZONES).getResultList();
    }

    @Override
    public Iterable<Zone> findAll(Iterable<Long> longs) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long count() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Long aLong) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Zone zone) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Iterable<? extends Zone> zones) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Zone> findAllZone() {
        return em.createNamedQuery(Zone.FIND_ALL_ZONES).getResultList();
    }
}
