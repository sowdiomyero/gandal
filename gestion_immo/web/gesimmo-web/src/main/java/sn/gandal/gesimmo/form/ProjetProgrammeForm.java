/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.form;

import sn.gandal.gesimmo.dto.BasicResponse;

/**
 *
 * @author ddiaw
 */
public class ProjetProgrammeForm extends BasicResponse{
    
    private Long idProgramme;
    private Long idProjet;

    public ProjetProgrammeForm() {
    }

    public Long getIdProgramme() {
        return idProgramme;
    }

    public void setIdProgramme(Long idProgramme) {
        this.idProgramme = idProgramme;
    }

    public Long getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Long idProjet) {
        this.idProjet = idProjet;
    }

   
    
}
