/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author DYSOW
 */

@Entity
@Table(name = "prestataire")
class Prestataire extends Personne {

    private static final long serialVersionUID = 1L;
    
      public enum TYPE_CONTRAT {
        NON_DEFINI,
        PRESTATION,
        INTERIM
    }
    
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id_prestataire")
//    private Long idPrestataire;
        
    @Column(name = "code_prestataire")
    private String codePrestataire;
    
    @Column(name = "raison_sociale")
    private String raisonSociale;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_prestataire")
    private TYPE_CONTRAT typePrestation;

    public Prestataire() {
    }

    public String getCodePrestataire() {
        return codePrestataire;
    }

    public void setCodePrestataire(String codePrestataire) {
        this.codePrestataire = codePrestataire;
    }

    public TYPE_CONTRAT getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(TYPE_CONTRAT typePrestation) {
        this.typePrestation = typePrestation;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

  
    
    
    
    
    
}
