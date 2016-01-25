/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import java.util.List;

import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.tools.ProjetFormFilter;

/**
 *
 * @author msall
 */
public interface IActiviteDao {

    public List<Activite> getAllActivite();

    public Activite getActivitesByName(String nomActivite);

    public Activite findActivitesByTypeAndByNom(String type, String name);

    public List<Activite> getActiviteByTypeAndEtat(String type, int etat);

    public void addActivite(Activite activite);

    public Activite updateActivite(Activite activite);

    public Activite getActiviteById(Long idActivite);

    public void deleteActivite(Activite activite);

 
    public List<Activite> findActivitesByCriteres(ProjetFormFilter projetFormFilter);

    public List<Activite> findActivitesByCriteresExacts(ProjetFormFilter projetFormFilter);

 
}
