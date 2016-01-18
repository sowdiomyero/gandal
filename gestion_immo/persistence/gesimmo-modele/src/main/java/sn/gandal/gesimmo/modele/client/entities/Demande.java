/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.modele.client.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DYSOW
 */
@Entity
@Table(name = "demande")
public class Demande extends AbstractDateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public enum TYPE {
        REPARATION,
        ENTRETIEN,
        REMPLACEMENT,
        INSTALLATION
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_demande")
    private Long id;
    
    @Column(name = "code_demande")
    private String codeDemande;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_demande")
    private TYPE typeDemande;
    
    @Column(name = "resume")
    private String resume;
    
    @Column(name = "date_echeance")
    @Temporal(TemporalType.DATE)
    private Date dateEcheance;
    
    @ManyToOne
    @JoinColumn(name = "id_createur")
    private User createur;
    
    @ManyToOne
    @JoinColumn(name = "id_responsable")
    private User attribution;
    
    @ManyToOne
    @JoinColumn(name = "id_prestataire")
    private Prestataire prestataire;   
    

    public Demande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeDemande() {
        return codeDemande;
    }

    public void setCodeDemande(String codeDemande) {
        this.codeDemande = codeDemande;
    }

    public TYPE getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TYPE typeDemande) {
        this.typeDemande = typeDemande;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public User getCreateur() {
        return createur;
    }

    public void setCreateur(User createur) {
        this.createur = createur;
    }

    public User getAttribution() {
        return attribution;
    }

    public void setAttribution(User responsable) {
        this.attribution = responsable;
    }
    
    
    
}
