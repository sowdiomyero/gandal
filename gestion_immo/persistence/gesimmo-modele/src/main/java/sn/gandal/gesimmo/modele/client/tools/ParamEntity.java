/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.modele.client.tools;

/**
 *
 * @author msall
 */
public class ParamEntity {

    public ParamEntity() {
    }
    
    public static int ETAT_ACTIVE = 1;
    public static int ETAT_DESACTIVE = 0;
    public static int COMPTE_ACTIVE = 1;
    public static int COMPTE_DESACTIVE = 0;
    
    public static int USER_SURVEILLE = 1;
    public static int USER_NON_SURVEILLE = 0;
    
    public static String ACTIVITE_TYPE_PROJET = "PROJET";
    public static String ACTIVITE_TYPE_PROGRAMME = "PROGRAM";
    public static String ACTIVITE_TYPE_ETAPE = "ETAPE";
    
    public static int ACTIVITE_ETAT_PLANIFIE = 5;
    public static int ACTIVITE_ETAT_DEMARRE = 10;
    public static int ACTIVITE_ETAT_SUSPENDU = 20;
    public static int ACTIVITE_ETAT_REDEMARRE = 30;
    public static int ACTIVITE_ETAT_TERMINE = 40;
    public static int ACTIVITE_ETAT_ABANDONNE = 50;
    
    public static String LOCALISATION_TYPE_LOCALITE = "LOCALITE";
    public static String LOCALISATION_TYPE_INCIDENT = "INCIDENT";
}
