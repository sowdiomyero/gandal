package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.annotation.JsonIgnore;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Membre extends Personne {

	public enum STATUS {
		ONLINE("En Ligne"), OFFLINE("Hors Ligne");
		public String desc;

		private STATUS(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	public enum ACTIF {
		OUI, NON, DESACTIVE
	}

	@Id
	@Column(name = "ID_MEMBRE")
	private Long idMembre;

	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORIE")
	public Categorie categorie;

	@ManyToMany
	@JoinTable(name = "membre_profil", joinColumns = @JoinColumn(name = "ID_MEMBRE", referencedColumnName = "ID_MEMBRE"), inverseJoinColumns = @JoinColumn(name = "ID_PROFIL", referencedColumnName = "ID_PROFIL"))
	private List<Profil> profil = new ArrayList<Profil>();

	@OneToOne(optional = true, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "ID_COMPTE")
	private Compte compte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LOCALITE")
	private Localite localiteMembre;

	@Column(name = "ID_LOCALITE", insertable = false, updatable = false)
	private Long idLocalite;

	@OneToOne(mappedBy = "responsable")
	private Equipe team;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private STATUS status;

	@Enumerated(EnumType.STRING)
	@Column(name = "ACTIF")
	private ACTIF actif;

	@OneToMany(mappedBy = "membre", fetch = FetchType.LAZY)
	private List<Inscription> inscriptions;
	/***
	 * /*** ******************************************** MODEL TOOLS POUR LES
	 * REQUETES SQL **********
	 ********************************************** 
	 */
	public static Model.Finder<Long, Membre> find = new Model.Finder<Long, Membre>(
			Long.class, Membre.class);

	public Membre() {
		this.status = STATUS.OFFLINE;
		this.actif = ACTIF.OUI;
	}

	public Long getId() {
		return idMembre;
	}

	public static Membre findMembreById(Long id) {
		Query<Membre> query = Membre.find.where().eq("idMembre", id).query();
		Logger.info("Requete generee ==> " + query.getGeneratedSql());
		List<Membre> membres = query.findList();
		Logger.info("Resultats ==> NB : " + membres.size());
		return membres.get(0);
	}

	public static Membre findMembreByMail(String email) {
		return find.where().eq("email", email).findUnique();
	}

	public static List<Membre> findMembresByIdLocalite(Long id) {
		return find.where().eq("idLocalite", id).findList();

	}

	public static List<Membre> findAllMembres() {

		Query<Membre> query = find.where().ne("actif", "DESACTIVE").query();

		List<Membre> resultat = query.findList();
		Logger.info("Requete generee ==> " + query.getGeneratedSql());
		return resultat;

	}

	public static List<Membre> findAllMembresNonResponsableEquipe() {

		String sql = "select * from membre m where m.actif != 'DESACTIVE' and m.id_membre not in"
				+ " (select e.responsable_id from equipe e where e.responsable_id= m.id_membre)";
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		List<Membre> resultat = new ArrayList<Membre>();

		for (SqlRow sqlRow : sqlQuery.findList()) {
			Membre mb = findMembreById(sqlRow.getLong("id_membre"));
			resultat.add(mb);
		}
		Logger.info("Taille du tableau  ==> " + resultat.size());
		return resultat;

	}

	// Methode qui verifie si un numero de telephone est unique

	public static boolean isPhoneNumberExist(String phone, Membre loggedMembre) {
		List<Membre> result = new ArrayList<Membre>();
		if (phone != null || !phone.equals("")) {
			Query<Membre> query = find.query();
			result = query
					.where()
					.raw("idMembre !=" + loggedMembre.getId()
							+ " AND (telephone = '" + phone
							+ "' OR telephone1 = '" + phone + "') AND telephone1 <> ''").findList();
			Logger.info("Requete sql executee : " + query.getGeneratedSql());
		}

		if (result != null && result.size() > 0) {

			return true;
		} else {
			return false;
		}

	}

	// Methode qui verifie si un cni est unique

	public static boolean isCniExist(String cni, Membre loggedMembre) {
		List<Membre> result = new ArrayList<Membre>();
		if (cni != null) {
			Query<Membre> query = find.query();
			result = query
					.where()
					.raw("idMembre !=" + loggedMembre.getId()
							+ " AND (carteIdentite = '" + cni + "')")
					.findList();
			Logger.info("Requete sql executee : " + query.getGeneratedSql());

			Logger.info("Requete taille: " + result.size());
		}

		if (result != null && result.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	/***
	 * ******************************************** GETTERS ET SETTERS
	 * **********
	 ********************************************** 
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Profil> getProfil() {
		return profil;
	}

	public void setProfil(List<Profil> profil) {
		this.profil = profil;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;

	}

	@JsonIgnore
	public Localite getLocaliteMembre() {
		return localiteMembre;
	}

	public void setLocaliteMembre(Localite localiteMembre) {
		this.localiteMembre = localiteMembre;
	}

	@JsonIgnore
	public Equipe getTeam() {
		return team;
	}

	public void setTeam(Equipe team) {
		this.team = team;
	}

	public void addProfil(Profil profilByName) {
		if (!profil.contains(profilByName))
			profil.add(profilByName);
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public Long getIdLocalite() {
		if (localiteMembre != null) {
			return localiteMembre.getId();
		} else
			return null;
	}

	public void setIdLocalite(Long idLocalite) {
		this.idLocalite = idLocalite;
	}

	public ACTIF getActif() {
		return actif;
	}

	public void setActif(ACTIF actif) {
		this.actif = actif;
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public String getNomLocaliteMembre() {
		if (localiteMembre != null) {
			return localiteMembre.getNom();
		} else
			return "";
	}
	
	//Verifier si le numero de tel exist
	
	public static boolean isPhoneNumberExist(String phone) {
		List<Membre> result = new ArrayList<Membre>();
		if (phone != null) {
			Query<Membre> query = find.query();
			result = query
					.where()
					.raw("(telephone = '" + phone + "' OR telephone1 = '"
							+ phone + "')").findList();
			Logger.info("Requete sql executee : " + query.getGeneratedSql());
		}

		if (result != null && result.size() > 0) {

			return true;
		} else {
			return false;
		}

	}
	
	//Verifier si l'adresse email existe
	
	public static boolean isEmailExist (String email) {
		List<Membre> result = new ArrayList<Membre>();
		if(email != null  ){
			Query<Membre> query = find.query();
			 result =	query.where()
			.raw("email = '"+email +"'")
			.findList();
			 Logger.info("Requete sql executee : "+query.getGeneratedSql());
			 
			 Logger.info("Requete taille: "+result.size());
		}				
		
 		if(result != null  && result.size()>0){
			return true;
		}else{
			return false;
		}

	}
	public static boolean isEmailExist (String email, Membre loggedMembre) {
		List<Membre> result = new ArrayList<Membre>();
		if(email != null  ){
			Query<Membre> query = find.query();
			 result =	query.where()
			.raw("idMembre !=" + loggedMembre.getId()
					+ " AND email = '"+email +"'")
			.findList();
			 Logger.info("Requete sql executee : "+query.getGeneratedSql());
			 
			 Logger.info("Requete taille: "+result.size());
		}				
		
 		if(result != null  && result.size()>0){
			return true;
		}else{
			return false;
		}

	}

	public  int TousLesMembres() {
		String sql = "SELECT COUNT(*) as allMembres FROM membre";
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		int allMembres=sqlQuery.findUnique().getInteger("allMembres");
		  Logger.info("Nombre de membre : "+allMembres);
		return allMembres;
	}
	
	public  int MembresDesactives() {
		String sql = "SELECT COUNT(*) as membreDesactives FROM membre WHERE actif ='DESACTIVE'";
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		int membreDesactives=sqlQuery.findUnique().getInteger("membreDesactives");
		  Logger.info("Nombre de membre desactives : "+membreDesactives);
		return membreDesactives;
	}
	
	public  int MembresActives() {
		String sql = "SELECT COUNT(*) as membresActives FROM membre WHERE actif ='OUI'";
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		int membresActives=sqlQuery.findUnique().getInteger("membresActives");
		  Logger.info("Nombre de membre actifs  : "+membresActives);
		return membresActives;
	}	
	public  int MembresBloques() {
		String sql = "SELECT COUNT(*) as membresBloques FROM membre WHERE actif ='NON'";
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		int membresBloques=sqlQuery.findUnique().getInteger("membresBloques");
		  Logger.info("Nombre de membre bloques: "+membresBloques);
		return membresBloques;
	}
}
