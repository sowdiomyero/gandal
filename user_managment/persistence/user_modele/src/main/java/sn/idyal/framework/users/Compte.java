package sn.idyal.framework.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue(value ="COMPTE")
public class Compte extends BaseEntity implements Serializable {
    
    @Basic(optional = true)
    @Column()
    @Temporal(TemporalType.DATE)
    private Date cptDateDeb;
    @Basic(optional = true)
    @Column()
    @Temporal(TemporalType.DATE)
    private Date cptDeadLine;
    @Basic(optional = true)
    @Column(length = 40)
    private String cptChefProjet;
    @Basic(optional = true)
    @Column(length = 50)
    private String cptContrat;
    @Basic(optional = true)
    @Column(length = 50)
    private String cptPo;

    public Compte() {
        super();   
    }

    public Date getCptDateDeb() {
        return cptDateDeb;
    }

    public void setCptDateDeb(Date cptDateDeb) {
        this.cptDateDeb = cptDateDeb;
    }

    @Override
    public int hashCode() {
        Integer id=getId();
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }



    @Override
    public String toString() {
        return String.format("Compte[%s,%s]", getId(), getCptDateDeb());
    }

}
