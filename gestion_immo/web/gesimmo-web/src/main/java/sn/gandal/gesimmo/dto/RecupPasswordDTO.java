/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 *
 * @author sniang
 */
public class RecupPasswordDTO {
    
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String login;
    private String msg;
    private int resultat;

    public RecupPasswordDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephone
     */
   

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @return the resultat
     */
    public int getResultat() {
        return resultat;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @param resultat the resultat to set
     */
    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

}
