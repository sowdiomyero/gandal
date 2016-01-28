package controllers;

import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.CodeRetour;
import services.LocaliteManager;
import services.MembreManager;
import views.html.cartographie.index;
import views.html.localite.list;
import views.html.infoLocalite;
import views.html.tableaulocalite;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Localisation extends Controller {
	public static LocaliteManager manager = new LocaliteManager();
	public static Result sayHello() {
		List<Region> result = Region.findAllRegions();
		List<Departement> departements=Departement.findAllDeptartements();
		List<Quartier> quartiers=Quartier.findQuartiers();
		List<Membre> membres=Membre.findAllMembres();
		return ok(Json.toJson(result));
	}

	public static Result cartographie() {
		List<Membre> membres = Membre.findAllMembresNonResponsableEquipe();
		List<Localite> dpts = Localite.findDistinctLocalites();
		return ok(index.render(Compte.findByEmail(request().username()), membres, dpts));
	}

	public static Result listLocalites() {
		Compte compte = Compte.findByEmail(request().username());
		List<Membre> membres = Membre.findAllMembresNonResponsableEquipe();
		Logger.info("LES RESPONSABLES : "+membres.size());
		List<Localite> dpts = Localite.findDistinctLocalites();
		return ok(list.render(compte, dpts,membres,new Localite()));
	}
	public static Result getRegionById(Long id){
		return ok(Json.toJson(Region.findRegionById(id)));
	}

	public static Result getAllDepartements() {

		List<Departement> departements=Departement.findAllDeptartements();
		return ok(Json.toJson(departements));
	}
	public static Result getAllQuartiers() {

		List<Quartier> quartiers=Quartier.findQuartiers();
		return ok(Json.toJson(quartiers));
	}

	public static Result getDepartementsByRegionId(Long regionId) {

		List<Departement> departements=Region.findDepartementsByIdRegion(regionId);
		return ok(Json.toJson(departements));
	}

	public static Result getQuartiersByDepartementsId(Long deptId) {

		List<Quartier> departements=Quartier.findQuartiersByIdDept(deptId);
		return ok(Json.toJson(departements));
	}

	public static Result addQuartier() {
		DynamicForm dynamicForm = form().bindFromRequest();
	 	
		 Logger.info("Elements de la requete : " + dynamicForm.toString());
	     CodeRetour retour = manager.addLocalite(dynamicForm);

	        return ok(Json.toJson(retour));
		
	}

	public static Result search() {

		DynamicForm dynamicForm = form().bindFromRequest();
		String typeCible=  dynamicForm.get("typeCible").split(":")[0];
		int populationBetween=  Integer.parseInt(dynamicForm.get("between"));
		int populationAnd=  Integer.parseInt(dynamicForm.get("and"));
		int nbMilitants=  Integer.parseInt(dynamicForm.get("nbMilitants"));
		String message=  dynamicForm.get("message");

		String[][] locations = null;

		/*if(typeCible.equalsIgnoreCase("ALL")){
            locations = Localite.getAllLocalites();
        }else{*/
		locations=Localite.searchLocalites(typeCible,populationBetween, populationAnd, nbMilitants);
		//}
		/*}else if(typeCible.equalsIgnoreCase("REGION")) {
            locations=Localite.getLocalitesByType("REGION");
        }else if(typeCible.equalsIgnoreCase("DEPARTEMENT")) {
            locations=Localite.getLocalitesByType("DEPARTEMENT");
        }else if(typeCible.equalsIgnoreCase("COMMUNE")) {
            locations=Localite.getLocalitesByType("COMMUNE");
        }*/

		return ok(Json.toJson(locations));
	}

	public static Result recherche() {

		DynamicForm dynamicForm = form().bindFromRequest();
		String typeCible=  dynamicForm.get("typeCible").split(":")[0];
		int populationBetween=  Integer.parseInt(dynamicForm.get("between"));
		int populationAnd=  Integer.parseInt(dynamicForm.get("and"));
		int nbMilitants=  Integer.parseInt(dynamicForm.get("nbMilitants"));
		String message=  dynamicForm.get("message");

		List<Localite>locations=Localite.rechercheLocalites(typeCible,populationBetween, populationAnd, nbMilitants);
		Logger.info("Resultats de la requete : "+locations);
		Compte compte = Compte.findByEmail(request().username());
		List<Membre> membres = Membre.findAllMembresNonResponsableEquipe();
		return ok(list.render(compte,locations,membres, new Localite()));
	}

	public static Result sendSms() {

		DynamicForm dynamicForm = form().bindFromRequest();
		String typeCible=  dynamicForm.get("typeCible").split(":")[0];
		int populationBetween=  Integer.parseInt(dynamicForm.get("between"));
		int populationAnd=  Integer.parseInt(dynamicForm.get("and"));
		int nbMilitants=  Integer.parseInt(dynamicForm.get("nbMilitants"));
		String message=  dynamicForm.get("message");

		List<Membre> membres = new ArrayList<Membre>();
		membres=Localite.searchMembres(typeCible, populationBetween, populationAnd, nbMilitants);
		return ok(Json.toJson("Message en cours d'envoie ....."));
	}

	public static Result getLocaliteByType(String niveau) {
		/**
		 * Les information retourner :
		 * [ idZone, nomZone, typeZone{R,D,S,Q}, idResponsable, NomPrenomResponsable, nbMilitants, nbHabitants, nbSection ]
		 */
		String[][] locations =null;
		if(niveau == null || niveau.equalsIgnoreCase("ALL"))
			locations  =Localite.getAllLocalites();
		else
			locations= Localite.getLocalitesByType(niveau);

		return ok(Json.toJson(locations));
	}

	public static Result getAllLocalites() {

		return ok(Json.toJson(Localite.getAllLocalites()));
	}


	public static Result infosLocalite(Long idLocalite) {
		Localite localite= Localite.findLocaliteById(idLocalite);
		return ok(infoLocalite.render(Compte.findByEmail(request().username()), localite));
	}
	
	public static Result updateQuartier() {
		
		
		DynamicForm dynamicForm = form().bindFromRequest();
	 	String rattachement=dynamicForm.get("rattachement");
		
		 Logger.info("LE RESPONSABLE: " + rattachement);
		 Logger.info("Elements de la requete : " + dynamicForm.toString());
	     CodeRetour retour = manager.updateLocalite(dynamicForm);

	        return ok(Json.toJson(retour));
		
	}
}
