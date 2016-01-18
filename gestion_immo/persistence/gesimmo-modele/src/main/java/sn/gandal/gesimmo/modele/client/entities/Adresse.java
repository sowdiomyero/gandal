/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.modele.client.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author DYSOW
 */
@Embeddable
class Adresse implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "adr_num_voie", length = 28)
    private String numVoie;

    @Column(name = "adr_libelle_voie", length = 128)
    private String libelleVoie;

    @Column(name = "adr_porte", length = 5)
    private String porte;

    @Column(name = "adr_num_etage", length = 10)
    private Integer etage;

    @Column(name = "adr_code_postal", length = 5)
    private String codePostal;

    @Column(name = "adr_escalier", length = 2)
    private String escalier;

    @Column(name = "adr_commune", length = 64)
    private String commune;


    public Adresse() {
    }

    public String getNumVoie() {
        return numVoie;
    }

    public void setNumVoie(String numVoie) {
        this.numVoie = numVoie;
    }

    public String getLibelleVoie() {
        return libelleVoie;
    }

    public void setLibelleVoie(String libelleVoie) {
        this.libelleVoie = libelleVoie;
    }
  

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

  
    public Integer getEtage() {
        return etage;
    }

    public void setEtage(Integer etage) {
        this.etage = etage;
    }

   

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getEscalier() {
        return escalier;
    }

    public void setEscalier(String escalier) {
        this.escalier = escalier;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }
    
}
