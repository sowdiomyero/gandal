/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.controller;

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
import sn.gandal.gesimmo.form.JournalForm;
import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import sn.gandal.gesimmo.metier.services.IEtapeMetier;
import sn.gandal.gesimmo.metier.services.IJournalMetier;
import sn.gandal.gesimmo.metier.services.IProjetMetier;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.entities.Etape;
import sn.gandal.gesimmo.modele.client.entities.Programme;
import sn.gandal.gesimmo.modele.client.entities.Projet;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;

/**
 *
 * @author msall
 */
@Controller
public class EtapeController {

    private long idCurrentEtape = 0;
    private long idProjetParent = 0;
    private float ponderationDispo = 0;
    private float progressionProgrammeParent = 0;
    private float progressionProjetParent = 0;
    private Etape currentEtape;
    private Projet projetParent;

    @Autowired
    IActiviteMetier metierActivite;
    @Autowired
    IEtapeMetier metierEtape;
    @Autowired
    IProjetMetier metierProjet;

    @Autowired
    IJournalMetier journalMetier;

    Logger LOG = Logger.getLogger(EtapeController.class.getName());

    @RequestMapping(value = "/etapelist", method = RequestMethod.GET)
    String etapeListForm(@RequestParam(value = "activite") Long idParent, Model model) {
        //pour initialiser le formulaire create étape
        EtapeForm etapeForm = new EtapeForm();
        etapeForm.setIdParent(idParent);
        model.addAttribute("etapeForm", etapeForm);
        //fin itialisation
        List<Etape> etapes = new ArrayList<Etape>();
        Projet projet = metierProjet.getProjetById(idParent);
        if (projet != null) {
            model.addAttribute("nomProjetParent", projet.getNomActivite());
            // model.addAttribute("nomProgramParentSinceEtape", projet.getParent().getNomActivite());
            //  model.addAttribute("idProgramParentSinceTravaux", projet.getParent().getIdActivite());
            idProjetParent = idParent;
            projetParent = projet;

            if ((projet.getActiviteList() != null) && (!projet.getActiviteList().isEmpty())) {
                for (Activite act : projet.getActiviteList()) {
                    if (act.getEtat() != ParamEntity.ETAT_DESACTIVE) {
                        Etape e = (Etape) act;
                        etapes.add(e);
                    }
                }
                //Nbre d'étapes par etat
                model.addAttribute("nbr_etape_termine", Activite.filterActivitiesByState(projet.getActiviteList(), ParamEntity.ACTIVITE_ETAT_TERMINE).size());
                model.addAttribute("nbr_etape_en_cours", Activite.filterActivitiesByState(projet.getActiviteList(), ParamEntity.ACTIVITE_ETAT_DEMARRE).size());
                model.addAttribute("nbr_etape_suspendu", Activite.filterActivitiesByState(projet.getActiviteList(), ParamEntity.ACTIVITE_ETAT_SUSPENDU).size());
                model.addAttribute("nbr_etape_abandonne", Activite.filterActivitiesByState(projet.getActiviteList(), ParamEntity.ACTIVITE_ETAT_ABANDONNE).size());
            }
        }

        System.out.println("########          taille etapes apres initialisation  :  " + etapes.size());

        model.addAttribute("etapes", etapes);

        return "etapelist";
    }

    //Récupération de l'étape a éditer
    @RequestMapping(value = "/getEtape", method = RequestMethod.GET)
    public @ResponseBody
    EtapeForm editEtape(@RequestParam(value = "idActivite") Long idActivite) {

        EtapeForm editEtape = new EtapeForm();
        Etape etape = (Etape) metierEtape.getEtapeById(idActivite);
        if (etape == null) {
            editEtape.setMsg("Etape null");
            editEtape.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editEtape;
        }

        idCurrentEtape = idActivite;
        currentEtape = metierEtape.getEtapeById(idActivite);

        editEtape.setNomActivite(etape.getNomActivite());
        editEtape.setDescription(etape.getDescription());
        editEtape.setDateDebPrevu(etape.getDateDebPrevu().toString());
        editEtape.setDateFinPrevu(etape.getDateFinPrevu().toString());
        editEtape.setBudgetPrevu(etape.getBudgetPrevu());
        editEtape.setNbreEmploiPrevu(etape.getNbreEmploiPrevu());
        editEtape.setPonderation(etape.getPonderation());
        editEtape.setTauxRealisation(etape.getTauxRealisation());

        editEtape.setMsg("Visualisation de l'étape passée avec succés");
        return editEtape;
    }

