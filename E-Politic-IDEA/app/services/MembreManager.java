package services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Categorie;
import models.Compte;
import models.Localite;
import models.Membre;
import models.Profil;
import models.Membre.ACTIF;
import models.utils.Hash;
import models.utils.Mail;

import org.apache.commons.mail.EmailException;

import play.Configuration;
import play.Logger;
import play.data.DynamicForm;
import play.i18n.Messages;
import play.mvc.Result;
import views.html.membre.index;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 11/06/15 Time: 16:36 To change
 * this template use File | Settings | File Templates.
 */
public class MembreManager {

	@Transactional
	public CodeRetour addMembre(DynamicForm dynamicForm) {

		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		Ebean.beginTransaction();

		try {

			String nom = dynamicForm.get("nom");
			String prenom = dynamicForm.get("prenom");
			String sexe = dynamicForm.get("sexe");
			String email = dynamicForm.get("email");
			String categorie = dynamicForm.get("categorie");
			String telephone1 = dynamicForm.get("telephone");
			String telephone2 = dynamicForm.get("telephone2");
			String profession = dynamicForm.get("profession");

			String accces = dynamicForm.get("accces");

			String profil = dynamicForm.get("profil");
			Logger.info("Profil : " + profil);
			String localite = dynamicForm.get("localite");

			// 2 split
			// TODO s'assurer qu'il n'existe aucun membre et compte qui
			// possede les numeros de telephone et les em@il
			Membre membre = new Membre();
			membre.setNom(nom);
			membre.setPrenom(prenom);
			membre.setEmail(email);
			membre.setSexe(sexe);
			membre.setCategorie(Categorie.valueOf(categorie));
			membre.setTelephone(telephone1);
			membre.setTelephone1(telephone2);
			membre.setProfession(profession);

			Long idLocalite = Long.parseLong(localite.split(":")[1]);
			Logger.info("Localite : " + idLocalite);
			if (idLocalite > 0) {
				Localite loc = Localite.findLocaliteById(idLocalite);

				membre.setLocaliteMembre(loc);
			}

			membre.save();

			// Ajouter le membre dans la base
			if (accces != null
					&& (accces.equalsIgnoreCase("on") || accces
							.equalsIgnoreCase("1"))) {
				Compte compte = new Compte();
				compte.email = email;
				compte.fullname = nom.toUpperCase() + " " + prenom;
				compte.passwordHash = Hash.createPassword(email.split("@")[0]);
				compte.confirmationToken = UUID.randomUUID().toString();
				Logger.info("Compte : " + compte.getEmail() + " "
						+ compte.getFullname() + " "
						+ compte.getConfirmationToken());
				compte.save();
				Logger.info("Compte : " + compte.getEmail() + " "
						+ compte.getFullname() + " "
						+ compte.getConfirmationToken());
				membre.setCompte(compte);
				Logger.info("Membre : " + membre.getCompte().getNom());
				Long idProfil = Long.parseLong(profil.split(":")[1]);
				Logger.info("Profil : " + idProfil);
				if (idProfil > 0) {
					Profil pf = Profil.findProfilById(idProfil);
					membre.addProfil(pf);
				} else {
					membre.addProfil(Profil
							.findProfilByName(Profil.CODE.RESPONSABLE.name()));
				}

				membre.update();
				sendMailAskForConfirmation(compte);

				retour.setCodeRetour(CodeRetour.RETOUR_OK);
				retour.setMessage("Enregistrement des données effectuée avec Succes");
				Ebean.commitTransaction();
			}

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");

			Logger.error("Une exception s'est produite pendant la transaction"
					+ ex.getMessage());
			Ebean.rollbackTransaction();
		}
		return retour;
	}

