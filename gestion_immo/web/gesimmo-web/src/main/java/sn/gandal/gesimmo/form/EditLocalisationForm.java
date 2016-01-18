/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.form;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;

/**
 *
 * @author sniang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditLocalisationForm extends BasicResponse {

    private String dateCreation;
    private String dateUpdated;
    private Long idLocalisation;
    @JsonProperty
    private String localisation;

    @NotNull
    @NotEmpty
    private String nom;
    @NotNull
    @NotEmpty
    private String description;
    private String longitude;
    private String latitude;
    private String longitudeMin;
    private String longitudeMax;
    private String latitudeMin;
    private String latitudeMax;    

    @NotNull
    @NotEmpty
    private String type;
    private String coordonnees;
    private String nomLocaliteGoogle;

    private List<Localisation> rattachements = new ArrayList<Localisation>();
    private String rattachement;
    
    private List<String> dType = new ArrayList<String>();

    private List<String> typeIncidentOuLocalite = new ArrayList<String>();
    private Map<Long,String> responsables = new HashMap<Long,String>();
    private String responsable;
//    private List<String> typeIncidentOuLocalite = new ArrayList<String>();
    private String dT;

    private String gravite;
    
    private String nbNiveaux;
    private String nbObjets;
      
      
      
      

    public String getLatitudeMin() {
        return latitudeMin;
    }

    public void setLatitudeMin(String latitudeMin) {
        this.latitudeMin = latitudeMin;
    }

    public String getLatitudeMax() {
        return latitudeMax;
    }

    public void setLatitudeMax(String latitudeMax) {
        this.latitudeMax = latitudeMax;
    }

    
    public String getLongitudeMin() {
        return longitudeMin;
    }

    public void setLongitudeMin(String longitudeMin) {
        this.longitudeMin = longitudeMin;
    }

    public String getLongitudeMax() {
        return longitudeMax;
    }

    public void setLongitudeMax(String longitudeMax) {
        this.longitudeMax = longitudeMax;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public EditLocalisationForm() {
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

    public Long getIdLocalisation() {
        return idLocalisation;
    }

    public void setIdLocalisation(Long idLocalisation) {
        this.idLocalisation = idLocalisation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public String getNomLocaliteGoogle() {
        return nomLocaliteGoogle;
    }

    public void setNomLocaliteGoogle(String nomLocaliteGoogle) {
        this.nomLocaliteGoogle = nomLocaliteGoogle;
    }

    public List<Localisation> getRattachements() {
        return rattachements;
    }

    public void setRattachements(List<Localisation> rattachements) {
        this.rattachements = rattachements;
    }

    public String getRattachement() {
        return rattachement;
    }

    public void setRattachement(String rattachement) {
        this.rattachement = rattachement;
    }

    public List<String> getdType() {
        return dType;
    }

    public void setdType(List<String> dType) {
        this.dType = dType;
    }

    public String getdT() {
        return dT;
    }

    public void setdT(String dT) {
        this.dT = dT;
    }

    public List<String> getTypeIncidentOuLocalite() {
        return typeIncidentOuLocalite;
    }

    public void setTypeIncidentOuLocalite(List<String> typeIncidentOuLocalite) {
        this.typeIncidentOuLocalite = typeIncidentOuLocalite;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public String getNbNiveaux() {
        return nbNiveaux;
    }

    public void setNbNiveaux(String nbNiveaux) {
        this.nbNiveaux = nbNiveaux;
    }

    public String getNbObjets() {
        return nbObjets;
    }

    public void setNbObjets(String nbObjets) {
        this.nbObjets = nbObjets;
    }

    public Map<Long, String> getResponsables() {
        return responsables;
    }

    public void setResponsables(Map<Long, String> responsables) {
        this.responsables = responsables;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

}
