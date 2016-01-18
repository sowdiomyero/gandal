/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier.services;

import java.util.Date;
import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Projet;

/**
 *
 * @author MSALL
 */
public interface IProjetMetier {
    
    public List<Projet> getAllProjets();
    
    public Projet getProjetById(Long idProjet);
    
    public Projet getProjetByName(String nomProjet);
    
    public boolean isProjetNameExist(String nomProjet, Long idProjet);
    
    public List<Projet> getProjetByEtat(int etat);
    
    public List<Projet> getProjetsByRechercheAvancee(int etat, Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu);
    
    public List<Projet> getProjetsByRechercheAvanceeAllEtat(Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu);
}
