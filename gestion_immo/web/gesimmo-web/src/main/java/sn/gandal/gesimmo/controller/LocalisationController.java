package sn.gandal.gesimmo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import sn.gandal.gesimmo.form.*;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.gandal.gesimmo.dto.BasicResponse;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import static sn.gandal.gesimmo.controller.ProgrammeController.logger;

import sn.gandal.gesimmo.metier.services.IIndicateurMetier;
import sn.gandal.gesimmo.metier.services.IProjetMetier;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.LocalisationIndicateur;
import sn.gandal.gesimmo.modele.client.entities.ObjetIncident;
import sn.gandal.gesimmo.modele.client.entities.BatimentLocalite;
import sn.gandal.gesimmo.modele.client.entities.Compte;
import sn.gandal.gesimmo.modele.client.entities.SiteLocalite;
import sn.gandal.gesimmo.modele.client.entities.TableConfig;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.tools.LocalisationFormFilter;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;

/**
 *
 * @author ddiaw
 */
@Controller
public class LocalisationController {

    @Autowired
    ILocalisationMetier localisationMetier;

    @Autowired
    IProjetMetier projetMetier;

    @Autowired
    IGesimmoMetier gesimmoMetier;

    @Autowired
    IIndicateurMetier IndicateurMetier;

    private long idLocalisationSaisi = 0;
    private String DTypeSelectionne = null;

    Logger LOG = Logger.getLogger(LocalisationController.class.getName());

    @RequestMapping(value = "/localisationList")
    public String localisationListForm(Model model) {
        List<Localisation> elements = localisationMetier.findLocalisationByEtat(Localisation.ETAT.FONCTIONNEL);
        model.addAttribute("localisations", elements);
        if(elements.size()>0)
        model.addAttribute("localisationSelected", elements.get(0));
        LocalisationFormFilter localisationFormFilter;
        localisationFormFilter = new LocalisationFormFilter();
        localisationFormFilter.setdType(gesimmoMetier.getTypeLicenceLocalite());
        model.addAttribute("localisationFormFilter", localisationFormFilter);      

        getCurrentUserName();
        return "localisationList";
    }

    //TODO ne pas utiliser l'entité au niveau de la couche web
    @RequestMapping(value = "/localisation/{id}", method = RequestMethod.GET)
    public String getLolcalisation(@PathVariable(value = "id") Long idLocalisation, Model model) {
       EditLocalisationForm formBinding = new EditLocalisationForm();
       //1 - peuler l'objet dto qui devra contenir toutes les informations ncessaires
       
       //2 - Pour assurer la compatibilité ascendante, mainetenir l'objet localisationSelected avec le type actuel
       
       //3 - Verifier l'état du formulaire
       
       model.addAttribute("localisationSelected", localisationMetier.getLocalisationById(idLocalisation));
       
       model.addAttribute("responsables", gesimmoMetier.findAllResponsables());
        
       return "fragment/ficheLocalisation";
    }
    
