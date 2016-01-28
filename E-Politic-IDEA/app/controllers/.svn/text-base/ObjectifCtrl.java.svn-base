package controllers;

import static play.data.Form.form;
import models.Compte;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.CodeRetour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.Inscription;
import models.Inscription.STATE;
import models.Localite;
import views.html.infogenerale.index;

 
 
 
 
/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 03/06/15
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */

 
  

@Security.Authenticated(Secured.class)
public class ObjectifCtrl extends Controller {
	
	
	public static Result add() {
		CodeRetour cr = new CodeRetour();
		DynamicForm dynamicForm = form().bindFromRequest();
 		String idString = dynamicForm.get("idlocalite");
 		String objectifString = dynamicForm.get("objectifPourcent");
 		 
		Logger.info (idString+objectifString);
		Localite localite=Localite.findLocaliteById(Long.parseLong(idString));
		if(localite.getInfoAdministrative().getNbElecteurs()==0){
			cr.setCodeRetour(CodeRetour.NBELECTEUR_NOT_EMPTY);
			cr.setMessage("Veuillez d'abord renseigner le nombre d'electeurs");
			return ok(Json.toJson(cr));

			
		}
		else{
			if(objectifString==""){
				cr.setCodeRetour(CodeRetour.NBELECTEUR_NOT_EMPTY);
				cr.setMessage("Veuillez renseigner l'un des deux champs");
				return ok(Json.toJson(cr));
				
			}
			else{
		       localite.setObjectifs(Double.parseDouble(objectifString));
		       localite.update();
			}
	 
		} 
	 
		//return ok(index.render(Compte.findByEmail(request().username()),localite));
		
		return ok(Json.toJson(cr));
		
		 
		
	}
	

}