    // UPDATE ETAPE
    @RequestMapping(value = "/editEtape", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EtapeForm updateEtape(@RequestBody @Valid EtapeForm etapeForm, HttpServletRequest request, Model m) throws Exception {
        if (metierEtape.isEtapeNameExist(etapeForm.getNomActivite(), idCurrentEtape, idProjetParent)) {
            etapeForm.setMsg("Une étape existe avec le meme nom  [" + etapeForm.getNomActivite() + "] vous devez utiliser un autre nom");

            etapeForm.setResultat(etapeForm.RETOUR_OBJECTNAME_INVALID);
            return etapeForm;
        }
        
        Etape etapeVerify = metierEtape.getEtapeById(idCurrentEtape);
        if (!isValidPonderation(etapeForm.getPonderation(), projetParent, etapeVerify.getPonderation())) {
            etapeForm.setMsg("La valeur de la pondération est incorrecte ! Valeur disponible : <= " + ponderationDispo);

            etapeForm.setResultat(etapeForm.RETOUR_EXCEPTION);
            return etapeForm;
        }

        try {
            Etape etape = currentEtape;
            etape.setNomActivite(etapeForm.getNomActivite());
            etape.setDescription(etapeForm.getDescription());

            etape.setBudgetPrevu(etapeForm.getBudgetPrevu());
            etape.setNbreEmploiPrevu(etapeForm.getNbreEmploiPrevu());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDeb = sdf.parse(etapeForm.getDateDebPrevu());
            Date dateFin = sdf.parse(etapeForm.getDateFinPrevu());
            etape.setDateDebPrevu(dateDeb);
            etape.setDateFinPrevu(dateFin);
            etape.setPonderation(etapeForm.getPonderation());
            etape.setTauxRealisation(etapeForm.getTauxRealisation());
            
            if(etapeVerify.getTauxRealisation() != etape.getTauxRealisation()){
                updateTauxProjetParent((Projet)etape.getParent(), etape.getPonderation(), etapeForm.getTauxRealisation(), etapeVerify.getTauxRealisation());
            }
            
            metierActivite.updateActivite(etape);
            
            System.out.println("##############################      UPDATE ETAPE       ###############################");
            
            etapeForm = new EtapeForm();
            etapeForm.setResultat(EtapeForm.RETOUR_OK);
            etapeForm.setMsg("Etape modifiée avec succès avec les informations suivantes : [ Nom =  " + etape.getNomActivite() + " ]");
            m.addAttribute("etapeForm", etapeForm);

        } catch (Exception ex) {
            System.out.println("EtapeController --> Update Activite error : ---------->>>>>>  " + ex.getMessage());
            etapeForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            etapeForm.setMsg("Une erreur est survenue pendant le traitement!! ex: " + ex.getMessage());
        } finally {
            return etapeForm;
        }
    }

    // DELETE ETAPE
    @RequestMapping(value = "etape/deleteEtape", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteEtape(@RequestParam(value = "idActivite") Long idActivite, Model model) {

        BasicResponse response = new BasicResponse();
        Etape etapeToFind;
        try {
            etapeToFind = metierEtape.getEtapeById(idActivite);

            if (etapeToFind != null) {
                //Désactivation de l'étape
                etapeToFind.setEtat(ParamEntity.ETAT_DESACTIVE);
                metierActivite.updateActivite(etapeToFind);

                response.setMsg("L'étape [" + etapeToFind.getNomActivite().toUpperCase() + " ] a été supprimé de la base de données avec succes");
                response.setResultat(BasicResponse.RETOUR_OK);
            } else {
                response.setMsg("Vous ne pouvez pas supprimer cette étape");
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

    // content-type=application/json;odata=verbose
    //ADD ETAPE
    @RequestMapping(value = "/addEtape", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EtapeForm addEtape(@RequestBody @Valid EtapeForm etapeForm, HttpServletRequest request, Model m) throws ParseException {
           
         if (!isValidPonderation(etapeForm.getPonderation(), projetParent, 0)) {
            etapeForm.setMsg("La valeur de la pondération est incorrecte ! Valeur disponible : <= " + ponderationDispo);

            etapeForm.setResultat(etapeForm.RETOUR_EXCEPTION);
            return etapeForm;
        }
        try {
            Etape etape = new Etape();
            etape.setNomActivite(etapeForm.getNomActivite());
            etape.setDescription(etapeForm.getDescription());
            etape.setBudgetPrevu(etapeForm.getBudgetPrevu());
            etape.setBudgetPrevu(etapeForm.getNbreEmploiPrevu());
            etape.setEtat(ParamEntity.ETAT_ACTIVE);
            // Conversion des dates
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            etape.setDateDebPrevu(sdf.parse(etapeForm.getDateDebPrevu()));
            etape.setDateFinPrevu(sdf.parse(etapeForm.getDateFinPrevu()));
            if (etape.getDateFinPrevu().compareTo(etape.getDateDebPrevu()) < 0) {
                etapeForm.setResultat(Subscriber.RETOUR_EXCEPTION);
                etapeForm.setMsg("La date fin prévu ne doit pas etre inférieure à la date de début");
                return etapeForm;
            }
            etape.setPonderation(etapeForm.getPonderation());
            etape.setType("ETAPE");
            etape.setParent( metierProjet.getProjetById(etapeForm.getIdParent()));
            // Persistence de l'objet
            metierActivite.addActivite(etape);

            // Réinitialisation du formulaire de création d'une étape  
            etapeForm = new EtapeForm();
            // Message de succès
            etapeForm.setResultat(EtapeForm.RETOUR_OK);
            etapeForm.setMsg("Etape créée avec succès avec les informations suivantes : [ Nom =  " + etapeForm.getNomActivite() + " ]");
            m.addAttribute("etapeForm", etapeForm);
        } catch (Exception ex) {
            etapeForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            etapeForm.setMsg("Une erreur est survenue pendant le traitement!!" + ex.getMessage());
        } finally {
            return etapeForm;
        }
    }

    @RequestMapping(value = "/ficheEtape", method = RequestMethod.GET)
    String showFicheEtape(Model model, @RequestParam(value = "idEtape") Long idEtape) {
        Etape etape = metierEtape.getEtapeById(idEtape);
        JournalForm journalForm = new JournalForm();
        journalForm.setIdActivite(idEtape);

        model.addAttribute("etape", etape);
        model.addAttribute("journalForm", journalForm);

        model.addAttribute("journals", journalMetier.findJournalsByIdActivite(idEtape));

        return "ficheEtape";
    }
    
    public boolean isValidPonderation(float ponderationTravaux, Projet projetParent, float ponderationBeforeModification) {
        float pondOfAllChildren = 0;

        for (Activite act : projetParent.getActiviteList()) {
            if (act.getEtat() != ParamEntity.ETAT_DESACTIVE) {
                pondOfAllChildren += act.getPonderation();
            }
        }
        pondOfAllChildren -= ponderationBeforeModification;
        ponderationDispo = 100 - pondOfAllChildren;
        if ((pondOfAllChildren + ponderationTravaux)>100){
            return false;
        }else{
            return true;
        }
    }
    
    public void updateTauxProjetParent(Projet projetParent, float ponderationEtape, float tauxRealisationEtape, float tauxRealisationEtapeBeforeModifcation) {
        float progression = tauxRealisationEtape - tauxRealisationEtapeBeforeModifcation;
        progressionProjetParent = (ponderationEtape * progression) / 100;
        float tauxProjetParent = progressionProjetParent + projetParent.getTauxRealisation();
        projetParent.setTauxRealisation(tauxProjetParent);
        if (projetParent.getTauxRealisation() == 100) {
            projetParent.setEtat(ParamEntity.ACTIVITE_ETAT_TERMINE);
        } else {
            projetParent.setEtat(ParamEntity.ACTIVITE_ETAT_DEMARRE);
        }
        metierActivite.updateActivite(projetParent);
    }
    
    public void updateTauxProgrammeParent(Programme programParent, float ponderationProjet, float progressionProjet) {
        progressionProgrammeParent = (ponderationProjet * progressionProjet) / 100;
        float tauxProgramParent = progressionProgrammeParent + programParent.getTauxRealisation();
        programParent.setTauxRealisation(tauxProgramParent);
        if (programParent.getTauxRealisation() == 100) {
            programParent.setEtat(ParamEntity.ACTIVITE_ETAT_TERMINE);
        } else {
            programParent.setEtat(ParamEntity.ACTIVITE_ETAT_DEMARRE);
        }
        metierActivite.updateActivite(programParent);
    }
}
