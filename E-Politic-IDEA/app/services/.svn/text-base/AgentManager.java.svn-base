package services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;

import controllers.routes;
import models.Categorie;
import models.Compte;
import models.Equipe;
import models.Localite;
import models.Membre;
import models.Profil;
import models.UserExterne;
import models.utils.Hash;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;

public class AgentManager {

	public CodeRetour controleForm(DynamicForm dynamicForm) {

		String nom = dynamicForm.get("nom");
		String prenom = dynamicForm.get("prenom");
		String sexe = dynamicForm.get("sexe");
		String email = dynamicForm.get("email");
		String dateNias = dynamicForm.get("datenaissance");
		String cni = dynamicForm.get("cni");
		String telephone1 = dynamicForm.get("telephone");
		String telephone2 = dynamicForm.get("telephone2");
		String profession = dynamicForm.get("profession");

		CodeRetour retour = new CodeRetour();

		if (nom.equals("")) {
			retour.setMessage("Vous devez renseigner le nom");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (prenom.equals("")) {
			retour.setMessage("Vous devez renseigner le prenom");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (email.equals("")) {
			retour.setMessage("Vous devez renseigner l'email");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (dateNias.equals("")) {
			retour.setMessage("Vous devez renseigner la date de naissance");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (cni.equals("")) {
			retour.setMessage("Vous devez renseigner le numero d'indentification");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (telephone1.equals("")) {
			retour.setMessage("Vous devez renseigner au moins un numero de téléphone");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (profession.equals("")) {
			retour.setMessage("Vous devez renseigner la profession");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		return retour;
	}


	public CodeRetour addAgent(DynamicForm dynamicForm, Long idEquipe) {

		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		try {

			String nom = dynamicForm.get("nom");
			String prenom = dynamicForm.get("prenom");
			String sexe = dynamicForm.get("sexe");
			String email = dynamicForm.get("email");
			String dateNias = dynamicForm.get("datenaissance");
			String cni = dynamicForm.get("cni");
			String telephone1 = dynamicForm.get("telephone");
			String telephone2 = dynamicForm.get("telephone2");
			String profession = dynamicForm.get("profession");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date datenaissance = (Date) sdf.parse(dateNias);

			if (UserExterne.isPhoneNumberExist(telephone1)) {
				retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
				retour.setMessage("Le numero de téléphone que vous avez renseigné ["
						+ telephone1 + "] est utilisé par un autre agent.");
				retour.setError(true);
				return retour;
			}

			if (!telephone2.equals("")
					&& UserExterne.isPhoneNumberExist(telephone2)) {
				retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
				retour.setMessage("Le numero de téléphone que vous avez renseigné ["
						+ telephone2 + "] est utilisé par un autre agent.");
				retour.setError(true);
				return retour;
			}

			if (UserExterne.isCniExist(cni)) {
				retour.setCodeRetour(CodeRetour.CNI_NOT_VALID);
				retour.setMessage("Le numero de carte d'identite que vous avez renseigné ["
						+ cni + "] est utilisé par un autre agent");
				retour.setError(true);
				return retour;
			}

			if (UserExterne.isEmailExist(email)) {
				retour.setCodeRetour(CodeRetour.EMAIL_DUPLIQUE);
				retour.setMessage("L'adresse email que vous avez renseigné ["
						+ email + "] est utilisé par un autre agent.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isEmailValide(email)) {
				retour.setCodeRetour(CodeRetour.EMAIL_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un email correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isPhoneNumberValide(telephone1)) {
				retour.setCodeRetour(CodeRetour.TEL_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un numéro correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!telephone2.equals("") && !UserExterne.isPhoneNumberValide(telephone2)) {
				retour.setCodeRetour(CodeRetour.TEL_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un numéro correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isNameValide(nom)) {
				retour.setCodeRetour(CodeRetour.NAME_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un nom correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isNameValide(prenom)) {
				retour.setCodeRetour(CodeRetour.NAME_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un prénom correct.");
				retour.setError(true);
				return retour;
			}

			UserExterne userext = new UserExterne();

			userext.setNom(nom);
			userext.setPrenom(prenom);
			userext.setEmail(email);
			userext.setDateNaissance(datenaissance);
			userext.setSexe(sexe);
			userext.setCarteIdentite(cni);
			userext.setTelephone(telephone1);
			userext.setTelephone1(telephone2);
			userext.setProfession(profession);

			Equipe equipe = Equipe.findEquipeByIdEquipe(idEquipe);
			userext.setEquipe(equipe);

			userext.save();

			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");

		}

		return retour;
	}

	@Transactional
	public CodeRetour updateAgent(DynamicForm dynamicForm) {

		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		try {

			Long idAgent = Long.parseLong(dynamicForm.get("idAgent"));
						
			String nom = dynamicForm.get("nom");
			String prenom = dynamicForm.get("prenom");
			String sexe = dynamicForm.get("sexe");
			String email = dynamicForm.get("email");
			String dateNias = dynamicForm.get("datenaissance");
			String cni = dynamicForm.get("cni");
			String telephone1 = dynamicForm.get("telephone");
			String telephone2 = dynamicForm.get("telephone2");
			String profession = dynamicForm.get("profession");
			
			UserExterne userext = UserExterne.findUserExtById(idAgent);
			
			if (UserExterne.isPhoneNumberExistOnChanging(telephone1, userext)) {
				retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
				retour.setMessage("Le numero de téléphone que vous avez renseigné ["
						+ telephone1 + "] est utilisé par un autre agent.");
				retour.setError(true);
				return retour;
			}

			if (!telephone2.equals("")
					&& UserExterne.isPhoneNumberExistOnChanging(telephone2, userext)) {
				retour.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
				retour.setMessage("Le numero de téléphone que vous avez renseigné ["
						+ telephone2 + "] est utilisé par un autre agent.");
				retour.setError(true);
				return retour;
			}
			
			if (UserExterne.isCniExistOnChanging(cni, userext)) {
				retour.setCodeRetour(CodeRetour.CNI_NOT_VALID);
				retour.setMessage("Le numero de carte d'identite que vous avez renseigné ["
						+ cni + "] est utilisé par un autre agent");
				retour.setError(true);
				return retour;
			}

			if (UserExterne.isEmailExistOnChanging(email, userext)) {
				retour.setCodeRetour(CodeRetour.EMAIL_DUPLIQUE);
				retour.setMessage("L'adresse email que vous avez renseigné ["
						+ email + "] est utilisé par un autre agent.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isEmailValide(email)) {
				retour.setCodeRetour(CodeRetour.EMAIL_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un email correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isPhoneNumberValide(telephone1)) {
				retour.setCodeRetour(CodeRetour.TEL_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un numéro correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!telephone2.equals("") && !UserExterne.isPhoneNumberValide(telephone2)) {
				retour.setCodeRetour(CodeRetour.TEL_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un numéro correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isNameValide(nom)) {
				retour.setCodeRetour(CodeRetour.NAME_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un nom correct.");
				retour.setError(true);
				return retour;
			}
			
			if (!UserExterne.isNameValide(prenom)) {
				retour.setCodeRetour(CodeRetour.NAME_NOT_VALIDE);
				retour.setMessage("Veuillez renseigner un prénom correct.");
				retour.setError(true);
				return retour;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date datenaissance = (Date) sdf.parse(dateNias);


			Logger.info("Elements de user : " + userext.getDateNaissance());
			userext.setNom(nom);
			userext.setPrenom(prenom);
			userext.setEmail(email);
			userext.setDateNaissance(datenaissance);
			userext.setSexe(sexe);
			userext.setCarteIdentite(cni);
			userext.setTelephone(telephone1);
			userext.setTelephone1(telephone2);
			userext.setProfession(profession);
			Logger.info("Elements de user apré modif : "+ userext.getDateNaissance());
			userext.update();

			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");

		}
		return retour;
	}

}
