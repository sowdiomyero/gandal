package models;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("QUARTIER")
public class Quartier extends Localite {

	public static Model.Finder<Long, Membre> find = new Model.Finder<Long, Membre>(Long.class, Membre.class);
	
	public static Model.Finder<Long, Quartier> findQuartier = new Model.Finder<Long, Quartier>(Long.class, Quartier.class);
	

	public Quartier() {

	}

    public static List<Membre> findMembresByIdQuartier(Long id) {
	     return find.where().eq("localite", id).findList();

	}
   //TODO
    public static List<Quartier> findMembresByIdQuartier(int populationBetween,int populationAnd,int nbMilitants) {
        List<Quartier> results= findQuartier.where().between("population", populationBetween, populationAnd)
                .findList();
        List<Quartier> response= new ArrayList<Quartier>();
        for(Quartier qr : results){
            Logger.info("Parcours : ndMilitants "+qr.getNbMembres());
            if(qr.getNbMembres() > nbMilitants){
                response.add(qr);
                Logger.info("Ajout du quartier de la liste ID : "+qr.getId());
            }
        }
        Logger.info("Nombre de quartiers ayant un NbMilitant > : "+nbMilitants+" ==> "+results.size());
        return response;
    }
	//TODO
	public static Quartier findQuartierById(Long id) {
	    return findQuartier.where().eq("id", id).findUnique();
	}
    public static List<Quartier> findQuartiersByIdDept(Long idDpt) {
         List<Quartier> quartiers =  new ArrayList<Quartier>();
        quartiers=findQuartier.where().eq("idDepartement", idDpt).findList();
        return quartiers;
    }
	
	public static List<Quartier> findQuartiers() {
		return findQuartier.all();
	      
	}




    public int getNbMembres() {
        /* List<Membre> mb= findMembresByIdQuartier(getId());
        Logger.info("Nombre de militants pour le quartier ID : "+getId() +" ==> "+mb.size());
        if(mb != null && mb.size() >0)
            return mb.size();
            */
        return 0;
    }

}
