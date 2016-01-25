

package sn.gandal.gesimmo.modele.client.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author isene
 */
@Entity
@Table(name = "etat")
@NamedQueries({
    @NamedQuery(name = Etat.ETAT_FIND_BY_NOM, query = "SELECT e FROM Etat e WHERE e.nom=:nom"),
    @NamedQuery(name = Etat.ETAT_FIND_ALL, query = "SELECT e FROM Etat e"),
     @NamedQuery(name = Etat.ETAT_DELETE_BY_ID, query = "DELETE FROM Etat e WHERE e.id =:id")
})
public class Etat implements Serializable{
    public static final String ETAT_FIND_BY_NOM = "etatFindByNom";
    public static final String ETAT_FIND_ALL = "etatFindAll";
    public static final String ETAT_DELETE_BY_ID = "etatDeleteById";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotNull
    @Size(min = 4, max = 40)
    private String nom;
    @Column
    @Size(min = 4, max = 200)
    private String description;

    public Etat() {
    }

    public Etat(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  nom ;
    }
    
}
