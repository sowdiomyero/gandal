/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author sniang
 */
@Entity
@Table(name="compte")

public class Compte extends AbstractDateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_compte")
    private Long idCompte;
    @Column(name="etat")
    private int etatCompte;
    @Column(name="login", nullable = false)
    private String login;
    @Column(name="password", nullable = false)
    private String password;
    @OneToOne(mappedBy = "compte")
    @JoinColumn(name = "id_user")
    private User user;

    @Transient
    private String passwordClair;
     
    public Compte() {
        super();
    }
    public Compte(Date dateCreation, int etatCompte, String login, String password) {
        this.etatCompte=etatCompte;
        this.login=login;
        this.password=password;
    }

    /**
     * @return the idCompte
     */
    public Long getIdCompte() {
        return idCompte;
    }

    /**
     * @param idCompte the idCompte to set
     */
    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }


    /**
     * @return the etatCompte
     */
    public int getEtatCompte() {
        return etatCompte;
    }

    /**
     * @param etatCompte the etatCompte to set
     */
    public void setEtatCompte(int etatCompte) {
        this.etatCompte = etatCompte;
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getPasswordClair() {
        return passwordClair;
    }

    public void setPasswordClair(String passwordClair) {
        this.passwordClair = passwordClair;
    }
    
    
}
