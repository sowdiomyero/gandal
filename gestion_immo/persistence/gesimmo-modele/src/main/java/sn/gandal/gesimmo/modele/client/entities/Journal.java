/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
 

/**
 *
 * @author ddiaw
 */
@Entity
@Table(name = "journal")
@NamedQueries({
    @NamedQuery(name = Journal.FIND_JOURNAL_BY_ID, query = "SELECT j FROM Journal j where j.idJournal = :idJournal"),
    @NamedQuery(name = Journal.FIND_JOURNAL_BY_ID_ACTIVITE, query = "SELECT j FROM Journal j where j.activite.idActivite = :id_activite and j.etat = :etat")
})

public class Journal extends AbstractDateEntity implements Serializable {

    public static final String FIND_JOURNAL_BY_ID = "findJournalById";
    public static final String FIND_JOURNAL_BY_ID_ACTIVITE = "findJournalByIdActivite";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_journal")
    private Long idJournal;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "description" , length = 1024 )
    private String description;
    @Column(name = "etat", length = 2)
    private int etat=1;
    
   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_activite")
    private Activite activite;

    public Journal() {
    }

    public Long getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(Long idJournal) {
        this.idJournal = idJournal;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.libelle;
    }

}
