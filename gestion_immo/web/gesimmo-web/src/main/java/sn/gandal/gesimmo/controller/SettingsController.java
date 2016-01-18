package sn.gandal.gesimmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.gandal.gesimmo.form.MessagerieForm;
import sn.gandal.gesimmo.form.RapportForm;
import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 10/02/15 Time: 23:28 To change
 * this template use File | Settings | File Templates.
 */
@Controller
public class SettingsController {

    @Autowired
    IGesimmoMetier metier;

    Logger LOG = Logger.getLogger(SettingsController.class.getName());

    @ModelAttribute("securites")
    public MessagerieForm.SECURITY_TYPE[] securites() {
        return MessagerieForm.SECURITY_TYPE.values();
    }
    @ModelAttribute("frequencies")
    public RapportForm.FREQUENCIES[] frequencies() {
        return RapportForm.FREQUENCIES.values();
    }
    @ModelAttribute("formats")
    public RapportForm.FORMAT_TYPE[] formats() {
        return RapportForm.FORMAT_TYPE.values();
    }
    @ModelAttribute("typeDonnees")
    public RapportForm.TRANSACTION_TYPE[] typeDonnees() {
        return RapportForm.TRANSACTION_TYPE.values();
    }


    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String getEsecureSettings(Model model) {
        MessagerieForm messagerieForm = new MessagerieForm();

        RapportForm rapportForm = new RapportForm();



        Subscriber subscriber = new Subscriber();

        model.addAttribute("subscriber", subscriber);
        model.addAttribute("messagerieForm", messagerieForm);
        model.addAttribute("rapportForm", rapportForm);

        return "settings";
    }






    @RequestMapping(value = "settings/messagerie", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MessagerieForm submittedFromData(@RequestBody @Valid MessagerieForm messagerieForm, HttpServletRequest request, Model m) {

         return  messagerieForm;

    }



    @RequestMapping(value = "settings/rapport", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = "application/json")
    public @ResponseBody
    RapportForm submittedRapportData(@RequestBody @Valid RapportForm rapportForm, HttpServletRequest request,  Model m) {


        return  rapportForm;

    }


}
