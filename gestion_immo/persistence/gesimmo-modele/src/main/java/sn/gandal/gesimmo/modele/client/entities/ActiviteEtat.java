
package sn.gandal.gesimmo.modele.client.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.CascadeType;

/**
 *
 * @author isene
 */
@Entity
@Table(name="activite_etat")
public class ActiviteEtat implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_activite")
    private Activite activite;
    @ManyToOne
    @JoinColumn(name = "id_etat")
    private Etat etat;

    public ActiviteEtat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }


    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

}
