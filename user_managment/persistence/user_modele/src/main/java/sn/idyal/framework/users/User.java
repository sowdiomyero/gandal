package sn.idyal.framework.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 24/05/14
 * Time: 03:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u where u.login <> 'admin'"),
        @NamedQuery(name = User.FIND_USER_BY_LOGIN, query = "SELECT u FROM User u " + "where u.login= :login"),
        @NamedQuery(name = User.COUNT_NB_USERS, query = "SELECT COUNT(u) FROM User u")})
public class User extends BaseEntity implements Serializable {

    public final static String FIND_ALL = "User.findAllUsers";
    public final static String COUNT_NB_USERS = "User.getNbUser";
    public final static String FIND_USER_BY_LOGIN = "User.findUserByLogin";
    @Column(name = "USRNOM",nullable = false, length = 50)
    private String nom;
    @Column(name = "USRPRENOM",length = 50)
    private String prenom;
    @Column(name = "USRLOGIN",length = 50)
    private String login;
    @Column(name = "USREMAIL",length = 50)
    private String email;
    @Column(name = "USRPHONE",length = 50)
    private String telephone;
    @Column(name = "USRPASSWORD",length = 64)
    private String motDePasse;
    @Column(name = "USRLOGGED")
    private boolean logged;

    @Column(name = "USRACTIVE")
    private boolean actif;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "User_userid")}, inverseJoinColumns = {
            @JoinColumn(name = "Role_roleid")
    }
    )
    private List<Role> roles;

    public User() {
        roles = new ArrayList<Role>();

    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("user[%s]", getId(), getNom());
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
