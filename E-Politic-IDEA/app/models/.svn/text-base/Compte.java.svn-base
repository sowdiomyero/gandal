package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.Membre.ACTIF;
import models.utils.AppException;
import models.utils.Hash;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Compte: yesnault Date: 20/01/12
 */

@Entity
public class Compte extends BaseEntity {

	@Id
	@Column(name = "ID_COMPTE")
	private Long idCompte;

	@Column(unique = true, name = "EMAIL")
	public String email;

	@Column(unique = false, name = "FULL_NAME")
	public String fullname;
	@Column(name = "CONFIRMATION_TOKEN")
	public String confirmationToken;

	@Column(name = "PASSWORD_HASH")
	public String passwordHash;

	@Column(name = "VALIDATED")
	public Boolean validated = true;

	@Column(name = "DISABLED")
	public Boolean disabled = false;

	@OneToOne(mappedBy = "compte")
	private Membre membre;

	// -- Queries (long id, user.class)
	public static Model.Finder<Long, Compte> find = new Model.Finder<Long, Compte>(
			Long.class, Compte.class);

	/**
	 * Retrieve a user from an email.
	 * 
	 * @param email
	 *            email to search
	 * @return a user
	 */
	public static Compte findByEmail(String email) {
		return find.where().eq("email", email).findUnique();
	}

	/**
	 * Retrieve a user from a fullname.
	 * 
	 * @param fullname
	 *            Full name
	 * @return a user
	 */
	public static Compte findByFullname(String fullname) {
		return find.where().eq("fullname", fullname).findUnique();
	}

	/**
	 * Retrieves a user from a confirmation token.
	 * 
	 * @param token
	 *            the confirmation token to use.
	 * @return a user if the confirmation token is found, null otherwise.
	 */
	public static Compte findByConfirmationToken(String token) {
		return find.where().eq("confirmationToken", token).findUnique();
	}

	/**
	 * Authenticate a Compte, from a email and clear password.
	 * 
	 * @param email
	 *            email
	 * @param clearPassword
	 *            clear password
	 * @return Compte if authenticated, null otherwise
	 * @throws AppException
	 *             App Exception
	 */
	public static Compte authenticate(String email, String clearPassword)
			throws AppException {

		// get the compte with email only to keep the salt password
		Compte compte = find.where().eq("email", email).findUnique();
		if (compte != null) {
			Membre membre = compte.getMembre();
			if (membre != null)
				compte.getMembre().setStatus(Membre.STATUS.ONLINE);
			if (membre.getActif() == ACTIF.NON)
				membre.setActif(ACTIF.OUI);
			compte.update();
			if (Hash.checkPassword(clearPassword, compte.passwordHash)) {
				return compte;
			}
		}
		return null;
	}

	public void changePassword(String password) throws AppException {
		this.passwordHash = Hash.createPassword(password);
		this.save();
	}

	/**
	 * Confirms an account.
	 * 
	 * @return true if confirmed, false otherwise.
	 * @throws AppException
	 *             App Exception
	 */
	public static boolean confirm(Compte compte) throws AppException {
		if (compte == null) {
			return false;
		}

		compte.confirmationToken = null;
		compte.validated = true;
		compte.save();
		return true;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String flname) {
		this.fullname = flname;
	}

	public String getEmail() {
		return email;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public boolean getValidated() {
		return validated;
	}

	public Long getId() {
		return idCompte;
	}

	public Long getIdEquipe() {
		if (membre != null && membre.getTeam() != null)
			return membre.getTeam().getId();
		return null;
	}

	public Long getIdLocalite() {
		if (membre != null && membre.getLocaliteMembre() != null)
			return membre.getLocaliteMembre().getId();
		return null;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	@JsonIgnore
	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getNom() {
		if (this.membre != null)
			return membre.getNom();
		else
			return "";
	}

	public String getPrenom() {
		if (this.membre != null)
			return membre.getPrenom();
		else
			return "";
	}

	public Date getDateNaissance() {

		if (this.membre != null && membre.getDateNaissance() != null)
			return membre.getDateNaissance();
		else
			return new Date();
	}

	public String getCarteIdentite() {
		if (this.membre != null)
			return membre.getCarteIdentite();
		else
			return "";
	}

	public String getTelephone() {
		if (this.membre != null)
			return membre.getTelephone();
		else
			return "";
	}

	public String getTelephone1() {
		if (this.membre != null)
			return membre.getTelephone1();
		else
			return "";
	}

	public String getProfession() {
		if (this.membre != null)
			return membre.getProfession();
		else
			return "";
	}

	public String getNomCategorie() {
		if (this.membre != null && membre.getCategorie() != null)
			return membre.getCategorie().toString();
		else
			return "";
	}

	// Methode qui verifie si le membre a un profil ADMIN
	public boolean isAdmin() {
		if (this.membre != null && membre.getProfil() != null) {
			Profil prof = Profil.findProfilByName("ADMIN");
			return membre.getProfil().contains(prof);
		} else
			return false;

	}

	// Methode qui verifie si le membre a un profil ADMIN
	public boolean isResponsable() {
		if (this.membre != null && membre.getProfil() != null) {
			Profil prof = Profil.findProfilByName("RESPONSABLE");
			return membre.getProfil().contains(prof);
		} else
			return false;

	}

	// Methode qui rerourne la localite du membre disposant de ce compte
	public Localite getLocalite() {
		if (membre != null)
			return membre.getLocaliteMembre();
		return null;
	}
	
	public String getNomLocalite() {
		if (this.membre != null && membre.getLocaliteMembre()!=null  )
			return membre.getLocaliteMembre().getNom();
		else
			return "";
	}
	
	public int getPopulationLocalite() {
		if (this.membre != null && membre.getLocaliteMembre()!=null && membre.getLocaliteMembre().getInfoAdministrative()!=null)
			return membre.getLocaliteMembre().getInfoAdministrative().getPopulation();
		else
			return 0;
	}
	
	public int getNbElecteursLocalite() {
		if (this.membre != null && membre.getLocaliteMembre()!=null && membre.getLocaliteMembre().getInfoAdministrative()!=null)
			return membre.getLocaliteMembre().getInfoAdministrative().getNbElecteurs();
		else
			return 0;
	}

	public int getNbTotalInscritsLocalite() {
		if (this.membre != null && membre.getLocaliteMembre()!=null  )
			return membre.getLocaliteMembre().getNbTotalInscrits();
		else
			return 0;
	}
	
	public Double getObjectifsLocalite() {
		if (this.membre != null && membre.getLocaliteMembre()!=null  )
			return membre.getLocaliteMembre().getObjectifs();
		else
			return null;
	}
	
	public double getPourcentageRealisationLocalite() {
		if (this.membre != null && membre.getLocaliteMembre()!=null  )
			return membre.getLocaliteMembre().getPourcentageRealisation();
		else
			return 0;
	}
}
