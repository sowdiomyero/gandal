package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;
@Entity(name="localite_activite")
public final class LocaliteActivite extends BaseEntity {
//	@EmbeddedId
//	private LocaliteActivitePk localiteActivitePk;
	
    @Id
    @Column(name = "ID_LOCALITE_ACTIVITE")
    public Long id;
 
	@Column(name = "ID_LOCALITE", nullable=false)
	private Long idLocalite;
	  @Column(name = "ID_ACTIVITE", nullable=false)
	private Long idActivite;
	
	@JoinColumn(name="ID_ACTIVITE", referencedColumnName = "ID_ACTIVITE",insertable=false,updatable=false)	
	  @ManyToOne(fetch=FetchType.LAZY, optional=false)
  private Activites activites;

	
	@JoinColumn(name="ID_LOCALITE", referencedColumnName = "ID_LOCALITE",insertable=false,updatable=false)	
	  @ManyToOne(fetch=FetchType.LAZY)
 
    private Localite localites;


	public LocaliteActivite() {
		super();
	}


	public LocaliteActivite(Activites activites, Localite localite) {
		super();
	
		this.activites = activites;
		this.localites = localite;
	}


 


	public Activites getActivites() {
		return activites;
	}


	public void setActivites(Activites activites) {
		this.activites = activites;
	}


	public Localite getLocalite() {
		return localites;
	}


	public void setLocalite(Localite localite) {
		this.localites = localite;
	}
	
    public static Model.Finder<Long, LocaliteActivite> findAllLocaliteActivite= new Model.Finder<Long, LocaliteActivite>(Long.class, LocaliteActivite.class);

    
    public static List<LocaliteActivite> findActiviteByLocalite(Long idLocalite){
		          return findAllLocaliteActivite.where().eq("localites.idLocalite", idLocalite).findList();
     	
    	 }
    
    
   
    
    
    
    
    


	public Long getIdLocalite() {
		return idLocalite;
	}


	public void setIdLocalite(Long idLocalite) {
		this.idLocalite = idLocalite;
	}


	public Long getIdActivite() {
		return idActivite;
	}


	public void setIdActivite(Long idActivite) {
		this.idActivite = idActivite;
	}


	public Localite getLocalites() {
		return localites;
	}


	public void setLocalites(Localite localites) {
		this.localites = localites;
	}


	
	
	 public static  LocaliteActivite findLocaliteActivite(Long idLocalite,Long idActivite){
	        return findAllLocaliteActivite.where().eq("localites.idLocalite", idLocalite).eq("activites.id", idActivite).findUnique();

	}
	
	

}
