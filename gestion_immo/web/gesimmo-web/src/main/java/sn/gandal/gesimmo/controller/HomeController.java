package sn.gandal.gesimmo.controller;


import java.util.ArrayList;
import java.util.List;

import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.gandal.gesimmo.form.EditLocalisationForm;
import sn.gandal.gesimmo.metier.services.IIndicateurMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.SiteLocalite;
import sn.gandal.gesimmo.modele.client.tools.LocalisationFormFilter;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;

@Controller
public class HomeController {

    @Autowired
    IGesimmoMetier metier;
    @Autowired
    ILocalisationMetier localisationMetier;
    @Autowired
    IIndicateurMetier indicateurMetier;
    @Autowired
    IGesimmoMetier gesimmoMetier;

    //TODO à l'accueil il faut envoyer un objet de type site

    @RequestMapping(value = "/n")
    public String homePage(Model model) {

        EditLocalisationForm localisationForm = new EditLocalisationForm();
        List<Localisation> locs = new ArrayList<Localisation>();
        locs.addAll(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        locs.add(0, new BatimentLocalite());
        
        localisationForm.setRattachements(locs);
        model.addAttribute("localisationForm", localisationForm);
        
        LocalisationFormFilter localisationFormFilter = new LocalisationFormFilter();
        localisationFormFilter.setTypeIncidentOuLocalite(SiteLocalite.getAllTypes());
        localisationFormFilter.setdT(gesimmoMetier.getTypeLicenceLocalite().get(0));
        localisationFormFilter.setdType(gesimmoMetier.getTypeLicenceLocalite());
        model.addAttribute("localisationFormFilter", localisationFormFilter);
        return "homepage";
    }


    @RequestMapping(value = "/forgotPassword")
    public String forgotPassword(Model model) {

        return "forgotPassword";
    }

    @RequestMapping(value = "/loginfailed")
    public String loginFailed(ModelMap model) {
        model.addAttribute("error", "Incorrect Username Or Password");
        model.addAttribute("subscriber", new Subscriber());
        return "homepage";
    }

    @RequestMapping(value = "/userslist")
    public String userListForm(Model model) {
        //pour initialiser le formulaire create user
        Subscriber subscriber = new Subscriber();
        subscriber.setRoles(metier.findAllRoles());

        model.addAttribute("subscriber", subscriber);


        //fin itialisation
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("users", metier.findAllUsersWithoutUserConnected(login));
        if (metier.findAllUsersWithoutUserConnected(login) != null) {
            model.addAttribute("nbUsers", metier.findAllUsersWithoutUserConnected(login).size() + 1);

        } else {
            model.addAttribute("nbUsers", 1);

        }
        // TODO : ici aussi, vous avez la liste de tous les utilisateurs, pas besoin de requeter la base à nouveau, passez par la methode static dans Activite
        model.addAttribute("nbUsersActif", metier.findUSersByEtat(1).size());
        model.addAttribute("nbUsersInactif", metier.findUSersByEtat(0).size());
        return "userslist";
    }
    

   

}
