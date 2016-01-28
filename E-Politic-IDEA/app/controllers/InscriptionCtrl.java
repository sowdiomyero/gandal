package controllers;

import static play.data.Form.form;
import models.Compte;
import play.Logger;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Inscription;
import models.Inscription.STATE;
import models.Localite;
import views.html.dashboard.index;
import views.html.membre.*;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 03/06/15 Time: 15:56 To change
 * this template use File | Settings | File Templates.
 */

@Security.Authenticated(Secured.class)
public class InscriptionCtrl extends Controller {

	public static Result add() {

		DynamicForm dynamicForm = form().bindFromRequest();
		String nbreInscrit = dynamicForm.get("nbreInscrit");
		Date dateInscription = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateInscription = (Date) sdf.parse(dynamicForm
					.get("dateInscription"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String idString = dynamicForm.get("idlocalite");

		Localite localite = Localite.findLocaliteById(Long.parseLong(idString));
		Inscription inscit = new Inscription();
		inscit.setDateInscription(dateInscription);
		inscit.setDateCreation(new Date());
		inscit.setDateUpdated(new Date());

		inscit.setEquipe(localite.getTeam());
		inscit.setEtat(STATE.VALIDATED);
		inscit.setLocalite(localite);

		Long nbInscrits = Long.parseLong(nbreInscrit);
		inscit.setNbInscriptions(nbInscrits);
 		inscit.save();
 		
 	 
		return ok(dashboard.render(Compte.findByEmail(request().username())));

	}

}