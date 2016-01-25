/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.dto;

/**
 *
 * @author MSKANE
 */
public class BasicResponse {
  
    public static final int RETOUR_OK=200;
    public static final int RETOUR_EXCEPTION=-100;
    public static final int RETOUR_ID_INVALID =110;
    public static final int RETOUR_ACTION_NOT_ALLOWED=300;
    public static final int RETOUR_DEMANDE_ECHEC=300;
    public static final int RETOUR_OK_REDIRECT=600;
    public static final int RETOUR_DEMANDE_CANCELLED=700;
    public static final int RETOUR_DEMANDE_ACCEPTED=800;
    public static final int RETOUR_OBJECT_NOT_FOUND=900;
    public static final int RETOUR_DAB_LICENCE=1000;
    public static final int RETOUR_GOOD_LICENCE=1100;
    public static final int RETOUR_ZONE_NOT_IN_LICENCE=1200;
    
    public static final int RETOUR_OBJECTNAME_INVALID=100;
    
    private String msg;
    private int resultat;
   

    public BasicResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    
    
    
}
