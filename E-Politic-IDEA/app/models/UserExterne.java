package models;


import play.Logger;
import play.db.ebean.Model;

import javax.persistence.*;

import com.avaje.ebean.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 01/06/15
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class UserExterne extends Personne {

    @Id
    @Column(name = "ID_USER_EXT")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "EQUIPE_ID")
    private Equipe equipe;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Inscription> inscriptions;

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

  //  public static Model.Finder<Long, UserExterne> find = new Model.Finder<Long, UserExterne>(Long.class, UserExterne.class);

    public static UserExterne getUserByPhoneNumber(String phoneNumber){
        String realPhone =phoneNumber;
        if(phoneNumber.trim().length() > 9) {

            realPhone = phoneNumber.trim().substring(phoneNumber.trim().length() - 9);
        }

        Logger.info("Valeur du numero [ "+phoneNumber+" ] qui a été substringué : "+realPhone);
        UserExterne user= find.where().eq("telephone",realPhone).findUnique();
        if(user==null)
            user= find.where().eq("telephone1",realPhone).findUnique();
        return user;
    }

    public Long getId() {
        return id;
    }

    public static Model.Finder<Long, UserExterne> find = new Model.Finder<Long, UserExterne>(Long.class, UserExterne.class);


    
	public static UserExterne findUserExtById(Long id) {
		UserExterne resp = new UserExterne();
		UserExterne team = find.where().eq("id", id).findUnique();
		if(team != null)
			return team;
		
		return resp;
    }
	
	// Methode qui verifie si un numero de telephone est unique
	
			public static boolean isPhoneNumberExist (String phone) {
				List<UserExterne> result = new ArrayList<>();
				if(phone != null  ){
					Query<UserExterne> query = find.query();
					 result =	query.where()
					.raw("telephone = '"+phone+"' OR telephone1 = '"+phone+"'")
					.findList();
					 Logger.info("Requete sql executee : "+query.getGeneratedSql());
				}	
				
		 		if(result != null  && result.size()>0){
					return true;
				}else{
					return false;
				}
			}
			
			
			// Methode qui verifie si un cni est unique
			
				public static boolean isCniExist (String cni) {
					List<UserExterne> result = new ArrayList<>();
					if(cni != null  ){
						Query<UserExterne> query = find.query();
						 result =	query.where()
						.raw("carteIdentite = '"+cni +"'")
						.findList();
						 Logger.info("Requete sql executee : "+query.getGeneratedSql());
						 
						 Logger.info("Requete taille: "+result.size());
					}				
					
			 		if(result != null  && result.size()>0){
						return true;
					}else{
						return false;
					}

				}
				
				// Methode qui verifie si un email est unique
				
				public static boolean isEmailExist (String email) {
					List<UserExterne> result = new ArrayList<>();
					if(email != null  ){
						Query<UserExterne> query = find.query();
						 result =	query.where()
						.raw("email = '"+email +"'")
						.findList();
						 Logger.info("Requete sql executee : "+query.getGeneratedSql());
						 
						 Logger.info("Requete taille: "+result.size());
					}				
					
			 		if(result != null  && result.size()>0){
						return true;
					}else{
						return false;
					}

				}
    
				
				// Methode qui verifie si un numero de telephone est unique a la modification de l'agent
				
				public static boolean isPhoneNumberExistOnChanging(String phone, UserExterne user) {
					List<UserExterne> result = new ArrayList<UserExterne>();
					if (phone != null) {
						//Query<UserExterne> query = find.query();
						Query<UserExterne> query = find.query();
						result = query.where()
								.raw("ID_USER_EXT !=" + user.getId()
										+ " AND (telephone = '" + phone
										+ "' OR telephone1 = '" + phone + "')").findList();
						Logger.info("Requete sql executee : " + query.getGeneratedSql());
					}

					if (result != null && result.size() > 0) {

						return true;
					} else {
						return false;
					}

				}
				
				
				// Methode qui verifie si un cni est unique a la modification de l'agent
				
				public static boolean isCniExistOnChanging(String cni, UserExterne user) {
					List<UserExterne> result = new ArrayList<UserExterne>();
					if (cni != null) {
						//Query<UserExterne> query = find.query();
						Query<UserExterne> query = find.query();
						result = query.where()
								.raw("ID_USER_EXT !=" + user.getId()
										+ " AND (carteIdentite = '" + cni + "')").findList();
						Logger.info("Requete sql executee : " + query.getGeneratedSql());
					}

					if (result != null && result.size() > 0) {

						return true;
					} else {
						return false;
					}

				}
				
				// Methode qui verifie si un email est unique a la modification de l'agent
				
				public static boolean isEmailExistOnChanging(String email, UserExterne user) {
					List<UserExterne> result = new ArrayList<UserExterne>();
					if (email != null) {
						//Query<UserExterne> query = find.query();
						Query<UserExterne> query = find.query();
						result = query.where()
								.raw("ID_USER_EXT !=" + user.getId()
										+ " AND (EMAIL = '" + email + "')").findList();
						Logger.info("Requete sql executee : " + query.getGeneratedSql());
					}

					if (result != null && result.size() > 0) {

						return true;
					} else {
						return false;
					}

				}
				
				// Methode qui verifie si un email est valide
					
				public static boolean isEmailValide(String email) {
			        boolean essai = Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", email);
			        
			        return essai;
			    }

				
				// Methode qui verifie si un numero est valide
				
				public static boolean isPhoneNumberValide(String numero) {
					boolean essai = Pattern.matches("^((\\+\\d{1,3}(-| )?\\(?\\d\\)?(-| )?\\d{1,5})|(\\(?\\d{2,6}\\)?))(-| )?(\\d{3,4})(-| )?(\\d{4})(( x| ext)\\d{1,5}){0,1}$", numero);
			        
			        return essai;
			    }
				
				// Methode qui verifie si un numero est valide
				
				public static boolean isNameValide(String name) {
					boolean essai = Pattern.matches("\\p{L}*( \\p{L}*)*", name);
			        
			        return essai;
			    }

}
