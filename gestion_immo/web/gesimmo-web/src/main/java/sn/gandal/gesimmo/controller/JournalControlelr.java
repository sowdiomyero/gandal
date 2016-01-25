/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.controller;

import java.text.ParseException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.JournalForm;
import sn.gandal.gesimmo.form.ProjetForm;
import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import sn.gandal.gesimmo.metier.services.IJournalMetier;
import sn.gandal.gesimmo.modele.client.entities.Journal;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;
import sn.gandal.gesimmo.utils.RequestMappingUtils;

/**
 *
 * @author kcisse
 */
@Controller
public class JournalControlelr {

    @Autowired
    IJournalMetier journalMetier;

    @Autowired
    IActiviteMetier activiteMetier;

    private Long idJournalSaisi = 0L;
    
        Logger LOG = Logger.getLogger(JournalControlelr.class.getName());


    @RequestMapping(value = "/getJournalProgramme", method = RequestMethod.GET)
    public @ResponseBody
    JournalForm getJournal(@ModelAttribute(value = "idJournal") Long idJournal, Model m) {

        JournalForm result = new JournalForm();

        try {
            if (idJournal == null) {
                result.setMsg("L'identifiant du journal est inconnu");
                result.setResultat(JournalForm.RETOUR_EXCEPTION);
            } else {
                Journal journal = journalMetier.findJournalById(idJournal);
                result.setDescription(journal.getDescription());
                result.setEtat(journal.getEtat());
                result.setIdActivite(journal.getActivite().getIdActivite());
                result.setIdJournal(journal.getIdJournal());
                result.setLibelle(journal.getLibelle());
                result.setDateCreation(journal.getDateCreation());
                result.setDateUpdate(journal.getDateUpdated());
                result.setMsg("Journal trouve");
                result.setResultat(JournalForm.RETOUR_OK);
            }
        } catch (Exception e) {
            result.setMsg("Une exception s'est produite : " + e.getMessage());
            result.setResultat(JournalForm.RETOUR_OK);
        } finally {
            return result;
        }

    }

