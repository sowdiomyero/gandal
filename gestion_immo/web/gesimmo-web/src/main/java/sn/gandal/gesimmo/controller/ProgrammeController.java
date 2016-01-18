package sn.gandal.gesimmo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.form.JournalForm;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.ProgrammeForm;
import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.metier.services.IActiviteEtatMetier;
import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import sn.gandal.gesimmo.metier.services.IEtatMetier;
import sn.gandal.gesimmo.metier.services.IJournalMetier;
import sn.gandal.gesimmo.metier.services.ILocalisationMetier;
import sn.gandal.gesimmo.metier.services.IProgrammeMetier;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.entities.Localisation;
import sn.gandal.gesimmo.modele.client.entities.Programme;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;
import sn.gandal.gesimmo.modele.client.tools.ProjetFormFilter;

/**
 *
 * @author kcisse
 */
@Controller
public class ProgrammeController {

    @Autowired
    IProgrammeMetier programmeMetier;

    @Autowired
    IActiviteMetier metier;

    @Autowired
    IJournalMetier journalMetier;

    @Autowired
    ILocalisationMetier localiteMetier;

    @Autowired
    IEtatMetier etatMetier;
    @Autowired
    IActiviteEtatMetier activiteEtatMetier;
    Long idProgrammeSaisi;

    // TODO : Logguer toutes les requetes reçues en même temps que les exceptions
    static final Logger logger = Logger.getLogger(ProgrammeController.class.getName());

    @RequestMapping(value = "/programmelist")
    public String programmeListForm(Model model) {
        ProgrammeForm programmeForm = new ProgrammeForm();
        model.addAttribute("programmeForm", programmeForm);
        model.addAttribute("projetFormFilter", new ProjetFormFilter());
        model.addAttribute("projetListFilterExact", new ProjetFormFilter());

        model.addAttribute("localites", localiteMetier.findAllTLocaliteByEtat(Localisation.ETAT.FONCTIONNEL));

        model.addAttribute("etats", etatMetier.findAllEtat());

        List<Programme> programmes = programmeMetier.getAllProgrammes();
        model.addAttribute("programmes", programmes);
        return "programmelist";
    }

