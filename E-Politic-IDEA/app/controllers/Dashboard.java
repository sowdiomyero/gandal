package controllers;

import models.*;
import models.utils.DateUtils;
import models.JourInscription;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.dashboard.dashboard;
import views.html.dashboard.index;

import java.math.BigDecimal;
import java.util.*;

/**
 * Compte: dysow
 * Date: 22/01/12
 */
@Security.Authenticated(Secured.class)
public class Dashboard extends Controller {

    public static Result index() {
        List<Membre> membres = Membre.findAllMembres();
        List<Localite> dpts = Localite.findDistinctLocalites();
        return ok(index.render(Compte.findByEmail(request().username()), membres, dpts));
    }

    public static Result displayDashboard() {

        List<Membre> membres = Membre.findAllMembres();
        List<Localite> dpts = Localite.findDistinctLocalites();
        List<JourInscription> inscriptions = getInscriptionsSemaine();
        return ok(dashboard.render(Compte.findByEmail(request().username()), membres, dpts, inscriptions));
    }

    public static Result sayHello() {
        List<Region> result = Region.findAllRegions();

        return ok(Json.toJson(result));
    }

    public static Result getRegionInscriptionStatistics() {
        List<Region> result = Region.findAllRegions();

        Map retour = new HashMap();
        for(int i =0; i< result.size(); i++){

        	Double[] values = new Double[3];
            values[0] = (double) result.get(i).getNbPopulation();
            values[1] =(double) result.get(i).getNbInscrits();
            values[2] = result.get(i).getNbObjectifs();
            Logger.info("Tableau des valeurs avant ajout dans la Map : "+values);
            retour.put(result.get(i).getNom(), values);
        }
        //String rez = ""+tabRegions+tabElecteurs+tabInscrits+tabPopulation;
        return ok(Json.toJson(retour));
    }

    public static Result getRegionObjectifsStatistics() {
        List<Region> result = Region.findAllRegions();

        Map retour = new HashMap();
        for(int i =0; i< result.size(); i++){

            Double[] values = new Double[3];
            values[0] = (double) result.get(i).getNbPopulation();
            values[1] = (double) result.get(i).getNbInscrits();
            values[2] =result.get(i).getNbObjectifs();
            Logger.info("Tableau des valeurs avant ajout dans la Map : "+values);
            retour.put(result.get(i).getNom(), values);
        }
        return ok(Json.toJson(retour));
    }

    public static Result getInscriptionBetweenTwoDates() {

        return ok(Json.toJson(getInscriptionsSemaine()));
    }


    public static Result getPourcentageInscriptionByRegion() {
        List<Region> result = Region.findAllRegions();

        Object [][] rez = new Object[result.size()][2];
        int totalInscription = getTotalInscription(result);

        for(int i =0; i< result.size(); i++){
            Object[] tab = new Object[2];
            double pourcentageRounded =0L;
            tab[0] = result.get(i).getNom();
            double pourcentage = (Double.valueOf(result.get(i).getNbInscrits())/Double.valueOf(totalInscription)) * 100;
            if(pourcentage > 0 ){
                BigDecimal bd = new BigDecimal(pourcentage);
                BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                pourcentageRounded =rounded.doubleValue();
                tab[1] = pourcentageRounded;
            } else{
                tab[1] = pourcentageRounded;
            }
           /* BigDecimal bd = new BigDecimal(pourcentage);
            BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            double pourcentageRounded =rounded.doubleValue();
            tab[1] = pourcentageRounded;*/

            Logger.info("Total des inscriptions de ["+result.get(i).getNom()+"] donne : ["+result.get(i).getNbInscrits()
                    +"] sur un total de "+totalInscription+" ==> % "+pourcentageRounded);

            rez[i] = tab;


        }
        //String rez = ""+tabRegions+tabElecteurs+tabInscrits+tabPopulation;
        return ok(Json.toJson(rez));
    }

    private static int getTotalInscription(List<Region> result) {
        int retour= 0;
        for(int i =0; i< result.size(); i++){
          retour += result.get(i).getNbInscrits();
        }
        Logger.info("Total des inscriptions de "+result.size()+" regions donne : "+retour);
        return retour;
    }

    private static List<JourInscription> getInscriptionsSemaine() {

        List<JourInscription> list = new ArrayList<JourInscription>();
        JourInscription ji = new JourInscription();
        //String [][] list01 = new String[5][2];
        String [] data = new String[2];

        // date 5
        String dt01 = DateUtils.getPreviousDateString(-4);
        String dt02 = DateUtils.getPreviousDateString(-3);
        String dateSemaine = getDayFromDate(dt01);
        long nbInscrits = Inscription.getNbInscriptionNotValidatedBetweenTwoDates(dt01, dt02);

        ji.setDay(dateSemaine);
        ji.setValue(String.valueOf(nbInscrits));
        list.add(ji);

        // date 4
        dt01 = DateUtils.getPreviousDateString(-3);
        dt02 = DateUtils.getPreviousDateString(-2);
        dateSemaine = getDayFromDate(dt01);
        nbInscrits = Inscription.getNbInscriptionNotValidatedBetweenTwoDates(dt01, dt02);

        ji = new JourInscription();
        ji.setDay(dateSemaine);
        ji.setValue(String.valueOf(nbInscrits));
        list.add(ji);
        // date 3
        dt01 = DateUtils.getPreviousDateString(-2);
        dt02 = DateUtils.getPreviousDateString(-1);
        dateSemaine = getDayFromDate(dt01);
        nbInscrits = Inscription.getNbInscriptionNotValidatedBetweenTwoDates(dt01, dt02);

        ji = new JourInscription();
        ji.setDay(dateSemaine);
        ji.setValue(String.valueOf(nbInscrits));
        list.add(ji);
        // date 2
        dt01 = DateUtils.getPreviousDateString(-1);
        dt02 = DateUtils.getPreviousDateString(0);
        dateSemaine = getDayFromDate(dt01);
        nbInscrits = Inscription.getNbInscriptionNotValidatedBetweenTwoDates(dt01, dt02);

        ji = new JourInscription();
        ji.setDay(dateSemaine);
        ji.setValue(String.valueOf(nbInscrits));
        list.add(ji);
        // date 1

        dt01 = DateUtils.getPreviousDateString(0);
        dt02 = DateUtils.getPreviousDateString(1);
        dateSemaine = getDayFromDate(dt01);
        nbInscrits = Inscription.getNbInscriptionNotValidatedBetweenTwoDates(dt01, dt02);

        ji = new JourInscription();
        ji.setDay(dateSemaine);
        ji.setValue(String.valueOf(nbInscrits));
        list.add(ji);

        return  list;
    }

    private static String getDayFromDate(String date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.getPreviousDateString(date));
        int day = cal.get(Calendar.DAY_OF_WEEK);
        Logger.info("gatDayFromDate : "+date+" ==> "+day);
        //switch (day){
        if(day == 2)
                return "Lundi";
        if(day == 3)
                return "Mardi";
        if(day == 4)
                return "Mercredi";
        if(day == 5)
                return "Jeudi";
        if(day == 6)
                return "Vendredi";
        if(day == 7)
                return "Samedi";
        if(day == 1)
                return "Dimanche";

        return  "";
    }

}
