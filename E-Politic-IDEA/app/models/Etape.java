package models;

import javax.persistence.*;

import play.db.ebean.Model;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 01/06/15 Time: 18:40 To change
 * this template use File | Settings | File Templates.
 */
@Entity
public class Etape extends BaseEntity {

	public static enum STATE {
		EN_COURS, SUSPENDU, TERMINE, DEMARRE, PLANIFIE, ABANDONNE, EN_ATTENTE

	}

	@Id
	@Column(name = "ID_ETAPE")
	private Long id;

	@Column(name = "NOM_ETAPE")
	private String nomEtape;
	@Column(name = "DESC_ETAPE")
	private String descEtape;
	@Column(name = "DEBUT_ETAPE")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Column(name = "FIN_ETAPE")
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	@Column(name = "CONTACT_ETAPE")
	private String contatact;
	@Column(name = "AUTORITE_ETAPE")
	private String autorite;
	@Column(name = "COUT_PREVISIONEL_ETAPE")
	private double coutPrevisionel;
	@Column(name = "COUT_REEL_ETAPE")
	private double coutReel;
	@Column(name = "EMPLOI_PREVISIONEL_ETAPE")
	private int emploiPrevisionel;
	@Column(name = "EMPLOI_REEL_ETAPE")
	private int emploiReel;
	@Column(name = "NIVEAU_EXECUTION_ETAPE")
	private double niveauExecution;
	@Column(name = "TAUX_DECAISSEMENT_ETAPE")
	private double tauxDecaissement;
	@Column(name = "ETAT")
	@Enumerated(EnumType.STRING)
	private Etape.STATE etat;
	/*********************************************/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PROJET")
	private Projet projet;

	public static Model.Finder<Long, Etape> find = new Model.Finder<Long, Etape>(
			Long.class, Etape.class);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomEtape() {
		return nomEtape;
	}

	public void setNomEtape(String nomEtape) {
		this.nomEtape = nomEtape;
	}

	public String getDescEtape() {
		return descEtape;
	}

	public void setDescEtape(String descEtape) {
		this.descEtape = descEtape;
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

	public double getCoutPrevisionel() {
		return coutPrevisionel;
	}

	public void setCoutPrevisionel(double coutPrevisionel) {
		this.coutPrevisionel = coutPrevisionel;
	}

	public double getCoutReel() {
		return coutReel;
	}

	public void setCoutReel(double coutReel) {
		this.coutReel = coutReel;
	}

	public int getEmploiPrevisionel() {
		return emploiPrevisionel;
	}

	public void setEmploiPrevisionel(int emploiPrevisionel) {
		this.emploiPrevisionel = emploiPrevisionel;
	}

	public int getEmploiReel() {
		return emploiReel;
	}

	public void setEmploiReel(int emploiReel) {
		this.emploiReel = emploiReel;
	}

	public double getNiveauExecution() {
		return niveauExecution;
	}

	public void setNiveauExecution(double niveauExecution) {
		this.niveauExecution = niveauExecution;
	}

	public double getTauxDecaissement() {
		return tauxDecaissement;
	}

	public void setTauxDecaissement(double tauxDecaissement) {
		this.tauxDecaissement = tauxDecaissement;
	}

	public Etape.STATE getEtat() {
		return etat;
	}

	public String getEtatToString() {

		if (etat != null)
			return etat.name();
		else {
			return "";

		}
	}

	public void setEtat(Etape.STATE etat) {
		this.etat = etat;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public static Etape findEtapeById(Long id) {
		return find.where().eq("id", id).findUnique();
	}

	public int getDureePrevisionnelEtape() {
		return (int) ((dateFin.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24));

	}

	public int getDureeActuelEtape() {
		return (int) ((new Date().getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24));

	}

	public double getDureeActuelEtapePourcentage() {
		if (getDureePrevisionnelEtape() != 0)
		{
			double x=  Double.valueOf(getDureePrevisionnelEtape());
			double y=  Double.valueOf(getDureeActuelEtape());

			
		
			return  ( (y / x)) * 100;
		}
			
		else
			return 0;

	}

}
