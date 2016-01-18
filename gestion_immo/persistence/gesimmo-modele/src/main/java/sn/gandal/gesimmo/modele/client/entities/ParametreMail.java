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
 * @author mskane
 */
@Entity
@Table(name = "parametremail")
public class ParametreMail extends AbstractDateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_parametremail")
    private Long idParametreMail;

 

    @Column(name = "email")
    private String email;

 

    @Column(name = "serveur_mail")
    private String serveurMail ;
    
    @Column(name = "nom_utilisateur")
    private String nomUtilisateur;
 
    @Column(name = "password")
    private String password;
    
    @Column(name = "port_smtp")
    private String portSmtp;
    
 
    
    public ParametreMail() {
    }


   

    public Long getIdParametreMail() {
        return idParametreMail;
    }

    public void setIdParametreMail(Long idParametreMail) {
        this.idParametreMail = idParametreMail;
    }

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPortSmtp() {
        return portSmtp;
    }

    public void setPortSmtp(String portSmtp) {
        this.portSmtp = portSmtp;
    }

    public String getServeurMail() {
        return serveurMail;
    }

    public void setServeurMail(String serveurMail) {
        this.serveurMail = serveurMail;
    }

    public void setParamSmtpHost(String property) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
 
    
}
