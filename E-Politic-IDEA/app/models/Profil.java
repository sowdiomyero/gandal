package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 01/06/15
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
@Entity
//@AttributeOverrides({@AttributeOverride(name = "ID", column = @Column(name = "ID_PROFIL"))})
public class Profil extends BaseEntity {

    public static enum CODE{
        ADMIN("Administrateur de l'application"),
        RESPONSABLE ("Responsable ayant une vue limitée sur sa localité")   ;

        public String desc;

        private CODE(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
    @Id
    @Column(name="ID_PROFIL")
    private Long id;

    @Column(name="DESC_PROFIL")
    private String descProfil;

    @Enumerated(EnumType.STRING)
    @Column(name="NAME_PROFIL")
    private CODE nom;

/*    @ManyToMany(mappedBy = "profil")
    private List<Membre> membres;*/

    public static Model.Finder<Long, Profil> findProfil = new Model.Finder<Long, Profil>(Long.class, Profil.class);


    public Profil() {
    }
    public Long getId() {
        return id;
    }

    public String getDescProfil() {
        return descProfil;
    }

    public void setDescProfil(String descProfil) {
        this.descProfil = descProfil;
    }

    public CODE getNom() {
        return nom;
    }

    public void setNom(CODE nom) {
        this.nom = nom;
    }

    /*public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }*/

    public static Profil findProfilByName(String name) {
        return findProfil.where().eq("nom",name).findUnique();
    }
    public static Profil findProfilById(Long id) {
        return findProfil.where().eq("id",id).findUnique();
    }

    public static List<Profil> findAllProfils() {
        return findProfil.all();
    }
}
