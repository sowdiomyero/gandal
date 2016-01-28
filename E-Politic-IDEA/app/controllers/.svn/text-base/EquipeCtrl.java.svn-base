package controllers;

import models.Compte;
import models.Equipe;
import models.Inscription;
import models.Localite;
import models.Membre;
import models.Inscription.STATE;
import models.utils.SmsManager;
import models.UserExterne;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.AgentManager;
import services.CodeRetour;
import services.LocaliteManager;
import views.html.equipe.listmembresequipe;
import views.html.messages.listemessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Query;

import static play.data.Form.form;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 03/06/15 Time: 17:23 To change
 * this template use File | Settings | File Templates.
 */
@Security.Authenticated(Secured.class)
public class EquipeCtrl extends Controller {
	public static AgentManager manager = new AgentManager();

	public static Result GO_HOME_SMS = redirect(routes.EquipeCtrl
			.showAllMessageEquipes());

	public static Result index() {
		List<Equipe> lesEquipes = Equipe.findAllEquipes();
		return ok(views.html.equipe.index.render(
				Compte.findByEmail(request().username()), lesEquipes));
	}

	public static Result membresEquipe(Long id) {
		List<UserExterne> lesUsersExt = Equipe.findUserExterneByIdEquipe(id);

		List<Inscription> outGoingMsg = Inscription
				.findOutGoingMsgByIdResponsable(Compte
						.findByEmail(request().username()).getMembre().getId());

		return ok(listmembresequipe.render(
				Compte.findByEmail(request().username()), lesUsersExt, id,
				outGoingMsg));
	}

	public static Result addMembreEquipe(Long idEquipe) throws ParseException {

		// Recuperer les information du userext depuis le formulaire
		
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());

		CodeRetour retour = new CodeRetour();
		retour = manager.addAgent(dynamicForm, idEquipe);
		
