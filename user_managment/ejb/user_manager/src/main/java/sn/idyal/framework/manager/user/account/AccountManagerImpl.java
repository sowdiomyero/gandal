package sn.idyal.framework.manager.user.account;

import sn.idyal.framework.users.Role;
import sn.idyal.framework.users.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 27/05/14
 * Time: 15:28
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class AccountManagerImpl implements IAccountManager,Serializable {

    @PersistenceContext(name = "framework")
    EntityManager em;

    @Override
    public void add(Object entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Object entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public void update(Object entity) {
        em.merge(entity);
    }

    @Override
    public void merge(Object entity) {
        em.merge(entity);
    }

    @Override
    public List<User> getAllUser() {
        return em.createNamedQuery(User.FIND_ALL).getResultList();
    }

    @Override
    public List<Role> getUserRoles(Role role) {
        return null;
    }

    @Override
    public List<User> getRolesUser(User user) {
        return null;
    }

    @Override
    public List<Role> getAllRole() {
        return em.createNamedQuery(Role.ALL).getResultList();
    }

    @Override
    public List<Role> getDefaultRole() {
        return em.createNamedQuery(Role.FIND_BY_ROLENAME).setParameter("rolename", Role.DEFAULT_USER).getResultList();
    }

    @Override
    public List getAll(Class clazz) {
        return em.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a").getResultList();
    }


    public <T> List<T> getAllGeneric(Class<T> clazz) {
        return em.createQuery("SELECT a FROM "+clazz.getSimpleName()+" a").getResultList();
    }
    @Override
    public User getUserByLogin(String mLogin) {
        return (User) em.createNamedQuery(User.FIND_USER_BY_LOGIN).setParameter("login", mLogin).getSingleResult();
    }


}
