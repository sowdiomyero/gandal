package sn.gandal.gesimmo.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import sn.gandal.gesimmo.modele.client.entities.Role;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 06/02/15 Time: 16:39 To change
 * this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscriber {

    public static final int RETOUR_OK = 200;
    public static final int RETOUR_EXCEPTION = -100;
    public static final int RETOUR_EMAIL_INVALID = 100;
    public static final int RETOUR_PHONE_INVALID = 400;
    public static final int RETOUR_LOGIN_INVALID = 300;
    
    @NotEmpty
    @NotNull
    private String[] userRoles;

    @NotNull
    @NotEmpty
    private String nom;
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String prenom;

    @NotEmpty
    private String telephone;

    private String msg;
    private int resultat;

    private List<Role> roles;

    private Role role;

    public String[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
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

    @Override
    public String toString() {
        return "Subscriber [name=" + nom
                + ", prenom=" + prenom + "]";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
