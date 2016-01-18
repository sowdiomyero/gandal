
package sn.gandal.gesimmo.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.Journal;
import sn.gandal.gesimmo.service.IJournalDao;

/**
 *
 * @author isene
 */
@Transactional
@Repository
public class JournalDaoImpl implements IJournalDao {

    @PersistenceContext
    EntityManager em;

    public Journal findJournalById(Long idJournal) {
        try {
              return em.find(Journal.class, idJournal);
        } catch (Exception e) {
            return new Journal();
        }
      
    }

    public List<Journal> findJournalsByIdActivite(Long idActivite) {
        
        
        
        List<Journal> journals = em.createNamedQuery(Journal.FIND_JOURNAL_BY_ID_ACTIVITE).setParameter("id_activite", idActivite).setParameter("etat", 1).getResultList();
        if (journals != null) {
            return journals;
        } else {
            return new ArrayList<Journal>();
        }
    }

    public void updateJournal(Journal journal) {
        em.merge(journal);
    }

    public <S extends Journal> S save(S s) {
        return em.merge(s);
    }

    public <S extends Journal> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Journal findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Journal> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Journal> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        em.remove(em.find(Journal.class, id));
    }

    public void delete(Journal t) {
        em.remove(t);
    }

    public void delete(Iterable<? extends Journal> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
