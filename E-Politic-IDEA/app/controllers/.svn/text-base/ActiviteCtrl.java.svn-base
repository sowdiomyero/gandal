package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.data.Form.form;
import models.Activites;
import models.Compte;
import models.Equipe;
import models.Localite;
import models.LocaliteActivite;
import models.UserExterne;
import play.data.DynamicForm;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.ActiviteManager;
import services.AgentManager;
import services.CodeRetour;
import views.html.activite.*;

@Security.Authenticated(Secured.class)
public class ActiviteCtrl extends Controller {

	public static ActiviteManager manager = new ActiviteManager();

	public static Result index(Long id) {

		Localite loca = Localite.findLocaliteById(id);

		List<Activites> mesActivites = null;
		mesActivites = loca.getListActivites();
		if (mesActivites != null) {
			Logger.info("Liste activites recuperer " + mesActivites.size());
		} else
			Logger.info("Liste activites ne sont pas  recuperer ");

		Logger.info("Valeur du username_email reçu " + request().username());
		return ok(index.render(Compte.findByEmail(request().username()),
				mesActivites));
	}

	public static Result addActivite(Long idLocalite) {

		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());

		CodeRetour retour = new CodeRetour();
		retour = manager.addActivite(dynamicForm, idLocalite);

		return ok(Json.toJson(retour));
	}

	public static Result delActivite() {
		DynamicForm dynamicForm = form().bindFromRequest();
		Long idAct = Long.parseLong(dynamicForm.get("idActDel"));
		Long idLoc = Long.parseLong(dynamicForm.get("idLocDel"));

		CodeRetour retour = new CodeRetour();
		LocaliteActivite lc = LocaliteActivite.findLocaliteActivite(idLoc, idAct);
		if(lc == null){
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Agent non existant");
		}
		
		lc.delete();

		return ok(Json.toJson(retour));
	}

	public static Result updateActivite() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
		Logger.info("Elements de la requete : " + dynamicForm.toString());

		CodeRetour retour = new CodeRetour();
		retour = manager.updateActivite(dynamicForm);

		return ok(Json.toJson(retour));
	}
}