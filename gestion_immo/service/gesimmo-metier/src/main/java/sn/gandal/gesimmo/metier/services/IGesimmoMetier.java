/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier.services;

import sn.gandal.gesimmo.modele.client.entities.*;

import java.util.List;
import java.util.Map;
import sn.gandal.gesimmo.mail.sender.EmailSenderImpl;

/**
 *
 * @author sniang
 */
public interface IGesimmoMetier {

    public Object saveObject(Object objet);

    public Object createNewUserAccount(User user);


    public Object findEntityById(Long id, Class entityClass);

    public boolean exists(Long id);

    public boolean isAccountWithLoginExist(String login);

    public Compte getAccountWithLoginExist(String login);

    public Compte findAccountByLogin(String login);

    public boolean isUserWithEmailExist(String email);

    public boolean isUserWithTelExist(String tel);

    public User findUserById(Long id);
    

    public Role findRoleByName(String nameRole);

    public User findUserByEmail(String userMail);

    public User findUserByIdCompte(Long idCompte);

    public void updateUser(User user, EmailSenderImpl.TYPE_MAIL typemail);

    public void updateCompte(Compte compte);

    public long count();

    public Long delete(Long id, Class clazz);

    public String getUserNameFromEmail(String email);

    public List<User> findAllUsers();

    public Map<Long, String> findAllResponsables();
    public Map<Long, String> findAllPrestataires();

    public List<User> findAllUsersWithoutUserConnected(String login);

    public void send(User user, String subject, String passwordGenerated);

    public void sendAccountCreated(User user, String subject, String passwordGenerated);

    public void sendAccountUpdated(User user, String subject);

    public void sendRapport(String filePath);

    public void logJobExecution(JobExecutorHistory jobDetail);

    public List<Role> findAllRoles();

    public boolean isRoleWithNameExist(String name);

    public Object updateObject(Object objet);

    public void updateRole(Role role);

    public boolean isRoleWithNameExist(String nameRole, Long idRole);

    public List<User> findUsersByNameRole(String nameRole);

    public List<User> findUSersByEtat(int etat);

    public boolean isUserWithTelExist(String tel, Long idUser);

    public boolean isUserWithMailExist(String mail, Long idUser);

    public ParametreMail getDefaultParamAcces();

    public User isUserWithLoginAndEmailExist(String login, String email);

    public String genererPassword();

    public List<String> getTypeLicenceLocalite();
    public boolean isZoneInLicenceArea(Double lat, Double lon);

    public List<Zone> findAllZones();
}
