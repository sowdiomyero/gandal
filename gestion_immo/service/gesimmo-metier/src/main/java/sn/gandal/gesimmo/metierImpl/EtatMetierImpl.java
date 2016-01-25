/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metierImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IEtatMetier;
import sn.gandal.gesimmo.modele.client.entities.Etat;
import sn.gandal.gesimmo.service.IEtatDao;

/**
 *
 * @author isene
 */
@Service
public class EtatMetierImpl implements IEtatMetier{
    @Autowired
    IEtatDao dao;

    @Override
    public Etat findEtatByNom(String nom) {
       return dao.findEtatByNom(nom);
    }

    @Override
    public List<Etat> findAllEtat() {
       return dao.findAllEtat();
    }

    @Override
    public Etat updateEtat(Etat etat) {
        return dao.updateEtat(etat);
    }

    @Override
    public boolean deleteEtat(Long id) {
       return dao.deleteEtat(id);
    }

    @Override
    public Etat findById(Long id) {
       return dao.findOne(id);
    }

    
    
}
