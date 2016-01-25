/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.form;

import com.fasterxml.jackson.annotation.*;
import sn.gandal.gesimmo.dto.BasicResponse;

/**
 *
 * @author ddiaw
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditIndicateurForm extends BasicResponse {

    private String dateCreation;
    private String dateUpdated;
    private String libelleIndicateur;
    private String nomIndicateur;
    private String uniteIndicateur;

    public EditIndicateurForm() {
    }

    
    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getLibelleIndicateur() {
        return libelleIndicateur;
    }

    public void setLibelleIndicateur(String libelleIndicateur) {
        this.libelleIndicateur = libelleIndicateur;
    }

    public String getNomIndicateur() {
        return nomIndicateur;
    }

    public void setNomIndicateur(String nomIndicateur) {
        this.nomIndicateur = nomIndicateur;
    }

    public String getUniteIndicateur() {
        return uniteIndicateur;
    }

    public void setUniteIndicateur(String uniteIndicateur) {
        this.uniteIndicateur = uniteIndicateur;
    }
   
    
}
