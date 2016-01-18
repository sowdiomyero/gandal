/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metierImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IActiviteEtatMetier;
import sn.gandal.gesimmo.modele.client.entities.ActiviteEtat;
import sn.gandal.gesimmo.service.IActiviteEtatDao;

/**
 *
 * @author isene
 */
@Service
public class ActiviteEtatMetierImpl implements IActiviteEtatMetier{
    @Autowired
    IActiviteEtatDao dao;

    @Override
    public List<ActiviteEtat> getActiviteEtatByIdActivite(Long idActivite) {
        return dao.getActiviteEtatByIdActivite(idActivite);
    }

    @Override
    public ActiviteEtat saveActiviteEtat(ActiviteEtat activiteEtat) {
       return dao.saveActiviteEtat(activiteEtat);
    }

   
    
}
