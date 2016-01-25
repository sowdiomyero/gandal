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
 * @author kcisse
 */
public class ProgrammeForm extends BasicResponse{
       
    public static final int RETOUR_OK=200;
    public static final int RETOUR_EXCEPTION=-100;
    public static final int RETOUR_ROLENAME_INVALID=100;
    
    private Long idActivite;
    @NotNull @NotEmpty
    private String nomActivite;
    private String description;
    private String dateDebPrevu;
    private String dateFinPrevu;
    private Double budgetPrevu;
    private int nbreEmploiPrevu;
    private int etat;

    public ProgrammeForm() {
    }

    
    public Long getIdActivite() {
        return idActivite;
    }

    public Double getBudgetPrevu() {
        return budgetPrevu;
    }

    public void setBudgetPrevu(Double budgetPrevu) {
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
    
    @Override
    public String toString() {
        return "Ajout du role [ " + nomActivite  + "]";
    }
    
}
