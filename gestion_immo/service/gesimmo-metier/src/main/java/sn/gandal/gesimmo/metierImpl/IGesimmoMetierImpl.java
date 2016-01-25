/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metierImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.metier.utils.PasswordGenerator;
import sn.gandal.gesimmo.modele.client.entities.*;
import sn.gandal.gesimmo.service.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import sn.gandal.gesimmo.mail.sender.EmailSenderImpl;
import sn.gandal.gesimmo.mail.sender.IEmailSender;

/**
 * @author sniang
 */
@Service
public class IGesimmoMetierImpl implements IGesimmoMetier {

    @Autowired
    IUsersDao user;

    @Autowired
    IZoneDao zoneDao;

    @Autowired
    IRoleDao role;
    @Autowired
    ICompteDao compte;
    @Autowired
    IJobExecutorDao job;
    @Autowired
    private org.springframework.core.env.Environment env;

    @Autowired
    IEmailSender sender;

    Logger LOG = Logger.getLogger(IGesimmoMetierImpl.class.getName());

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountWithLoginExist(String login) {
        return compte.isAccountWithLoginExist(login);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findEntityById(Long id, Class entityClass) {

        if (entityClass == User.class) {
            return user.findOne(id);
        }

        if (entityClass == Role.class) {
            return role.findOne(id);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Long delete(Long id, Class clazz) {
        if (clazz == User.class) {
            User u = user.findUserById(id);
            if (u != null && u.isUserInRole("ROLE_SUPER_ADMIN")) {
                try {
                    throw new RuntimeException("Vous n'avez pas le droit de supprimer un utilisateur SUPER ADMINISTRATEUR");
                } catch (RuntimeException ex) {
                    Logger.getLogger(IGesimmoMetierImpl.class.getName()).log(Level.SEVERE, ex.getMessage());
                    return null;
                }
            } else {
                user.delete(id);
                return id;
            }
        }

        if (clazz == Role.class) {

            Role roleToFind = (Role) role.findOne(id);

            if (roleToFind != null && roleToFind.getNameRole().equalsIgnoreCase("ROLE_SUPER_ADMIN")) {
                try {
                    throw new RuntimeException("Vous n'avez pas les droits de supprimer le role SUPER ADMINISTRATEUR ");
                } catch (Exception ex) {
                    Logger.getLogger(IGesimmoMetierImpl.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            } else {
                role.delete(id);
                return id;
            }

        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Object saveObject(Object objet) {

        if (objet.getClass() == Compte.class) {
            return compte.save((Compte) objet);
        }
        if (objet.getClass() == User.class) {
            return user.save((User) objet);
        }

        if (objet.getClass() == Role.class) {
            return role.save((Role) objet);

        }
        if (objet.getClass() == Zone.class) {
            return zoneDao.save((Zone) objet);

        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.;
    }

    @Override
    @Transactional
    public Object createNewUserAccount(User pUser) {

        //1 - Instancier un compte et le remplir
        Compte cpt = new Compte();
        cpt.setDateCreation();
        cpt.setEtatCompte(1);
        cpt.setLogin(PasswordGenerator.splitEmailForLogin(pUser.getUserMail()));
        //String passwordToSendInEmail = PasswordGenerator.genererDefaultPassword();
        String passwordToSendInEmail = PasswordGenerator.splitEmailForLogin(pUser.getUserMail());
        //2 - Generer un password pour le compte
        cpt.setPassword(PasswordGenerator.crypter(passwordToSendInEmail));
        //3 - Enregistrer l'utilisateur après un setCompte()
        //compte.save(cpt);
        pUser.setCompte(cpt);

        User response = user.save(pUser);
        ParametreMail params = getDefaultParamAcces();

        sender.send(pUser, "Création Compte Utilisateur", passwordToSendInEmail, EmailSenderImpl.TYPE_MAIL.ACCOUNT_CREATION, params);
        return response;
    }

    @Override
    public List<User> findAllUsers() {
        return user.findAllUsers();
    }

    @Override
    public Map<Long, String> findAllResponsables() {
        List<User> users= user.findAllUsers();
        Map<Long, String> resultat = new HashMap<Long, String>();
        for(User u : users){
            String fullName = u.getUserPrenom()+" "+u.getUserName().toUpperCase();
            resultat.put(u.getIdUser(), u.toString());
        }
        return resultat;
    }

    @Override
    public Map<Long, String> findAllPrestataires() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean isUserWithEmailExist(String email) {
        return user.isUserWithEmailExist(email);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getUserNameFromEmail(String email) {
        return PasswordGenerator.splitEmailForLogin(email);
    }

    @Override
    public Compte getAccountWithLoginExist(String login) {
        return compte.findAccountByLogin(login);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public User findUserById(Long idUser) {
        return user.findUserById(idUser);

    }
    
    @Override
    public User findUserByEmail(String userMail) {
        return user.findUserByEmail(userMail);
    }

    @Override
    public void updateUser(User editUser, EmailSenderImpl.TYPE_MAIL typemail) {
        user.updateUser(editUser);
        ParametreMail params = getDefaultParamAcces();
        
        
        sender.send(editUser, "Modification des informations de l'utilisateur", editUser.getCompte().getPasswordClair(), typemail, params);
    }

    @Override
    public Compte findAccountByLogin(String login) {
        return compte.findAccountByLogin(login);
    }

    @Override
    public User findUserByIdCompte(Long idCompte) {
        return user.findUserByIdCompte(idCompte);
    }

    @Override
    public void updateCompte(Compte cpt) {
        compte.updateCompte(cpt);
    }

    @Override
    public List<User> findAllUsersWithoutUserConnected(String login) {
        return user.findAllUsersWithoutUserConnected(login);

    }

    @Override
    public void send(User user, String subject, String passwordGenerated) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendAccountCreated(User user, String subject, String passwordGenerated) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendAccountUpdated(User user, String subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendRapport(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logJobExecution(JobExecutorHistory jobDetail) {
        job.save(jobDetail);
    }

    @Override
    public List<Role> findAllRoles() {
        return role.findAllRoles();

    }

    @Override
    public boolean isRoleWithNameExist(String name) {
        Role r = role.findRoleByName(name);

        if (r != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object updateObject(Object objet) {
        if (objet.getClass() == Role.class) {
            role.updateRole((Role) objet);

        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRole(Role r) {
        role.updateRole(r);
    }

    @Override
    public boolean isRoleWithNameExist(String nameRole, Long idRole) {
        Role r = role.findRoleByName(nameRole, idRole);
        if (r != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> findUsersByNameRole(String nameRole) {

        Role r = role.findRoleByName(nameRole);

        if (r != null) {
            return r.getUsers();

        } else {
            return null;
        }

    }

    @Override
    public List<User> findUSersByEtat(int etat) {
        List<Compte> lc = compte.findComptesByEtat(etat);
        List<User> lu = new ArrayList<User>();
        for (Compte c : lc) {
            if (c.getUser() != null) {
                lu.add(c.getUser());
            }

        }
        return lu;

    }

    @Override
    public boolean isUserWithTelExist(String tel, Long idUser) {
        return user.isUserWithTelExist(tel, idUser);
    }

    @Override
    public boolean isUserWithMailExist(String mail, Long idUser) {
        return user.isUserWithMailExist(mail, idUser);
    }

    @Override
    public boolean isUserWithTelExist(String tel) {
        return user.isUserWithTelExist(tel);
    }

    @Override
    public Role findRoleByName(String nameRole) {
        return role.findRoleByName(nameRole);
    }

    @Override
    public ParametreMail getDefaultParamAcces() {
        FileInputStream in = null;

        ParametreMail params = new ParametreMail();

        try {
            Properties props = new Properties();
            String path = System.getProperty("catalina.base") + File.separator + "conf" + File.separator + "gesimmo.properties";
            in = new FileInputStream(path);
            props.load(in);
            in.close();

            LOG.info("++++env+++++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.host"));
            LOG.info("+++++env++++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.username"));
            LOG.info("++++++env+++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.password"));
            LOG.info("++++++env+++++++++++++++++" + env.getProperty("gesimmo.param.messagerie.port"));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++catalina.base " + System.getProperty("catalina.base"));

            params.setServeurMail(props.getProperty("gesimmo.param.messagerie.host"));
            params.setNomUtilisateur(props.getProperty("gesimmo.param.messagerie.username"));
            params.setPassword(props.getProperty("gesimmo.param.messagerie.password"));
            params.setPortSmtp(props.getProperty("gesimmo.param.messagerie.port"));

            return params;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(IGesimmoMetierImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IGesimmoMetierImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return params;
    }

    @Override
    public User isUserWithLoginAndEmailExist(String login, String email) {

        return user.isUserWithLoginAndEmailExist(login, email);
    }

    @Override
    public String genererPassword() {
        return PasswordGenerator.genererDefaultPassword();
    }


    @Override
    public List<String> getTypeLicenceLocalite() {
        List<String> resultat = new ArrayList<String>();
        resultat.add("SITE");
        resultat.add("BATIMENT");
        //resultat.add("LOCALITE");
        resultat.add("INCIDENT");
         
        return resultat;
    }

    @Override
    public boolean isZoneInLicenceArea(Double lat, Double lon) {
        return true;
    }

    @Override
    public List<Zone> findAllZones() {
        return zoneDao.findAllZone();
    }
}
