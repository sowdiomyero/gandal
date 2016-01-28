package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import com.fasterxml.jackson.annotation.JsonIgnore;

import models.utils.DateUtils;
import play.Logger;
import play.db.ebean.Model;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 04/06/15 Time: 12:42 To change
 * this template use File | Settings | File Templates.
 */
@Entity
public class Inscription extends BaseEntity {

	public static enum STATE {
		NOT_VALIDATED, VALIDATED, NOT_WELL_FORMED, DUPLICATED
	}

	@Id
	@Column(name = "ID_INS")
	private Long idInscription;

	@Column(name = "DATE_CREATION")
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	@JoinColumn(name = "ID_EQUIPE", referencedColumnName = "ID_EQUIPE")
	@ManyToOne(optional = false)
	private Equipe equipe;

	@JoinColumn(name = "ID_USER_EXT", referencedColumnName = "ID_USER_EXT")
	@ManyToOne(optional = true)
	private UserExterne user;

	@JoinColumn(name = "ID_MEMBRE", referencedColumnName = "ID_MEMBRE")
	@ManyToOne(optional = true)
	private Membre membre;

	@JoinColumn(name = "ID_LOCALITE", referencedColumnName = "ID_LOCALITE")
	@ManyToOne(optional = false)
	private Localite localite;

	@Column(name = "NB_INSCRITS")
	private Long nbInscriptions;

	@Column(name = "ETAT")
	@Enumerated(EnumType.STRING)
	private Inscription.STATE etat;

	@Column(name = "MSG_INCOMING")
	private String messageIn;

	@Column(name = "MSG_OUTGOING")
	private String messageOut;

	@Column(name = "DATE_UPDATED")
	@Temporal(TemporalType.DATE)
	private Date dateUpdated;

	@Column(name = "DATE_INSCRIPTION")
	@Temporal(TemporalType.DATE)
	private Date dateInscription;

	public static Model.Finder<Long, Inscription> find = new Model.Finder<Long, Inscription>(
			Long.class, Inscription.class);

	public Inscription() {
		this.etat = STATE.NOT_VALIDATED;
	}

	public static List<Inscription> findInscriptionBetweenTwoDates(
			String date1, String date2) {
		return find.where().between("dateInscription", date1, date2).findList();
	}

	public Long getIdInscription() {
		return idInscription;
	}

	public void setIdInscription(Long idInscription) {
		this.idInscription = idInscription;
	}

	public static List<Inscription> findInscriptionByIdResponsable(Long idResp) {
		return find.where().eq("membre.idMembre", idResp).findList();
	}

	public static List<Inscription> findInscriptionByIdLocalite(Long idLoc) {
		return find.where().eq("localite.idLocalite", idLoc).findList();
	}

	public static List<Inscription> findInscriptionValideByIdLocalite(Long idLoc) {
		return find.where().eq("localite.idLocalite", idLoc)
				.eq("etat", "VALIDATED").orderBy("dateInscription desc")
				.findList();
	}

	public static List<Inscription> findInscriptionNotValideByIdLocalite(
			Long idLoc) {
		return find.where().eq("localite.idLocalite", idLoc)
				.eq("etat", "NOT_VALIDATED").orderBy("dateInscription desc")
				.findList();
	}

	public static List<Inscription> findInscriptionDuplicateByIdLocalite(
			Long idLoc) {
		return find.where().eq("localite.idLocalite", idLoc)
				.eq("etat", "DUPLICATED").orderBy("dateInscription desc")
				.findList();
	}

	public static List<Inscription> findInscriptionBadFormatByIdLocalite(
			Long idLoc) {
		return find.where().eq("localite.idLocalite", idLoc)
				.eq("etat", "NOT_WELL_FORMED").orderBy("dateInscription desc")
				.findList();
	}

	public static List<Inscription> findOutGoingMsgByIdResponsable(
			Long idResponsable) {
		return find.where().eq("membre.idMembre", idResponsable)
				.orderBy("dateUpdated desc").findList();
	}

	public static Inscription findInscriptionById(Long id) {
		return find.where().eq("idInscription", id).findUnique();
	}

	public static Long getNbInscriptionBetweenTwoDates(String date1,
			String date2) {
		Long retour = 0L;
		List<Inscription> result = find.where()
				.between("dateInscription", date1, date2).findList();
		Logger.info("Nombre d'inscriptions retrouvé : " + result.size());
		if (result != null && result.size() > 0) {

			for (Inscription inscription : result) {

				if (inscription.getNbInscriptions() != null) {
					Logger.info("Inscription Add : "
							+ inscription.getNbInscriptions());
					retour += inscription.getNbInscriptions();
				}
			}
		}

		return retour;
	}

