package sn.gandal.gesimmo.controller.v1.admin;


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


@Controller(value = "homeV1Controller")
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

    @RequestMapping(value = "/home/index")
    public String indexPage(Model model) {

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
        return "index";
    }

    @RequestMapping(value = "/")
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
        return "index";
    }


    @RequestMapping(value = "/home/forgotPassword")
    public String forgotPassword(Model model) {
        return "forgotPassword";
    }

    @RequestMapping(value = "/home/loginfailed")
    public String loginFailed(ModelMap model) {
        model.addAttribute("error", "Incorrect Username Or Password");
        model.addAttribute("subscriber", new Subscriber());
        return "index";
    }
    
    @RequestMapping(value = "/home/help")
    public String help(ModelMap model) {
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
        return "index";
    }

    
    

   

}
