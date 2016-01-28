package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public class BaseEntity extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_UPDATED")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateUpdated;


    /**
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }



    /**
     * @return the dateUpdated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }


   
    @Override
    public void save() {
        this.dateCreation = new Date();
        this.dateUpdated = new Date();
        super.save();
    }

    @Override
    public void update(Object o) {
        this.dateUpdated = new Date();
       // super.update(o);
    }

}
