/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import sn.gandal.gesimmo.service.IRoleDao;
import sn.gandal.gesimmo.modele.client.entities.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author sniang
 */
@Transactional
@Repository
public class RoleDaoImpl implements IRoleDao {

    @PersistenceContext
    EntityManager em;

    public <S extends Role> S save(S s) {
        return em.merge(s);
    }

    public <S extends Role> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Role findOne(Long id) {
        return em.find(Role.class, id);
    }

    public boolean exists(Long id) {
        return (em.find(Role.class, id).getIdRole() > 0);
    }

    public Iterable<Role> findAll() {
        return em.createQuery("Select * from role").getResultList();
    }

    public Iterable<Role> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        return em.createNamedQuery("Select * from role").getMaxResults();
    }

    public void delete(Long id) {
        Role role = em.find(Role.class, id);
        if (role != null) {
            em.remove(role);
        }
    }

    public void delete(Role r) {
        if (r != null) {
            em.remove(r);
        }
    }

    public void delete(Iterable<? extends Role> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role findRoleByName(String nameRole) {
        List<Role> roles = (List<Role>) em.createQuery("Select r from Role r where r.nameRole =:nameRole")
                .setParameter("nameRole", nameRole).getResultList();
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        } else {
            return null;
        }
    }

    public void updateRole(Role role) {
        Role r = em.merge(role);
    }

    public List<Role> findAllRoles() {
        return em.createQuery("Select r from Role r ").getResultList();
    }

    public Role findRoleByName(String nameRole, Long idRole) {
        List<Role> roles = (List<Role>) em.createQuery("Select r from Role r where r.nameRole =:nameRole and r.idRole <>:idRole")
                .setParameter("nameRole", nameRole).setParameter("idRole", idRole).getResultList();
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        } else {
            return null;
        }
    }

}
