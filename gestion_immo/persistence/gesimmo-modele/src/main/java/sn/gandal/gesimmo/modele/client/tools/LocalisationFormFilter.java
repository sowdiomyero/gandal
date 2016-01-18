/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.modele.client.tools;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author kcisse
 */
public class LocalisationFormFilter {

    private String longitudeMin;
    private String longitudeMax;
    private String latitudeMin;
    private String latitudeMax;
    private String nom;
    private String description;

    @NotNull
    @NotEmpty
    private String type;


    private List<String> dType = new ArrayList<String>();

    private List<String> typeIncidentOuLocalite = new ArrayList<String>();
    private String dT;

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

    
 
    public String getLatitudeMin() {
        return latitudeMin;
    }

    public LocalisationFormFilter() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

}
