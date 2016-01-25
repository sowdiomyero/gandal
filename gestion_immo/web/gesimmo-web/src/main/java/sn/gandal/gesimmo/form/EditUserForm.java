/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;

import sn.gandal.gesimmo.dto.BasicResponse;

/**
 *
 * @author sniang
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditUserForm extends BasicResponse {

    private String dateCreation;
    private String dateUpdated;
    private Long idUser;

//    private List<Role> userRoles;
//    @NotEmpty
//    @NotNull
    private String[] userRoles;

    private int etatCompte;
    @JsonProperty
    private String role;
//    @NotNull
//    @NotEmpty
    private String nom;
    @Email
    private String email;
//    @NotNull(message = "Le prenom ne peut être Null")
//    @NotEmpty(message = "Le prenom ne peut être vide")
    private String prenom;
//    @NotNull
//    @NotEmpty
    private String telephone;
    private String password;
    private String newPassword;
    private String confirmPassword;
    private String login;

    public int getEtatCompte() {
        return etatCompte;
    }

    public String[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public EditUserForm() {
    }

    public void setEtatCompte(int etatCompte) {
        this.etatCompte = etatCompte;
    }

    public Long getIdUser() {
        return idUser;
    }


    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "EditUserForm{" + "nom=" + nom + ", email=" + email + ", prenom=" + prenom + ", telephone=" + telephone + '}';
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
