/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metierImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IEtapeMetier;
import sn.gandal.gesimmo.modele.client.entities.Etape;
import sn.gandal.gesimmo.service.IEtapeDao;

/**
 *
 * @author msall
 */
@Service
public class EtapeMetierImpl implements IEtapeMetier{
    @Autowired
    IEtapeDao dao;

    @Override
    public Etape getEtapeById(Long idEtape) {
        return dao.getEtapeById(idEtape);
    }

    @Override
    public Etape getEtapeByName(String nomEtape, Long idParent) {
       return dao.getEtapeByName(nomEtape, idParent);
    }

    @Override
    public boolean isEtapeNameExist(String nomEtape, Long idEtape, Long idParent) {
        return dao.isEtapeNameExist(nomEtape, idEtape, idParent);
    }

    @Override
    public List<Etape> getEtapeByEtat(int etat, Long idParent) {
        return dao.getEtapeByEtat(etat, idParent);
    }

    @Override
    public void updateEtape(Etape etape) {
        dao.updateEtape(etape);
    }
    
}