	public static Long getNbInscriptionNotValidatedBetweenTwoDates(
			String date1, String date2) {
		Long retour = 0L;
		List<Inscription> result = new ArrayList<>();
		/*
		 * find.where() .eq("etat",STATE.VALIDATED.name())
		 * .raw("dateInscription >= "+date1+" AND dateInscription < "+date2) //
		 * .between("dateInscription", date1, date2) .findList();
		 */

		com.avaje.ebean.Query<Inscription> query = find
				.where()
				.eq("etat", STATE.VALIDATED.name())
				.raw("dateInscription >= '" + date1
						+ "' AND dateInscription < '" + date2 + "'").query();

		result = query.findList();

		Logger.info("###### Inscriptions retrouvé : entre les dates : ["
				+ date1 + " - " + date2 + "[ ==> " + result.size());

		Logger.info("****** SQL : entre les dates : [ "
				+ query.getGeneratedSql() + " ]");
		if (result != null && result.size() > 0) {

			for (Inscription inscription : result) {

				if (inscription.getNbInscriptions() != null) {
					Logger.info("Inscription Add : "
							+ inscription.getNbInscriptions());
					retour += inscription.getNbInscriptions();
				}
			}
		}

		return retour;
	}

	public static Long getNbInscriptionLocaliteBetweenTwoDates(Long idLocalite,
			String date1, String date2) {
		Long retour = 0L;
		List<Inscription> result = find
				.where()
				.eq("localite.idLocalite", idLocalite)
				.eq("etat", STATE.VALIDATED.name())
				.raw("dateInscription >= " + date1 + " AND dateInscription < "
						+ date2)
				// .between("dateInscription", date1, date2)
				.findList();
		Logger.info("###### Inscriptions retrouvé : entre les dates : ["
				+ date1 + " - " + date2 + "[ ==> " + result.size());
		if (result != null && result.size() > 0) {

			for (Inscription inscription : result) {

				if (inscription.getNbInscriptions() != null) {
					Logger.info("Inscription Add : "
							+ inscription.getNbInscriptions());
					retour += inscription.getNbInscriptions();
				}
			}
		}

		return retour;
	}

	/*********************************************** inscription mensuelle ****************************************************************/

	// inscription par mois
	public static Long getNbInscriptionLocaliteMonthly(Long idLocalite,
			int month) {
		Long retour = 0L;
		
		String sql = "select * from inscription where ID_LOCALITE = "+ idLocalite + " and ETAT = 'VALIDATED' and  DATE_INSCRIPTION< now() and month(DATE_INSCRIPTION)="+month;
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);
		List<Inscription> result = new ArrayList<>();
		
		for (SqlRow sqlRow : sqlQuery.findList()) {
			Inscription ins = findInscriptionById(sqlRow.getLong("ID_INS"));
			result.add(ins);
		}
		
		
		
		if (result != null && result.size() > 0) {

			for (Inscription inscription : result) {

				if (inscription.getNbInscriptions() != null) {
					Logger.info("Inscription Add : "
							+ inscription.getNbInscriptions());
					retour += inscription.getNbInscriptions();
				}
			}
		}

