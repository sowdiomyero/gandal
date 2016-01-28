package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.ebean.Model;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 01/06/15
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Activites extends BaseEntity {


    @Id
    @Column(name = "ID_ACTIVITE")
    private Long id;

    @Column(name = "NOM_ACTIVITE")
    private String nomActivite;
    @Column(name = "DESC_ACTIVITE")
    private String descActivite;
    @Column(name = "SECTEUR_ACTIVITE")
    private String secteur;
    
 

 
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="activites")
    public List<LocaliteActivite> localites;

    public Activites(String nomActivite) {
        this.nomActivite = nomActivite;
     
    }

    public Activites() {
    }

    public Long getId() {
        return id;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDescActivite() {
        return descActivite;
    }

    public void setDescActivite(String descActivite) {
        this.descActivite = descActivite;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

	public List<LocaliteActivite> getLocalites() {
		return localites;
	}

	public void setLocalites(List<LocaliteActivite> localites) {
		this.localites = localites;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	// trouver une activité par son ID
	public static Model.Finder<Long, Activites> findActivites = new Model.Finder<Long, Activites>(Long.class, Activites.class);
	
	public static Activites findActiviteById(Long id) {
        return findActivites.where().eq("id",id).findUnique();
    }

    
}
