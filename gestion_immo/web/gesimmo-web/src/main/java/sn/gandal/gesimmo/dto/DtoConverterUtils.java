/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.dto;

import sn.gandal.gesimmo.form.EditLocalisationForm;
import sn.gandal.gesimmo.modele.client.entities.Localisation;

/**
 *
 * @author DYSOW
 */
public class DtoConverterUtils {
    
    public static EditLocalisationForm convert(Localisation loc){
        
        EditLocalisationForm result = new EditLocalisationForm();
        result.setLatitude(loc.getLatitude());
        result.setLongitude(loc.getLongitude());
        result.setDescription(loc.getDescription());
        result.setIdLocalisation(loc.getIdLocalisation());
        result.setType(loc.getType());
        result.setTypeIncidentOuLocalite(loc.getSpecificTypes());
        result.setNom(loc.getNomLocalisable());
        result.setResponsable(loc.getAttribution().toString());
        result.setdT(loc.getDType());
        
        
        return result;
    }
}
