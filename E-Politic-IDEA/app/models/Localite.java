package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import models.Inscription.STATE;
import play.Logger;
import play.db.ebean.Model;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 01/06/15 Time: 17:42 To change
 * this template use File | Settings | File Templates.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "DTYPE")
public class Localite extends BaseEntity {

	@Id
	@Column(name = "ID_LOCALITE")
	public Long idLocalite;

	@Column(name = "NOM_LOCALITE", unique = false)
	// on peut avoir deux localités avec le même nom : Pikine Dakar et Pikine
	// Saint Louis
	public String nom;

	@Column(name = "NB_SECTIONS")
	public int nbSections;

	@Column(name = "NB_MILITANTS")
	public int nbMilitants;

	@Column(name = "OBJECTIFS")
	public double objectifs;

	@Column(name = "DTYPE", insertable = false, updatable = false)
	public String dType;

	@Embedded
	public InfoAdministrative infoAdministrative;
	@Embedded
	public InfoGeographique infoGeographique;

	@OneToOne(mappedBy = "localite")
	public Equipe team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Localite parentLocalite;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parentLocalite")
	public List<Localite> childsLocalite = new ArrayList<Localite>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "localites")
	public List<LocaliteActivite> activites;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "localites")
	public List<LocaliteProjet> localiteProjet;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "localite")
	public List<Section> sections = new ArrayList<Section>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "localiteMembre")
	public List<Membre> membres = new ArrayList<Membre>();

	@OneToMany(mappedBy = "localite", fetch = FetchType.LAZY)
	private List<Inscription> inscriptions;

	public static Model.Finder<Long, Localite> findLocalite = new Model.Finder<Long, Localite>(
			Long.class, Localite.class);

	public static Model.Finder<Long, Activites> findActivites = new Model.Finder<Long, Activites>(
			Long.class, Activites.class);

	public Localite() {
		infoAdministrative = new InfoAdministrative();
		infoGeographique = new InfoGeographique();
	}

	public int getNbPopulation() {
		int result = getInfoAdministrative().getPopulation();
		List<Localite> fils = getChildsLocalite();
		if (fils != null && fils.size() > 0) {
			for (Localite loc : fils) {
				result += loc.getNbPopulation();
			}
		}
		return result;
	}

	public double getNbObjectifs() {
		double result = getObjectifs();
		Logger.info(" Nb Objectifs pour la seule localite " + getNom() + " : "
				+ result);
		List<Localite> fils = getChildsLocalite();
		if (fils != null && fils.size() > 0) {
			for (Localite loc : fils) {
				result += loc.getNbObjectifs();
			}
		}
		Logger.info(" Nb Objectifs pour la localite " + getNom()
				+ " et ses enfants : " + result);
		return result;
	}

	public double getPourcentageObjectifs() {
		if (getNbInscrits() <= 0)
			return 0L;
		double inscrits = Double.valueOf(getNbInscrits());
		double objectif = getObjectifs();

		double rapport = objectif / inscrits;
		BigDecimal bd = new BigDecimal(rapport);
		BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return rounded.doubleValue();

	}

	public int getNombreObjectifs() {
		double electeur = 0L;
		double objectif = 0L;
		int rapport = 0;
		InfoAdministrative infoAdministrative = getInfoAdministrative();
		if (infoAdministrative != null
				&& infoAdministrative.getNbElecteurs() != 0
				&& getObjectifs() != 0) {
			Logger.info("Info administratives : "
					+ infoAdministrative.getNbElecteurs());
			electeur = Double.valueOf(infoAdministrative.getNbElecteurs());
			objectif = getObjectifs();
			if (electeur > 0 && objectif > 0)
				rapport = (int) ((objectif * electeur) / 100);
		}
		return rapport;
	}

	public int getNbMilitantsLocalite() {

		int result = getNbMilitants();
		List<Localite> fils = getChildsLocalite();
		if (fils != null && fils.size() > 0) {
			for (Localite loc : fils) {
				result += loc.getNbMilitantsLocalite();
			}
		}
		return result;
	}

	public int getNbInscrits() {
		int result = getInfoAdministrative().getNbInscrits();
		List<Localite> fils = getChildsLocalite();
		if (fils != null && fils.size() > 0) {
			for (Localite loc : fils) {
				result += loc.getInfoAdministrative().getNbInscrits();
			}
		}
		return result;
	}

	public int getNbTotalInscrits() {
		int result = getInfoAdministrative().getNbInscrits();
		List<Inscription> L = getInscriptions();
		for (Inscription i : L) {
			if (i.getEtat() == STATE.VALIDATED)
				result += i.getNbInscriptions();
		}
		return result;
	}

	public double getPourcentageRealisation() {

		if (this.getNombreObjectifs() > 0) {
			double pourcentage = ((this.getNbTotalInscrits() * 100) / (this
					.getNombreObjectifs()));
			BigDecimal bd = new BigDecimal(pourcentage);
			BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			return rounded.doubleValue();
		}
		return 0;
	}

	// gettter et setter sur la table associative localite projet

	public List<LocaliteProjet> getLocaliteProjet() {
		return localiteProjet;
	}

	public void setLocaliteProjet(List<LocaliteProjet> localiteProjet) {
		this.localiteProjet = localiteProjet;
	}

	public double getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(double objectifs) {
		this.objectifs = objectifs;
	}

	public void setObjectifs(Double obj) {
		objectifs = obj;
	}

	public String getNom() {
		return nom;
	}

	public Long getId() {
		return idLocalite;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@JsonIgnore
	public List<Membre> getMembres() {
		return membres;
	}

	public void setMembres(List<Membre> membres) {
		this.membres = membres;
	}

	public void addMembre(Membre mb) {
		this.membres.add(mb);
	}

	public int getNbSections() {
		return nbSections;
	}

	public void setNbSections(int nbSections) {
		this.nbSections = nbSections;
	}

	public List<LocaliteActivite> getActivites() {
		return activites;
	}

	public void setActivites(List<LocaliteActivite> activites) {
		this.activites = activites;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public InfoAdministrative getInfoAdministrative() {
		return infoAdministrative;
	}

	public void setInfoAdministrative(InfoAdministrative infoAdministrative) {
		this.infoAdministrative = infoAdministrative;
	}

	public InfoGeographique getInfoGeographique() {
		return infoGeographique;
	}

	public void setInfoGeographique(InfoGeographique infoGeographique) {
		this.infoGeographique = infoGeographique;
	}

	public Equipe getTeam() {
		return team;
	}

	public String getNomResponsable() {
		if (team == null)
			return "No Team Defined";
		if (team.getResponsable() == null)
			return "No Lead Defined";
		return team.getResponsable().getNomPrenom();
		
		
		
	}
	public String getNomParentLocalite() {
		if (parentLocalite == null)
			return "No Parent Defined";
		Logger.info("NOM DU PARENT parentLocalite.getNom()" +parentLocalite.getNom());
		return parentLocalite.getNom();
	}
	public Long getIdParentLocalite() {
		if (parentLocalite == null)
			return null;
	
	
		return parentLocalite.getId();
	}
	public String getNomEquipe() {
		if (team == null)
			return "No Team Defined";

		return team.getNom();
	}

	public String getStatutResponsable() {
		if (team == null)
			return "No Team Defined";
		if (team.getResponsable() == null)
			return "No Team Lead Defined";
		return team.getResponsable().getStatus().getDesc();
	}

	public Long getIdResponsable() {
		if (team == null)
			return null;
		if (team.getResponsable() == null)
			return null;
		return team.getResponsable().getId();
	}

	public void setTeam(Equipe team) {
		this.team = team;
	}

	public String getDType() {
		return dType;
	}

	@JsonIgnore
	public Localite getParentLocalite() {
		return parentLocalite;
	}

	public void setParentLocalite(Localite parentLocalite) {
		this.parentLocalite = parentLocalite;
	}

	/**
	 * EBEAN REQUEST
	 */
	@JsonIgnore
	public static List<Localite> findAllLocalites() {
		return findLocalite.all();
	}

	public static List<Localite> findDistinctLocalites() {
		return Ebean.find(Localite.class).setDistinct(true)
				.select("idLocalite, nom").findList();
	}

	public static Localite findLocaliteById(Long id) {
		Localite loc = findLocalite.where().eq("idLocalite", id).findUnique();
		Logger.info("Localité retrouvée : " + loc);
		return loc;
	}

	public static List<Membre> findMembresLocaliteById(Long id) {
		return Membre.findMembresByIdLocalite(id);
	}

	public static List<Membre> findMembresLocaliteById2(Long id) {
		return findLocalite.where().eq("idLocalite", id).findUnique()
				.getMembres();
	}

	public static String[][] getAllLocalites() {
		List<Localite> localites = Localite.findAllLocalites();
		return getLocalitesInArray(localites);
	}

	public static List<Localite> getListAllLocalites() {
		List<Localite> localites = Localite.findAllLocalites();
		return localites;
	}

	public static String[][] getLocalitesByType(String type) {

		List<Localite> localites = findLocalite.where().eq("dType", type)
				.findList();
		return getLocalitesInArray(localites);

	}

	public static String[][] searchLocalites(String type,
			int populationBetween, int populationAnd, int nbMilitants) {
		List<Localite> results = new ArrayList<Localite>();
		Logger.info("Parametres de la requete searchLocalites : [type : "
				+ type + "] - [populationBetween : " + populationBetween
				+ "] - [ " + populationAnd + "] - [nbMilitants :" + nbMilitants
				+ "]");
		if (type == null || type.trim().equalsIgnoreCase("ALL")) {
			results = findLocalite
					.where()
					.between("infoAdministrative.population",
							populationBetween, populationAnd)
					.gt("nbMilitants", nbMilitants).findList();
		} else {
			results = findLocalite
					.where()
					.eq("dType", type)
					.between("infoAdministrative.population",
							populationBetween, populationAnd)
					.gt("nbMilitants", nbMilitants).findList();
		}
		Logger.info("Nombre de valeur retournées par la recherche de localité : "
				+ results.size());
		return getLocalitesInArray(results);

	}

	public static List<Membre> searchMembres(String type,
			int populationBetween, int populationAnd, int nbMilitants) {
		List<Membre> membres = new ArrayList<Membre>();
		List<Localite> results = new ArrayList<Localite>();
		Logger.info("Parametres de la requete searchLocalites : [type : "
				+ type + "] - [populationBetween : " + populationBetween
				+ "] - [ " + populationAnd + "] - [nbMilitants :" + nbMilitants
				+ "]");
		if (type == null || type.trim().equalsIgnoreCase("ALL")) {
			results = findLocalite
					.where()
					.between("infoAdministrative.population",
							populationBetween, populationAnd)
					.gt("nbMilitants", nbMilitants).findList();
		} else {
			results = findLocalite
					.where()
					.eq("dType", type)
					.between("infoAdministrative.population",
							populationBetween, populationAnd)
					.gt("nbMilitants", nbMilitants).findList();
		}
		for (Localite localite : results) {
			for (Membre membre : localite.getMembres()) {
				if (membre.getTelephone() != null
						|| membre.getTelephone1() != null)
					membres.add(membre);
			}
		}
		Logger.info("Nombre de localités retournées par la recherche de localité : "
				+ results.size());
		Logger.info("Nombre de membres concernés par la recherche de : "
				+ membres.size());

		return membres;
	}

	public static String[][] getLocalitesInArray(List<Localite> localites) {

		String[][] locations = new String[localites.size()][11];

		for (int i = 0; i < localites.size(); i++) {
			Localite ligne = localites.get(i);
			// locations[0]= new String[]{Nom Quartier, longitude, lattitude,
			// alt,"COMMUNE"};
			locations[i] = new String[] { ligne.getNom(), // nomLocalite 0
					String.valueOf(ligne.getInfoGeographique().getLatitude()), // longitude
																				// 1
					String.valueOf(ligne.getInfoGeographique().getLongitude()), // latitude
																				// 2
					String.valueOf(ligne.getInfoGeographique().getAltitude()), // altitude
																				// 3
					ligne.getDType(), // Type Localite4
					String.valueOf(ligne.getId()), // 5
					ligne.getNomResponsable(), // 6
					ligne.getParentLocalite() != null ? ligne
							.getParentLocalite().getNom() : "No Parent", // 7
					String.valueOf(ligne.getNbSections()), // 8
					String.valueOf(ligne.getInfoAdministrative()
							.getPopulation()), // 9
					String.valueOf(ligne.getId()), // 10
					String.valueOf(ligne.getNbMilitants()), // 11 nbMilitants
					String.valueOf(ligne.getStatutResponsable()) // 12
			};

		}
		return locations;
	}

	public static List<Localite> rechercheLocalites(String type,
			int populationBetween, int populationAnd, int nbMilitants) {
		List<Localite> results = new ArrayList<Localite>();
		Logger.info("Parametres de la requete searchLocalites : [type : "
				+ type + "] - [populationBetween : " + populationBetween
				+ "] - [ " + populationAnd + "] - [nbMilitants :" + nbMilitants
				+ "]");
		if (type == null || type.trim().equalsIgnoreCase("ALL")) {
			results = findLocalite
					.where()
					.between("infoAdministrative.population",
							populationBetween, populationAnd)
					.gt("nbMilitants", nbMilitants).findList();
		} else {
			results = findLocalite
					.where()
					.eq("dType", type)
					.between("infoAdministrative.population",
							populationBetween, populationAnd)
					.gt("nbMilitants", nbMilitants).findList();
		}
		Logger.info("Nombre de valeur retournées par la recherche de localité : "
				+ results.size());
		return results;

	}

	public int getNbMilitants() {
		return nbMilitants;
	}

	public void setNbMilitants(int nbMilitants) {
		this.nbMilitants = nbMilitants;
	}

	public void setId(Long id) {
		this.idLocalite = id;
	}

	@JsonIgnore
	public List<Localite> getChildsLocalite() {
		return childsLocalite;
	}

	public void setChildsLocalite(List<Localite> childsLocalite) {
		this.childsLocalite = childsLocalite;
	}

	public void addChildsLocalite(Localite childsLocalite) {
		this.childsLocalite.add(childsLocalite);
	}

	public void setdType(String dType) {
		this.dType = dType;
	}

	@JsonIgnore
	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public List<Localite> getParentsLocalite() {

		List<Localite> liste = new ArrayList<Localite>();
		Localite loc = this;
		liste.add(loc);

		while (loc.getParentLocalite() != null) {
			loc = loc.getParentLocalite();
			liste.add(loc);
		}

		return liste;

	}

	public List<Activites> getListActivites() {
		List<Activites> mesActivites = new ArrayList<Activites>();
		for (LocaliteActivite locActiv : this.getActivites()) {
			Activites activ = locActiv.getActivites();
			mesActivites.add(activ);

		}
		return mesActivites;
	}

	public List<Projet> getListProjets() {

		List<Projet> mesProjet = new ArrayList<Projet>();

		for (LocaliteProjet locProj : this.getLocaliteProjet()) {
			Projet projet = locProj.getProjets();
			mesProjet.add(projet);

		}
		return mesProjet;
	}

	public Integer getNbElecteurs() {
		if (infoAdministrative != null)
			return infoAdministrative.getNbElecteurs();
		return null;
	}

	public String getGoogleCodeQuartier() {
		if (infoGeographique != null)
			return infoGeographique.getGoogleCodeQuartier();
		return null;
	}

	public Double getLongitude() {
		if (infoGeographique != null)
			return infoGeographique.getLongitude();
		return null;
	}

	public Double getLatitude() {
		if (infoGeographique != null)
			return infoGeographique.getLatitude();
		return null;
	}

	public Double getAltitude() {
		if (infoGeographique != null)
			return infoGeographique.getAltitude();
		return null;
	}

	public List<Projet> getListExterneProjet() {

		List<Projet> allProjets = Projet.findAllProjet();
		List<Projet> projets = getListProjets();

		// difference des deux listes

		List<Projet> result = new ArrayList<Projet>();

		for (Projet projet : allProjets) {

			if (!projets.contains(projet))
				result.add(projet);
		}

		return result;
	}

	public int NombrePopulations() {

		int populations = 0;
		List<Localite> liste = findAllLocalites();
		for (Localite loc : liste) {

			if (loc.getInfoAdministrative() != null) {
				populations += loc.getInfoAdministrative().getPopulation();
			}
		}

		return populations;
	}

	public int NombreSections() {
		int sections = 0;
		List<Localite> liste = findAllLocalites();
		for (Localite loc : liste) {	 
			sections += loc.getNbSections();
		}
		return sections;
	}

	public int NombreInscrits() {
		 
		int inscrits = 0;
		List<Localite> liste = findAllLocalites();
		for (Localite loc : liste) {

			if (loc.getInfoAdministrative() != null) {
				inscrits += loc.getNbTotalInscrits();
			}
		}
		return inscrits;
	}

	public int NombreElecteurs() {
	

		int electeurs = 0;
		List<Localite> liste = findAllLocalites();
		for (Localite loc : liste) {

			if (loc.getInfoAdministrative() != null) {
				electeurs += loc.getInfoAdministrative().getNbElecteurs();
			}
		}
		return electeurs;
	}
}