		return ok(Json.toJson(retour));
	}

	public static Result delMembreEquipe() {
		DynamicForm dynamicForm = form().bindFromRequest();
		Long idUserExt = Long.parseLong(dynamicForm.get("idAgentDel"));
		
		Logger.info("identifiant : " + idUserExt);

		CodeRetour retour = new CodeRetour();

		UserExterne user = UserExterne.findUserExtById(idUserExt);
		if(user == null){
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Agent non existant");
		}
		
		user.delete();
		
		return ok(Json.toJson(retour));
	}

	public static Result updateMembreEquipe() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());
		
		CodeRetour retour = new CodeRetour();
		retour = manager.updateAgent(dynamicForm);
		
		return ok(Json.toJson(retour));
	}
	

	public static Result showAllMessageEquipes() {
		Long idLoc = Compte.findByEmail(request().username()).getIdLocalite();
		List<Inscription> insc = Inscription.findInscriptionByIdLocalite(idLoc);
		List<Inscription> inscValide = Inscription
				.findInscriptionValideByIdLocalite(idLoc);
		List<Inscription> inscNotValide = Inscription
				.findInscriptionNotValideByIdLocalite(idLoc);
		List<Inscription> inscDup = Inscription
				.findInscriptionDuplicateByIdLocalite(idLoc);
		List<Inscription> inscBad = Inscription
				.findInscriptionBadFormatByIdLocalite(idLoc);
		List<Inscription> outGoingMsg = Inscription
				.findOutGoingMsgByIdResponsable(Compte
						.findByEmail(request().username()).getMembre().getId());

		if (insc != null)
			Logger.info("Liste projet recuperer " + insc.size());
		else
			Logger.info("Liste projet non recuperer ");

		Logger.info("Valeur du username_email reçu " + request().username());
		return ok(listemessage.render(Compte.findByEmail(request().username()),
				inscValide, inscNotValide, inscBad, inscDup, outGoingMsg));
	}

	public static Result updateInscriptionToValide(Long idIns) {
		Inscription valide = Inscription.findInscriptionById(idIns);

		valide.setEtat(STATE.VALIDATED);
		valide.update();

		Long idLoc = Compte.findByEmail(request().username()).getIdLocalite();
		List<Inscription> insc = Inscription.findInscriptionByIdLocalite(idLoc);

		if (insc != null)
			Logger.info("Liste projet recuperer " + insc.size());
		else
			Logger.info("Liste projet non recuperer ");

		Logger.info("Valeur du username_email reçu " + request().username());
		return GO_HOME_SMS;
	}

	public static Result updateInscriptionToNotValide(Long idIns) {
		Inscription valide = Inscription.findInscriptionById(idIns);

		valide.setEtat(STATE.NOT_VALIDATED);
		valide.update();

		Long idLoc = Compte.findByEmail(request().username()).getIdLocalite();
		List<Inscription> insc = Inscription.findInscriptionByIdLocalite(idLoc);

		if (insc != null)
			Logger.info("Liste projet recuperer " + insc.size());
		else
			Logger.info("Liste projet non recuperer ");

		Logger.info("Valeur du username_email reçu " + request().username());
		return GO_HOME_SMS;
	}

	public static Result updateInscriptionToBadSyntax(Long idIns) {
		Inscription valide = Inscription.findInscriptionById(idIns);

		valide.setEtat(STATE.NOT_WELL_FORMED);
		valide.update();

		Long idLoc = Compte.findByEmail(request().username()).getIdLocalite();
		List<Inscription> insc = Inscription.findInscriptionByIdLocalite(idLoc);

		if (insc != null)
			Logger.info("Liste projet recuperer " + insc.size());
		else
			Logger.info("Liste projet non recuperer ");

		Logger.info("Valeur du username_email reçu " + request().username());
		return GO_HOME_SMS;
	}

	public static Result updateInscriptionToDuplicate(Long idIns) {
		Inscription valide = Inscription.findInscriptionById(idIns);

		valide.setEtat(STATE.DUPLICATED);
		valide.update();

		Long idLoc = Compte.findByEmail(request().username()).getIdLocalite();
		List<Inscription> insc = Inscription.findInscriptionByIdLocalite(idLoc);

		if (insc != null)
			Logger.info("Liste projet recuperer " + insc.size());
		else
			Logger.info("Liste projet non recuperer ");

		Logger.info("Valeur du username_email reçu " + request().username());
		return GO_HOME_SMS;
	}

	public static Result pushSmsToAgent(Long idEquipeResponsable) {

		Logger.info("Requete envoyée au runner ..... ");

		DynamicForm dynamicForm = form().bindFromRequest();
		String message = dynamicForm.get("message");

		Equipe equipe = Equipe.findEquipeByIdEquipe(idEquipeResponsable);
		Long idLoc = Compte.findByEmail(request().username()).getIdLocalite();
		Membre membre = Compte.findByEmail(request().username()).getMembre();
		Localite loc = Localite.findLocaliteById(idLoc);

		Inscription insc = new Inscription();
		insc.setEquipe(equipe);
		insc.setLocalite(loc);
		insc.setMessageOut(message);
		insc.setMembre(membre);
		insc.save();

		List<UserExterne> lesUsersExt = Equipe
				.findUserExterneByIdEquipe(idEquipeResponsable);
		SmsManager.Envelop sms = new SmsManager.Envelop(message, lesUsersExt);
		SmsManager.sendSms(sms);

		return redirect(routes.EquipeCtrl.membresEquipe(idEquipeResponsable));
	}

	public static Result delSmsEquipe() {
		
		DynamicForm dynamicForm = form().bindFromRequest();
		Long id = Long.parseLong(dynamicForm.get("idsms"));
		
		CodeRetour retour = new CodeRetour();

		Inscription ins = Inscription.findInscriptionById(id);
		
		if(ins == null)
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
		
		ins.delete();
		
		return ok(Json.toJson(retour));
	}

}
