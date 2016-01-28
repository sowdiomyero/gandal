package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipe extends BaseEntity {

	@Id
	@Column(name = "ID_EQUIPE")
	private Long id;

	@Column(name = "NOM_EQUIPE")
	String nom;

	@OneToOne
	@JoinColumn(name = "RESPONSABLE_ID")
	private Membre responsable;

    @Column(name = "RESPONSABLE_ID", insertable = false, updatable = false)
    private Long idMembre;

	@OneToOne
	@JoinColumn(name = "LOCALITE_ID")
	private Localite localite;

	@OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    @JsonBackReference
	List<UserExterne> teamMembers = new ArrayList<UserExterne>();
	
	public static Model.Finder<Long, Equipe> find = new Model.Finder<Long, Equipe>(Long.class, Equipe.class);


    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Inscription> inscriptions;

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Equipe(String nom) {
        this.nom = nom;
    }
    public Long getId() {
        return id;
    }
    public Equipe() {
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Membre getResponsable() {
		return responsable;
	}

	public String getNomResponsable() {
		if (this.responsable != null)
			return responsable.getNom();
		else
			return "";
	}
	
	public String getNomLocalite() {
		if (this.localite != null)
			return localite.getNom();
		else
			return "";
	}


	public void setResponsable(Membre responsable) {
		this.responsable = responsable;
	}

	public List<UserExterne> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<UserExterne> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public Localite getLocalite() {
		return localite;
	}

	public void setLocalite(Localite localite) {
		this.localite = localite;
	}

	public static List<Equipe> findAllEquipes() {
		// TODO Auto-generated method stub
		return find.all();
	}
	
	 
	
	public static List<UserExterne> findUserExterneByIdEquipe(Long id) {
		List<UserExterne> resp = new ArrayList<UserExterne>();
		Equipe team = find.where().eq("id", id).findUnique();
		if(team != null)
			return team.getTeamMembers();
		
		return resp;
    }
	
	public static Equipe findEquipeByIdEquipe(Long id) {
		Equipe resp = new Equipe();
		Equipe team = find.where().eq("id", id).findUnique();
		if(team != null)
			return team;
		
		return resp;
    }
	
	
}
