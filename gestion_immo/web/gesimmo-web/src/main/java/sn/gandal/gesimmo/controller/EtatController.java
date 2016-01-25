/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.metier.services.IEtatMetier;
import sn.gandal.gesimmo.modele.client.entities.Etat;

/**
 *
 * @author isene
 */
@Controller
public class EtatController {

    @Autowired
    IEtatMetier metier;

    Logger LOG = Logger.getLogger(EtatController.class.getName());

    @RequestMapping(value = "/etat/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteEtape(@RequestParam(value = "idEtat") Long idEtat) {
        BasicResponse response = new BasicResponse();
        Etat etat = metier.findById(idEtat);
        if (idEtat < 1 || etat.getId() == null) {
            response.setResultat(BasicResponse.RETOUR_ID_INVALID);
            response.setMsg("L'idientifiant de l'etat est invalide");
            return response;
        }
        metier.deleteEtat(idEtat);
        response.setResultat(BasicResponse.RETOUR_OK);
        response.setMsg("L'etat a ete suspprime avec success");
        return response;

    }

}
