/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Etape;

/**
 *
 * @author msall
 */
public interface IEtapeMetier {
    
    public Etape getEtapeById(Long idEtape);

    public Etape getEtapeByName(String nomEtape, Long idParent);

    public boolean isEtapeNameExist(String nomEtape, Long idEtape, Long idParent);

    public List<Etape> getEtapeByEtat(int etat, Long idParent);
    
    public void updateEtape(Etape etape);

}
