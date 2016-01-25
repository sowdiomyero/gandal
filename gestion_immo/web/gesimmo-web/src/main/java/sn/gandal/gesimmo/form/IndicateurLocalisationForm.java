/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.form;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.List;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;

/**
 *
 * @author ddiaw
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicateurLocalisationForm extends BasicResponse {

    private String dateCreation;
    private String dateUpdated;
    private String libelleIndicateur;
    private String nomIndicateur;
    private String uniteIndicateur;
    private List<Indicateur> indicateurs = new ArrayList<Indicateur>();
    private Long idLocalisation;
    private String valeur;

    private Long idIndicateurLocalisation;

    public IndicateurLocalisationForm() {
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

    public List<Indicateur> getIndicateurs() {
        return indicateurs;
    }

    public void setIndicateurs(List<Indicateur> indicateurs) {
        this.indicateurs = indicateurs;
    }

    public Long getIdLocalisation() {
        return idLocalisation;
    }

    public void setIdLocalisation(Long idLocalisation) {
        this.idLocalisation = idLocalisation;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Long getIdIndicateurLocalisation() {
        return idIndicateurLocalisation;
    }

    public void setIdIndicateurLocalisation(Long idIndicateurLocalisation) {
        this.idIndicateurLocalisation = idIndicateurLocalisation;
    }

}
