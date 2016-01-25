/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.ProgrammeForm;
import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.metier.services.IUserActiviteMetier;

/**
 *
 * @author isene
 */
@Controller
public class ActiviteController {

    @Autowired
    IActiviteMetier metier;
    @Autowired
    IUserActiviteMetier metierUserActivite;
    @Autowired
    IGesimmoMetier metiergesimmo;
    static final Logger logger = Logger.getLogger(ActiviteController.class.getName());


    @RequestMapping(value = "activite/changerEtat", method = RequestMethod.GET)
    public @ResponseBody
    ProgrammeForm changerEtatActivite(@RequestParam(value = "idActivite") Long idProgramme, @RequestParam(value = "nomEtat") String nomEtat) {
        logger.info("***************************start  changer etat  projet ****************************");
        ProgrammeForm form = new ProgrammeForm();
        try {
          if(metier.updateEtat(idProgramme, nomEtat)){
            form.setMsg("L'etat a ete change avec succes");
            logger.info("***************************end  changer etat  programme ****************************");
            return form;
          }else{
           form.setMsg("L'etat n'a pas pu etre change ");
            logger.info("***************************  changer etat  update fails ****************************");
            return form;
          }
        } catch (Exception e) {
            form.setMsg("L'etat n'a pas pu etre change :" + e.getMessage());
            form.setResultat(BasicResponse.RETOUR_EXCEPTION);
            logger.log(Level.INFO, "***************************exception  changer etat  programme ****************************{0}", e.getMessage());
            return form;
        }
    }
}
