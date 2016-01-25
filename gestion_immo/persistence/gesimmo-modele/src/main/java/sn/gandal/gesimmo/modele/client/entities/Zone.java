/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author msall
 */
@Entity
@Table(name = "zone")
@NamedQueries({
    @NamedQuery(name = Zone.FIND_ZONE_BY_ID, query = "SELECT l FROM Zone l where l.idZone = :idZone"),
    @NamedQuery(name = Zone.FIND_ALL_ZONES, query = "SELECT l FROM Zone l")

})
public class Zone extends AbstractDateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ZONE_BY_ID = "findZoneById";
    public static final String FIND_ALL_ZONES = "findAllTZones";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zone")
    private Long idZone;
    @Column(name = "code", length = 60, nullable = false)
    private String code;
    @Column(name = "nom", length = 80, nullable = false)
    private String nomZoneGoogle;

    @Column(name = "description", length = 100, nullable = true)
    private String description;


    @Column(name = "polygone", length = 255)
    private String polygone;
    
    @Column(name = "couleur", length = 64)
    private String couleur;

    @Version
    @Column(name = "version", columnDefinition = "INT(11) default '0'")
    private int version;


    public Zone() {
    }

    public Long getIdZone() {
        return idZone;
    }

    public void setIdZone(Long idZone) {
        this.idZone = idZone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNomZoneGoogle() {
        return nomZoneGoogle;
    }

    public void setNomZoneGoogle(String nomZoneGoogle) {
        this.nomZoneGoogle = nomZoneGoogle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPolygone() {
        return polygone;
    }

    public void setPolygone(String polygone) {
        this.polygone = polygone;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCouleur() {
        if(couleur == null)
            return "#FF0000";
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
    
}