	@Transactional
	public CodeRetour updateMembre(DynamicForm dynamicForm) {

	
		CodeRetour retour = controleFormUpdate(dynamicForm);

		if (retour.isError())
			return retour;

		Ebean.beginTransaction();

		try {

			Logger.info("Elements de la requete : " + dynamicForm.toString());
			Long idMembre = Long.parseLong(dynamicForm.get("idMembre"));
			String nom = dynamicForm.get("nom");
			String prenom = dynamicForm.get("prenom");
			String sexe = dynamicForm.get("sexe");
			String email = dynamicForm.get("email");
			String categorie = dynamicForm.get("categorie");
			String telephone1 = dynamicForm.get("telephone");
			String telephone2 = dynamicForm.get("telephone2");
			String profession = dynamicForm.get("profession");
			String localite = dynamicForm.get("localite");
			Logger.info("Localite : " + localite);
			String profil = dynamicForm.get("profil");

			Membre membre = Membre.findMembreById(idMembre);
			Logger.info("Elements de membre : " + membre.getNom());
			Localite localiteMembre = membre.getLocaliteMembre();

			membre.setNom(nom);
			membre.setPrenom(prenom);
			membre.setEmail(email);
			membre.setSexe(sexe);
			membre.setTelephone(telephone1);
			membre.setTelephone1(telephone2);
			membre.setProfession(profession);
			membre.setCategorie(Categorie.valueOf(categorie));
			Long idLocalite = (long) 0;
			if (localite != null) {
				Long idlite = Long.parseLong(localite.split(":")[1]);

				if (idLocalite > 0) {
					Localite loc = Localite.findLocaliteById(idLocalite);
					membre.setLocaliteMembre(loc);
				}
			}
			Logger.info("Elements de membre : "
					+ membre.getLocaliteMembre().getNom());
			if (profil != null) {
				Long idProfil = Long.parseLong(profil.split(":")[1]);
				if (idProfil != null && idProfil > 0) {
					Profil pf = Profil.findProfilById(idProfil);
					membre.addProfil(pf);
				} else {
					membre.addProfil(Profil
							.findProfilByName(Profil.CODE.RESPONSABLE.name()));
				}
			}
			membre.update();
			Logger.info("Elements de user : " + membre.toString());

			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");
			Ebean.commitTransaction();
		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");
			Logger.error("Une exception s'est produite pendant la transaction");
			Ebean.rollbackTransaction();
		}

		return retour;
	}

	private static void sendMailAskForConfirmation(Compte compte)
			throws EmailException, MalformedURLException {
		String subject = Messages.get("mail.confirm.subject");

		String urlString = "http://"
				+ Configuration.root().getString("server.hostname");
		urlString += "/confirm/" + compte.confirmationToken;
		URL url = new URL(urlString); // validate the URL, will throw an
		// exception if bad.
		String message = Messages.get("mail.confirm.message", url.toString(),
				compte.email, compte.email.split("@")[0]);

		Mail.Envelop envelop = new Mail.Envelop(subject, message, compte.email);
		Mail.sendMail(envelop);
	}
	
	
public static CodeRetour blockMembre(Long idMembre) {
	
	CodeRetour retour = new CodeRetour();
	Ebean.beginTransaction();
	try{
			Membre membre = Membre.findMembreById(idMembre);
			Logger.info("Elements de membre : "
					+ membre.getNom()+" "+ membre.getPrenom()+" "+ membre.getEmail());
			Compte compte= membre.getCompte();
			compte.setDisabled(true);
			Logger.info("Elements de compte : "+compte.getDisabled());
			compte.update();
			membre.setActif(ACTIF.DESACTIVE);
			membre.update();
			Ebean.commitTransaction();
		}catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");
			retour.setError(true);
			Logger.error("Une exception s'est produite pendant la transaction"
					+ ex.getMessage());
			 ex.printStackTrace();
			Ebean.rollbackTransaction();
		}
		

