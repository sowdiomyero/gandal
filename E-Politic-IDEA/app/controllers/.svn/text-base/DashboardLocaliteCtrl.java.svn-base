package controllers;

import java.util.*;
import java.util.Map.Entry;

import models.Compte;
import models.Inscription;
import models.Localite;
import models.Projet;
import models.Region;
import models.UserExterne;
import models.utils.DateUtils;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.localite.*;

@Security.Authenticated(Secured.class)
public class DashboardLocaliteCtrl extends Controller {

	public static Result dashboard() {
		return ok(dashboard.render(Compte.findByEmail(request().username())));
	}

	/********************************************/

	private static String getMonthName(int i) {

		String mois = "";

		switch (i) {
		case 1:
			mois = "Janvier";
			break;
		case 2:
			mois = "Février";
			break;
		case 3:
			mois = "Mars";
			break;
		case 4:
			mois = "Avril";
			break;
		case 5:
			mois = "Mai";
			break;
		case 6:
			mois = "Juin";
			break;
		case 7:
			mois = "Juillet";
			break;
		case 8:
			mois = "Aout";
			break;
		case 9:
			mois = "Septembre";
			break;
		case 10:
			mois = "Octobre";
			break;
		case 11:
			mois = "Novembre";
			break;
		case 12:
			mois = "Décembre";
			break;
		}

		return mois;
	}

	public static Result getLocaliteInscriptionStatistics() {
		Map<String, Long> hash = new LinkedHashMap<String, Long>();
		Long idlocalite = Compte.findByEmail(request().username())
				.getIdLocalite();

		Calendar cal = Calendar.getInstance();
		int m = cal.get(Calendar.MONTH) + 1;

		for (int j = 1; j <= 12; j++) {
			String mois = getMonthName(m);

			hash.put(mois,
					Inscription.getNbInscriptionLocaliteMonthly(idlocalite, m));
			m--;
			if (m == 0)
				m = 12;
		}

		return ok(Json.toJson(hash));
	}

	public static Result getLocaliteInscriptionByUserStatistics() {

		Long idlocalite = Compte.findByEmail(request().username())
				.getIdLocalite();
		HashMap<Long, Long> hash = Inscription.getNbInscriptionLocaliteByUserExt(idlocalite);
		String fullName;

		int n = hash.size();
		Object[][] rez = new Object[n][2];

		int i = 0;
		for (Entry<Long, Long> entry : hash.entrySet()) {
			Object[] tab = new Object[2];

			Long id = entry.getKey();
			Long som = entry.getValue();
			fullName = UserExterne.findUserExtById(id).getNomPrenom();
			tab[0] = fullName;
			tab[1] = som;
			rez[i] = tab;
			i++;
		}
		return ok(Json.toJson(rez));
	}

	public static Result getLocaliteCoutProjetStatistics() {
		Long idlocalite = Compte.findByEmail(request().username())
				.getIdLocalite();
		Localite loca = Localite.findLocaliteById(idlocalite);

		List<Projet> result = loca.getListProjets();

		Map retour = new HashMap();
		for (int i = 0; i < result.size(); i++) {

			Double[] values = new Double[2];
			values[0] = result.get(i).getCoutPrevisionel();
			values[1] = result.get(i).getValCoutReel();

			Logger.info("Tableau des valeurs avant ajout dans la Map : "
					+ values);
			retour.put(result.get(i).getNomProjet(), values);
		}

		return ok(Json.toJson(retour));
	}

	public static Result getLocaliteEmploiProjetStatistics() {
		Long idlocalite = Compte.findByEmail(request().username())
				.getIdLocalite();
		Localite loca = Localite.findLocaliteById(idlocalite);

		List<Projet> result = loca.getListProjets();

		Map retour = new HashMap();
		for (int i = 0; i < result.size(); i++) {

			int[] values = new int[2];
			values[0] = result.get(i).getEmploiPrevisionel();
			values[1] = result.get(i).getValEmploiReel();

			Logger.info("Tableau des valeurs avant ajout dans la Map : "
					+ values);
			retour.put(result.get(i).getNomProjet(), values);
		}

		return ok(Json.toJson(retour));
	}

	public static Result getLocaliteProjetTauxAvStatistics() {
		Long idlocalite = Compte.findByEmail(request().username())
				.getIdLocalite();
		Localite loca = Localite.findLocaliteById(idlocalite);
		List<Projet> result = loca.getListProjets();
		

		Object[][] rez = new Object[result.size()][2];

		for (int i = 0; i < result.size(); i++) {
			Object[] tab = new Object[2];
			tab[0] = result.get(i).getNomProjet();
			tab[1] = result.get(i).getValNiveauExecution();
			rez[i] = tab;

		}
		return ok(Json.toJson(rez));
	}
	
//	public static Result getLocaliteProjetTauxDecaissementStatistics() {
//		Long idlocalite = Compte.findByEmail(request().username())
//				.getIdLocalite();
//		Localite loca = Localite.findLocaliteById(idlocalite);
//		List<Projet> result = loca.getListProjets();
//		
//
//		Object[][] rez = new Object[result.size()][2];
//
//		for (int i = 0; i < result.size(); i++) {
//			Object[] tab = new Object[2];
//			tab[0] = result.get(i).getNomProjet();
//			tab[1] = result.get(i).getTauxDecaissement();
//			rez[i] = tab;
//
//		}
//		return ok(Json.toJson(rez));
//	}
//	
	
}
