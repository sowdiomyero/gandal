package controllers;

import models.*;
import models.utils.SmsManager;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static play.data.Form.form;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 03/06/15
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */

public class SmsPullPush extends Controller {

    public static Result pushSms() {

        Logger.info("Requete envoyée au runner ..... ");

        DynamicForm dynamicForm = form().bindFromRequest();
        String typeCible=  dynamicForm.get("typeCible").split(":")[0];
        int populationBetween=  Integer.parseInt(dynamicForm.get("between"));
        int populationAnd=  Integer.parseInt(dynamicForm.get("and"));
        int nbMilitants=  Integer.parseInt(dynamicForm.get("nbMilitants"));
        String message=  dynamicForm.get("message");

        List<Membre> membres = new ArrayList<>();

        membres=Localite.searchMembres(typeCible, populationBetween, populationAnd, nbMilitants);

       SmsManager.Envelop sms = new SmsManager.Envelop(message,membres);
        SmsManager.sendSms(sms);


        return ok(Json.toJson("Message en cours d'envoie ....."));
    }

    public static Result pullSms(String message, String numero, String password) {

        Logger.warn("Message SMS reçu depuis l'exterieur : "+message+" au numero suivant : "+numero);
        Logger.info("SMS reçu !! ");

        UserExterne client= UserExterne.getUserByPhoneNumber(numero);
        if(client == null)
            return ok("Ce numéro n'est associé à aucune localité ni à aucune équipe. Approchez vous de votre responsable politique.");

        Equipe equipe = client.getEquipe();
        if(equipe == null)
            return ok("Ce numéro n'est associé à aucune équipe ou commission. Approchez vous de votre responsable politique pour vous ajouter dans la plateforme.");

        Localite localite = client.getEquipe().getLocalite();
        if(localite == null)
            return ok("Vous êtes connu du système mais votre localité n'a pas été ajoutée au système. " +
                    "Approchez vous du responsable politique de votre localité "+ (equipe.getResponsable() != null ? equipe.getResponsable().getNomPrenom() : ""));


        Inscription inscription = new Inscription();

        inscription.setEquipe(equipe);
        inscription.setLocalite(localite);
        inscription.setUser(client);

        inscription.setMessageIncoming(message);
        String response = parseSms(message, numero, inscription);
        inscription.setMessageOut(response);

        try{
            inscription.save();
        }catch (Exception ex){
            Logger.info("Une exception s'est produite pendant l'ajout d'une inscription "+ex.getMessage());
        }


        return ok(response);
    }

    private static String parseSms(String sms, String numero, Inscription inscription) {
       String response = "";
       String [] msg = sms.split(" ");
       if(msg.length < 2){
           inscription.setEtat(Inscription.STATE.NOT_WELL_FORMED);
           inscription.setNbInscriptions(0L);
           response = "Le format du SMS n'est pas correct. Format attendu : INS valeur jjMMaaaa";
       }else{

           // verification des formats saisis
           try{
            int value = Integer.parseInt(msg[1]);
            int dateValue =0;
               if(msg.length>=3){
                  if (msg[2].length() != 8)
                      throw new Exception("La taille du parametre [date] n'est pas correcte ....");
                   dateValue = Integer.parseInt(msg[2]);
               }
           }catch (Exception ex){
               response = "Le format du SMS n'est pas correct. Format attendu :[ INS valeur jjMMaaaa ]. Les deuxieme et troisieme parametres doivent etre des nombres numeriques";
               inscription.setEtat(Inscription.STATE.NOT_WELL_FORMED);
               return response;
           }

           if(msg.length>=3) {
                   DateFormat sourceFormat = new SimpleDateFormat("ddMMyyyy");
                   String dateAsString =msg[2];
                   try {
                       Date date = sourceFormat.parse(dateAsString);
                       inscription.setDateInscription(date);
                   } catch (ParseException e) {
                       Logger.info("Une exception s'est produite pendant le parsing de la date [ "+msg[2]+" ]"+e.getMessage());
                       response = "Le format de la date n'est pas correct. Format attendu : INS valeur jjMMaaaa";
                       inscription.setEtat(Inscription.STATE.NOT_WELL_FORMED);
                   }
               }else{
               inscription.setDateInscription(new Date());
           }
           inscription.setNbInscriptions(Long.parseLong(msg[1]));

           response = "Merci pour votre message "+inscription.getUser().getNomPrenom()+". Vous avez declaré une inscription "+
                   " sur la localité [" +inscription.getLocalite().getNom()+"] pour la date du "+ (msg.length >= 3 ?inscription.getDateInscription().toLocaleString() : " Jour");
       }

        return response;
    }

}
