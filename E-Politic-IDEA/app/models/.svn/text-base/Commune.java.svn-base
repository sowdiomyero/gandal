package models;

import play.Logger;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("COMMUNE")
public class Commune extends Localite {

/*    @Id
    @Column(name = "ID_COMMUNE")
    private Long id;*/

	public static Finder<Long, Membre> find = new Finder<Long, Membre>(Long.class, Membre.class);

	public static Finder<Long, Commune> findQuartier = new Finder<Long, Commune>(Long.class, Commune.class);


	public Commune() {

	}
/*
    public Long getId() {
        return id;
    }*/
	
	public static List<Membre> findMembresByIdQuartier(Long id) {
	     return find.where().eq("id", id).findList();

	}

    public static List<Commune> findMembresByIdQuartier(int populationBetween,int populationAnd,int nbMilitants) {
        List<Commune> results= findQuartier.where().between("population", populationBetween, populationAnd)
                .findList();
        List<Commune> response= new ArrayList<Commune>();
        for(Commune qr : results){
            Logger.info("Parcours : ndMilitants "+qr.getNbMembres());
            if(qr.getNbMembres() > nbMilitants){
                response.add(qr);
                Logger.info("Ajout du quartier de la liste ID : "+qr.getId());
            }
        }
        Logger.info("Nombre de quartiers ayant un NbMilitant > : "+nbMilitants+" ==> "+results.size());
        return response;
    }
	
	public static Commune findQuartierById(Long id) {
	    return findQuartier.where().eq("id", id).findUnique();
	}
    public static List<Commune> findQuartiersByIdDept(Long idDpt) {
         List<Commune> quartiers =  new ArrayList<Commune>();
        quartiers=findQuartier.where().eq("idDepartement", idDpt).findList();
        return quartiers;
    }
	
	public static List<Commune> findQuartiers() {
		return findQuartier.all();
	      
	}




    public int getNbMembres() {
        List<Membre> mb= findMembresByIdQuartier(getId());
        Logger.info("Nombre de militants pour le quartier ID : "+getId() +" ==> "+mb.size());
        if(mb != null && mb.size() >0)
            return mb.size();
        return 0;
    }

    public static String[][] getAllLocalites() {
        /**
         * Les information retourner :
         * [ idZone, nomZone, typeZone{R,D,S,Q}, idResponsable, NomPrenomResponsable, nbMilitants, nbHabitants, nbSection ]
         */
        List<Commune> quartiers= Commune.findQuartiers();
        List<Departement> dpts= Departement.findAllDeptartements();
        List<Region> regions= Region.findAllRegions();
        int taille =quartiers.size() + dpts.size() +regions.size() ;

    /*  String[][] locations=new String[taille][11];
        int j=0;
        for(int i=0; i< quartiers.size(); i++){
            Quartier ligne= quartiers.get(i);
            //locations[0]= new String[]{Nom Quartier, longitude, lattitude, alt,"COMMUNE"};
            locations[j]=new String[]{
                    ligne.getNomQuartier(),                //nomLocalite  0
                    String.valueOf(ligne.getLatitude()),  //longitude    1
                    String.valueOf(ligne.getLongitude()),   // latitude    2
                    String.valueOf(ligne.getAltitude()),   //altitude     3
                    "COMMUNE",                             //Type Localite4
                    String.valueOf(ligne.getIdResponsable()),           //5
                    ligne.getNomPrenomResponsable(),                    //6
                    ligne.getRattachement(),                            //7
                    String.valueOf(ligne.getNbSections()),              //8
                    String.valueOf(ligne.getPopulation()),              //9
                    String.valueOf(ligne.getId()),                       //10
                    String.valueOf(ligne.getNbMembres())                //11
            };
            j++;
        }

        for(int i=0; i< dpts.size(); i++){
            Departement ligne= dpts.get(i);
            //locations[0]= new String[]{Nom Quartier, longitude, lattitude, alt,"COMMUNE"};
            locations[j]=new String[]{
                    ligne.getNomDepartement(),                //nomLocalite  0
                    String.valueOf(ligne.getLongitude()),  //longitude    1
                    String.valueOf(ligne.getLatitude()),   // latitude    2
                    String.valueOf(ligne.getAltitude()),   //altitude     3
                    "DEPARTEMENT",                             //Type Localite4
                    String.valueOf(ligne.getIdResponsable()),           //5
                    ligne.getNomPrenomResponsable(),                    //6
                    ligne.getRattachement(),                            //7
                    String.valueOf(ligne.getNbSections()),              //8
                    String.valueOf(ligne.getPopulation()),              //9
                    String.valueOf(ligne.getId()),                       //10
                    String.valueOf(ligne.getNbMembres())                //11
            };
            j++;
        }

        for(int i=0; i< regions.size(); i++){
            Region ligne= regions.get(i);
            //locations[0]= new String[]{Nom Quartier, longitude, lattitude, alt,"COMMUNE"};
            locations[j]=new String[]{
                    ligne.getNomRegion(),                //nomLocalite  0
                    String.valueOf(ligne.getLatitude() ),  //longitude    1
                    String.valueOf(ligne.getLongitude()),   // latitude    2
                    String.valueOf(ligne.getAltitude()),   //altitude     3
                    "REGION",                             //Type Localite4
                    String.valueOf(ligne.getIdResponsable()),           //5
                    ligne.getNomPrenomResponsable(),                    //6
                    ligne.getRattachement(),                            //7
                    String.valueOf(ligne.getNbSections()),              //8
                    String.valueOf(ligne.getPopulation()),              //9
                    String.valueOf(ligne.getId()),                       //10
                    String.valueOf(ligne.getNbMembres())                //11
            };
            j++;
        }*/

        //QUARTIERS
            String[][] locations = new String[1][5];
            locations[0]= new String[]{"Pikine Nord", "14.627401", "-14.452361999999994", "4","COMMUNE"};
            locations[1]= new String[]{"Fanaye Walo", "14.157401", "-14.252361999999994", "5","COMMUNE"};
            locations[2]= new String[]{"Guediawaye", " 14.297401", "-14.652361999999994", "3","COMMUNE"};
            locations[3]= new String[]{"Maristes", "14.397401", "-14.458361999999994", "2","COMMUNE"};
            locations[4]= new String[]{"Ndiof Yoor", "14.497401", "-14.452961999999994", "1","COMMUNE"};

            //REGION

                locations[5]= new String[]{"Saint-Louis", "14.627401", "-14.452361999999994", "4","REGION"};
                locations[6]= new String[]{"Fatick", "14.157401", "-14.252361999999994", "5","REGION"};
            //DEPARTEMENT"
                locations[7]= new String[]{"Podor", "16.662506225634584", "-14.955110577866435", "4","DEPARTEMENT"};
                locations[8]= new String[]{"Koumpenntoum", "14.157401", "-14.252361999999994", "5","DEPARTEMENT"};
                locations[9]= new String[]{"Pout", "14.772226723221783", "-17.067232243716717", "5","DEPARTEMENT"};


        return locations;
    }
}
