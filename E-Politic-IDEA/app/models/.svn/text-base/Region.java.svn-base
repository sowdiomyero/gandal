package models;

import play.db.ebean.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("REGION")
public class Region extends Localite{

	
    public static Model.Finder<Long, Departement> findDepartements = new Model.Finder<Long, Departement>(Long.class, Departement.class);
	
	public static Model.Finder<Long, Region> findRegion = new Model.Finder<Long, Region>(Long.class, Region.class);
	
	public Region(){
		
	}
	
	public static List<Departement> findDepartementsByIdRegion(Long id) {
		 return findDepartements.where().eq("id", id).findList();
	      
	}
	
	public static Region findRegionById(Long id) {
		return findRegion.where().eq("id", id).findUnique();
	}
	
	public static List<Region> findAllRegions() {
	     return findRegion.all();
	}
	
	


      public int getNbMembres() {
       /* int result=0;
        if(departements != null && departements.size() >0) {
            for(Departement dp : departements){
                for(Quartier qr : dp.getQuartiers())
                     result+= qr.getMembres().size();
            }
        }*/
        return  0;
    }
    public static List<Region> findMembresByIdQuartier(int populationBetween, int populationAnd, int nbMilitants) {
        List<Region> results= findRegion.where().between("population", populationBetween, populationAnd)
                .findList();

        List<Region> response= new ArrayList<Region>();
        for(Region qr : results){
            if(qr.getNbMembres() > nbMilitants)
                response.add(qr);
        }
        return response;
    }

    public static List<Membre> findMembresByIdRegion(Long idLocalitte) {
        List<Departement> dpts= findDepartements.where().eq("idRegion", idLocalitte).findList();
        //List<Quartier> quartierList = dpt.getQuartiers();
        List<Membre> membreList = new ArrayList<Membre>();
        for(Departement qr : dpts){
            membreList.addAll(Departement.findMembresByIdDept(qr.getId()));
        }
        return membreList;
    }
}
