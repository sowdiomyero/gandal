package models;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 01/06/15
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Section extends BaseEntity {

    @Id
    @Column(name = "ID_SECTION")
    private Long id;

    @Column(name = "NOM_SECTION")
    private String nomProjet;
    @Column(name = "DESC_SECTION")
    private String descProjet;

/*   Plus tard gerer les membres d'une section
    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    List<Membre> membres;
*/

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_LOCALITE")
    private Localite localite;


    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getDescProjet() {
        return descProjet;
    }

    public void setDescProjet(String descProjet) {
        this.descProjet = descProjet;
    }

    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public Long getId() {
        return id;
    }
}
