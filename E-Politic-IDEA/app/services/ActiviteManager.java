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
import models.Localite;
import models.LocaliteActivite;
import models.Membre;
import models.Profil;
import models.UserExterne;
import models.utils.Hash;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;

public class ActiviteManager {

	public CodeRetour controleForm(DynamicForm dynamicForm) {

		String nom = dynamicForm.get("nom");
		String description = dynamicForm.get("description");
		String secteur = dynamicForm.get("secteur");

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

		if (secteur.equals("")) {
			retour.setMessage("Vous devez renseigner le secteur d'activité");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		return retour;
	}

	@Transactional
	public CodeRetour addActivite(DynamicForm dynamicForm, Long idLocalite) {

		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		Ebean.beginTransaction();

		try {

			String nom = dynamicForm.get("nom");
			String description = dynamicForm.get("description");
			String secteur = dynamicForm.get("secteur");

			Activites act = new Activites();
			act.setNomActivite(nom);
			act.setDescActivite(description);
			act.setSecteur(secteur);
			act.save();

			Long idAct = act.getId();
			Logger.info("Elements de la requete id activite : "
					+ idAct.toString());

			LocaliteActivite locAct = new LocaliteActivite();

			Localite localite = Localite.findLocaliteById(idLocalite);
			locAct.setLocalite(localite);
			locAct.setActivites(act);

			locAct.setIdLocalite(idLocalite);
			locAct.setIdActivite(idAct);

			locAct.save();

			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

			Ebean.commitTransaction();

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");

		}

		return retour;
	}

	
	public CodeRetour updateActivite(DynamicForm dynamicForm) {

		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;		

		try {
			
			Long idAct = Long.parseLong(dynamicForm.get("idAct"));
			
			String nom = dynamicForm.get("nom");
			String description = dynamicForm.get("description");
			String secteur = dynamicForm.get("secteur");

			Activites act = Activites.findActiviteById(idAct);
			act.setNomActivite(nom);
			act.setDescActivite(description);
			act.setSecteur(secteur);
			act.update();

			retour.setCodeRetour(CodeRetour.RETOUR_OK);
			retour.setMessage("Enregistrement des données effectuée avec Succes");

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");

		}

		return retour;
	}

}
