package views.forms;

import models.*;
import models.utils.AppException;
import models.utils.Hash;
import play.data.DynamicForm;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 11/06/15
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
public class AddMembreForm {
    DynamicForm dynamicForm;

    String nom ;
    String prenom;
    String sexe ;
    String email ;
    String categorie ;
    String telephone1 ;
    String telephone2 ;
    String profession ;

    String accces;

    String profil ;

    String localite ;

    Long idLocalite = Long.parseLong(localite.split(":")[1]);

    public AddMembreForm(DynamicForm form) {
        this.dynamicForm = form;

        String nom = dynamicForm.get("nom");
        String prenom = dynamicForm.get("prenom");
        String sexe = dynamicForm.get("sexe");
        String email = dynamicForm.get("email");
        String categorie = dynamicForm.get("categorie");
        String telephone1 = dynamicForm.get("telephone");
        String telephone2 = dynamicForm.get("telephone2");
        String profession = dynamicForm.get("profession");
        String accces = dynamicForm.get("accces");
        String profil = dynamicForm.get("profil");
        String localite = dynamicForm.get("localite"); // 2 split
    }

    public Membre getMembre(){

        Membre membre = new Membre();
        membre.setNom(nom);
        membre.setPrenom(prenom);
        membre.setEmail(email);
        membre.setSexe(sexe);
        membre.setCategorie(Categorie.valueOf(categorie));
        membre.setTelephone(telephone1);
        membre.setTelephone1(telephone2);
        membre.setProfession(profession);


        return membre;
    }

    public Localite getLoclaite(){
        Long idLocalite = Long.parseLong(localite.split(":")[1]);
        Localite loc = null;
        if (idLocalite > 0) {
            loc = Localite.findLocaliteById(idLocalite);
        }

        return loc;
    }
    public Compte getCompte(Membre membre){

        Compte compte = new Compte();
        if (accces != null
                && (accces.equalsIgnoreCase("on") || accces
                .equalsIgnoreCase("1"))) {

            try {
                compte.email = email;
                compte.fullname = nom.toUpperCase() + " " + prenom;
                compte.passwordHash = Hash.createPassword(email.split("@")[0]);
                compte.confirmationToken = UUID.randomUUID().toString();
                //compte.save();
                membre.setCompte(compte);
                Long idProfil = Long.parseLong(profil.split(":")[1]);
                if (idProfil > 0) {
                    Profil pf = Profil.findProfilById(idProfil);
                    membre.addProfil(pf);
                } else {
                    membre.addProfil(Profil
                            .findProfilByName(Profil.CODE.RESPONSABLE.name()));
                }
               // membre.update();
               // sendMailAskForConfirmation(compte);
            } catch (AppException e) {
                e.printStackTrace();

            }
        }
        return compte;
    }
}
