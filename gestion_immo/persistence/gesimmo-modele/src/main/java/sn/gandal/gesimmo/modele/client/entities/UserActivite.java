/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author msall
 */
@Entity
@Table(name = "user_activite")
public class UserActivite extends AbstractDateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user_activite")
    private Long idUserActivite;
    @Column(name = "fonction")
    private String fonction;
    @ManyToOne
    @JoinColumn(name = "id_activite")
    private Activite activite;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Long getIdUserActivite() {
        return idUserActivite;
    }

    public void setIdUserActivite(Long idUserActivite) {
        this.idUserActivite = idUserActivite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserActivite != null ? idUserActivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserActivite)) {
            return false;
        }
        UserActivite other = (UserActivite) object;
        if ((this.idUserActivite == null && other.idUserActivite != null) || (this.idUserActivite != null && !this.idUserActivite.equals(other.idUserActivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserActivite[ id=" + idUserActivite + " ]";
    }

}
