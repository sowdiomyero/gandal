/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.modele.client.entities.Programme;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;
import sn.gandal.gesimmo.service.IProgrammeDao;

/**
 *
 * @author kcisse
 */
@Transactional
@Repository
public class ProgrammeDaoImpl implements IProgrammeDao{

    @PersistenceContext
    EntityManager em;

    public List<Programme> getAllProgrammes() {
        return em.createNamedQuery(Programme.FIND_ALL_PROGRAMMES).setParameter("etat",ParamEntity.ETAT_ACTIVE).getResultList();
    }

    public Programme getProgrammeById(Long idProgramme) {
        return em.find(Programme.class, idProgramme);
    }

    public Programme getProgrammeByName(String nomProjet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isProgrammeNameExist(String nomProgramme, Long idProgramme) {
         List<Programme> projets = em.createNamedQuery(Programme.IF_PROGRAMME_EXIST_WITH_NAME).setParameter("nomProgramme", nomProgramme).setParameter("idProgramme", idProgramme).getResultList();
        if ((projets != null) && (!projets.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    public List<Programme> getProgrammeByEtat(int etat) {
        return em.createNamedQuery(Programme.FIND_PROGRAMMES_BY_ETAT).setParameter("etat", etat).getResultList();
    }

    public List<Programme> getProgrammesByRechercheAvancee(int etat, Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Programme> getProgrammesByRechercheAvanceeAllEtat(Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public <S extends Programme> S save(S s) {
        return em.merge(s);
    }

    public <S extends Programme> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Programme findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Programme> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Programme> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Programme t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Iterable<? extends Programme> itrbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Programme updateProgramme(Programme programme) {
        return  em.merge(programme);
    }
    

}
