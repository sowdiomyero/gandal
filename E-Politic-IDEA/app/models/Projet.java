package models;

import javax.persistence.*;

import play.db.ebean.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 01/06/15 Time: 18:40 To change
 * this template use File | Settings | File Templates.
 */
@Entity
public class Projet extends BaseEntity {

	public static enum STATE {
		EN_COURS, SUSPENDU, TERMINE, DEMARRE, PLANIFIE, ABANDONNE, EN_ATTENTE

	}

	@Id
	@Column(name = "ID_PROJET")
	private Long id;

	@Column(name = "NOM_PROJET")
	private String nomProjet;
	@Column(name = "DESC_PROJET")
	private String descProjet;
	@Column(name = "DEBUT_PROJET")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Column(name = "FIN_PROJET")
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	@Column(name = "CONTACT_PROJET")
	private String contatact;
	@Column(name = "AUTORITE_PROJET")
	private String autorite;
	@Column(name = "COUT_PREVISIONEL_PROJET")
	private double coutPrevisionel;
	@Column(name = "EMPLOI_PREVISIONEL_PROJET")
	private int emploiPrevisionel;
	@Column(name = "ETAT")
	@Enumerated(EnumType.STRING)
	private Projet.STATE etat;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "projet")
	public List<Etape> etapes;
	/*********************************************/

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "projets")
	public List<LocaliteProjet> localiteProjet;

	public static Model.Finder<Long, Projet> findProjet = new Model.Finder<Long, Projet>(
			Long.class, Projet.class);

	public Projet() {
	}

	public static List<Projet> findAllProjet() {
		return findProjet.all();
	}

	public Long getId() {
		return id;
	}

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

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getContatact() {
		return contatact;
	}

	public void setContatact(String contatact) {
		this.contatact = contatact;
	}

	public String getAutorite() {
		return autorite;
	}

	public void setAutorite(String autorite) {
		this.autorite = autorite;
	}

	// trouver un projet par son ID

	public double getCoutPrevisionel() {
		
		BigDecimal bd = new BigDecimal(coutPrevisionel);
		//BigDecimal rounded = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

	public void setCoutPrevisionel(double coutPrevisionel) {
		this.coutPrevisionel = coutPrevisionel;
	}

  

	public int getEmploiPrevisionel() {
		return emploiPrevisionel;
	}

	public void setEmploiPrevisionel(int emploiPrevisionel) {
		this.emploiPrevisionel = emploiPrevisionel;
	}

 

	public Projet.STATE getEtat() {
		return etat;
	}

	public String getEtatName() {
		if (etat != null)
			return etat.name();
		return "";
	}

	public void setEtat(Projet.STATE etat) {
		this.etat = etat;
	}

	public List<LocaliteProjet> getLocaliteProjet() {
		return localiteProjet;
	}

	public void setLocaliteProjet(List<LocaliteProjet> localiteProjet) {
		this.localiteProjet = localiteProjet;
	}

	public static Projet findProjetById(Long id) {
		return findProjet.where().eq("id", id).findUnique();
	}

	public List<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}

	

	public double getValNiveauExecution() {
		if (etapes != null && etapes.size() != 0) {
			double niveauTotalEtape = 0;
			for (Etape etape : etapes) {
				niveauTotalEtape += etape.getNiveauExecution();
			}

			BigDecimal bd = new BigDecimal(niveauTotalEtape / etapes.size());
			BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

			return rounded.doubleValue();
		}
		return 0;
	}
	
	public int getValEmploiReel() {
		if (etapes != null && etapes.size() != 0) {
			int emploiTotalEtape = 0;
			for (Etape etape : etapes) {
				emploiTotalEtape += etape.getEmploiReel();
			}
 
			return emploiTotalEtape  ;
		}
		return 0;
	}
	
	public double getValCoutReel() {
		if (etapes != null && etapes.size() != 0) {
			double coutTotalEtape = 0;
			for (Etape etape : etapes) {
				coutTotalEtape += etape.getCoutReel();
			}
 
			return coutTotalEtape ;
		}
		return 0;
	}
}
