package services;

import static play.data.Form.form;

import java.util.UUID;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.Transactional;

import models.Categorie;
import models.Commune;
import models.Compte;
import models.Departement;
import models.Equipe;
import models.Localite;
import models.Membre;
import models.Profil;
import models.Quartier;
import models.Region;
import models.utils.Hash;
import play.Logger;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Result;

public class LocaliteManager {
	
	public CodeRetour controleForm(DynamicForm dynamicForm) {
		
		
		String nbh=dynamicForm.get("nbHabitants");
		String nbE=dynamicForm.get("nbElecteurs");
		String nbo=dynamicForm.get("nbObjectifs");
		String nbs=dynamicForm.get("sections");
        String dType = dynamicForm.get("dtype");
        String responsableData = dynamicForm.get("responsable");
    	String rattachement=dynamicForm.get("rattachement");
		
		
		
	
		CodeRetour retour = new CodeRetour();
		
		
		if (nbh.equals("")) {
			retour.setMessage("Vous devez renseigner le nombre d'habitants de la localite");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
		
		if (nbE.equals("")) {
			retour.setMessage("Vous devez renseigner le nombre d'electeurs de la localite");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (nbo.equals("")) {
			retour.setMessage("Vous devez renseigner l'objectif de la localite");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (nbs.equals("")) {
			retour.setMessage("Vous devez renseigner le nombre de section de la localite");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}

		if (dType==null) {
			retour.setMessage("Vous devez renseigner le type de la localite");
			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
			retour.setError(true);
			return retour;
		}
//		if (rattachement==null) {
//			retour.setMessage("Vous devez renseigner le rattachement de la localite");
//			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
//			retour.setError(true);
//			return retour;
//		}
//		
//		if (responsableData==null) {
//			retour.setMessage("Vous devez renseigner le RESPONSABLE de la localite");
//			retour.setCodeRetour(CodeRetour.FORM_SUBMISSION_ERROR);
//			retour.setError(true);
//			return retour;
//		}
		


		return retour;

	}
	
	
	@Transactional
	public CodeRetour updateLocalite(DynamicForm dynamicForm) {

		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		Ebean.beginTransaction();

		try {
			
			
			String idLocalite=dynamicForm.get("idlocalite");
			Logger.info("id localite : "+idLocalite);
			
			Logger.info("Elements de la requête : "+dynamicForm.toString());
			Long idloc=(long) Long.parseLong(idLocalite);
			Localite loc=Localite.findLocaliteById(idloc);
			
			String nbh=dynamicForm.get("nbHabitants");
			String nbE=dynamicForm.get("nbElecteurs");
			String nbo=dynamicForm.get("nbObjectifs");
			String nbs=dynamicForm.get("sections");
			
			int nbHabitant = Integer.parseInt(nbh);

			int nbElecteurs = Integer.parseInt(nbE);
				
			double nbObjectif=Double.parseDouble(nbo);
			int nbsection=Integer.parseInt(nbs);
			loc.getInfoAdministrative().setNbElecteurs(nbElecteurs);
	        loc.setObjectifs(nbObjectif);
		    loc.setNbSections(nbsection);
		    loc.getInfoAdministrative().setPopulation(nbHabitant);
			loc.update();
			
		

			String dType = dynamicForm.get("dtype");
			
			
			
			if(dType != null && dType.equalsIgnoreCase("DEPARTEMENT"))
				loc.setdType("DEPARTEMENT");
			else if(dType != null && dType.equalsIgnoreCase("COMMUNE"))
				loc.setdType("COMMUNE");
			else if(dType != null && dType.equalsIgnoreCase("REGION"))
				loc.setdType("REGION");
			
			loc.update();
		
			
			
			// Modifier le responsable
			String responsableData = dynamicForm.get("responsable");
			 Logger.info("LE RESPONSABLE: " + responsableData.trim().length());
			
					
					if(responsableData != null && responsableData.trim().length() >0 && (!responsableData.endsWith(":") )){
						Long idMembre = Long.valueOf(dynamicForm.get("responsable").split(":")[1]);
						if(idMembre > 0){
							if(loc.getTeam()!=null){
							
							  loc.getTeam().setNom("Team - "+loc.getNom());
							  Membre responsable = Membre.findMembreById(idMembre);
						
							  responsable.setLocaliteMembre(loc);
							  responsable.update();
							  loc.getTeam().setResponsable(responsable);
							  loc.getTeam().update();
						
							  loc.update();
							}
							else{
								Equipe equipe =new Equipe();
								equipe.setNom("Team - "+loc.getNom());
								equipe.setLocalite(loc);
								Membre responsable = Membre.findMembreById(idMembre);
								loc.addMembre(responsable);
								responsable.setLocaliteMembre(loc);
								responsable.save();
								equipe.setResponsable(responsable);
								equipe.save();
								loc.update();
							}
						}
						
						
					}
					else{
						  loc.getTeam().setResponsable(null);
						  loc.getTeam().update();
						loc.update();
					}
					
					
					
					
		
			String rattachement=dynamicForm.get("rattachement");
		
			
			// Logger.info(" oh rattachement :" + idrattachement);
			 if (rattachement!=null &&  rattachement.trim().length() >0 && (!rattachement.endsWith(":") )) {
				
					String [] values =dynamicForm.get("rattachement").split(":");
					String type = values[0];
			

			if(Integer.parseInt(values[1]) >0) {
				Localite dependOn= Localite.findLocaliteById(Long.parseLong(values[1]));
				loc.setParentLocalite(dependOn);
				loc.update();
			
			
		}
			
				
			}
			 
			 else{
					loc.setParentLocalite(null);
					loc.update();
			 }
			
		
				retour.setCodeRetour(CodeRetour.RETOUR_OK);
				retour.setMessage("Enregistrement des données effectuée avec Succes");
				Ebean.commitTransaction();
			

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");

			Logger.error("Une exception s'est produite pendant la transaction"
					+ ex.getMessage());
			Ebean.rollbackTransaction();
		}
		return retour;
	}
	

	
	
	
	
	
	@Transactional
	public CodeRetour addLocalite(DynamicForm dynamicForm) {

		CodeRetour retour = controleForm(dynamicForm);

		if (retour.isError())
			return retour;

		Ebean.beginTransaction();

		try {
			
			
			
			Localite localite= new Quartier();
			
			String dType = dynamicForm.get("dtype");

			if(dType != null && dType.equalsIgnoreCase("DEPARTEMENT"))
				localite= new Departement();
			else if(dType != null && dType.equalsIgnoreCase("COMMUNE"))
				localite= new Commune();
			else if(dType != null && dType.equalsIgnoreCase("REGION"))
				localite= new Region();

			String [] gps=dynamicForm.get("gps").split("\\(");
			gps=gps[1].split("\\)");
			gps=gps[0].split(",");
			Logger.info("GPS Data Splitted : 0 : "+gps[0]+" 1 :"+gps[1]);
			localite.getInfoGeographique().setLatitude(Double.parseDouble(gps[0]));
			localite.getInfoGeographique().setLongitude(Double.parseDouble(gps[1]));

			localite.getInfoGeographique().setGoogleCodeQuartier(dynamicForm.get("nomLocaliteGoogle"));
			localite.setNom(dynamicForm.get("nomLocaliteCorrige"));

			localite.getInfoAdministrative().setPopulation(Integer.parseInt(dynamicForm.get("nbHabitants")));

			//localite.getInfoAdministrative().setNbInscrits(Integer.parseInt(dynamicForm.get("nbInscits")));
			localite.getInfoAdministrative().setNbElecteurs(Integer.parseInt(dynamicForm.get("nbElecteurs")));
			localite.setObjectifs(Double.parseDouble(dynamicForm.get("nbObjectifs")));

			localite.setNbSections(Integer.parseInt(dynamicForm.get("sections")));
			localite.save();
			// verifier si la localité a été ratachée à un membre
			String responsableData = dynamicForm.get("responsable");
			if(responsableData != null && responsableData.trim().length() >0){
				Long idMembre = Long.valueOf(dynamicForm.get("responsable").split(":")[1]);
				if(idMembre > 0){
					Equipe equipe =new Equipe();
					equipe.setNom("Team - "+localite.getNom());
					equipe.setLocalite(localite);
					Membre responsable = Membre.findMembreById(idMembre);
					localite.addMembre(responsable);
					responsable.setLocaliteMembre(localite);
					responsable.save();
					equipe.setResponsable(responsable);
					equipe.save();
					localite.update();
				}
			}
			String [] values =dynamicForm.get("rattachement").split(":");
			String type = values[0];

			if(Integer.parseInt(values[1]) >0) {
				Localite dependOn= Localite.findLocaliteById(Long.parseLong(values[1]));
				localite.setParentLocalite(dependOn);
				localite.update();
			}
		
		

		
		
				retour.setCodeRetour(CodeRetour.RETOUR_OK);
				retour.setMessage("Enregistrement des données effectuée avec Succes");
				Ebean.commitTransaction();
			

		} catch (Exception ex) {
			retour.setCodeRetour(CodeRetour.RETOUR_KO);
			retour.setMessage("Exception s'est produite pendant l'enregistrement des données");

			Logger.error("Une exception s'est produite pendant la transaction"
					+ ex.getMessage());
			Ebean.rollbackTransaction();
		}
		return retour;
	}
}
