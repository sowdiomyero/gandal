package controllers;

import static play.data.Form.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.jndi.ldap.ManageReferralControl;

import models.Compte;
import models.Equipe;
import models.Etape;
import models.Localite;
import models.LocaliteProjet;
import models.Projet;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.CodeRetour;
import services.ProjetManager;
import views.html.projets.*;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 03/06/15 Time: 18:04 To change
 * this template use File | Settings | File Templates.
 */

@Security.Authenticated(Secured.class)
public class ProjetCtrl extends Controller {

	public static ProjetManager manager = new ProjetManager();

	public static Result addProject() {

		return ok();
	}

	public static Result home() {
		return ok();
	}

	// Creation d'un projet et affectation a la localite
	public static Result addProjet(Long idLoc) throws ParseException {

		// Recuperer les information du projet depuis le formulaire
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());

		CodeRetour retour = new CodeRetour();
		retour = manager.addProjet(dynamicForm, idLoc);

		return ok(Json.toJson(retour));
	}

	public static Result index(Long id) {

		Localite loca = Localite.findLocaliteById(id);

		List<Projet> externeProjets = loca.getListExterneProjet();
		List<Projet> projets = loca.getListProjets();

		return ok(index.render(Compte.findByEmail(request().username()),
				projets, externeProjets));
	}

	public static Result updateProject() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();

		CodeRetour retour = new CodeRetour();
		retour = manager.updateProjet(dynamicForm);

		return ok(Json.toJson(retour));
	}

	public static Result delProjet() {
		DynamicForm dynamicForm = form().bindFromRequest();
		Long idPro = Long.parseLong(dynamicForm.get("idProDel"));
		Long idLoc = Long.parseLong(dynamicForm.get("idLocDel"));

		CodeRetour retour = new CodeRetour();
		LocaliteProjet lp = LocaliteProjet.findLocaliteProjet(idLoc, idPro);
		if (lp == null) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Projet non existant");
		}

		lp.delete();

		return ok(Json.toJson(retour));
	}

	public static Result addProjetToLocalite(Long idPro, Long idLoc) {

		Projet pro = Projet.findProjetById(idPro);
		Localite loc = Localite.findLocaliteById(idLoc);
		LocaliteProjet locpro = new LocaliteProjet();
		locpro.setLocalite(loc);
		locpro.setProjets(pro);
		locpro.setIdLocalite(idLoc);

		locpro.setIdProjet(idPro);
		locpro.save();

		return redirect(routes.ProjetCtrl.index(idLoc));
	}

	public static Result infoProjet(Long idPro) {

		Projet projet = Projet.findProjetById(idPro);

		Compte compte = Compte.findByEmail(request().username());
		return ok(infoProjet.render(compte, projet));
	}

	// Creation d'un projet et affectation a la localite
	public static Result addEtape(Long idProjet) throws ParseException {

		// Recuperer les information du projet depuis le formulaire
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());

		CodeRetour retour = new CodeRetour();
		retour = manager.addEtape(dynamicForm, idProjet);

		return ok(Json.toJson(retour));
	}

	public static Result updateEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());

		CodeRetour retour = new CodeRetour();
		retour = manager.updateEtape(dynamicForm);

		return ok(Json.toJson(retour));
	}

	public static Result delEtape() {
		DynamicForm dynamicForm = form().bindFromRequest();
		Long idEtape = Long.parseLong(dynamicForm.get("idEtapeDel"));

		CodeRetour retour = new CodeRetour();
		Etape etape = Etape.findEtapeById(idEtape);
		if (etape == null) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Agent non existant");
		}
		etape.delete();

		return ok(Json.toJson(retour));
	}

	// changer eat du projet

	public static Result updateStateProjet(Long idProjet, String etat) {
		
		CodeRetour retour = new CodeRetour();
		Projet projet = Projet.findProjetById(idProjet);
		if (etat.equals("demarre")) {
			projet.setEtat(Projet.STATE.DEMARRE);
		}
		if (etat.equals("planifier")) {
			projet.setEtat(Projet.STATE.PLANIFIE);
			
		}
		if (etat.equals("suspendre")) {
			projet.setEtat(Projet.STATE.SUSPENDU);
		}
		if (etat.equals("terminer")) {
			projet.setEtat(Projet.STATE.TERMINE);
		}
		if (etat.equals("abandonner")) {
			projet.setEtat(Projet.STATE.ABANDONNE);
		}
		if (etat.equals("en cours")) {
			projet.setEtat(Projet.STATE.EN_COURS);
		}
		projet.update();
		return ok(Json.toJson(retour));

	}

	public static Result updateDetailsProject() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());

		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String nom = dynamicForm.get("nomProUp");
		String description = dynamicForm.get("descriptionProUp");

		CodeRetour cr = new CodeRetour();
		if (nom.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Projet pro = Projet.findProjetById(idPro);
			pro.setNomProjet(nom);
			pro.setDescProjet(description);
			pro.update();
		}
		return ok(Json.toJson(cr));
	}

	public static Result updatePersonnesProject() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String contact = dynamicForm.get("contactProUp");
		String autorite = dynamicForm.get("autoriteProUp");

		CodeRetour cr = new CodeRetour();
		if (contact.equals("") || autorite.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Projet pro = Projet.findProjetById(idPro);
			pro.setContatact(contact);
			pro.setAutorite(autorite);
			pro.update();
		}
		return ok(Json.toJson(cr));
	}

	public static Result updateEmploisProject() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelProUp");

		CodeRetour cr = new CodeRetour();
		if (emploiPrevisionnel.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Projet pro = Projet.findProjetById(idPro);

			pro.setEmploiPrevisionel(Integer.parseInt(emploiPrevisionnel));
			pro.update();
		}
		return ok(Json.toJson(cr));
	}

	public static Result updateBudgetsProject() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String coutPrevisionnel = dynamicForm.get("coutPrevisionnelProUp");

		CodeRetour cr = new CodeRetour();
		if (coutPrevisionnel.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Projet pro = Projet.findProjetById(idPro);

			pro.setCoutPrevisionel(Double.parseDouble(coutPrevisionnel));
			pro.update();
		}
		return ok(Json.toJson(cr));
	}

	public static Result updateDatesProject() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));
		String dateDebut = dynamicForm.get("dateDProUp");
		String dateFin = dynamicForm.get("dateFProUp");

		CodeRetour cr = new CodeRetour();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (dateDebut.equals("") || dateFin.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {
			Date dateD = (Date) sdf.parse(dateDebut);
			Date dateF = (Date) sdf.parse(dateFin);
			Projet pro = Projet.findProjetById(idPro);
			if (dateF.getTime() < dateD.getTime()) {
				cr.setMessage("La date de fin du projet doit etre superieur ou egal a la date de début");
				cr.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				cr.setError(true);
			} else {
				pro.setDateDebut(dateD);
				pro.setDateFin(dateF);

				pro.update();
			}
		}
		return ok(Json.toJson(cr));
	}

	public static Result infoEtape(Long idEtape) {

		Etape etape = Etape.findEtapeById(idEtape);

		Compte compte = Compte.findByEmail(request().username());
		return ok(infoEtape.render(compte, etape));
	}

	// changer eat du projet

	public static Result updateStateEtape(Long idEtape, String etat) {
		Etape etape = Etape.findEtapeById(idEtape);
		CodeRetour retour = new CodeRetour();
		if (etat.equals("demarre")) {
			etape.setEtat(Etape.STATE.DEMARRE);
		}
		if (etat.equals("planifier")) {
			etape.setEtat(Etape.STATE.PLANIFIE);
		}
		if (etat.equals("suspendre")) {
			etape.setEtat(Etape.STATE.SUSPENDU);
		}
		if (etat.equals("terminer")) {
			etape.setEtat(Etape.STATE.TERMINE);
		}
		if (etat.equals("abandonner")) {
			etape.setEtat(Etape.STATE.ABANDONNE);
		}
		if (etat.equals("en cours")) {
			etape.setEtat(Etape.STATE.EN_COURS);
		}
		etape.update();
		return ok(Json.toJson(retour));

	}

	public static Result updateNiveauExecutionEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idEtape = Long.parseLong(dynamicForm.get("idEtape"));

		String niveau = dynamicForm.get("niveauExecutionEtapeUp");

		CodeRetour cr = new CodeRetour();
		if (niveau.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Etape etape = Etape.findEtapeById(idEtape);
			etape.setNiveauExecution(Double.parseDouble(niveau));
			etape.update();
		}
		return ok(Json.toJson(cr));
	}

	/****************************************************** Modifier etape ********************************************************/

	public static Result updateDetailsEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String nom = dynamicForm.get("nomProUp");
		String description = dynamicForm.get("descriptionProUp");

		CodeRetour cr = new CodeRetour();
		if (nom.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Etape pro = Etape.findEtapeById(idPro);
			pro.setNomEtape(nom);
			pro.setDescEtape(description);
			pro.update();
		}
		return ok(Json.toJson(cr));
	}

	public static Result updatePersonnesEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String contact = dynamicForm.get("contactProUp");
		String autorite = dynamicForm.get("autoriteProUp");

		CodeRetour cr = new CodeRetour();
		if (contact.equals("") || autorite.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Etape pro = Etape.findEtapeById(idPro);
			pro.setContatact(contact);
			pro.setAutorite(autorite);
			pro.update();
		}

		return ok(Json.toJson(cr));
	}

	public static Result updateEmploisEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String emploiPrevisionnel = dynamicForm.get("emlpoiPrevisionnelProUp");
		String emploiReel = dynamicForm.get("emlpoiReelProUp");

		CodeRetour cr = new CodeRetour();
		if (emploiPrevisionnel.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Etape etape = Etape.findEtapeById(idPro);
			Projet pro = etape.getProjet();

			int	emploPre =  Integer.parseInt(emploiPrevisionnel);
			if(emploPre > pro.getEmploiPrevisionel()){
				cr.setMessage("Le nombre d'emploi previsionnel de l'etape doit etre inferieur au nombre d'emploi prévisionnel du projet");
				cr.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				cr.setError(true);
			}

			if (!emploiReel.equals("")) {
				etape.setEmploiReel(Integer.parseInt(emploiReel));

			}

			etape.setEmploiPrevisionel(Integer.parseInt(emploiPrevisionnel));
			etape.update();
		}

		return ok(Json.toJson(cr));
	}

	public static Result updateBudgetsEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String coutPrevisionnel = dynamicForm.get("coutPrevisionnelProUp");
		String coutReel = dynamicForm.get("coutReelProUp");

		CodeRetour cr = new CodeRetour();
		if (coutPrevisionnel.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		}

		else {

			Etape etape = Etape.findEtapeById(idPro);
			Projet pro = etape.getProjet();
			
			double coutPre = Double.parseDouble(coutPrevisionnel);
			if(coutPre > pro.getCoutPrevisionel()){
				cr.setMessage("Le cout previsionnel de l'etape doit etre inferieur au coup prévisionnel du projet");
				cr.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				cr.setError(true);
			}
			

			etape.setCoutPrevisionel(Double.parseDouble(coutPrevisionnel));
			if (!coutReel.equals("")) {
				etape.setCoutReel(Double.parseDouble(coutReel));

			}

			etape.update();
		}

		return ok(Json.toJson(cr));
	}

	public static Result updateDatesEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));
		String dateDebut = dynamicForm.get("dateDProUp");
		String dateFin = dynamicForm.get("dateFProUp");

		CodeRetour cr = new CodeRetour();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if (dateDebut.equals("") || dateFin.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir les champs vides.");
		} else {
			
			Date dateD = (Date) sdf.parse(dateDebut);
			Date dateF = (Date) sdf.parse(dateFin);
			Etape etape = Etape.findEtapeById(idPro);
			Projet pro = etape.getProjet();
			if (dateF.getTime() < dateD.getTime()) {
				cr.setMessage("La date de fin du l'étape doit etre superieur ou egal a la date de début");
				cr.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				cr.setError(true);
			}
			if(dateD.getTime() < pro.getDateDebut().getTime() || dateD.getTime() > pro.getDateFin().getTime()){
				cr.setMessage("La date de début de l'étape doit etre dans la periode d'exécution du projet");
				cr.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				cr.setError(true);
			}
			if(dateF.getTime() < pro.getDateDebut().getTime() || dateF.getTime() > pro.getDateFin().getTime()){
				cr.setMessage("La date de fin de l'étape doit etre dans la periode d'exécution du projet");
				cr.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
				cr.setError(true);
			}

			else{
				etape.setDateDebut(dateD);
				etape.setDateFin(dateF);
				etape.update();
			}
		}
		return ok(Json.toJson(cr));
	}

	public static Result terminerEtape() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		Long idPro = Long.parseLong(dynamicForm.get("idPro"));

		String coutReel = dynamicForm.get("coutReelProUp");
		String emploiReel = dynamicForm.get("emlpoiReelProUp");

		CodeRetour cr = new CodeRetour();
		if (coutReel.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir le cout reel.");
		}
		if (emploiReel.equals("")) {
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			cr.setMessage("Veuillez remplir le nombre d'emploi.");
		}

		Etape pro = Etape.findEtapeById(idPro);

		pro.setEmploiReel(Integer.parseInt(emploiReel));
		pro.setCoutReel(Double.parseDouble(coutReel));

		pro.update();

		return ok(Json.toJson(cr));
	}

}
