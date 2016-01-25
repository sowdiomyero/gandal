/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.form;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import sn.gandal.gesimmo.dto.BasicResponse;

/**
 *
 * @author msall
 */
public class EtapeForm extends BasicResponse{
    
     private Long idActivite;
    @NotNull @NotEmpty
    private String nomActivite;
    private String description;
    private String dateDebPrevu;
    private String dateFinPrevu;
    private double budgetPrevu;
    private int nbreEmploiPrevu;
    private int etat;
    private float ponderation;
    private float tauxRealisation;
    
       private Long idParent;

    public EtapeForm() {
    }

    public Long getIdActivite() {
        return idActivite;
    }

    public float getTauxRealisation() {
        return tauxRealisation;
    }

    public void setTauxRealisation(float tauxRealisation) {
        this.tauxRealisation = tauxRealisation;
    }

    public float getPonderation() {
        return ponderation;
    }

    public void setPonderation(float ponderation) {
        this.ponderation = ponderation;
    }

    public double getBudgetPrevu() {
        return budgetPrevu;
    }

    public void setBudgetPrevu(double budgetPrevu) {
        this.budgetPrevu = budgetPrevu;
    }

    public int getNbreEmploiPrevu() {
        return nbreEmploiPrevu;
    }

    public void setNbreEmploiPrevu(int nbreEmploiPrevu) {
        this.nbreEmploiPrevu = nbreEmploiPrevu;
    }

    public void setIdActivite(Long idActivite) {
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateDebPrevu() {
        return dateDebPrevu;
    }

    public void setDateDebPrevu(String dateDebPrevu) {
        this.dateDebPrevu = dateDebPrevu;
    }

    public String getDateFinPrevu() {
        return dateFinPrevu;
    }

    public void setDateFinPrevu(String dateFinPrevu) {
        this.dateFinPrevu = dateFinPrevu;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Long getIdParent() {
        return idParent;
    }

    public void setIdParent(Long idParent) {
        this.idParent = idParent;
    }
    
    
    
    @Override
    public String toString() {
        return "Ajout du role [ " + nomActivite  + "]";
    }
}
