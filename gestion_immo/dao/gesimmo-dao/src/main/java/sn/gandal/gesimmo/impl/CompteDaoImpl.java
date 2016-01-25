/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import sn.gandal.gesimmo.service.ICompteDao;
import sn.gandal.gesimmo.modele.client.entities.Compte;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sniang
 */
@Transactional
@Repository
public class CompteDaoImpl implements ICompteDao {

    @PersistenceContext
    EntityManager em;

    public <S extends Compte> S save(S s) {
        return em.merge(s);
    }

    public <S extends Compte> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Compte findOne(Long id) {
        return em.find(Compte.class, id);
    }

    public boolean exists(Long id) {
        return (em.find(Compte.class, id).getIdCompte() > 0);
    }

    public Iterable<Compte> findAll() {
        return em.createQuery("Select * from comptes").getResultList();
    }

    public Iterable<Compte> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        return em.createNamedQuery("Select * from comptes").getMaxResults();
    }

    public void delete(Long id) {
        em.remove(em.find(Compte.class, id));
    }

    public void delete(Compte t) {
        em.remove(t);
    }

    public void delete(Iterable<? extends Compte> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compte findAccountByLogin(String login) {
        List<Compte> cpt = new ArrayList<Compte>();
        try {
            cpt = (List<Compte>) em.createQuery("select c from Compte c where c.login=:login").setParameter("login", login)
                    .getResultList();

        } catch (NoResultException ex) {
            cpt = null;
        } catch (Exception ex) {

        }
        if (!cpt.isEmpty()) {
            return cpt.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean isAccountWithLoginExist(String login) {
        if (findAccountByLogin(login) != null) {
            return true;
        }
        return false;
    }

    public void updateCompte(Compte compte) {
        Compte comp = em.merge(compte);
        System.out.println(comp.getPassword());
    }

    public List<Compte> findComptesByEtat(int etat) {

        List<Compte> cpt = (List<Compte>) em.createQuery("select c from Compte c where c.etatCompte=:etat").setParameter("etat", etat)
                .getResultList();
        return cpt;

    }

}
