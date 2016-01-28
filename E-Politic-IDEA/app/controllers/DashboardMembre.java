package controllers;

import models.Compte;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.membre.dashboard;

@Security.Authenticated(Secured.class)
public class DashboardMembre extends Controller {
	
	

	public static Result dashboard() {
		// List<Equipe> lesEquipes=Equipe.findAllEquipes();

		// We recovered the username from the session

		return ok(dashboard.render(Compte.findByEmail(request().username())));
	}
}
