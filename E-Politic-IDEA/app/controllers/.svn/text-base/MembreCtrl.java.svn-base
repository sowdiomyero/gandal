package controllers;

import static play.data.Form.form;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import models.Compte;
import models.Localite;
import models.Membre;
import models.Profil;
import models.utils.Mail;

import org.apache.commons.mail.EmailException;

import play.Configuration;
import play.Logger;
import play.data.DynamicForm;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.CodeRetour;
import services.MembreManager;
import views.html.membre.index;

/**
 * Created with IntelliJ IDEA. Compte: DYSOW Date: 26/05/15 Time: 18:06 To
 * change this template use File | Settings | File Templates.
 */
@Security.Authenticated(Secured.class)
public class MembreCtrl extends Controller {

	public static MembreManager manager = new MembreManager();
	public static Result index() {
		List<Membre> lesMembre = Membre.findAllMembres();
        Logger.info("Nombre de memres retrouvés : "+lesMembre.size());
		List<Localite> listLocalite = Localite.getListAllLocalites();
		List<Profil> listProfil = Profil.findAllProfils();
		Membre membre =new Membre();
		return ok(index.render(Compte.findByEmail(request().username()),
				lesMembre, listLocalite, listProfil, membre));
	}

	public static Result getMembreByLocaliteIdAndTypeLocalite(Long idLocalitte) {
		List<Membre> lesMembre = new ArrayList<Membre>();
		lesMembre = Localite.findMembresLocaliteById(idLocalitte);
		List<Localite> listLocalite = Localite.getListAllLocalites();
		List<Profil> listProfil = Profil.findAllProfils();
		Membre membre =new Membre();
		return ok(index.render(Compte.findByEmail(request().username()),
				lesMembre, listLocalite, listProfil,membre));
	}

	public static Result addMembre() {

        DynamicForm dynamicForm = form().bindFromRequest();
        Logger.info("Elements de la requete : " + dynamicForm.toString());
        CodeRetour retour = manager.addMembre(dynamicForm);

        return ok(Json.toJson(retour));
	}

	
	public static Result blockMembre(Long idMembre) {
		
		 return ok(Json.toJson(manager.blockMembre(idMembre)));

	}
	
	public static Result updateMembre() throws ParseException {
		DynamicForm dynamicForm = form().bindFromRequest();
        //todo tester si les unicités sont respectée (email, telephone etc... sur compte et membre)
        CodeRetour cr = manager.updateMembre(dynamicForm);
        //TODO gerer les codes retour au niveau des requetes ajax
        return ok(Json.toJson(cr));
	}
	
	private static void sendMailAskForConfirmation(Compte compte)
			throws EmailException, MalformedURLException {
		String subject = Messages.get("mail.confirm.subject");

		String urlString = "http://"
				+ Configuration.root().getString("server.hostname");
		urlString += "/confirm/" + compte.confirmationToken;
		URL url = new URL(urlString); // validate the URL, will throw an
										// exception if bad.
		String message = Messages.get("mail.confirm.message", url.toString(),
				compte.email, compte.email.split("@")[0]);

		Mail.Envelop envelop = new Mail.Envelop(subject, message, compte.email);
		Mail.sendMail(envelop);
	}

}
