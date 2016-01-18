/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.controller;

import sn.gandal.gesimmo.metier.services.IJournalMetier;
import sn.gandal.gesimmo.metier.services.IProgrammeMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import sn.gandal.gesimmo.metier.services.IUserActiviteMetier;
import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import sn.gandal.gesimmo.metier.services.IProjetMetier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.EtapeForm;
import sn.gandal.gesimmo.form.EditLocalisationForm;
import sn.gandal.gesimmo.form.JournalForm;
import sn.gandal.gesimmo.form.ProjetForm;
import sn.gandal.gesimmo.form.ProjetProgrammeForm;
import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.form.UserActiviteForm;
import sn.gandal.gesimmo.metier.*;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.entities.Etape;
import sn.gandal.gesimmo.modele.client.entities.Programme;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.Projet;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.entities.UserActivite;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;

/**
 *
 * @author msall
 */
@Controller
public class ProjetController {

    @Autowired
    IActiviteMetier metier;
    @Autowired
    IProjetMetier projetMetier;

    @Autowired
    ILocalisationMetier localisationMetier;
    @Autowired
    IActiviteMetier activiteMetier;
    @Autowired
    IProgrammeMetier programmeMetier;
    @Autowired
    IJournalMetier journalMetier;
    @Autowired
    IUserActiviteMetier metierUserActivite;
    @Autowired
    IGesimmoMetier metiergesimmo;

    private long idProjetSaisi = 0;
    private List<Projet> projetsRcherche = new ArrayList<Projet>();

    Logger LOG = Logger.getLogger(ProjetController.class.getName());

    // TODO : utiliser la classe RequestMappingUtils pour definir les routes
    @RequestMapping(value = "/projetlist")
    public String projetListForm(Model model) {
        //pour initialiser le formulaire create projet
        ProjetForm projetForm = new ProjetForm();
        model.addAttribute("projetForm", projetForm);
        //fin itialisation
        List<Projet> projets = projetMetier.getAllProjets();
//        List<Activite> activites = metier.getAllActivite();
        if (projetsRcherche.isEmpty()) {
            model.addAttribute("projets", projets);
        } else {
            model.addAttribute("projets", projetsRcherche);
            projetsRcherche = new ArrayList<Projet>();
        }
        //TODO : ne pas requeter directement mais passer par un filter ==> Voir dans Activite.filterActivitiesByState()
        //Nbre de projet par etat
//        model.addAttribute("nbr_projet_termine", projetMetier.getProjetByEtat(ParamEntity.ACTIVITE_ETAT_TERMINE).size());
        model.addAttribute("nbr_projet_termine", Activite.filterActivitiesByState(projets, ParamEntity.ACTIVITE_ETAT_TERMINE).size());
        model.addAttribute("nbr_projet_en_cours", Activite.filterActivitiesByState(projets, ParamEntity.ACTIVITE_ETAT_DEMARRE).size());
        model.addAttribute("nbr_projet_suspendu", Activite.filterActivitiesByState(projets, ParamEntity.ACTIVITE_ETAT_SUSPENDU).size());
        model.addAttribute("nbr_projet_abandonne", Activite.filterActivitiesByState(projets, ParamEntity.ACTIVITE_ETAT_ABANDONNE).size());

        // Pour la recherche avancée d'un projet : ces valeurs sont utilisées dans le select etat pour la recherche avancée
        model.addAttribute("etat_desactive", ParamEntity.ETAT_DESACTIVE);
        model.addAttribute("etat_abandonne", ParamEntity.ACTIVITE_ETAT_ABANDONNE);
        model.addAttribute("etat_creation", ParamEntity.ETAT_ACTIVE);
        model.addAttribute("etat_en_cours", ParamEntity.ACTIVITE_ETAT_DEMARRE);
        model.addAttribute("etat_suspendu", ParamEntity.ACTIVITE_ETAT_SUSPENDU);
        model.addAttribute("etat_redemarre", ParamEntity.ACTIVITE_ETAT_REDEMARRE);
        model.addAttribute("etat_termine", ParamEntity.ACTIVITE_ETAT_TERMINE);

        return "projetlist";
    }

