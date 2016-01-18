package sn.gandal.gesimmo.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.EditIndicateurForm;
import sn.gandal.gesimmo.form.IndicateurLocalisationForm;
import sn.gandal.gesimmo.metier.services.IIndicateurMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import sn.gandal.gesimmo.modele.client.entities.Indicateur;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.LocalisationIndicateur;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;

@Controller
public class IndicateurController {

    @Autowired
    IGesimmoMetier metier;
    @Autowired
    ILocalisationMetier localisationMetier;
    @Autowired
    IIndicateurMetier indicateurMetier;

    private String nomIndSaisi;
    private long idIndSaisi;

    @RequestMapping(value = "/getParamIndicateur", method = RequestMethod.GET)
    public @ResponseBody
    IndicateurLocalisationForm getParamIndicateur(@RequestParam(value = "nomIndicateur") String nomIndicateur) {

        IndicateurLocalisationForm indicateurLocalisationForm = new IndicateurLocalisationForm();
        if (nomIndicateur != null || !nomIndicateur.equals("")) {
            Indicateur inds = indicateurMetier.findIndicateurByName(nomIndicateur);
            if (inds == null) {
                indicateurLocalisationForm.setMsg("Indicateur null.");
                indicateurLocalisationForm.setResultat(BasicResponse.RETOUR_EXCEPTION);
                return indicateurLocalisationForm;
            }

            nomIndSaisi = nomIndicateur;

            //indicateurLocalisationForm.setIndicateur(inds);
            indicateurLocalisationForm.setNomIndicateur(inds.getNomIndicateur());
            indicateurLocalisationForm.setLibelleIndicateur(inds.getLibelle());
            indicateurLocalisationForm.setUniteIndicateur(inds.getUniteIndicateur());
            indicateurLocalisationForm.setMsg("Recuperations passée avec succés");
            return indicateurLocalisationForm;
        }
        else{
            indicateurLocalisationForm.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
            indicateurLocalisationForm.setMsg("Veillez choisir un indicateur correcte");
            return indicateurLocalisationForm;
        }
    }

    @RequestMapping(value = "/addIndicateurLocalisation", method = RequestMethod.POST)
    public @ResponseBody
    IndicateurLocalisationForm addLocalisation(@RequestBody @Valid IndicateurLocalisationForm localisationForm, HttpServletRequest request, Model m) {
        /* ENREGISTREMENT DES DONNEES DANS LA BASE */
        try {
            Localisation locFind;
            Indicateur indFind;

            LocalisationIndicateur localisationIndicateur = new LocalisationIndicateur();
            if (localisationForm.getNomIndicateur() == null || localisationForm.getNomIndicateur().equalsIgnoreCase("")) {
                localisationForm.setResultat(localisationForm.RETOUR_DEMANDE_ECHEC);
                localisationForm.setMsg("Vous devez choisir un indicateur");
                return localisationForm;
            }
            if (localisationForm.getValeur() == null || localisationForm.getValeur().equalsIgnoreCase("")) {
                localisationForm.setResultat(localisationForm.RETOUR_DEMANDE_ECHEC);
                localisationForm.setMsg("Vous devez choisir renseigner le champs valeur");
                return localisationForm;
            }
            locFind = localisationMetier.getLocalisationById(localisationForm.getIdLocalisation());
            indFind = indicateurMetier.findIndicateurByName(localisationForm.getNomIndicateur());
            if (locFind == null) {
                localisationForm.setResultat(localisationForm.RETOUR_DEMANDE_ECHEC);
                localisationForm.setMsg("L'objet localisable null");
                return localisationForm;
            }
            if (indFind == null) {
                localisationForm.setResultat(localisationForm.RETOUR_DEMANDE_ECHEC);
                localisationForm.setMsg("Indicateur null");
                return localisationForm;
            }
            localisationIndicateur.setLocalisation(locFind);
            localisationIndicateur.setIndicateur(indFind);
            localisationIndicateur.setValeur(localisationForm.getValeur());
            indicateurMetier.saveLocalisationIndicateur(localisationIndicateur);

            localisationForm.setResultat(localisationForm.RETOUR_OK);
            localisationForm.setMsg("Enrégistrement éffectué avec succès");
            m.addAttribute("localisationForm", localisationForm);
        } catch (Exception ex) {
            localisationForm.setResultat(localisationForm.RETOUR_EXCEPTION);
            localisationForm.setMsg("Une erreur est survenue pendant le traitement!! " + ex);
        } finally {
            return localisationForm;
        }

    }

    @RequestMapping(value = "/indicateurslist")
    public String indicateurPage(Model model) {

        List<Indicateur> indicateurs = indicateurMetier.findAllIndicateurByEtat(ParamEntity.ETAT_ACTIVE);
        model.addAttribute("indicateurs", indicateurs);
        model.addAttribute("indicateurForm", new EditIndicateurForm());
        return "indicateurList";
    }