    @RequestMapping(value = "/addProgramme", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ProgrammeForm addProgramme(@RequestBody @Valid ProgrammeForm programmeForm, HttpServletRequest request, Model m) throws ParseException {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++ DEscription projet" + programmeForm.getDescription());
        try {
            Programme programme = new Programme();
            programme.setNomActivite(programmeForm.getNomActivite());
            programme.setType(ParamEntity.ACTIVITE_TYPE_PROGRAMME);
            programme.setDescription(programmeForm.getDescription());
            programme.setBudgetPrevu(programmeForm.getBudgetPrevu());
            programme.setBudgetPrevu(programmeForm.getNbreEmploiPrevu());
            programme.setEtat(ParamEntity.ETAT_ACTIVE);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            programme.setDateDebPrevu(sdf.parse(programmeForm.getDateDebPrevu()));
            programme.setDateFinPrevu(sdf.parse(programmeForm.getDateFinPrevu()));
            if (programme.getDateFinPrevu().compareTo(programme.getDateDebPrevu()) < 0) {
                programmeForm.setResultat(Subscriber.RETOUR_EXCEPTION);
                programmeForm.setMsg("La date fin prévu ne doit pas etre inférieur a la date début");
                return programmeForm;
            }
            Programme programmeCreated = (Programme) programmeMetier.saveProgramme(programme);
            programmeForm = new ProgrammeForm();
            programmeForm.setResultat(ProgrammeForm.RETOUR_OK);
            programmeForm.setMsg("Programme crée avec succès avec les informations suivantes : [ Nom =  " + programmeCreated.getDescription() + " ]");
            m.addAttribute("programmeForm", programmeForm);
        } catch (Exception ex) {
            programmeForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            programmeForm.setMsg("Une erreur est survenue pendant le traitement!!" + ex.getMessage());
            System.out.println("----" + ex.getMessage());
        } finally {
            return programmeForm;
        }

    }

    @RequestMapping(value = "/ficheProgramme", method = RequestMethod.GET)
    String showFicheProjet(Model model, @RequestParam(value = "idProgramme") Long idProgramme) {
        Programme programme = programmeMetier.getProgrammeById(idProgramme);
//        Projet projet = metier.getProjetById(3L);
        JournalForm journalForm = new JournalForm();
        journalForm.setIdActivite(idProgramme);
        model.addAttribute("programme", programme);
        model.addAttribute("journalForm", journalForm);
        model.addAttribute("editJournalForm", new JournalForm());
        model.addAttribute("journals", journalMetier.findJournalsByIdActivite(idProgramme));
        model.addAttribute("programmeForm", new ProgrammeForm());

        return "ficheProgramme";
    }

    @RequestMapping(value = "programme/deleteProgramme", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteProgramme(@RequestParam(value = "idActivite") Long idActivite, Model model) {

        BasicResponse response = new BasicResponse();
        Programme programmeToFind;
        try {
            programmeToFind = programmeMetier.getProgrammeById(idActivite);

            if (programmeToFind != null) {
                /*suppression définitive
                 metier.deleteActivite(projetToFind);
                 */
                //Désactivation du projet
                programmeToFind.setEtat(ParamEntity.ETAT_DESACTIVE);
                programmeMetier.updateProgramme(programmeToFind);

                response.setMsg("Le programme [" + programmeToFind.getNomActivite().toUpperCase() + " ] a été supprimé de la base de données avec succes");
                response.setResultat(BasicResponse.RETOUR_OK);

            }
        } catch (Exception ex) {
            response.setResultat(BasicResponse.RETOUR_EXCEPTION);
            response.setMsg("Une erreur est survenue pendant le traitement!!= " + ex.getMessage());
        } finally {
            return response;
        }

    }

    //Récupération du projet a éditer
    @RequestMapping(value = "/getProgramme", method = RequestMethod.GET)
    public @ResponseBody
    ProgrammeForm editProgramme(@RequestParam(value = "idActivite") Long idActivite) {
        System.out.println("------" + idActivite);
        ProgrammeForm editProgrammeForm = new ProgrammeForm();
        Programme programme = (Programme) programmeMetier.getProgrammeById(idActivite);
        if (programme == null) {
            editProgrammeForm.setMsg("programme null");
            editProgrammeForm.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editProgrammeForm;
        }

        idProgrammeSaisi = idActivite;
        editProgrammeForm.setNomActivite(programme.getNomActivite());
        editProgrammeForm.setDescription(programme.getDescription());
        editProgrammeForm.setDateDebPrevu(programme.getDateDebPrevu().toString());
        editProgrammeForm.setDateFinPrevu(programme.getDateFinPrevu().toString());

        editProgrammeForm.setBudgetPrevu(programme.getBudgetPrevu());
        editProgrammeForm.setNbreEmploiPrevu(programme.getNbreEmploiPrevu());

        editProgrammeForm.setMsg("Visualisation du programme passée avec succés");
        return editProgrammeForm;
    }

    @RequestMapping(value = "/editProgramme", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ProgrammeForm updateProgramme(@RequestBody @Valid ProgrammeForm programmeForm, HttpServletRequest request, Model m) throws ParseException {

        if (programmeMetier.isProgrammeNameExist(programmeForm.getNomActivite(), idProgrammeSaisi)) {
            programmeForm.setMsg("Un programme existe avec le meme nom  [" + programmeForm.getNomActivite() + "] vous devez utiliser un autre nom");

            programmeForm.setResultat(programmeForm.RETOUR_ROLENAME_INVALID);
            return programmeForm;
        }

        try {
            Programme programme = (Programme) programmeMetier.getProgrammeById(idProgrammeSaisi);
            programme.setNomActivite(programmeForm.getNomActivite());
            programme.setDescription(programmeForm.getDescription());

            programme.setBudgetPrevu(programmeForm.getBudgetPrevu());
            programme.setNbreEmploiPrevu(programmeForm.getNbreEmploiPrevu());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDeb = sdf.parse(programmeForm.getDateDebPrevu());
            Date dateFin = sdf.parse(programmeForm.getDateDebPrevu());
            programme.setDateDebPrevu(dateDeb);
            programme.setDateFinPrevu(dateFin);

            programmeMetier.updateProgramme(programme);
            // TODO Internationnaliser le texte contenu dans les messages
            programmeForm = new ProgrammeForm();
            programmeForm.setResultat(programmeForm.RETOUR_OK);
            programmeForm.setMsg("Projet modifié avec succès avec les informations suivantes : [ Nom =  " + programme.getNomActivite() + " ]");
            m.addAttribute("projetForm", programmeForm);
        } catch (Exception ex) {
            programmeForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            programmeForm.setMsg("Une erreur est survenue pendant le traitement!! ex: " + ex.getMessage());
            // TODO utiliser le logger à la place du sop qui ecrit sur la console et pas forcement sur un fichier
            System.out.println(ex.getMessage());
        } finally {
            return programmeForm;
        }
    }

    //************************************************
    @RequestMapping(value = "/programmelisteFilter", method = RequestMethod.POST)
    public String projetListFilter(@ModelAttribute ProjetFormFilter projetFormFilter, BindingResult result, Model model) {
        try {
            logger.info("***************************start  filtre projet ****************************");
            if (result.hasErrors()) {
                return "programmelist";
            }
            ProgrammeForm programmeForm = new ProgrammeForm();
            model.addAttribute("programmeForm", programmeForm);
            List<Activite> programmes = metier.findActivitesByCriteres(projetFormFilter, "PROGRAM");
            
            model.addAttribute("localites", localiteMetier.findAllTLocaliteByEtat(Localisation.ETAT.FONCTIONNEL));

            model.addAttribute("etats", etatMetier.findAllEtat());

            model.addAttribute("programmes", programmes);

            model.addAttribute("projetFormFilter", projetFormFilter);
            model.addAttribute("projetListFilterExact", new ProjetFormFilter());
            logger.info("***************************end  filtre projet ****************************");
            return "programmelist";
        } catch (Exception e) {
            logger.log(Level.INFO, "***************************exception  filtre projet ****************************{0}", e.getMessage());
            return "programmelist";
        }
    }

 //    @RequestMapping(value = "/programmeListFilterExact", method = RequestMethod.POST)
//    public String projetListFilterExact(@ModelAttribute ProjetFormFilter projetListFilterExact, BindingResult result, Model model) {
//        try {
//            logger.info("***************************start  recherche avancee  projet ****************************");
//            if (result.hasErrors()) {
//                return "programmelist";
//            }
//            ProjetForm projetForm = new ProjetForm();
//            model.addAttribute("projetForm", projetForm);
//            List<Activite> programmes = metier.findActivitesByCriteresExacts(projetListFilterExact, "PROGRAM");
//            model.addAttribute("programmes", programmes);
//
//            model.addAttribute("localites", localiteMetier.findAllTLocaliteByEtat(1));
//            model.addAttribute("etats", etatMetier.findAllEtat());
//            model.addAttribute("projetFormFilter", new ProjetFormFilter());
//            model.addAttribute("projetListFilterExact", projetListFilterExact);
//            logger.info("***************************end  recherche avancee  projet ****************************");
//            return "programmelist";
//        } catch (Exception e) {
//            logger.log(Level.INFO, "***************************exception  recherche avancee  projet ****************************{0}", e.getMessage());
//            return "programmelist";
//        }
//    }
}