    //Récupération du projet a éditer
    @RequestMapping(value = "/getProjet", method = RequestMethod.GET)
    public @ResponseBody
    ProjetForm editProjet(@RequestParam(value = "idActivite") Long idActivite) {

        ProjetForm editProjet = new ProjetForm();
        Projet projet = (Projet) projetMetier.getProjetById(idActivite);
        if (projet == null) {
            editProjet.setMsg("Projet null");
            editProjet.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editProjet;
        }

        idProjetSaisi = idActivite;
        editProjet.setNomActivite(projet.getNomActivite());
        editProjet.setDescription(projet.getDescription());
        editProjet.setDateDebPrevu(projet.getDateDebPrevu().toString());
        editProjet.setDateFinPrevu(projet.getDateFinPrevu().toString());

        editProjet.setBudgetPrevu(projet.getBudgetPrevu());
        editProjet.setNbreEmploiPrevu(projet.getNbreEmploiPrevu());

        editProjet.setMsg("Visualisation du projet passée avec succés");
        return editProjet;
    }

    @RequestMapping(value = "/editProjet", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ProjetForm updateProjet(@RequestBody @Valid ProjetForm projetForm, HttpServletRequest request, Model m) throws ParseException {
        if (projetMetier.isProjetNameExist(projetForm.getNomActivite(), idProjetSaisi)) {
            projetForm.setMsg("Un projet existe avec le meme nom  [" + projetForm.getNomActivite() + "] vous devez utiliser un autre nom");

            projetForm.setResultat(projetForm.RETOUR_OBJECTNAME_INVALID);
            return projetForm;
        }

        try {
            Projet projet = (Projet) projetMetier.getProjetById(idProjetSaisi);
            projet.setNomActivite(projetForm.getNomActivite());
            projet.setDescription(projetForm.getDescription());

            projet.setBudgetPrevu(projetForm.getBudgetPrevu());
            projet.setNbreEmploiPrevu(projetForm.getNbreEmploiPrevu());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDeb = sdf.parse(projetForm.getDateDebPrevu());
            Date dateFin = sdf.parse(projetForm.getDateDebPrevu());
            projet.setDateDebPrevu(dateDeb);
            projet.setDateFinPrevu(dateFin);

            metier.updateActivite(projet);
            projetForm = new ProjetForm();
            projetForm.setResultat(ProjetForm.RETOUR_OK);
            projetForm.setMsg("Projet modifié avec succès avec les informations suivantes : [ Nom =  " + projet.getNomActivite() + " ]");
            m.addAttribute("projetForm", projetForm);
        } catch (Exception ex) {
            projetForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            projetForm.setMsg("Une erreur est survenue pendant le traitement!! ex: " + ex.getMessage());
        } finally {
            return projetForm;
        }
    }

    @RequestMapping(value = "projet/deleteProjet", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteProjet(@RequestParam(value = "idActivite") Long idActivite, Model model) {

        BasicResponse response = new BasicResponse();
        Projet projetToFind;
        try {
            projetToFind = projetMetier.getProjetById(idActivite);

            if (projetToFind != null) {
                /*suppression définitive
                 metier.deleteActivite(projetToFind);
                 */
                //Désactivation du projet
                projetToFind.setEtat(ParamEntity.ETAT_DESACTIVE);
                metier.updateActivite(projetToFind);

                response.setMsg("Le projet [" + projetToFind.getNomActivite().toUpperCase() + " ] a été supprimé de la base de données avec succes");
                response.setResultat(BasicResponse.RETOUR_OK);
            } else {

                response.setMsg("Vous ne pouvez pas supprimer ce projet");
                response.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
            }
        } catch (Exception ex) {
            response.setResultat(BasicResponse.RETOUR_EXCEPTION);
            response.setMsg("Une erreur est survenue pendant le traitement!!= " + ex.getMessage());
            LOG.info("++++++++++++++++++++++" + ex.getMessage());
        } finally {
            return response;
        }

    }

    @RequestMapping(value = "/addProjet", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ProjetForm addProjet(@RequestBody @Valid ProjetForm projetForm, HttpServletRequest request, Model m) throws ParseException {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++ DEscription projet" + projetForm.getDescription());
        try {
            Projet projet = new Projet();
            projet.setNomActivite(projetForm.getNomActivite());
            projet.setDescription(projetForm.getDescription());
            projet.setType("PROJET");
            projet.setBudgetPrevu(projetForm.getBudgetPrevu());
            projet.setBudgetPrevu(projetForm.getNbreEmploiPrevu());
            projet.setEtat(ParamEntity.ETAT_ACTIVE);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            projet.setDateDebPrevu(sdf.parse(projetForm.getDateDebPrevu()));
            projet.setDateFinPrevu(sdf.parse(projetForm.getDateFinPrevu()));
            if (projet.getDateFinPrevu().compareTo(projet.getDateDebPrevu()) < 0) {
                projetForm.setResultat(Subscriber.RETOUR_EXCEPTION);
                projetForm.setMsg("La date fin prévu ne doit pas etre inférieur a la date début");
                return projetForm;
            }

            metier.addActivite(projet);

            projetForm = new ProjetForm();
            projetForm.setResultat(ProjetForm.RETOUR_OK);
            projetForm.setMsg("ProjetForm crée avec succès avec les informations suivantes : [ Nom =  " + projet.getNomActivite() + " ]");
            m.addAttribute("projetForm", projetForm);
        } catch (Exception ex) {
            projetForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            projetForm.setMsg("Une erreur est survenue pendant le traitement!!" + ex.getMessage());
        } finally {
            return projetForm;
        }

    }

    @RequestMapping(value = "/ficheProjet", method = RequestMethod.GET)
    String showFicheProjet(Model model, @RequestParam(value = "idProjet") Long idProjet) {
        idProjetSaisi = idProjet;
        Projet projet = projetMetier.getProjetById(idProjet);
        JournalForm journalForm = new JournalForm();
        journalForm.setIdActivite(idProjet);
        EditLocalisationForm editLocalisationForm = new EditLocalisationForm();

        model.addAttribute("projet", projet);
        model.addAttribute("journalForm", journalForm);
        EtapeForm etapeForm = new EtapeForm();
        etapeForm.setIdParent(idProjet);
        model.addAttribute("etapeForm", etapeForm);
        model.addAttribute("userActiviteForm", new UserActiviteForm());
        List<User> users = metiergesimmo.findAllUsers();
        if (users != null) {
            model.addAttribute("users", users);
        }else{
            model.addAttribute("users", new ArrayList<User>());
        }

        List<Etape> etapes = new ArrayList<Etape>();

        if ((projet.getActiviteList() != null) && (!projet.getActiviteList().isEmpty())) {
            for (Activite act : projet.getActiviteList()) {
                if (act.getEtat() != ParamEntity.ETAT_DESACTIVE) {
                    Etape e = (Etape) act;
                    etapes.add(e);
                }
            }
        }

        model.addAttribute("activitesProjet", etapes);

        model.addAttribute("journals", journalMetier.findJournalsByIdActivite(idProjet));

        model.addAttribute("editLocalisationForm", editLocalisationForm);
        model.addAttribute("listLocalite", localisationMetier.findAllTLocaliteByEtat(Localisation.ETAT.FONCTIONNEL));
        model.addAttribute("programmes", programmeMetier.getAllProgrammes());

        return "ficheProjet";
    }

    @RequestMapping(value = "/rechercherProjet", method = RequestMethod.POST)
    public @ResponseBody
    ProjetForm rechercherProjet(@RequestBody @Valid ProjetForm projetForm, HttpServletRequest request, Model m) throws ParseException {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++ BEGINING METHOD rechercherProjet()");
        int etatProjetSearch;
        String nomProjetSearch = "%%";
        String dateDebProjetSearch = "2000-11-29";
        Date d = new Date();
        String dateFinProjetSearch = "" + d.getYear() + "-" + d.getMonth() + "-" + d.getDay();
        double budgetPrevuProjetSearch = 2100000000;

        if (projetForm.getDateDebPrevu() != null) {
            dateDebProjetSearch = projetForm.getDateDebPrevu();
            System.out.println("++++++++++++++  DATE DEB CHOISIE : " + projetForm.getDateDebPrevu());
        }
        if (projetForm.getDateFinPrevu() != null) {
            dateFinProjetSearch = projetForm.getDateFinPrevu();
        }
        if (projetForm.getNomActivite() != null) {
            nomProjetSearch = "%" + projetForm.getNomActivite() + "%";
        }
        if (projetForm.getBudgetPrevu() != null) {
            budgetPrevuProjetSearch = projetForm.getBudgetPrevu();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDeb = sdf.parse(dateDebProjetSearch);
        Date dateFin = sdf.parse(dateFinProjetSearch);

        LOG.info("=============>" + dateDeb);
        LOG.info("=============>" + dateFin);

        if (dateDeb.after(dateFin)) {
            projetForm.setResultat(ProjetForm.RETOUR_DEMANDE_ECHEC);
            projetForm.setMsg("La date début doit etre inférieure à la date fin !!!");
            return projetForm;
        }

        try {
            List<Projet> projets = new ArrayList<Projet>();
            if (projetForm.getEtat() == ParamEntity.ETAT_DESACTIVE) {
                System.out.println("++++++++++++++   BEGINING METHOD getProjetsByRechercheAvanceeAllEtat(dateDeb, dateFin, nomProjetSearch, budgetPrevuProjetSearch)");
                projets = projetMetier.getProjetsByRechercheAvanceeAllEtat(dateDeb, dateFin, nomProjetSearch, budgetPrevuProjetSearch);
            } else {
                etatProjetSearch = projetForm.getEtat();
                System.out.println("++++++++++++++   BEGINING METHOD getProjetsByRechercheAvancee(etatProjetSearch, dateDeb, dateFin, nomProjetSearch, budgetPrevuProjetSearch)");
                projets = projetMetier.getProjetsByRechercheAvancee(etatProjetSearch, dateDeb, dateFin, nomProjetSearch, budgetPrevuProjetSearch);
            }
            projetsRcherche = projets;
            System.out.println("++++++++++++++  FIN METHODE ACCES A LA BD");

            projetForm = new ProjetForm();
            projetForm.setResultat(ProjetForm.RETOUR_OK);
            if (!projets.isEmpty()) {
                projetForm.setMsg("Votre recherche est positive !!!");
                System.out.println("++++++++++++++  RESULTAT RECHERCHE : " + projets.size() + " TROUVES");
            } else {
                projetForm.setMsg("Aucun élément trouvé !!!");
                System.out.println("++++++++++++++  RESULTAT RECHERCHE : AUCUN ELEMENT TROUVE");
            }
            m.addAttribute("projets", projets);
//            Map map = new HashMap();
//            map.put("projets", projets);
//            map.put("projetForm", projetForm);
//            m.mergeAttributes(map);

            m.addAttribute("projetForm", projetForm);
        } catch (Exception ex) {
            projetForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            projetForm.setMsg("Une erreur est survenue pendant le traitement!!" + ex.getMessage());
        } finally {
            return projetForm;
        }

    }

    @RequestMapping(value = "/affectProjectToProgram", method = RequestMethod.POST)
    public @ResponseBody
    ProjetProgrammeForm addProjectToProgram(@RequestBody @Valid ProjetProgrammeForm projetProgrammeForm, HttpServletRequest request, Model m) throws ParseException {

        try {
            Long idProjet = projetProgrammeForm.getIdProjet();
            Long idProgramme = projetProgrammeForm.getIdProgramme();
            Programme programme = programmeMetier.getProgrammeById(idProgramme);
            Projet projet = projetMetier.getProjetById(idProjet);
            System.out.println("-------------------------------------");
            System.out.println("-------------------------------------");
            System.out.println("-------------------------------------");
            System.out.println("Nom programme: " + programme.getNomActivite() + "Nom Projet: " + projet.getNomActivite());
            System.out.println("-------------------------------------");
            System.out.println("-------------------------------------");
            System.out.println("-------------------------------------");
            projet.setParent(programme);
            activiteMetier.updateActivite(projet);
            projetProgrammeForm.setResultat(projetProgrammeForm.RETOUR_OK);
            projetProgrammeForm.setMsg("projet  affecté avec succès");
        } catch (Exception ex) {
            projetProgrammeForm.setResultat(projetProgrammeForm.RETOUR_EXCEPTION);
            projetProgrammeForm.setMsg("Une erreur est survenue pendant le traitement!!" + ex.getMessage());
        } finally {
            return projetProgrammeForm;
        }

    }

    @RequestMapping(value = "/affecterProjetLocalite", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EditLocalisationForm affecterProjetLocalite(@RequestBody @Valid EditLocalisationForm editLocalisationForm, HttpServletRequest request) {
        System.out.println("-----------------" + editLocalisationForm.getIdLocalisation());
        try {
            Localisation localisation = localisationMetier.getLocalisationById(editLocalisationForm.getIdLocalisation());
            Projet projet = projetMetier.getProjetById(idProjetSaisi);
            List<Localisation> listlocalisationProjet = projet.getLocalisations();
            for (Localisation l : listlocalisationProjet) {
                if (l.getIdLocalisation() == localisation.getIdLocalisation()) {
                    editLocalisationForm.setMsg("Le projet " + projet.getNomActivite() + " existe déja dans cette localité");
                    editLocalisationForm.setResultat(Subscriber.RETOUR_OK);
                    return editLocalisationForm;
                }
            }
            projet.getLocalisations().add(localisation);
            metier.updateActivite(projet);
            editLocalisationForm.setMsg("Modification passée  avec succés");
            editLocalisationForm.setResultat(Subscriber.RETOUR_OK);
        } catch (Exception ex) {
            editLocalisationForm.setResultat(BasicResponse.RETOUR_EXCEPTION);
            editLocalisationForm.setMsg("Une erreur est survenue pendant le traitement!!= " + ex.getMessage());
            LOG.info("++++++++++++++++++++++" + ex.getMessage());
        } finally {
            return editLocalisationForm;
        }

    }

    @RequestMapping(value = "/affecterProjetPersonne", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserActiviteForm affecterProjetLocalite(@RequestBody @Valid UserActiviteForm userActiviteForm, HttpServletRequest request, Model model) {
        System.out.println("-----------------" + userActiviteForm.getIdUser());
        User user = metiergesimmo.findUserById(userActiviteForm.getIdUser());
            Projet projet = projetMetier.getProjetById(idProjetSaisi);
        if(! isResponsableExiste(projet)){
            userActiviteForm.setMsg("Le projet a déja un responsable !");
            userActiviteForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            return userActiviteForm;
        }
        try {
            UserActivite userActivite = new UserActivite();
            userActivite.setActivite(projet);
            userActivite.setFonction(userActiviteForm.getFonction());
            userActivite.setUser(user);
            metierUserActivite.addUserActivite(userActivite);

            userActiviteForm.setMsg("Affectation est effectuée  avec succés");
            userActiviteForm.setResultat(Subscriber.RETOUR_OK);

            model.addAttribute("userActiviteForm", new UserActiviteForm());
        } catch (Exception ex) {
            userActiviteForm.setResultat(BasicResponse.RETOUR_EXCEPTION);
            userActiviteForm.setMsg("Une erreur est survenue pendant le traitement!!= " + ex.getMessage());
            LOG.info("++++++++++++++++++++++" + ex.getMessage());
        } finally {
            return userActiviteForm;
        }

    }
    
    public boolean isResponsableExiste(Projet projet){
        boolean trouve = false;
        if((projet.getUserActivite() == null) || (projet.getUserActivite().isEmpty())){
            return false;
        }else{
            int i = 0;
            while ((! trouve) && (i<projet.getUserActivite().size())){
                trouve = projet.getUserActivite().get(i).getFonction().equalsIgnoreCase("Responsable");
                i++;
            }
        }
        return trouve;
    }

}
