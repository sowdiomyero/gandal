/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.form;

import sn.gandal.gesimmo.dto.BasicResponse;

/**
 *
 * @author msall
 */
public class UserActiviteForm extends BasicResponse{
    
    private long idUser;
    private String fonction;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
    
    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    
}
