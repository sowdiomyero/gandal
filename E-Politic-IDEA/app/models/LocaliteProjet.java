package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity(name="localite_projet")


 public final class LocaliteProjet extends BaseEntity {
  	
    @Id
    @Column(name = "ID_LOCALITE_PROJET")
    public Long id;
 
	@Column(name = "ID_LOCALITE", nullable=false)
	private Long idLocalite;
	  @Column(name = "ID_PROJET", nullable=false)
	private Long idProjet;
	
	@JoinColumn(name="ID_PROJET", referencedColumnName = "ID_PROJET",insertable=false,updatable=false)	
	  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  private Projet  projets;

	
	@JoinColumn(name="ID_LOCALITE", referencedColumnName = "ID_LOCALITE",insertable=false,updatable=false)	
	  @ManyToOne(fetch=FetchType.LAZY)
 
    private Localite localites;


	public LocaliteProjet() {
		super();
	}


	public LocaliteProjet(Projet projets, Localite localite) {
		super();
	
		this.projets = projets;
		this.localites = localite;
	}





	public Projet getProjets() {
		return projets;
	}


	public void setProjets(Projet projets) {
		this.projets = projets;
	}


	public Localite getLocalite() {
		return localites;
	}


	public void setLocalite(Localite localite) {
		this.localites = localite;
	}
	
    public static Model.Finder<Long, LocaliteProjet> findAllLocaliteProjet= new Model.Finder<Long, LocaliteProjet>(Long.class, LocaliteProjet.class);

    
    public static List<LocaliteProjet> findProjetByLocalite(Long idLocalite){
		          return findAllLocaliteProjet.where().eq("localites.idLocalite", idLocalite).findList();
     	
    	 }
    
    public static  LocaliteProjet findLocaliteProjet(Long idLocalite,Long idProjet){
        return findAllLocaliteProjet.where().eq("localites.idLocalite", idLocalite).eq("projets.id", idProjet).findUnique();

}
    
    
    
    
    
    
    
    


	public Long getIdLocalite() {
		return idLocalite;
	}


	public void setIdLocalite(Long idLocalite) {
		this.idLocalite = idLocalite;
	}


	
	public Long getIdProjet() {
		return idProjet;
	}


	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}


	 

	
	
	
	
	

}
