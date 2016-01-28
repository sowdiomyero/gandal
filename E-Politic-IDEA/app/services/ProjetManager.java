package services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;

import controllers.routes;
import models.Activites;
import models.Categorie;
import models.Compte;
import models.Equipe;
import models.Etape;
import models.Localite;
import models.LocaliteActivite;
import models.LocaliteProjet;
import models.Membre;
import models.Profil;
import models.Projet;
import models.UserExterne;
import models.utils.Hash;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;

public class ProjetManager {

	public CodeRetour controleForm(DynamicForm dynamicForm) {

		String nom = dynamicForm.get("nomProUp");
		String description = dynamicForm.get("descriptionProUp");
		String contact = dynamicForm.get("contactProUp");
		String autorite = dynamicForm.get("autoriteProUp");
		String coutPrevisionnel = dynamicForm.get("coutPrevisionnelProUp");
		String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelProUp");
		String dateDebut = dynamicForm.get("dateDProUp");
		String dateFin = dynamicForm.get("dateFProUp");

		CodeRetour retour = new CodeRetour();

		if (nom.equals("")) {
			retour.setMessage("Vous devez renseigner le nom");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (description.equals("")) {
			retour.setMessage("Vous devez renseigner la description");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (dateDebut.equals("")) {
			retour.setMessage("Vous devez renseigner la date de début");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (dateFin.equals("")) {
			retour.setMessage("Vous devez renseigner la date de fin");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (contact.equals("")) {
			retour.setMessage("Vous devez renseigner le contact");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (autorite.equals("")) {
			retour.setMessage("Vous devez renseigner l'autorité");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (coutPrevisionnel.equals("")) {
			retour.setMessage("Vous devez renseigner le cout prévisionnel");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (emploiPrevisionnel.equals("")) {
			retour.setMessage("Vous devez renseigner le nombre d'emplois prévisionnel");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		return retour;
	}
	

	@Transactional
	public CodeRetour addProjet(DynamicForm dynamicForm, Long idLocalite) {
	
		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		Ebean.beginTransaction();

		try {
			
			String nom = dynamicForm.get("nomProUp");
			String description = dynamicForm.get("descriptionProUp");
			String contact = dynamicForm.get("contactProUp");
			String autorite = dynamicForm.get("autoriteProUp");
			String coutPrevisionnel = dynamicForm.get("coutPrevisionnelProUp");
			String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelProUp");
			String dateDebut = dynamicForm.get("dateDProUp");
			String dateFin = dynamicForm.get("dateFProUp");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateD = (Date) sdf.parse(dateDebut);
			Date dateF = (Date) sdf.parse(dateFin);
			if(dateF.getTime() < dateD.getTime()){
				retour.setMessage("La date de fin du projet doit etre superieur ou egal a la date de début");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			Projet pro = new Projet();
			pro.setNomProjet(nom);
			pro.setDescProjet(description);
			pro.setDateDebut(dateD);
			pro.setDateFin(dateF);
			pro.setContatact(contact);
			pro.setAutorite(autorite);
			pro.setEmploiPrevisionel(Integer.parseInt(emploiPrevisionnel));
			pro.setCoutPrevisionel(Integer.parseInt(coutPrevisionnel));
			pro.setEtat(Projet.STATE.EN_ATTENTE);

			Localite loc = Localite.findLocaliteById(idLocalite);

			pro.save();

			LocaliteProjet lp = new LocaliteProjet();

			lp.setLocalite(loc);
			lp.setProjets(pro);
			lp.setIdLocalite(idLocalite);
			lp.setIdProjet(pro.getId());
			lp.save();
			
			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

			Ebean.commitTransaction();

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");
		}

		return retour;
	}

	public CodeRetour updateProjet(DynamicForm dynamicForm) {
		
		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		try {
			
			Long idPro = Long.parseLong(dynamicForm.get("idPro"));
			String nom = dynamicForm.get("nomProUp");
			String description = dynamicForm.get("descriptionProUp");
			String contact = dynamicForm.get("contactProUp");
			String autorite = dynamicForm.get("autoriteProUp");
			String coutPrevisionnel = dynamicForm.get("coutPrevisionnelProUp");
			String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelProUp");
			String dateDebut = dynamicForm.get("dateDProUp");
			String dateFin = dynamicForm.get("dateFProUp");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateD = (Date) sdf.parse(dateDebut);
			Date dateF = (Date) sdf.parse(dateFin);
			if(dateF.getTime() < dateD.getTime()){
				retour.setMessage("La date de fin du projet doit etre superieur ou egal a la date de début");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			Projet pro = Projet.findProjetById(idPro);
			pro.setNomProjet(nom);
			pro.setDescProjet(description);
			pro.setDateDebut(dateD);
			pro.setDateFin(dateF);
			pro.setContatact(contact);
			pro.setAutorite(autorite);
			pro.setEmploiPrevisionel(Integer.parseInt(emploiPrevisionnel));
			pro.setCoutPrevisionel(Double.parseDouble(coutPrevisionnel));
			pro.update();
			
			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");
		}

		return retour;
	}
	
	/****************************************************Etape_manager**************************************************************/
	
	public CodeRetour controleEtapeForm(DynamicForm dynamicForm) {

		String nom = dynamicForm.get("nomEtape");
		String description = dynamicForm.get("descriptionEtape");
		String contact = dynamicForm.get("contactEtape");
		String autorite = dynamicForm.get("autoriteEtape");
		String coutPrevisionnel = dynamicForm.get("coutPrevisionnelEtape");
		String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelEtape");
		String dateDebut = dynamicForm.get("dateDEtape");
		String dateFin = dynamicForm.get("dateFEtape");

		CodeRetour retour = new CodeRetour();

		if (nom.equals("")) {
			retour.setMessage("Vous devez renseigner le nom");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (description.equals("")) {
			retour.setMessage("Vous devez renseigner la description");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (dateDebut.equals("")) {
			retour.setMessage("Vous devez renseigner la date de début");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (dateFin.equals("")) {
			retour.setMessage("Vous devez renseigner la date de fin");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (contact.equals("")) {
			retour.setMessage("Vous devez renseigner le contact");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (autorite.equals("")) {
			retour.setMessage("Vous devez renseigner l'autorité");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (coutPrevisionnel.equals("")) {
			retour.setMessage("Vous devez renseigner le cout prévisionnel");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (emploiPrevisionnel.equals("")) {
			retour.setMessage("Vous devez renseigner le nombre d'emplois prévisionnel");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		return retour;
	}
	
	
	public CodeRetour controleEtapeUpdateForm(DynamicForm dynamicForm) {

		String nom = dynamicForm.get("nomEtapeUp");
		String description = dynamicForm.get("descriptionEtapeUp");
		String contact = dynamicForm.get("contactEtapeUp");
		String autorite = dynamicForm.get("autoriteEtapeUp");
		String coutPrevisionnel = dynamicForm.get("coutPrevisionnelEtapeUp");
		String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelEtapeUp");
		String dateDebut = dynamicForm.get("dateDEtapeUp");
		String dateFin = dynamicForm.get("dateFEtapeUp");

		CodeRetour retour = new CodeRetour();

		if (nom.equals("")) {
			retour.setMessage("Vous devez renseigner le nom");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (description.equals("")) {
			retour.setMessage("Vous devez renseigner la description");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (dateDebut.equals("")) {
			retour.setMessage("Vous devez renseigner la date de début");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (dateFin.equals("")) {
			retour.setMessage("Vous devez renseigner la date de fin");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (contact.equals("")) {
			retour.setMessage("Vous devez renseigner le contact");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (autorite.equals("")) {
			retour.setMessage("Vous devez renseigner l'autorité");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (coutPrevisionnel.equals("")) {
			retour.setMessage("Vous devez renseigner le cout prévisionnel");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		if (emploiPrevisionnel.equals("")) {
			retour.setMessage("Vous devez renseigner le nombre d'emplois prévisionnel");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		return retour;
	}
	
	
	
	public CodeRetour addEtape(DynamicForm dynamicForm, Long idProjet) {
	
		CodeRetour retour = controleEtapeForm(dynamicForm);

		if (retour.isError())
			return retour;

		try {
			
			String nom = dynamicForm.get("nomEtape");
			String description = dynamicForm.get("descriptionEtape");
			String contact = dynamicForm.get("contactEtape");
			String autorite = dynamicForm.get("autoriteEtape");
			String coutPrevisionnel = dynamicForm.get("coutPrevisionnelEtape");
			String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelEtape");
			String dateDebut = dynamicForm.get("dateDEtape");
			String dateFin = dynamicForm.get("dateFEtape");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateD = (Date) sdf.parse(dateDebut);
			Date dateF = (Date) sdf.parse(dateFin);
			
			Projet pro = Projet.findProjetById(idProjet);
			
			if(dateD.getTime() < pro.getDateDebut().getTime() || dateD.getTime() > pro.getDateFin().getTime()){
				retour.setMessage("La date de début de l'étape doit etre dans la periode d'exécution du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			if(dateF.getTime() < pro.getDateDebut().getTime() || dateF.getTime() > pro.getDateFin().getTime()){
				retour.setMessage("La date de fin de l'étape doit etre dans la periode d'exécution du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			if(dateF.getTime() < dateD.getTime()){
				retour.setMessage("La date de fin de l'étape doit etre superieur a la date de début");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			
			double coutPre = Double.parseDouble(coutPrevisionnel);
			int	emploPre =  Integer.parseInt(emploiPrevisionnel);
			if(coutPre > pro.getCoutPrevisionel()){
				retour.setMessage("Le cout previsionnel de l'etape doit etre inferieur au coup prévisionnel du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			if(emploPre > pro.getEmploiPrevisionel()){
				retour.setMessage("Le nombre d'emploi previsionnel de l'etape doit etre inferieur au nombre d'emploi du projet prévisionnel du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			Etape etape = new Etape();
			etape.setNomEtape(nom);
			etape.setDescEtape(description);
			etape.setDateDebut(dateD);
			etape.setDateFin(dateF);
			etape.setContatact(contact);
			etape.setAutorite(autorite);
			etape.setCoutPrevisionel(coutPre);
			etape.setEmploiPrevisionel(emploPre);
			etape.setEtat(Etape.STATE.EN_ATTENTE);
			 
			etape.setProjet(pro);

			etape.save();
			
			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");
		}

		return retour;
	}
	

	public CodeRetour updateEtape(DynamicForm dynamicForm) {
	
		CodeRetour retour = controleEtapeUpdateForm(dynamicForm);

		if (retour.isError())
			return retour;

		try {
			Long idEtape = Long.parseLong(dynamicForm.get("idEtape"));
			Logger.info("Etape trouvée : " + idEtape);
			
			String nom = dynamicForm.get("nomEtapeUp");
			String description = dynamicForm.get("descriptionEtapeUp");
			String contact = dynamicForm.get("contactEtapeUp");
			String autorite = dynamicForm.get("autoriteEtapeUp");
			String coutPrevisionnel = dynamicForm.get("coutPrevisionnelEtapeUp");
			String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelEtapeUp");
			String dateDebut = dynamicForm.get("dateDEtapeUp");
			String dateFin = dynamicForm.get("dateFEtapeUp");
			
			String coutReel = dynamicForm.get("coutReelEtapeUp");
			String emploiReel = dynamicForm.get("emlpoiReelEtapeUp");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateD = (Date) sdf.parse(dateDebut);
			Date dateF = (Date) sdf.parse(dateFin);
			
			Etape etape = Etape.findEtapeById(idEtape);
			Projet pro = etape.getProjet();
			
			Logger.error("Etape trouvée : " + etape.getAutorite());
			
			if(dateD.getTime() < pro.getDateDebut().getTime() || dateD.getTime() > pro.getDateFin().getTime()){
				retour.setMessage("La date de début de l'étape doit etre dans la periode d'exécution du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			if(dateF.getTime() < pro.getDateDebut().getTime() || dateF.getTime() > pro.getDateFin().getTime()){
				retour.setMessage("La date de fin de l'étape doit etre dans la periode d'exécution du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			if(dateF.getTime() < dateD.getTime()){
				retour.setMessage("La date de fin de l'étape doit etre superieur a la date de début");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			double coutPre = Double.parseDouble(coutPrevisionnel);
			int	emploPre =  Integer.parseInt(emploiPrevisionnel);
			if(coutPre > pro.getCoutPrevisionel()){
				retour.setMessage("Le cout previsionnel de l'etape doit etre inferieur au coup prévisionnel du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			if(emploPre > pro.getEmploiPrevisionel()){
				retour.setMessage("Le nombre d'emploi previsionnel de l'etape doit etre inferieur au nombre d'emploi prévisionnel du projet");
				retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				retour.setError(true);
				return retour;
			}
			etape.setNomEtape(nom);
			etape.setDescEtape(description);
			etape.setDateDebut(dateD);
			etape.setDateFin(dateF);
			etape.setContatact(contact);
			etape.setAutorite(autorite);
			etape.setEmploiPrevisionel(emploPre);
			etape.setCoutPrevisionel(coutPre);
			if (!coutReel.equals("")) {
				etape.setCoutReel(Double.parseDouble(coutReel));
			}
			if (!emploiReel.equals("")) {
				etape.setEmploiReel(Integer.parseInt(emploiReel));

			}
			etape.update();
			
			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");
		}

		return retour;
	}


}