		return retour;
	}

	// inscription par agent
	public static HashMap<Long, Long> getNbInscriptionLocaliteByUserExt(Long idLocalite) {

		String sql = "select ID_USER_EXT,sum(NB_INSCRITS) as somme from inscription where ID_LOCALITE = '"
				+ idLocalite + "' and ETAT = 'VALIDATED' group by ID_USER_EXT ";
		SqlQuery sqlQuery = Ebean.createSqlQuery(sql);

		
		HashMap resultat = new HashMap<>();

		for (SqlRow sqlRow : sqlQuery.findList()) {
			resultat.put(sqlRow.getLong("ID_USER_EXT"),sqlRow.getLong("somme"));
		}
		return resultat;
	}

	/***************************************** getteurs and setteurs *****************************************************/
	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	@JsonIgnore
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	@JsonIgnore
	public UserExterne getUser() {
		return user;
	}

	public void setUser(UserExterne user) {
		this.user = user;
	}

	@JsonIgnore
	public Localite getLocalite() {
		return localite;
	}

	public void setLocalite(Localite localite) {
		this.localite = localite;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Long getNbInscriptions() {
		return nbInscriptions;
	}

	public void setNbInscriptions(Long nbInscriptions) {
		this.nbInscriptions = nbInscriptions;
	}

	public String getMessageIncoming() {
		return messageIn;
	}

	public void setMessageIncoming(String message) {
		this.messageIn = message;
	}

	public String getMessageOut() {
		return messageOut;
	}

	public void setMessageOut(String messageOut) {
		this.messageOut = messageOut;
	}

	public STATE getEtat() {
		return etat;
	}

	public void setEtat(STATE etat) {
		this.etat = etat;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public String getFormatedDateInscription() {
		if (dateInscription == null)
			return "";
		return DateUtils.getFormatDateString(dateInscription, "dd/MM/yyyy");
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	@JsonIgnore
	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public String getMessageIn() {
		return messageIn;
	}

	public void setMessageIn(String messageIn) {
		this.messageIn = messageIn;
	}

	public static List<JourInscription> getInscriptionsSemaine(Long idLocalite) {

		List<JourInscription> list = new ArrayList<JourInscription>();
		JourInscription ji = new JourInscription();
		// String [][] list01 = new String[5][2];
		String[] data = new String[2];

		// date 5
		String dt01 = DateUtils.getPreviousDateString(-5);
		String dt02 = DateUtils.getPreviousDateString(-4);
		String dateSemaine = getDayFromDate(dt01);
		long nbInscrits = Inscription.getNbInscriptionLocaliteBetweenTwoDates(
				idLocalite, dt01, dt02);
		Logger.info("Nombre Inscrits entre " + dt01 + " et " + dt02 + " ::::: "
				+ nbInscrits);
		ji.setDay(dateSemaine);
		ji.setValue(String.valueOf(nbInscrits));
		list.add(ji);

		// date 4
		dt01 = DateUtils.getPreviousDateString(-4);
		dt02 = DateUtils.getPreviousDateString(-3);
		dateSemaine = getDayFromDate(dt01);
		nbInscrits = Inscription.getNbInscriptionLocaliteBetweenTwoDates(
				idLocalite, dt01, dt02);
		Logger.info("Nombre Inscrits entre " + dt01 + " et " + dt02 + " ::::: "
				+ nbInscrits);
		ji = new JourInscription();
		ji.setDay(dateSemaine);
		ji.setValue(String.valueOf(nbInscrits));
		list.add(ji);
		// date 3
		dt01 = DateUtils.getPreviousDateString(-3);
		dt02 = DateUtils.getPreviousDateString(-2);
		dateSemaine = getDayFromDate(dt01);
		nbInscrits = Inscription.getNbInscriptionLocaliteBetweenTwoDates(
				idLocalite, dt01, dt02);
		Logger.info("Nombre Inscrits entre " + dt01 + " et " + dt02 + " ::::: "
				+ nbInscrits);
		ji = new JourInscription();
		ji.setDay(dateSemaine);
		ji.setValue(String.valueOf(nbInscrits));
		list.add(ji);
		// date 2
		dt01 = DateUtils.getPreviousDateString(-2);
		dt02 = DateUtils.getPreviousDateString(-1);
		dateSemaine = getDayFromDate(dt01);
		nbInscrits = Inscription.getNbInscriptionLocaliteBetweenTwoDates(
				idLocalite, dt01, dt02);
		Logger.info("Nombre Inscrits entre " + dt01 + " et " + dt02 + " ::::: "
				+ nbInscrits);
		ji = new JourInscription();
		ji.setDay(dateSemaine);
		ji.setValue(String.valueOf(nbInscrits));
		list.add(ji);
		// date 1
		dt01 = DateUtils.getPreviousDateString(-1);
		dt02 = DateUtils.getPreviousDateString(0);
		dateSemaine = getDayFromDate(dt01);
		nbInscrits = Inscription.getNbInscriptionLocaliteBetweenTwoDates(
				idLocalite, dt01, dt02);
		Logger.info("Nombre Inscrits entre " + dt01 + " et " + dt02 + " ::::: "
				+ nbInscrits);
		ji = new JourInscription();
		ji.setDay(dateSemaine);
		ji.setValue(String.valueOf(nbInscrits));
		list.add(ji);
		return list;
	}

	private static String getDayFromDate(String date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.getPreviousDateString(date));
		int day = cal.get(Calendar.DAY_OF_WEEK);
		Logger.info("gatDayFromDate : " + date + " ==> " + day);
		// switch (day){
		if (day == 1)
			return "Lundi";
		if (day == 2)
			return "Mardi";
		if (day == 3)
			return "Mercredi";
		if (day == 4)
			return "Jeudi";
		if (day == 5)
			return "Vendredi";
		if (day == 6)
			return "Samedi";
		if (day == 7)
			return "Dimanche";

		return "";
	}

}