    @RequestMapping(value = "/journal/update", method = RequestMethod.GET)
    public @ResponseBody
    JournalForm update(@RequestParam(value = "idJournal") Long idJournal, @RequestParam(value = "libelle") String libelle, @RequestParam(value = "description") String description) {
        System.out.println("********************************Journal controller; update");
        JournalForm result = new JournalForm();

        try {
            if (idJournal == null || libelle == null || description == null) {
                result.setMsg("L'identifiant du journal est inconnu");
                result.setResultat(JournalForm.RETOUR_EXCEPTION);
                System.out.println("********************************Journal controller; null");
            } else {
                Journal journal = journalMetier.findJournalById(idJournal);
                journal.setDescription(description);
                journal.setLibelle(libelle);
                journalMetier.updateJournal(journal);
                result.setMsg("Le journal a ete mis a jour avec succes");
                result.setResultat(JournalForm.RETOUR_OK);
                System.out.println("********************************Journal controller; ok");
            }
        } catch (Exception e) {
            result.setMsg("Une exception s'est produite : " + e.getMessage());
            result.setResultat(JournalForm.RETOUR_OK);
            System.out.println("********************************Journal controller; exception");
        } finally {
            return result;
        }

    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @RequestMapping(value = "/addJournal", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    JournalForm addJournal(@RequestBody @Valid JournalForm journalForm, HttpServletRequest request, Model m) throws ParseException {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++ DEscription projet" + journalForm.getDescription());
        try {

            Journal journal = new Journal();
            journal.setDescription(journalForm.getDescription());
            journal.setLibelle(journalForm.getLibelle());
            journal.setEtat(ParamEntity.ETAT_ACTIVE);
            journal.setDateUpdated();
            journal.setDateCreation();
            journal.setActivite(activiteMetier.getActiviteById(journalForm.getIdActivite()));
            journalMetier.creteJournal(journal);

            journalForm.setResultat(ProjetForm.RETOUR_OK);
            journalForm.setMsg("Journal crée avec succès avec les informations suivantes : [ Libelle =  " + journal.getLibelle() + " ]");
            m.addAttribute("journalForm", journalForm);
        } catch (Exception ex) {
            journalForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            journalForm.setMsg("Une erreur est survenue pendant le traitement!!" + ex.getMessage());
        } finally {
            return journalForm;
        }

    }

    //Récupération du projet a éditer
    @RequestMapping(value = "/getJournal", method = RequestMethod.GET)
    public @ResponseBody
    JournalForm editJournal(@RequestParam(value = "idJournal") Long idJournal) {

        JournalForm journalForm = new JournalForm();
        Journal journal = journalMetier.findJournalById(idJournal);
        if (journal == null) {
            journalForm.setMsg("Journal null");
            journalForm.setResultat(BasicResponse.RETOUR_OBJECT_NOT_FOUND);
            return journalForm;
        }

        idJournalSaisi = idJournal;
        journalForm.setDescription(journal.getDescription());
        journalForm.setLibelle(journal.getLibelle());

        journalForm.setMsg("Visualisation du journal passée avec succés");
        return journalForm;
    }

    @RequestMapping(value = "/editJournal", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JournalForm updateJournal(@RequestBody @Valid JournalForm journalForm, HttpServletRequest request, Model m) throws ParseException {
//        if (projetMetier.isProjetNameExist(projetForm.getNomActivite(), idProjetSaisi)) {
//            projetForm.setMsg("Un projet existe avec le meme nom  [" + projetForm.getNomActivite() + "] vous devez utiliser un autre nom");
//            
//            projetForm.setResultat(projetForm.RETOUR_ROLENAME_INVALID);
//            return projetForm;
//        }

        try {
            Journal journal = journalMetier.findJournalById(journalForm.getIdJournal());
            journal.setDescription(journalForm.getDescription());
            journal.setLibelle(journalForm.getLibelle());
            journal.setDateUpdated();

            //journal.setVersion(projet.getVersion() + 1);
            journalMetier.updateJournal(journal);

            journalForm = new JournalForm();
            journalForm.setResultat(ProjetForm.RETOUR_OK);
            journalForm.setMsg("Journal modifié avec succès avec les informations suivantes : [ Libelle =  " + journal.getLibelle() + " ]");
            m.addAttribute("journalForm", journalForm);
        } catch (Exception ex) {
            journalForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            journalForm.setMsg("Une erreur est survenue pendant le traitement!! ex: " + ex.getMessage());
        } finally {
            return journalForm;
        }
    }

    @RequestMapping(value = "/deleteJournal", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteJournal(@RequestParam(value = "idJournal") Long idJournal, Model model) {

        BasicResponse response = new BasicResponse();
        Journal journal;

        try {
            journal = journalMetier.findJournalById(idJournal);
            if (journal != null) {

                journal.setEtat(ParamEntity.ETAT_DESACTIVE);
                journalMetier.updateJournal(journal);

                response.setMsg("Le journal [" + journal.getLibelle().toUpperCase() + " ] a été supprimé de la base de données avec succes");
                response.setResultat(BasicResponse.RETOUR_OK);
            } else {
                response.setMsg("Vous ne pouvez pas supprimer ce journal");
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
  @RequestMapping(value = RequestMappingUtils.JournalMapping.DELETE_BY_ID, method = RequestMethod.GET)
    public @ResponseBody
    BasicResponse delete(@RequestParam(value = "idJournal") Long idJournal) {
        BasicResponse response = new BasicResponse();
        try {
            if (idJournal == null || idJournal < 1) {
                response.setResultat(BasicResponse.RETOUR_ID_INVALID);
                response.setMsg("L'identifiant de l'etat est invalide");
                return response;
            }
            Journal journal = journalMetier.findJournalById(idJournal);
            if (journal == null || journal.getIdJournal() < 1) {
                response.setResultat(BasicResponse.RETOUR_ID_INVALID);
                response.setMsg("Le journal n'a pas pu etre tropuvé");
                return response;
            }
            journal.setEtat(0);
            journalMetier.updateJournal(journal);
            response.setResultat(BasicResponse.RETOUR_OK);
            response.setMsg("Journal supprime avec succes");
            return response;
        } catch (Exception e) {
            response.setResultat(BasicResponse.RETOUR_EXCEPTION);
            response.setMsg("Erreur :" + e.getMessage());
            return response;
        }
    }
      @RequestMapping(value = RequestMappingUtils.JournalMapping.ADD, method = RequestMethod.GET)
    public @ResponseBody
    BasicResponse add(@RequestParam(value = "idProgramme") Long idProgramme,@RequestParam(value = "libelle") String  libelle,@RequestParam(value = "description") String  description) {
        BasicResponse response = new BasicResponse();
        try {
            if (idProgramme == null || idProgramme < 1) {
                response.setResultat(BasicResponse.RETOUR_ID_INVALID);
                response.setMsg("L'identifiant du programme est invalide");
                return response;
            }
           journalMetier.createJournal(idProgramme, libelle, description);
            response.setResultat(BasicResponse.RETOUR_OK);
            response.setMsg("Journal ajoute avec succes");
            return response;
        } catch (Exception e) {
            response.setResultat(BasicResponse.RETOUR_EXCEPTION);
            response.setMsg("Erreur :" + e.getMessage());
            return response;
        }
    }
}
