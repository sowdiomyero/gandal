/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kcisse
 */
public class ZoneFormCollector {

   /* private List<String> latLong;*/
    private String nomZone;
    private String latLongs;
    private String codeZone;
    private String description;
    private String couleur;

    public ZoneFormCollector(String nomZone) {
        this.nomZone = nomZone;
    }

    public ZoneFormCollector() {
    }

    public String getNomZone() {
        return nomZone;
    }

    public void setNomZone(String nomZone) {
        this.nomZone = nomZone;
    }

    public String getLatLongs() {
        return latLongs;
    }

    public void setLatLongs(String latLongs) {
        this.latLongs = latLongs;
    }

    public String getCodeZone() {
        return codeZone;
    }

    public void setCodeZone(String codeZone) {
        this.codeZone = codeZone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}