    @RequestMapping(value = "/getLocalisation", method = RequestMethod.GET)
    public @ResponseBody
    EditLocalisationForm getLolcalisation(@RequestParam(value = "idLocalisation") Long idLocalisation, @RequestParam(value = "dtype") String dtype) {

        EditLocalisationForm editLolcalisation = new EditLocalisationForm();
        if (dtype.equals(TableConfig.DTYPE_LOCALITE)) {
            BatimentLocalite localisation = new BatimentLocalite();
            DTypeSelectionne = TableConfig.DTYPE_LOCALITE;
            localisation = (BatimentLocalite) localisationMetier.getLocalisationById(idLocalisation);
            if (localisation == null) {
                editLolcalisation.setMsg("Element introuvable.");
                editLolcalisation.setResultat(BasicResponse.RETOUR_EXCEPTION);
                return editLolcalisation;
            }

            idLocalisationSaisi = idLocalisation;

            editLolcalisation.setNom(localisation.getNomLocalisable());
            editLolcalisation.setDescription(localisation.getDescription());
            editLolcalisation.setLongitude(localisation.getLongitude());
            editLolcalisation.setLatitude(localisation.getLatitude());
            editLolcalisation.setType(localisation.getTypeToString());
            editLolcalisation.setGravite(null);
            editLolcalisation.setTypeIncidentOuLocalite(null);
            editLolcalisation.setTypeIncidentOuLocalite(BatimentLocalite.getAllTypes());
            if (localisation.getParentLocalisation() != null) {
                editLolcalisation.setRattachement(localisation.getParentLocalisation().toString());
            }
            editLolcalisation.setMsg("Visualisation de l'objet localisable passée avec succés");
            return editLolcalisation;
        }
        if (dtype.equals(TableConfig.DTYPE_INCIDENT)) {
            ObjetIncident localisation ;//= new ObjetIncident();
            DTypeSelectionne = TableConfig.DTYPE_INCIDENT;
            localisation = (ObjetIncident) localisationMetier.getLocalisationById(idLocalisation);
            if (localisation == null) {
                editLolcalisation.setMsg("Element introuvable.");
                editLolcalisation.setResultat(BasicResponse.RETOUR_EXCEPTION);
                return editLolcalisation;
            }

            idLocalisationSaisi = idLocalisation;

            editLolcalisation.setNom(localisation.getNomLocalisable());
            editLolcalisation.setDescription(localisation.getDescription());
            editLolcalisation.setLongitude(localisation.getLongitude());
            editLolcalisation.setLatitude(localisation.getLatitude());
            editLolcalisation.setType(localisation.getTypeToString());
            editLolcalisation.setGravite(localisation.getGravite());
            editLolcalisation.setTypeIncidentOuLocalite(null);
            editLolcalisation.setTypeIncidentOuLocalite(ObjetIncident.getAllTypes());
            if (localisation.getParentLocalisation() != null) {
                editLolcalisation.setRattachement(localisation.getParentLocalisation().toString());
            }
            editLolcalisation.setMsg("Visualisation de l'objet localisable passée avec succés");
            return editLolcalisation;
        }
        editLolcalisation.setMsg("TYPE INDISPONIBLE");
        return editLolcalisation;
    }

    @RequestMapping(value = "/editLocalisation", method = RequestMethod.POST)
    public @ResponseBody
    EditLocalisationForm editLolcalisation(@RequestBody @Valid EditLocalisationForm editLocaliteForm, HttpServletRequest request) {

        String nom = editLocaliteForm.getNom();
        String desc = editLocaliteForm.getDescription();
        String longitude = editLocaliteForm.getLongitude();
        String latitude = editLocaliteForm.getLatitude();
        String type = editLocaliteForm.getType();
        String gravite = editLocaliteForm.getGravite();
        String rattachement = editLocaliteForm.getRattachement();
        Long idRatt = 0L;
        if (!rattachement.equals("")) {
            idRatt = Long.parseLong(rattachement);
        }

        if (DTypeSelectionne.equals(TableConfig.DTYPE_LOCALITE)) {
            BatimentLocalite editLocalisation = (BatimentLocalite) localisationMetier.getLocalisationById(idLocalisationSaisi);
            String nomLocSelected = editLocalisation.getNomLocalisable();
            String descLocSelected = editLocalisation.getDescription();
            String longLocSelected = editLocalisation.getLongitude();
            String latLocSelected = editLocalisation.getLatitude();
            String typeLocSelected = editLocalisation.getTypeToString();
            String rattachementSelected = "";
            Long idRattSelected = 0L;
            if (editLocalisation.getParentLocalisation() != null) {
                rattachementSelected = editLocalisation.getParentLocalisation().toString();
                idRattSelected = editLocalisation.getParentLocalisation().getIdLocalisation();
            }

            if (!(nom.equals(nomLocSelected)) || !(desc.equals(descLocSelected)) || !(longitude.equals(longLocSelected)) || !(latitude.equals(latLocSelected)) || !(type.equals(typeLocSelected)) || (idRatt != idRattSelected)) {
                Localisation locFind = null;
                if (!rattachement.equals("")) {
                    locFind = localisationMetier.getLocalisationById(idRatt);
                }
                editLocalisation.setNomLocalisable(nom);
                editLocalisation.setDescription(desc);
                editLocalisation.setLongitude(longitude);
                editLocalisation.setLatitude(latitude);
                editLocalisation.setType(type);
                editLocalisation.setParentLocalisation(locFind);
                localisationMetier.updateLocalisation(editLocalisation);
                editLocaliteForm.setMsg("Modification passée  avec succés");
                editLocaliteForm.setResultat(Subscriber.RETOUR_OK);
                return editLocaliteForm;
            } else {
                editLocaliteForm.setMsg("Aucune modification  des informations opérée");
                editLocaliteForm.setResultat(Subscriber.RETOUR_OK);
                return editLocaliteForm;
            }
        }

        if (DTypeSelectionne.equals(TableConfig.DTYPE_INCIDENT)) {
            ObjetIncident editLocalisation = (ObjetIncident) localisationMetier.getLocalisationById(idLocalisationSaisi);
            String nomLocSelected = editLocalisation.getNomLocalisable();
            String descLocSelected = editLocalisation.getDescription();
            String longLocSelected = editLocalisation.getLongitude();
            String latLocSelected = editLocalisation.getLatitude();
            String typeLocSelected = editLocalisation.getTypeToString();
            String graviteLocSelected = editLocalisation.getGravite();
            String rattachementSelected = "";
            if (editLocalisation.getParentLocalisation() != null) {
                rattachementSelected = editLocalisation.getParentLocalisation().toString();
            }

            if (!(nom.equals(nomLocSelected)) || !(desc.equals(descLocSelected)) || !(longitude.equals(longLocSelected)) || !(latitude.equals(latLocSelected)) || !(type.equals(typeLocSelected)) || !(rattachement.equals(rattachementSelected)) || !(gravite.equals(graviteLocSelected))) {
                Localisation locFind = null;
                if (!rattachement.equals("")) {
                    locFind = localisationMetier.getLocalisationById(idRatt);
                }
                editLocalisation.setNomLocalisable(nom);
                editLocalisation.setDescription(desc);
                editLocalisation.setLongitude(longitude);
                editLocalisation.setLatitude(latitude);
                editLocalisation.setType(type);
                editLocalisation.setParentLocalisation(locFind);
                editLocalisation.setGravite(gravite);
                localisationMetier.updateLocalisation(editLocalisation);
                editLocaliteForm.setMsg("Modification passée  avec succés");
                editLocaliteForm.setResultat(Subscriber.RETOUR_OK);
                return editLocaliteForm;
            } else {
                editLocaliteForm.setMsg("Aucune modification  des informations opérée");
                editLocaliteForm.setResultat(Subscriber.RETOUR_OK);
                return editLocaliteForm;
            }
        }

        editLocaliteForm.setMsg("TYPE INDISPONIBLE ");
        editLocaliteForm.setResultat(Subscriber.RETOUR_OK);
        return editLocaliteForm;

    }

