package controllers;

import static play.data.Form.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Compte;
import models.Membre;
import models.utils.AppException;
import models.utils.Hash;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.CodeRetour;
import views.html.profil.index;

@Security.Authenticated(Secured.class)
public class ProfilCtrl extends Controller {

	public static Result index() {
		Compte c = Compte.findByEmail(request().username());
		if (c != null) {
			Logger.info("Compte " + c.getFullname());
		} else
			Logger.info(" Compte non recuperer ");

		return ok(index.render(Compte.findByEmail(request().username())));
	}

	public static Result update() {

		DynamicForm dynamicForm = form().bindFromRequest();

		CodeRetour cr = new CodeRetour();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date datenaissance = null;

		try {
			datenaissance = (Date) sdf.parse(dynamicForm.get("datenaissance"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String nom = dynamicForm.get("nom");
		String prenom = dynamicForm.get("prenom");
		String sexe = dynamicForm.get("sexe");
		String tel1 = dynamicForm.get("telephone");
		String tel2 = dynamicForm.get("telephone2");
		String profession = dynamicForm.get("profession");
		String cni = dynamicForm.get("cni");

		String password = dynamicForm.get("password");

		String newpassword = dynamicForm.get("newpassword");

		String cpassword = dynamicForm.get("confirmPassword");

		Compte compte = null;

		if (nom.trim().equals("") || prenom.trim().equals("")) {
			cr.setMessage("Vous devez renseigner votre nom et votre prenom");
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			return ok(Json.toJson(cr));
		}

		if (cni.trim().equals("")) {
			cr.setMessage("Vous devez renseigner votre numero de carte d'identite");
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			return ok(Json.toJson(cr));
		}

		if (tel1.trim().equals("")) {
			cr.setMessage("Vous devez renseigner votre numero de telephone");
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			return ok(Json.toJson(cr));
		}

		if (profession.trim().equals("")) {
			cr.setMessage("Vous devez renseigner votre profession");
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			return ok(Json.toJson(cr));
		}

		if (profession.trim().equals("")) {
			cr.setMessage("Vous devez renseigner votre profession");
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			return ok(Json.toJson(cr));
		}

		if (dynamicForm.get("datenaissance").trim().equals("")) {
			cr.setMessage("Vous devez renseigner votre date de naissance");
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			return ok(Json.toJson(cr));
		}

		if (sexe.trim().equals("")) {
			cr.setMessage("Vous devez renseigner votre  sexe");
			cr.setCodeRetour(CodeRetour.EMPTY_FIELD);
			return ok(Json.toJson(cr));
		}

		if (password == "") // ou il ne change poas de mot de passe
		{

			if ((newpassword != "") || (cpassword != "")) {

				cr.setCodeRetour(CodeRetour.PASSWORD_NOT_VALID);
				cr.setMessage("Veuillez donner le bon mot de passe");
				return ok(Json.toJson(cr));

			}
			compte = Compte.findByEmail(request().username());

		} else // il veut changer un mot de passe
		{

			try {
				compte = Compte.authenticate(request().username(), password);
			} catch (AppException e) {
				e.printStackTrace();
			}
			if (compte == null) {

				cr.setCodeRetour(CodeRetour.PASSWORD_NOT_VALID);
				cr.setMessage("Veuillez donner le bon mot de passe");
				return ok(Json.toJson(cr));

			}

			// verification conformité mots de passe

			if (newpassword.equals(cpassword)) {

				try {
					compte.setPasswordHash(Hash.createPassword(newpassword));
				} catch (AppException e) {
					e.printStackTrace();
				}
				compte.update();
			} else {

				cr.setCodeRetour(CodeRetour.PASSWORD_NOT_EQUAL);
				cr.setMessage("Les champs password renseignés ne correspondent pas.");
				return ok(Json.toJson(cr));

			}
		}

		Membre membre = compte.getMembre();
		membre.setDateNaissance(datenaissance);

		membre.setNom(nom);
		membre.setPrenom(prenom);
		membre.setProfession(profession);
		membre.setSexe(sexe);

		if (!Membre.isCniExist(cni, membre)) {
			membre.setCarteIdentite(cni);

		} else {
			cr.setCodeRetour(CodeRetour.CNI_NOT_VALID);
			cr.setMessage("Le numero de carte d'identite que vous avez renseigné ["
					+ cni + "] est utilisé par un autre membre");
			return ok(Json.toJson(cr));
		}

		if (!Membre.isPhoneNumberExist(tel1, membre)) {
			membre.setTelephone(tel1);

		} else {
			cr.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
			cr.setMessage("Le numero de telephone que vous avez renseigné ["
					+ tel1 + "] est utilisé par un autre membre");
			return ok(Json.toJson(cr));
		}

		if (!Membre.isPhoneNumberExist(tel2, membre)) {
			membre.setTelephone1(tel2);

		} else {
			cr.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
			cr.setMessage("Le numero de telephone que vous avez renseigné ["
					+ tel2 + "] est utilisé par un autre membre");
			return ok(Json.toJson(cr));
		}

		if (tel1.equals(tel2)) {
			cr.setCodeRetour(CodeRetour.TEL_DUPLIQUE);
			cr.setMessage("Les numeros de telephone que vous avez renseigné doivent etre differentes");
			return ok(Json.toJson(cr));
		}

		membre.update();

		return ok(Json.toJson(cr));

	}
}