		return retour;

	}

	public CodeRetour controleForm(DynamicForm dynamicForm) {
		String nom = dynamicForm.get("nom");
		String prenom = dynamicForm.get("prenom");
		String sexe = dynamicForm.get("sexe");
		String email = dynamicForm.get("email");
		String categorie = dynamicForm.get("categorie");
		String telephone1 = dynamicForm.get("telephone");
		String telephone2 = dynamicForm.get("telephone2");
		String profession = dynamicForm.get("profession");
		String accces = dynamicForm.get("accces");

		String profil = dynamicForm.get("profil");

		String localite = dynamicForm.get("localite");
		CodeRetour retour = new CodeRetour();
		
		
		if (nom.equals("") || prenom.equals("")) {
			retour.setMessage("Vous devez renseigner votre nom et votre prenom");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (profil.equals("")) {
			retour.setMessage("Vous devez renseigner un profil pour ce membre");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (telephone1.equals("") && telephone2.equals("")) {
			retour.setMessage("Vous devez renseigner au moins un numéro de telephone pour ce membre");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (sexe.equals("")) {
			retour.setMessage("Vous devez renseigner le sexe du membre");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (email.equals("")) {
			retour.setMessage("Vous devez renseigner une adresse email");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (profession.equals("")) {
			retour.setMessage("Vous devez renseigner votre profession");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		// verification cote BDD

		if (Membre.isPhoneNumberExist(telephone1)) {
			retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
			retour.setMessage("Le numero de téléphone que vous avez renseigné ["
					+ telephone1 + "] est utilisé par un autre agent.");
			retour.setError(true);
			return retour;
		}

		if (!telephone2.equals("")
				&& Membre.isPhoneNumberExist(telephone2)) {
			retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
			retour.setMessage("Le numero de téléphone que vous avez renseigné ["
					+ telephone2 + "] est utilisé par un autre agent.");
			retour.setError(true);
			return retour;
		}

		if (Membre.isEmailExist(email)) {
			retour.setCodeRetour(CodeRetour.EMAIL_DUPLIQUE);
			retour.setMessage("L'adresse email que vous avez renseigné ["
					+ email + "] est utilisé par un autre agent");
			retour.setError(true);
			return retour;
		}

		return retour;

	}
	
	public CodeRetour controleFormUpdate(DynamicForm dynamicForm) {
		String nom = dynamicForm.get("nom");
		String prenom = dynamicForm.get("prenom");
		String sexe = dynamicForm.get("sexe");
		String email = dynamicForm.get("email");
		String categorie = dynamicForm.get("categorie");
		String telephone1 = dynamicForm.get("telephone");
		String telephone2 = dynamicForm.get("telephone2");
		String profession = dynamicForm.get("profession");
		String accces = dynamicForm.get("accces");

		String profil = dynamicForm.get("profil");

		String localite = dynamicForm.get("localite");
		CodeRetour retour = new CodeRetour();
		
		
		
		Long idMembre = Long.parseLong(dynamicForm.get("idMembre"));
		Membre membreUpdate = Membre.findMembreById(idMembre);
				
		
		if (nom.equals("") || prenom.equals("")) {
			retour.setMessage("Vous devez renseigner votre nom et votre prenom");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (profil.equals("")) {
			retour.setMessage("Vous devez renseigner un profil pour ce membre");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (telephone1.equals("") && telephone2.equals("")) {
			retour.setMessage("Vous devez renseigner au moins un numéro de telephone pour ce membre");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (sexe.equals("")) {
			retour.setMessage("Vous devez renseigner le sexe du membre");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (email.equals("")) {
			retour.setMessage("Vous devez renseigner une adresse email");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (profession.equals("")) {
			retour.setMessage("Vous devez renseigner votre profession");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		// verification cote BDD

		if (Membre.isPhoneNumberExist(telephone1, membreUpdate)) {
			retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
			retour.setMessage("Le numero de téléphone que vous avez renseigné ["
					+ telephone1 + "] est utilisé par un autre agent.");
			retour.setError(true);
			return retour;
		}

		if (!telephone2.equals("")
				&& Membre.isPhoneNumberExist(telephone2, membreUpdate)) {
			retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
			retour.setMessage("Le numero de téléphone que vous avez renseigné ["
					+ telephone2 + "] est utilisé par un autre agent.");
			retour.setError(true);
			return retour;
		}

		if (Membre.isEmailExist(email, membreUpdate)) {
			retour.setCodeRetour(CodeRetour.EMAIL_DUPLIQUE);
			retour.setMessage("L'adresse email que vous avez renseigné ["
					+ email + "] est utilisé par un autre agent");
			retour.setError(true);
			return retour;
		}

		return retour;

	}

}
