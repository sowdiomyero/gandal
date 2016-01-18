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
import sn.gandal.gesimmo.modele.client.entities.Etape;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;
import sn.gandal.gesimmo.service.IEtapeDao;

/**
 *
 * @author msall
 */
@Transactional
@Repository
public class EtapeDaoImpl implements IEtapeDao, Serializable {

    @PersistenceContext
    EntityManager em;

    public Etape getEtapeById(Long idEtape) {
        return (Etape) em.createNamedQuery(Etape.FIND_ETAPE_BY_ID).setParameter("idEtape", idEtape).setParameter("etat", ParamEntity.ETAT_DESACTIVE).getSingleResult();
    }

    public Etape getEtapeByName(String nomEtape, Long idParent) {
        return (Etape) em.createNamedQuery(Etape.FIND_ETAPE_BY_NOM).setParameter("nomEtape", nomEtape).setParameter("idParent", idParent).setParameter("etat", ParamEntity.ETAT_DESACTIVE).getSingleResult();
    }

    public boolean isEtapeNameExist(String nomEtape, Long idEtape, Long idParent) {
        List<Etape> etapes = em.createNamedQuery(Etape.IF_ETAPE_EXIST_WITH_NAME).setParameter("nomEtape", nomEtape).setParameter("idEtape", idEtape).setParameter("idParent", idParent).setParameter("etat", ParamEntity.ETAT_DESACTIVE).getResultList();
        return (etapes != null) && (!etapes.isEmpty());
    }

    public List<Etape> getEtapeByEtat(int etat, Long idParent) {
        List<Etape> etapes = em.createNamedQuery(Etape.FIND_ETAPES_BY_ETAT).setParameter("etat", etat).setParameter("idParent", idParent).getResultList();
        return etapes;
    }

    public void updateEtape(Etape etape) {
        em.merge(etape);
    }

}
