/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;


/**
 *
 * @author ddiaw
 */
@Entity
@DiscriminatorValue(value = TableConfig.DTYPE_INCIDENT)
public class ObjetIncident extends Localisation {

    public static enum TYPE {
        INCENDIE,
        ACCIDENT,
        VOL,
        AGGRESSION
    }
    
     public static enum GRAVITE {
        CRITIQUE,
        MINEUR,
        MAJEUR,
        IMPACT_QOS
    }

   @Column(name = "gravite")
   private String gravite;
   
    public ObjetIncident() {
        super();
    }

    public String getDType() {
        return TableConfig.DTYPE_INCIDENT;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    
    
    public String getTypeToString() {

            return this.type;       
    }


    public static List<String> getAllTypes(){
      List<TYPE> res= Arrays.asList(TYPE.values());
      List<String>  response = new ArrayList<String>();
        for(TYPE value : res){
            response.add(value.name());
        }
        return response;
    }
    
    public static List <String> getAllGraviteLevel(){
      List<GRAVITE> res= Arrays.asList(GRAVITE.values());
      List<String>  response = new ArrayList<String>();
        for(GRAVITE value : res){
            response.add(value.name());
        }
        return response;
    }

    @Override
    public List<String> getSpecificTypes() {
        List<TYPE> res= Arrays.asList(TYPE.values());
      List<String>  response = new ArrayList<String>();
        for(TYPE value : res){
            response.add(value.name());
        }
        return response;
    }
    
    
}
