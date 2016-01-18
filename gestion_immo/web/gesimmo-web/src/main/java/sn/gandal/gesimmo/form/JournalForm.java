/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.form;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import sn.gandal.gesimmo.dto.BasicResponse;

/**
 *
 * @author msall
 */
public class JournalForm extends BasicResponse {

    public static final int RETOUR_OK = 200;
    public static final int RETOUR_EXCEPTION = -100;
    public static final int RETOUR_ROLENAME_INVALID = 100;

    private Long idJournal;

    private Long idActivite;
    @NotNull
    @NotEmpty
    private String libelle;
    private String description;

    private int etat;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    private Date dateUpdate;

    public Long getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Long idActivite) {
        this.idActivite = idActivite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public String toString() {
        return "Ajout du journal [ " + libelle + "]";
    }
}
