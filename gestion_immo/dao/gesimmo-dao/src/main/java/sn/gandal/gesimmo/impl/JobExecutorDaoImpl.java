/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.impl;

import sn.gandal.gesimmo.service.IJobExecutorDao;
import sn.gandal.gesimmo.modele.client.entities.JobExecutorHistory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sniang
 */
@Transactional
@Repository
public class JobExecutorDaoImpl implements IJobExecutorDao {

    @PersistenceContext
 EntityManager em;


    @Override
    public <S extends JobExecutorHistory> S save(S s) {
        return em.merge(s);
    }

    @Override
    public <S extends JobExecutorHistory> Iterable<S> save(Iterable<S> ses) {
        return em.merge(ses);
    }

    @Override
    public JobExecutorHistory findOne(Long aLong) {
        return em.find(JobExecutorHistory.class, aLong);
    }

    @Override
    public boolean exists(Long aLong) {
        return (em.find(JobExecutorHistory.class, aLong).getIdJob()>0);
    }

    @Override
    public Iterable<JobExecutorHistory> findAll() {
        return em.createQuery("Select * from job_execution_hist").getResultList();
    }

    @Override
    public Iterable<JobExecutorHistory> findAll(Iterable<Long> longs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long count() {
        return em.createNamedQuery("Select * from job_execution_hist").getMaxResults();
    }

    @Override
    public void delete(Long aLong) {
        em.remove(em.find(JobExecutorHistory.class, aLong));
    }

    @Override
    public void delete(JobExecutorHistory jobExecutorHistory) {
        em.remove(jobExecutorHistory);
    }

    @Override
    public void delete(Iterable<? extends JobExecutorHistory> jobExecutorHistories) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