    @RequestMapping(value = "indicateur/deleteIndicateur")
    public @ResponseBody
    BasicResponse deleteIndicateur(@RequestParam(value = "id") Long id, Model model) {
        BasicResponse response = new BasicResponse();
        Indicateur indToFind = indicateurMetier.getIndicateurById(id);
        try {
            if (indToFind != null) {
                List<LocalisationIndicateur> locInd = indToFind.getLocalisationIndicateurs();
                indToFind.setEtat(ParamEntity.ETAT_DESACTIVE);
                for (LocalisationIndicateur li : locInd) {
                    li.setLocalisation(null);
                    li.setIndicateur(null);
                    indicateurMetier.updateLocalisationIndicateur(li);
                }
                indicateurMetier.updateIndicateur(indToFind);

                response.setMsg("L'indicateur [" + indToFind.getNomIndicateur() + "] a été supprimé de la base de données avec succes");
                response.setResultat(BasicResponse.RETOUR_OK);
                return response;
            } else {
                response.setMsg("Indicateur est introuvable");
                response.setResultat(BasicResponse.RETOUR_OBJECT_NOT_FOUND);
                return response;
            }

        } catch (Exception ex) {
            response.setMsg(ex.getMessage() + " " + id + " ");
            response.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
            return response;
        }

    }

    @RequestMapping(value = "/addIndicateur", method = RequestMethod.POST)
    public @ResponseBody
    EditIndicateurForm addIndicateur(@RequestBody @Valid EditIndicateurForm indicateurForm, HttpServletRequest request, Model m) {
        /* ENREGISTREMENT DES DONNEES DANS LA BASE */
        try {
            Indicateur indFind;
            if (indicateurForm.getLibelleIndicateur() == null || indicateurForm.getLibelleIndicateur().equalsIgnoreCase("") || indicateurForm.getNomIndicateur() == null || indicateurForm.getNomIndicateur().equalsIgnoreCase("")) {
                indicateurForm.setResultat(indicateurForm.RETOUR_DEMANDE_ECHEC);
                indicateurForm.setMsg("Vous devez renseigner le nom, la description et l'unité de l'indicateur");
                return indicateurForm;
            }
            indFind = indicateurMetier.findIndicateurByName(indicateurForm.getNomIndicateur());
            if (indFind == null) {
                Indicateur indToSave = new Indicateur();
                indToSave.setEtat(ParamEntity.ETAT_ACTIVE);
                indToSave.setLibelle(indicateurForm.getLibelleIndicateur());
                indToSave.setNomIndicateur(indicateurForm.getNomIndicateur());
                indToSave.setUniteIndicateur(indicateurForm.getUniteIndicateur());
                indicateurMetier.saveIndicateur(indToSave);
                indicateurForm.setResultat(indicateurForm.RETOUR_OK);
                indicateurForm.setMsg("indicateur [" + indToSave.getNomIndicateur() + "] enrégistré avec succes");
                return indicateurForm;
            } else {
                if (indFind.getEtat() == ParamEntity.ETAT_DESACTIVE) {
                    indFind.setEtat(ParamEntity.ETAT_ACTIVE);
                    indicateurMetier.updateIndicateur(indFind);
                    indicateurForm.setResultat(indicateurForm.RETOUR_OK);
                    indicateurForm.setMsg("indicateur [" + indFind.getNomIndicateur() + "] enrégistré avec succes");
                    return indicateurForm;
                } else {
                    indicateurForm.setResultat(indicateurForm.RETOUR_ACTION_NOT_ALLOWED);
                    indicateurForm.setMsg("Un indicateur de ce type  existe en base");
                    return indicateurForm;
                }
            }

        } catch (Exception ex) {
            indicateurForm.setResultat(indicateurForm.RETOUR_EXCEPTION);
            indicateurForm.setMsg("Une erreur est survenue pendant le traitement!! " + ex);
        } finally {
            return indicateurForm;
        }

    }

    //Récupération du localisationIndicateur a éditer
    @RequestMapping(value = "/getIndicateurLocalisation", method = RequestMethod.GET)
    public @ResponseBody
    IndicateurLocalisationForm getIndicateurLocalisation(@RequestParam(value = "idIndicateurLocalisation") Long idIndicateurLocalisation) {

        IndicateurLocalisationForm indicateurLocalisationForm = new IndicateurLocalisationForm();
        LocalisationIndicateur localisationIndicateur = indicateurMetier.findLocalisationIndicateurById(idIndicateurLocalisation);
        if (localisationIndicateur == null) {
            indicateurLocalisationForm.setMsg("Journal null");
            indicateurLocalisationForm.setResultat(BasicResponse.RETOUR_OBJECT_NOT_FOUND);
            return indicateurLocalisationForm;
        }

        indicateurLocalisationForm.setValeur(localisationIndicateur.getValeur());

        indicateurLocalisationForm.setMsg("Visualisation   passée avec succés");
        return indicateurLocalisationForm;
    }

