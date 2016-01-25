/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u FROM User u where u.userMail=:userMail"),
    @NamedQuery(name = User.FIND_USER_BY_ID, query = "SELECT u FROM User u where u.idUser=:idUser"),
    @NamedQuery(name = User.FIND_USER_BY_ID_COMPTE, query = "SELECT u FROM User u where u.idCompte=:idCompte"),
    @NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u"),})
public class User extends Personne {

    public static final String FIND_USER_BY_EMAIL = "findUserByEmail";
    public static final String FIND_ALL_USERS = "findAllTUsers";

    public static final String FIND_USER_BY_ID = "findUserById";
    public static final String FIND_USER_BY_ID_COMPTE = "findUserByIdCompte";

        
    public User() {
        super();
    }

    public User(String userMail, int userLogged, String userNom, String userPrenom, String userPhone) {
        super(userMail,userLogged, userNom, userPrenom, userPhone);
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "ID_USER"),
                inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private List<Role> roles;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "createur")
    private List<Localisation> localisations;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    public List<UserActivite> userActivite;

    /**
     * @return the userMail
     */
    public List<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(List<Localisation> localisations) {
        this.localisations = localisations;
    }

    public List<UserActivite> getUserActivite() {
        return userActivite;
    }

    public void setUserActivite(List<UserActivite> userActivite) {
        this.userActivite = userActivite;
    }

  
    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (this.roles != null) {
            this.roles.add(role);
        } else {
            this.roles = new ArrayList<Role>();
            this.roles.add(role);
        }

    }

    public void addRole(Role role, boolean clearBeforeAdd) {
        if (this.roles != null && clearBeforeAdd) {
            this.roles.clear();
            this.roles.add(role);
        } else {
            if (this.roles != null) {
                this.roles.add(role);
            } else {
                this.roles = new ArrayList<Role>();
                this.roles.add(role);
            }
        }
        ;
    }

    public boolean isUserInRole(String roleName) {

        for (Role r : roles) {
            if (r.getNameRole().equalsIgnoreCase(roleName)) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    public String toString() {
//        //return  this.getUserPrenom()+" "+this.getUserName().toUpperCase();
//        return  getFullName();
//    }



}
