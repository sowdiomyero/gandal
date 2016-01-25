/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ddiaw
 */
@Entity
@DiscriminatorValue(value = TableConfig.DTYPE_SITE)
@NamedQueries({
    @NamedQuery(name = SiteLocalite.FIND_ALL_LOCALITE_BY_TYPE_AND_ETAT, query = "SELECT l FROM SiteLocalite l where l.etat = :etat and l.type = :type"),
    @NamedQuery(name = SiteLocalite.FIND_ALL_LOCALITE_BY_ETAT, query = "SELECT l FROM SiteLocalite l where l.etat = :etat"),
    @NamedQuery(name = SiteLocalite.FIND_LOCALITE_BY_NAME, query = "SELECT l FROM SiteLocalite l where l.nomLocalisable = :nom")})

public class SiteLocalite extends Localisation {

    // Noms requ�tes nomm�es
    public static final String FIND_ALL_LOCALITE_BY_TYPE_AND_ETAT = "findAllSiteByTypeAndEtat";
    public static final String FIND_ALL_LOCALITE_BY_ETAT = "findAllSiteByEtat";
    public static final String FIND_LOCALITE_BY_NAME = "findSiteByName";
    // Fin d�claration des noms des requ�tes nomm�es

    public SiteLocalite(String tout) {
        super.setNomLocalisable(tout);
    }

    public static enum TYPE {
        PUBLIC,PRIVE,RESIDENCIEL,AUTRE
    }

    @Column(name = "nb_objets")
    private Integer nbObjets;

    public SiteLocalite() {
        super();
    }

    public String getDType() {
        return TableConfig.DTYPE_SITE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeToString() {

       
            return type;
      
    }

    public static List<String> getAllTypes() {
        List<SiteLocalite.TYPE> res = Arrays.asList(SiteLocalite.TYPE.values());
        List<String> response = new ArrayList<String>();
        for (SiteLocalite.TYPE value : res) {
            response.add(value.name());
        }
        return response;
    }

    @Override
    public List<String> getSpecificTypes() {
       List<SiteLocalite.TYPE> res = Arrays.asList(SiteLocalite.TYPE.values());
        List<String> response = new ArrayList<String>();
        for (SiteLocalite.TYPE value : res) {
            response.add(value.name());
        }
        return response;
    }

    public Integer getNbObjets() {
        return nbObjets;
    }

    public void setNbObjets(Integer nbObjets) {
        this.nbObjets = nbObjets;
    }

}
