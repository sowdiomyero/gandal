package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("DEPARTEMENT")
public class Departement extends Localite{


	public static Model.Finder<Long, Quartier> findQuartier = new Model.Finder<Long, Quartier>(Long.class, Quartier.class);
	
	public static Model.Finder<Long, Departement> findDept = new Model.Finder<Long, Departement>(Long.class, Departement.class);

	public Departement() {
	}


	public static List<Quartier> findQuartiersByIdDept(Long id) {
		return findQuartier.where().eq("id", id).findList();
	
	}

    public static List<Membre> findMembresByIdDept(Long id) {
        List<Quartier> quartierList= findQuartiersByIdDept(id);

        List<Membre> membreList = new ArrayList<Membre>();
        for(Quartier qr : quartierList){
            membreList.addAll(Localite.findMembresLocaliteById(qr.getId()));
        }
        return membreList;
    }
	

	public static List<Departement> findAllDeptartements() {
		return findDept.all();
	}
}
