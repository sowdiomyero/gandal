package models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Compte: DYSOW
 * Date: 01/06/15
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public class Personne extends BaseEntity {

    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "DATE_NAISSANCE")
    private Date dateNaissance;
    @Column(name = "SEXE")
    private String sexe;
    @Column(name = "NUM_CNI")
    private String carteIdentite;
    @Column(name = "TEL_01")
    private String telephone;
    @Column(name = "TEL_02")
    private String telephone1;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PROFESSION")
    private String profession;

    public Personne() {
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getCarteIdentite() {
        return carteIdentite;
    }
    public void setCarteIdentite(String carteIdentite) {
        this.carteIdentite = carteIdentite;
    }
    public String getTelephone() {
        if(telephone == null || telephone.trim().length() <=  0)
            return telephone1;
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNomPrenom() {
        return nom.toUpperCase() +" "+prenom;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }
}
