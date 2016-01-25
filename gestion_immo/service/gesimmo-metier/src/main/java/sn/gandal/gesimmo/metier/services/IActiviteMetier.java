/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.tools.ProjetFormFilter;

/**
 *
 * @author msall
 */
public interface IActiviteMetier {

    // Activite
    public List<Activite> getAllActivite();

    public Activite getActivitesByName(String nomActivite);

    public Activite findActivitesByTypeAndByNom(String type, String name);

    public List<Activite> getActiviteByTypeAndEtat(String type, int etat);

    public Activite deleteActivite(Activite a);

    public Activite addActivite(Activite activite);

    public void updateActivite(Activite activite);

    public Activite getActiviteById(Long idActivite);
    // Fin Activite
     public boolean updateEtat( Long idActivite,String nomEtat);
     
     
     public List<Activite> findActivitesByCriteres(ProjetFormFilter projetFormFilter,String type);

    public List<Activite> findActivitesByCriteresExacts(ProjetFormFilter projetFormFilter , String type);
}
