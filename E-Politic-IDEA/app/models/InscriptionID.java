package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 04/06/15
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class InscriptionID implements Serializable {

    @Column(name = "ID_USER_EXT")
    private Long idUser;

    @Column(name = "ID_LOCALITE")
    private Long idLocalite;

    @Column(name = "DATE_CREATION")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    public InscriptionID() {
        dateCreation= new Date();
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }



    public Long getIdLocalite() {
        return idLocalite;
    }

    public void setIdLocalite(Long idLocalite) {
        this.idLocalite = idLocalite;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InscriptionID)) return false;

        InscriptionID that = (InscriptionID) o;

        if (!dateCreation.equals(that.dateCreation)) return false;
        if (!idLocalite.equals(that.idLocalite)) return false;
        if (!idUser.equals(that.idUser)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser.hashCode();
        result = 31 * result + dateCreation.hashCode();
        result = 31 * result + idLocalite.hashCode();
        return result;
    }
}
