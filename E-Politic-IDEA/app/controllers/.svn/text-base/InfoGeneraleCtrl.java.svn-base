package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Activites;
import models.Compte;
import models.Localite;
import models.LocaliteActivite;
import models.Projet;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.infogenerale.index;

@Security.Authenticated(Secured.class)
public class InfoGeneraleCtrl extends Controller {
	public static Result index(Long id) {
		

 
		
		Localite localite=Localite.findLocaliteById(id);


		return ok(index.render(Compte.findByEmail(request().username()),localite));
	}

}