    @RequestMapping(value = "/affecterResponsable", method = RequestMethod.POST)
    public @ResponseBody
    EditUserForm affecterResponsableLolcalisation(@RequestBody @Valid EditUserForm editUserForm, HttpServletRequest request) {
        System.out.println("-----------------" + editUserForm.getIdUser());
        User responsable = gesimmoMetier.findUserById(editUserForm.getIdUser());
        Localisation localisation = localisationMetier.getLocalisationById(idLocalisationSaisi);
        localisation.setUser(responsable);
        localisationMetier.updateLocalisation(localisation);

        editUserForm.setMsg("Modification passée  avec succés");
        editUserForm.setResultat(Subscriber.RETOUR_OK);
        return editUserForm;

    }

    @RequestMapping(value = "/ficheLocalisation", method = RequestMethod.GET)
    String showFicheUser(Model model, @RequestParam(value = "idLocalisation") Long idLocalisation, @RequestParam(value = "dtype") String dtype) {
        try {

            if (dtype.equals(TableConfig.DTYPE_LOCALITE)) {
                BatimentLocalite localisation = (BatimentLocalite) localisationMetier.getLocalisationById(idLocalisation);
                idLocalisationSaisi = idLocalisation;
                EditLocalisationForm editLocalisationForm = new EditLocalisationForm();
                editLocalisationForm.setIdLocalisation(idLocalisation);
                editLocalisationForm.setDateCreation(localisation.getDateCreation().toString());
                editLocalisationForm.setDateUpdated(localisation.getDateUpdated().toString());
                editLocalisationForm.setDescription(localisation.getDescription());
                editLocalisationForm.setLatitude(localisation.getLatitude());
                editLocalisationForm.setLongitude(localisation.getLongitude());
                editLocalisationForm.setNom(localisation.getNomLocalisable());
                editLocalisationForm.setType(localisation.getTypeToString());
                List<Indicateur> mesinIndicateurs = new ArrayList<Indicateur>();
                List<LocalisationIndicateur> lis = IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation);
                for (LocalisationIndicateur loin : lis) {
                    mesinIndicateurs.add(loin.getIndicateur());
                }
                List<Indicateur> inds = IndicateurMetier.findAllIndicateurByEtat(ParamEntity.ETAT_ACTIVE);
                List<Indicateur> otherInds = IndicateurMetier.supprimerIndicateurDansList(inds, mesinIndicateurs);
                //editLocalisationForm.setIdResponsable(localisation.getUser().getIdUser());
                model.addAttribute("indicateurs", otherInds);

                model.addAttribute("editLocalisationForm", editLocalisationForm);
                /*-----------------------Responsable----------------------------*/
                User responsable = localisation.getUser();
                model.addAttribute("users", gesimmoMetier.findUSersByEtat(ParamEntity.ETAT_ACTIVE));
                if (responsable == null) {
                    model.addAttribute("editUserForm", new EditUserForm());
                } else {
                    EditUserForm editUserForm = new EditUserForm();
                    editUserForm.setIdUser(responsable.getIdUser());
                    editUserForm.setDateCreation(responsable.getDateCreation().toString());
                    editUserForm.setDateUpdated(responsable.getDateUpdated().toString());
                    editUserForm.setNom(responsable.getUserName());
                    editUserForm.setPrenom(responsable.getUserPrenom());
                    editUserForm.setEmail(responsable.getUserMail());
                    editUserForm.setLogin(responsable.getCompte().getLogin());
                    editUserForm.setTelephone(responsable.getUserPhone());
                    model.addAttribute("editUserForm", editUserForm);

                }
                /*-----------------------Projets de la localite----------------------------*/
                List<Activite> projets = new ArrayList<Activite>();
                for (Activite activite : localisation.getActivites()) {
                    if (activite.getType().equalsIgnoreCase(ParamEntity.ACTIVITE_TYPE_PROJET) && activite.getEtat() == ParamEntity.ETAT_ACTIVE) {
                        projets.add(activite);
                    }
                }
                ProjetForm projetForm = new ProjetForm();
                projetForm.setNomActivite("nomActivite");
                model.addAttribute("projetForm", projetForm);
                model.addAttribute("projets", projets);
                /*-----------------------l'ensemble des projets----------------------------*/
                model.addAttribute("projetList", projetMetier.getAllProjets());

                /*-----------------------Indicateur de la localite----------------------------*/
                model.addAttribute("indicateursLocalisation", IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation));
                LOG.info("++++++++++++++++++++++++++++" + IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation).size());
            }

            if (dtype.equals(TableConfig.DTYPE_INCIDENT)) {
                ObjetIncident localisation = (ObjetIncident) localisationMetier.getLocalisationById(idLocalisation);
                idLocalisationSaisi = idLocalisation;
                EditLocalisationForm editLocalisationForm = new EditLocalisationForm();
                editLocalisationForm.setIdLocalisation(idLocalisation);
                editLocalisationForm.setDateCreation(localisation.getDateCreation().toString());
                editLocalisationForm.setDateUpdated(localisation.getDateUpdated().toString());
                editLocalisationForm.setDescription(localisation.getDescription());
                editLocalisationForm.setLatitude(localisation.getLatitude());
                editLocalisationForm.setLongitude(localisation.getLongitude());
                editLocalisationForm.setNom(localisation.getNomLocalisable());
                editLocalisationForm.setType(localisation.getTypeToString());
                List<Indicateur> mesinIndicateurs = new ArrayList<Indicateur>();
                List<LocalisationIndicateur> lis = IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation);
                for (LocalisationIndicateur loin : lis) {
                    mesinIndicateurs.add(loin.getIndicateur());
                }
                List<Indicateur> inds = IndicateurMetier.findAllIndicateurByEtat(ParamEntity.ETAT_ACTIVE);
                List<Indicateur> otherInds = IndicateurMetier.supprimerIndicateurDansList(inds, mesinIndicateurs);
                //editLocalisationForm.setIdResponsable(localisation.getUser().getIdUser());
                model.addAttribute("indicateurs", otherInds);

                model.addAttribute("editLocalisationForm", editLocalisationForm);
                /*-----------------------Responsable----------------------------*/
                User responsable = localisation.getUser();
                model.addAttribute("users", gesimmoMetier.findUSersByEtat(ParamEntity.ETAT_ACTIVE));
                if (responsable == null) {
                    model.addAttribute("editUserForm", new EditUserForm());
                } else {
                    EditUserForm editUserForm = new EditUserForm();
                    editUserForm.setIdUser(responsable.getIdUser());
                    editUserForm.setDateCreation(responsable.getDateCreation().toString());
                    editUserForm.setDateUpdated(responsable.getDateUpdated().toString());
                    editUserForm.setNom(responsable.getUserName());
                    editUserForm.setPrenom(responsable.getUserPrenom());
                    editUserForm.setEmail(responsable.getUserMail());
                    editUserForm.setLogin(responsable.getCompte().getLogin());
                    editUserForm.setTelephone(responsable.getUserPhone());
                    model.addAttribute("editUserForm", editUserForm);

                }
                /*-----------------------Projets de la localite----------------------------*/
                List<Activite> projets = new ArrayList<Activite>();
                for (Activite activite : localisation.getActivites()) {
                    if (activite.getType().equalsIgnoreCase(ParamEntity.ACTIVITE_TYPE_PROJET) && activite.getEtat() == ParamEntity.ETAT_ACTIVE) {
                        projets.add(activite);
                    }
                }
                ProjetForm projetForm = new ProjetForm();
                projetForm.setNomActivite("nomActivite");
                model.addAttribute("projetForm", projetForm);
                model.addAttribute("projets", projets);
                /*-----------------------l'ensemble des projets----------------------------*/
                model.addAttribute("projetList", projetMetier.getAllProjets());

                model.addAttribute("localisationForm", editLocalisationForm);
                /*-----------------------Indicateur de la localite----------------------------*/
                model.addAttribute("indicateursLocalisation", IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation));
                LOG.info("++++++++++++++++++++++++++++" + IndicateurMetier.findLocalisationIndicateurByIdLoc(idLocalisation).size());
            }

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        } finally {
            return "ficheLocalisation";
        }

    }

    @RequestMapping(value = "localisation/deleteLocalisation")
    public @ResponseBody
    BasicResponse deleteLocalisation(@RequestParam(value = "idLocalisation") Long idLocalisation, Model model) {
        BasicResponse response = new BasicResponse();
        Localisation locToFind = (Localisation) localisationMetier.getLocalisationById(idLocalisation);
        try {
            if (locToFind != null) {
                locToFind.setEtat(Localisation.ETAT.SUPPRIME);
                localisationMetier.updateLocalisation(locToFind);
                response.setMsg("L'objet localisable [" + locToFind.getNomLocalisable() + "] a été supprimé de la base de données avec succes");
                response.setResultat(BasicResponse.RETOUR_OK);
                return response;
            } else {
                response.setMsg("L'objet localisable est introuvable");
                response.setResultat(BasicResponse.RETOUR_OBJECT_NOT_FOUND);
                return response;
            }

        } catch (Exception ex) {
            response.setMsg(ex.getMessage() + " " + idLocalisation + " ");
            response.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
            return response;
        }

    }

    @RequestMapping(value = "/addLocalisation", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    EditLocalisationForm addLocalisation(@RequestBody @Valid EditLocalisationForm localisationForm, HttpServletRequest request, Model m) {
        /* ENREGISTREMENT DES DONNEES DANS LA BASE */
        try {
            String dtype = localisationForm.getdT();
            String[] gps = localisationForm.getCoordonnees().split("\\(");
            gps = gps[1].split("\\)");
            gps = gps[0].split(",");
            System.out.println("GPS Data Splitted : 0 : " + gps[0] + " 1 :" + gps[1]);
            String latitude = gps[0];
            String longitude = gps[1];
            String Ratt = localisationForm.getRattachement();
            String resp = localisationForm.getResponsable();
            
            String userName = getCurrentUserName();
            LOG.log(Level.INFO, "Username recuperé : "+userName);
            Localisation locFind = null;
            if (!Ratt.equals("")) {
                Long idRatt = Long.parseLong(Ratt);
                locFind = localisationMetier.getLocalisationById(idRatt);
            }
            User responsable = null;
            if (resp != null && !resp.equals("")) {
                try{
                    Long idResponsable = Long.parseLong(resp);                
                    responsable = (User) gesimmoMetier.findEntityById(idResponsable, User.class);
                }catch(Exception ex){
                    LOG.severe("On a tenté d'ajouter un responsable avec un ID inconnu : ID : "+resp);
                }
                
            }
            
            User createur = null;
            Compte cpt = gesimmoMetier.findAccountByLogin(userName);
            
            if (cpt != null && cpt.getUser() != null) {
                createur = cpt.getUser();
                
            }
            
            if (dtype.equals(TableConfig.DTYPE_BATIMENT)) {
                BatimentLocalite localisation = new BatimentLocalite();
                localisation.setNomLocalisable(localisationForm.getNom());
                localisation.setNomLocalisableGoogle(localisationForm.getNomLocaliteGoogle());
                localisation.setDescription(localisationForm.getDescription());
                localisation.setLatitude(latitude);
                localisation.setLongitude(longitude);
                localisation.setParentLocalisation(locFind);
                localisation.setCreateur(createur);
                localisation.setAttribution(responsable);
                localisation.setType(localisationForm.getType());
                localisation.setEtat(Localisation.ETAT.FONCTIONNEL);
                localisation.setNbNiveaux(Integer.parseInt(localisationForm.getNbNiveaux()));
                Localisation localisationCreated = localisationMetier.saveLocalisation(localisation);

                localisationForm = new EditLocalisationForm();
                localisationForm.setResultat(localisationForm.RETOUR_OK);
                localisationForm.setMsg("Objet localisable crée avec succès avec les informations suivantes : [ nom =  " + localisationCreated.getNomLocalisable() + " ]");
                m.addAttribute("localisationForm", localisationForm);
            } else if (dtype.equals(TableConfig.DTYPE_INCIDENT)) {
                ObjetIncident localisation = new ObjetIncident();
                localisation.setNomLocalisable(localisationForm.getNom());
                localisation.setNomLocalisableGoogle(localisationForm.getNomLocaliteGoogle());
                localisation.setDescription(localisationForm.getDescription());
                localisation.setLatitude(latitude);
                localisation.setLongitude(longitude);
                localisation.setParentLocalisation(locFind);
                localisation.setCreateur(createur);
                localisation.setAttribution(responsable);
                localisation.setGravite(localisationForm.getGravite());
                localisation.setType(localisationForm.getType());
                localisation.setEtat(Localisation.ETAT.FONCTIONNEL);

                Localisation localisationCreated = localisationMetier.saveLocalisation(localisation);

                localisationForm = new EditLocalisationForm();
                localisationForm.setResultat(localisationForm.RETOUR_OK);
                localisationForm.setMsg("Objet localisable crée avec succès avec les informations suivantes : [ nom =  " + localisationCreated.getNomLocalisable() + " ]");
                m.addAttribute("localisationForm", localisationForm);
            }
            
            else if (dtype.equals(TableConfig.DTYPE_SITE)) {
                SiteLocalite localisation = new SiteLocalite();
                localisation.setNomLocalisable(localisationForm.getNom());
                localisation.setNomLocalisableGoogle(localisationForm.getNomLocaliteGoogle());
                localisation.setDescription(localisationForm.getDescription());
                localisation.setLatitude(latitude);
                localisation.setLongitude(longitude);
                localisation.setParentLocalisation(locFind);
                localisation.setCreateur(createur);
                localisation.setAttribution(responsable);
                localisation.setNbObjets(Integer.parseInt(localisationForm.getNbObjets()));
                localisation.setType(localisationForm.getType());
                localisation.setEtat(Localisation.ETAT.FONCTIONNEL);
                Localisation localisationCreated = localisationMetier.saveLocalisation(localisation);

                localisationForm = new EditLocalisationForm();
                localisationForm.setResultat(localisationForm.RETOUR_OK);
                localisationForm.setMsg("Objet localisable Site crée avec succès avec les informations suivantes : [ nom =  " + localisationCreated.getNomLocalisable() + " ]");
                m.addAttribute("localisationForm", localisationForm);
            }

        } catch (Exception ex) {
            localisationForm.setResultat(localisationForm.RETOUR_EXCEPTION);
            localisationForm.setMsg("Une erreur est survenue pendant le traitement!! " + ex);
        } finally {
            return localisationForm;
        }

    }

    @RequestMapping(value = "/charger", method = RequestMethod.GET)
    public @ResponseBody
    String[][] getLocaliteByType(@RequestParam(value = "type") String type) {

        String[][] locations = null;
        if (type == null || type.equalsIgnoreCase("ALL")) {
            return localisationMetier.findAllLocalisationByEtatArray(Localisation.ETAT.FONCTIONNEL);
        } else {
            //return localisationMetier.findAllTLocaliteByTypeAndEtatArray(BatimentLocalite.TYPE.valueOf(type), 1);
            return localisationMetier.findAllTLocalisationByTypeAndEtatArray(type, Localisation.ETAT.FONCTIONNEL);
        }

    }

    @RequestMapping(value = "/load/localite", method = RequestMethod.GET)
    public String getDefaultLocaliteForm(Model model, @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "add") String add) {

        /*
         *
         * VERIFIER QUE LES DONNEES RECUES SONT AU BON FORMAT
         * */
        EditLocalisationForm localisationForm = new EditLocalisationForm();
        localisationForm.setLatitude(lat);
        localisationForm.setLongitude(lon);
        localisationForm.setCoordonnees("(" + lat + "," + lon + ")");
        localisationForm.setNomLocaliteGoogle(add);
        localisationForm.setNom(add);
        List<Localisation> locs = new ArrayList<Localisation>();
        locs.addAll(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        localisationForm.setRattachements(locs);
        localisationForm.setResponsables(gesimmoMetier.findAllResponsables());
        //il faut verifier les dtype de la licence
        List<String> licencesObjetType = gesimmoMetier.getTypeLicenceLocalite();

        // TODO A GERER DANS UN OBJECT LicenceContent plus global à la gestion des licences dans une seule methode metier
        if (licencesObjetType != null && licencesObjetType.size() > 0) {
            localisationForm.setResultat(BasicResponse.RETOUR_GOOD_LICENCE);
            localisationForm.setMsg("Success");
            localisationForm.setdT(licencesObjetType.get(0));
            localisationForm.setTypeIncidentOuLocalite(getTypeOfDtypeElements(licencesObjetType.get(0)));            
        } else {
            // RENOYER UNE BasicResponse avec code specifique
            localisationForm.setResultat(BasicResponse.RETOUR_DAB_LICENCE);
            localisationForm.setMsg("<p> Votre licence ne vous permet pas d'effectuer cette operation</p><br>");
        }

        boolean isPerimetreOk = gesimmoMetier.isZoneInLicenceArea(Double.parseDouble(lat), Double.parseDouble(lon));

        if (!isPerimetreOk) {
            // RENOYER UNE BasicResponse avec code specifique pour dire qu'il est dehors du perimetre de la licence
            localisationForm.setResultat(BasicResponse.RETOUR_ZONE_NOT_IN_LICENCE);
            localisationForm.setMsg("<p>" + localisationForm.getMsg() + " Vous etes en dehors du perimetre de la licence</p><br>");
        }
        // verifier si lon et lat sont dans le perimetre
        localisationForm.setdType(licencesObjetType);

        model.addAttribute("localisationForm", localisationForm);

        return "fragment/ficheAddLocaliteForm";

    }

    @RequestMapping(value = "/load/localite/type", method = RequestMethod.GET)
    public String getLocaliteFormByType(Model model, @RequestParam(value = "type") String type,
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "add") String add) {

        EditLocalisationForm localisationForm = new EditLocalisationForm();
        localisationForm.setLatitude(lat);
        localisationForm.setLongitude(lon);
        localisationForm.setCoordonnees("(" + lat + "," + lon + ")");
        localisationForm.setNomLocaliteGoogle(add);
        localisationForm.setNom(add);
        localisationForm.setResultat(BasicResponse.RETOUR_GOOD_LICENCE);
        localisationForm.setMsg("Success");
        List<Localisation> locs = new ArrayList<Localisation>();
        List<String> licencesObjetType = gesimmoMetier.getTypeLicenceLocalite();
        locs.addAll(localisationMetier.findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        localisationForm.setRattachements(locs);
        localisationForm.setResponsables(gesimmoMetier.findAllResponsables());
        localisationForm.setTypeIncidentOuLocalite(getTypeOfDtypeElements(type)); 
//        if (type.equals(TableConfig.DTYPE_BATIMENT)) {
//            localisationForm.setTypeIncidentOuLocalite(BatimentLocalite.getAllTypes());
//        }
//        if (type.equals(TableConfig.DTYPE_INCIDENT)) {
//            localisationForm.setTypeIncidentOuLocalite(ObjetIncident.getAllTypes());
//        }
//        if (type.equals(TableConfig.DTYPE_SITE)) {
//            localisationForm.setTypeIncidentOuLocalite(SiteLocalite.getAllTypes());
//        }
        localisationForm.setdType(licencesObjetType);
        localisationForm.setdT(type);

        model.addAttribute("localisationForm", localisationForm);

        return "fragment/ficheAddLocaliteForm";

    }

    @RequestMapping(value = "localisationListFilter/load/localite/categorie", method = RequestMethod.GET)
    public String getLocaliteFormBy(Model model, @RequestParam(value = "categorie") String categorie) {
        LocalisationFormFilter localisationFormFilter = new LocalisationFormFilter();
        localisationFormFilter.setdT(categorie);
        logger.info("***************************categorie:" + categorie + " ****************************");
        localisationFormFilter.setdType(gesimmoMetier.getTypeLicenceLocalite());
        localisationFormFilter.setTypeIncidentOuLocalite(getTypeOfDtypeElements(categorie));
        localisationFormFilter.getTypeIncidentOuLocalite().add("ALL");
        model.addAttribute("localisationFormFilter", localisationFormFilter);

        return "fragment/menu_localisation";

    }

    private List<String> getTypeOfDtypeElements(String categorie) {
        if (categorie.equals(TableConfig.DTYPE_BATIMENT)) {
            return BatimentLocalite.getAllTypes();
        }else  if (categorie.equals(TableConfig.DTYPE_INCIDENT)) {
            return ObjetIncident.getAllTypes();
        }else if (categorie.equals(TableConfig.DTYPE_SITE)) {
            return SiteLocalite.getAllTypes();
        }else{
            return new ArrayList<String>();
        }
        
    }

    @RequestMapping(value = "localisationListFilterCarte/load/localite/categorie", method = RequestMethod.GET)
    public String getLocaliteCarteFilterBy(Model model, @RequestParam(value = "categorie") String categorie) {
        LocalisationFormFilter localisationFormFilter = new LocalisationFormFilter();
        localisationFormFilter.setdT(categorie);
        logger.info("***************************categorie:" + categorie + " ****************************");
        localisationFormFilter.setdType(gesimmoMetier.getTypeLicenceLocalite());
        localisationFormFilter.setTypeIncidentOuLocalite(getTypeOfDtypeElements(categorie));
        localisationFormFilter.getTypeIncidentOuLocalite().add("ALL");
        model.addAttribute("localisationFormFilter", localisationFormFilter);

        return "fragment/menulocalisationCarte";

    }

    @RequestMapping(value = "/localisationListFilter", method = RequestMethod.POST)
    public String projetListFilter(@ModelAttribute LocalisationFormFilter localisationFormFilter, BindingResult result, Model model) {
        try {
            logger.info("***************************start  filtre localisation ****************************");
            if (result.hasErrors()) {
                return "localisationList";
            }
            List<Localisation> listLocalisationFilter = localisationMetier.findLocalisationByCriteres(localisationFormFilter);
            logger.log(Level.INFO, "***************************exception  filtre projet ****************************{0}", listLocalisationFilter.size());
            model.addAttribute("localisations", listLocalisationFilter);

            model.addAttribute("localisationFormFilter", localisationFormFilter);
            return "localisationList";
        } catch (Exception e) {
            logger.log(Level.INFO, "***************************exception  filtre projet ****************************{0}", e.getMessage());
            return "localisationList";
        }
    }

    @RequestMapping(value = "/localisationListFilterCarte", method = RequestMethod.POST, consumes = "application/json")
    public  @ResponseBody String[][] projetListFilterCarte(@RequestBody @Valid LocalisationFormFilter localisationFormFilter, BindingResult result, Model model) {
        String[][] locations = null;
//        try {
            if (localisationFormFilter.getdT().equalsIgnoreCase(TableConfig.DTYPE_LOCALITE)) {
                List<BatimentLocalite> listLocalisationFilter = localisationMetier.findLocaliteByCriteres(localisationFormFilter);
                return localisationMetier.findAllTLocaliteFilterArray(listLocalisationFilter);
            }
            if (localisationFormFilter.getdT().equalsIgnoreCase(TableConfig.DTYPE_INCIDENT)) {
                List<ObjetIncident> listLocalisationFilter = localisationMetier.findIncidentByCriteres(localisationFormFilter);
                return localisationMetier.findAllTIncidentFilterArray(listLocalisationFilter);
            }
//
//        } catch (Exception e) {
//            logger.log(Level.INFO, "***************************exception  filtre localisation ****************************{0}", e.getMessage());
//        }
        return locations;

    }
    
    private String getCurrentUserName(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            LOG.log(Level.INFO, "Username recuperé : "+userName);
            
            return userName;
    }
}