    //Récupération du localisationIndicateur a éditer
    @RequestMapping(value = "/updateIndicateurLocalisation", method = RequestMethod.POST)
    public @ResponseBody
    IndicateurLocalisationForm updateIndicateurLocalisation(@RequestBody @Valid IndicateurLocalisationForm indicateurLocalisationForm, HttpServletRequest request, Model m) {

        LocalisationIndicateur localisationIndicateur = indicateurMetier.findLocalisationIndicateurById(indicateurLocalisationForm.getIdIndicateurLocalisation());

        localisationIndicateur.setValeur(indicateurLocalisationForm.getValeur());
        indicateurMetier.updateLocalisationIndicateur(localisationIndicateur);
        indicateurLocalisationForm.setResultat(IndicateurLocalisationForm.RETOUR_OK);
        indicateurLocalisationForm.setMsg("Modification de la valeur passée avec succés");
        return indicateurLocalisationForm;
    }

    //Récupération du localisationIndicateur a éditer
    @RequestMapping(value = "/deleteIndicateurLocalisation", method = RequestMethod.GET)
    public @ResponseBody
    IndicateurLocalisationForm deleteIndicateurLocalisation(@RequestParam(value = "idIndicateurLocalisation") Long idIndicateurLocalisation, HttpServletRequest request) {

        IndicateurLocalisationForm indicateurLocalisationForm = new IndicateurLocalisationForm();
        LocalisationIndicateur localisationIndicateur = indicateurMetier.findLocalisationIndicateurById(idIndicateurLocalisation);
        if (localisationIndicateur == null) {
            indicateurLocalisationForm.setMsg("Journal null");
            indicateurLocalisationForm.setResultat(BasicResponse.RETOUR_OBJECT_NOT_FOUND);
            return indicateurLocalisationForm;
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++" + localisationIndicateur.getValeur() + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        indicateurMetier.deleteLocalisationIndicateur(localisationIndicateur);
        indicateurLocalisationForm.setResultat(IndicateurLocalisationForm.RETOUR_OK);
        indicateurLocalisationForm.setMsg("Suppression de l'indicateur  passée avec succés");
        return indicateurLocalisationForm;
    }

    //Récupération de l'Indicateur a éditer
    @RequestMapping(value = "/getIndicateur", method = RequestMethod.GET)
    public @ResponseBody
    EditIndicateurForm getIndicateur(@RequestParam(value = "id") Long id) {

        EditIndicateurForm indicateurForm = new EditIndicateurForm();
        Indicateur indicateur = indicateurMetier.getIndicateurById(id);
        if (indicateur == null) {
            indicateurForm.setMsg("indicateur null");
            indicateurForm.setResultat(BasicResponse.RETOUR_OBJECT_NOT_FOUND);
            return indicateurForm;
        }
        idIndSaisi = id;
        indicateurForm.setNomIndicateur(indicateur.getNomIndicateur());
        indicateurForm.setLibelleIndicateur(indicateur.getLibelle());
        indicateurForm.setUniteIndicateur(indicateur.getUniteIndicateur());

        indicateurForm.setMsg("Visualisation   passée avec succés");
        return indicateurForm;
    }

    @RequestMapping(value = "/editIndicateur", method = RequestMethod.POST)
    public @ResponseBody
    EditIndicateurForm editIndicateur(@RequestBody @Valid EditIndicateurForm editIndicateurForm, HttpServletRequest request) {

        String nom = editIndicateurForm.getNomIndicateur();
        String desc = editIndicateurForm.getLibelleIndicateur();
        String unite = editIndicateurForm.getUniteIndicateur();

        Indicateur editIndicateur = indicateurMetier.getIndicateurById(idIndSaisi);
        String nomIndSelected = editIndicateur.getNomIndicateur();
        String descIndSelected = editIndicateur.getLibelle();
        String uniteIndSelected = editIndicateur.getUniteIndicateur();

        if (!(nom.equals(nomIndSelected)) || !(desc.equals(descIndSelected)) || !(unite.equals(uniteIndSelected))) {
            if (indicateurMetier.isOthersIndicateursWithNameExist(nom, idIndSaisi)) {
                editIndicateurForm.setMsg("Un indcataur ayant le nom [" + nom + "] existe en base");
                editIndicateurForm.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
                return editIndicateurForm;
            }
            editIndicateur.setNomIndicateur(nom);
            editIndicateur.setLibelle(desc);
            editIndicateur.setUniteIndicateur(unite);
            indicateurMetier.updateIndicateur(editIndicateur);
            editIndicateurForm.setMsg("Modification passée  avec succés");
            editIndicateurForm.setResultat(BasicResponse.RETOUR_OK);
            return editIndicateurForm;
        } else {
            editIndicateurForm.setMsg("Aucune modification  des informations opérée");
            editIndicateurForm.setResultat(BasicResponse.RETOUR_OK);
            return editIndicateurForm;
        }

    }

}
