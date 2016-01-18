package sn.gandal.gesimmo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.dto.EditUserDTO;
import sn.gandal.gesimmo.form.EditUserForm;
import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.metier.utils.PasswordGenerator;
import sn.gandal.gesimmo.modele.client.entities.Compte;
import sn.gandal.gesimmo.modele.client.entities.Role;
import sn.gandal.gesimmo.modele.client.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import sn.gandal.gesimmo.dto.RecupPasswordDTO;
import sn.gandal.gesimmo.mail.sender.EmailSenderImpl.TYPE_MAIL;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 10/02/15 Time: 23:28 To change
 * this template use File | Settings | File Templates.
 */
@Controller
public class UserController {

    @Autowired
    IGesimmoMetier metier;
    private long idUserSaisi = 0;
    private String loginSaisi = null;

    Logger LOG = Logger.getLogger(UserController.class.getName());

    @RequestMapping(value = "user/addUser", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Subscriber submittedFromData(@RequestBody @Valid Subscriber subscriber, HttpServletRequest request, Model m) {

        if (metier.isUserWithEmailExist(subscriber.getEmail().trim())) {
            subscriber.setMsg("Un utilisateur existe avec la même adresse email [" + subscriber.getEmail() + "] vous devez utiliser une autre adresse mail");
            subscriber.setResultat(Subscriber.RETOUR_EMAIL_INVALID);
            return subscriber;
        }
        System.out.println("###########################+ " + subscriber.getTelephone());

        if (metier.isUserWithTelExist(subscriber.getTelephone().trim())) {
            subscriber.setMsg("Un utilisateur existe avec le même numero [" + subscriber.getTelephone() + "] vous devez utiliser une autre numero de téléphone");
            subscriber.setResultat(Subscriber.RETOUR_PHONE_INVALID);
            return subscriber;
        }
        String loginSplited = metier.getUserNameFromEmail(subscriber.getEmail());

        if (metier.isAccountWithLoginExist(loginSplited)) {
            subscriber.setMsg("Un utilisateur existe avec un login suivant : [" + loginSplited + "] vous devez utiliser une autre adresse mail");
            subscriber.setResultat(Subscriber.RETOUR_LOGIN_INVALID);
            return subscriber;
        }

        /* ENREGISTREMENT DES DONNEES DANS LA BASE */
        try {
            User user = new User();
            user.addRole(subscriber.getRole());
            user.setUserLogged(0);
            user.setUserMail(subscriber.getEmail());
            user.setUserName(subscriber.getNom());
            user.setUserPrenom(subscriber.getPrenom());
            user.setUserPhone(subscriber.getTelephone());
            User userCreated = (User) metier.createNewUserAccount(user);

            subscriber = new Subscriber();
            subscriber.setResultat(Subscriber.RETOUR_OK);
            subscriber.setMsg("Utilisateur crée avec succès avec les informations suivantes : [ Login =  " + userCreated.getCompte().getLogin() + " ]");
            m.addAttribute("subscriber", subscriber);
        } catch (Exception ex) {
            subscriber.setResultat(Subscriber.RETOUR_EXCEPTION);
            subscriber.setMsg("Une erreur est survenue pendant le traitement!!");
        } finally {
            return subscriber;
        }

    }

    @RequestMapping(value = "user/deleteUser", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteUser(@RequestParam(value = "idUser") Long idUser, Model model) {
        BasicResponse response = new BasicResponse();
        User userToFind = (User) metier.findEntityById(idUser, User.class);
        try {
            if (userToFind != null) {

                if (metier.delete(idUser, User.class) != null) {
                    response.setMsg("L'utilisateur [" + userToFind.getUserName().toUpperCase() + " " + userToFind.getUserPrenom() + "] a été supprimé de la base de données avec succes");
                    response.setResultat(BasicResponse.RETOUR_OK);
                } else {
                    response.setMsg("Vous ne pouvez pas supprimer un utilistateur de role SUPER ADMIN");
                    response.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
                }

            } else {
                response.setMsg("Il n'existe aucun utilisateur ayant l'id [ " + idUser + " ]");
                response.setResultat(BasicResponse.RETOUR_ID_INVALID);
            }
            return response;
        } catch (RuntimeException ex) {
            response.setMsg(ex.getMessage() + " " + idUser + " ");
            response.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
            return response;
        }

    }

    @RequestMapping(value = "/ficheUser", method = RequestMethod.GET)
    String showFicheUser(Model model, @RequestParam(value = "idUser") Long idUser) {
        User user = (User) metier.findEntityById(idUser, User.class);
        //model.addAttribute("roles",metier.findAllRoles());
        //model.addAttribute("User",user);

        EditUserForm ficheUserForm = new EditUserForm();
        ficheUserForm.setDateCreation(user.getDateCreation().toString());
        ficheUserForm.setDateUpdated(user.getDateUpdated().toString());
        ficheUserForm.setIdUser(user.getIdUser());
        ficheUserForm.setNom(user.getUserName());
        ficheUserForm.setPrenom(user.getUserPrenom());
        ficheUserForm.setEmail(user.getUserMail());
        ficheUserForm.setLogin(user.getCompte().getLogin());
        ficheUserForm.setTelephone(user.getUserPhone());
        int i = 0;
        ficheUserForm.setUserRoles(new String[user.getRoles().size()]);
        for (Role r : user.getRoles()) {
            ficheUserForm.getUserRoles()[i] = r.getNameRole();
            i++;

        }
        ficheUserForm.setEtatCompte(user.getCompte().getEtatCompte());

         model.addAttribute("userRoles", user.getRoles());
        model.addAttribute("roles", metier.findAllRoles());

        model.addAttribute("ficheUserForm", ficheUserForm);
        System.out.println("-----------------------------------size user.getRoles" + user.getRoles().size());

        System.out.println("++++++++++++++++++++++++++++++++++++size Roles " + metier.findAllRoles().size());
        //Affichage du nombre d'utilisateur sur les bages
        Subscriber subscriber = new Subscriber();
        model.addAttribute("subscriber", subscriber);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("nbUsers", metier.findAllUsersWithoutUserConnected(login).size() + 1);
        model.addAttribute("nbUsersActif", metier.findUSersByEtat(1).size());
        model.addAttribute("nbUsersInactif", metier.findUSersByEtat(0).size());
        return "ficheUser";
    }

    //user/updateUser
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public @ResponseBody
    EditUserForm updateUser(@RequestBody EditUserForm ficheUserForm, HttpServletRequest request) {
 

        User user = (User) metier.findEntityById(ficheUserForm.getIdUser(), User.class);
        try {
         
            if (metier.isUserWithTelExist(ficheUserForm.getTelephone().trim(), ficheUserForm.getIdUser())) {
                ficheUserForm.setMsg("Un utilisateur existe avec le même numero [" + ficheUserForm.getTelephone() + "] vous devez utiliser une autre numero de téléphone");
                ficheUserForm.setResultat(Subscriber.RETOUR_PHONE_INVALID);
                return ficheUserForm;
            }
            if (metier.isUserWithMailExist(ficheUserForm.getEmail().trim(), ficheUserForm.getIdUser())) {
                ficheUserForm.setMsg("Un utilisateur existe avec le même email [" + ficheUserForm.getEmail() + "] vous devez utiliser un email");
                ficheUserForm.setResultat(Subscriber.RETOUR_EMAIL_INVALID);
                return ficheUserForm;
            }
            user.setUserName(ficheUserForm.getNom());
            user.setUserPrenom(ficheUserForm.getPrenom());
            user.setUserMail(ficheUserForm.getEmail());
            user.setUserPhone(ficheUserForm.getTelephone());

            List<Role> roles = new ArrayList<Role>();
            Role role;
            for (String r : ficheUserForm.getUserRoles()) {
                role = metier.findRoleByName(r);
                if (role != null) {
                    roles.add(role);
                }
            }
            user.setRoles(roles);
            metier.updateUser(user,TYPE_MAIL.ACCOUNT_UPDATE);
            ficheUserForm.setMsg("user modifié avec succès avec les informations suivantes : [ Nom =  " + user.getUserName() + " ]");
            ficheUserForm.setResultat(ficheUserForm.RETOUR_OK);

        } catch (Exception ex) {
            System.out.println("----------------------------" + ex.getMessage());
            ficheUserForm.setMsg("Exeption" + ex.getMessage());
            ficheUserForm.setResultat(ficheUserForm.RETOUR_EXCEPTION);
        } finally {
            return ficheUserForm;
        }
    }

    @RequestMapping(value = "user/getUser", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO editUser(@RequestParam(value = "idUser") Long idUser) {

        EditUserDTO editUser = new EditUserDTO();
        User user = metier.findUserById(idUser);
        if (user == null) {
            editUser.setMsg("Le client son adresse ne doivent pas etre null.");
            editUser.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editUser;
        }

        idUserSaisi = idUser;

        editUser.setNom(user.getUserName());
        editUser.setPrenom(user.getUserPrenom());
        editUser.setEmail(user.getUserMail());
        editUser.setTelephone(user.getUserPhone());
        editUser.setLogin(user.getCompte().getLogin());
        editUser.setMsg("Visualisation de l'utilisateur passée avec succés");
        return editUser;
    }

    @RequestMapping(value = "user/editUser", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EditUserDTO editUserForm(@RequestBody @Valid EditUserDTO editUserForm, HttpServletRequest request) {

        String nom = editUserForm.getNom();
        String prenom = editUserForm.getPrenom();
        String telephone = editUserForm.getTelephone();
        String mail = editUserForm.getEmail();

        User editUser = metier.findUserById(idUserSaisi);
        String nomUserSelected = editUser.getUserName();
        String prenomUserSelected = editUser.getUserPrenom();
        String emailUserSelected = editUser.getUserMail();
        String telephoneUserSelected = editUser.getUserPhone();

        if (!(nom.equals(nomUserSelected)) || !(prenom.equals(prenomUserSelected)) || !(telephone.equals(telephoneUserSelected)) || !(mail.equals(emailUserSelected))) {
            if (metier.isUserWithTelExist(telephone, idUserSaisi)) {
                editUserForm.setMsg("Un utilisateur existe avec le même numero [" + editUserForm.getTelephone() + "] vous devez utiliser une autre numero de téléphone");
                editUserForm.setResultat(Subscriber.RETOUR_PHONE_INVALID);
                return editUserForm;
            }

            if (metier.isUserWithMailExist(mail, idUserSaisi)) {
                editUserForm.setMsg("Un utilisateur existe avec le même email [" + editUserForm.getEmail() + "] vous devez utiliser un email");
                editUserForm.setResultat(Subscriber.RETOUR_EMAIL_INVALID);
                return editUserForm;
            }

            editUser.setUserName(nom);
            editUser.setUserPrenom(prenom);
            editUser.setUserMail(mail);
            editUser.setUserPhone(telephone);
            metier.updateUser(editUser,TYPE_MAIL.ACCOUNT_UPDATE);
            editUserForm.setMsg("Modification passée++++ avec succés");
            editUserForm.setResultat(Subscriber.RETOUR_OK);
            return editUserForm;
        } else {
            editUserForm.setMsg("Aucune modification +++des informations opérée");
            editUserForm.setResultat(Subscriber.RETOUR_OK);
            return editUserForm;
        }

    }

    @RequestMapping(value = "user/getProfil", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO editProfil(@RequestParam(value = "login") String login) {
        loginSaisi = login;
        EditUserDTO editUser = new EditUserDTO();
        Compte compte = metier.findAccountByLogin(login);
        long idCompte = compte.getIdCompte();
        User user = metier.findUserByIdCompte(idCompte);
        editUser.setNom(user.getUserName());
        editUser.setPrenom(user.getUserPrenom());
        editUser.setEmail(user.getUserMail());
        editUser.setTelephone(user.getUserPhone());
        editUser.setLogin(compte.getLogin());
        //editUser.setPassword(compte.getPassword());
        editUser.setMsg("Visualisation de l'utilisateur passée avec succés");
        return editUser;
    }

    @RequestMapping(value = "user/changePassword", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EditUserDTO changePassword(@RequestBody @Valid EditUserDTO editUserForm, HttpServletRequest request, Model m, BindingResult result) {
        Compte compte = metier.findAccountByLogin(editUserForm.getLogin());
        long idAcompte = compte.getIdCompte();
        User editUser = metier.findUserByIdCompte(idAcompte);
        String actuelPassword = compte.getPassword();
        String passwordSaisi = editUserForm.getPassword();
        String passwordSaisiCrypte = PasswordGenerator.crypter(passwordSaisi);

        String nouveauPassword = editUserForm.getNewPassword();
        String confirmPassword = editUserForm.getConfirmPassword();

        String nomUser = editUser.getUserName();
        String prenomUser = editUser.getUserPrenom();
        String telephoneUser = editUser.getUserPhone();
        String emailUser = editUser.getUserMail();

        //Les valeur issues da la soummission du formulaire
        String nomUserSaisi = editUserForm.getNom();
        String prenomUserSaisi = editUserForm.getPrenom();
        String telephoneUserSaisi = editUserForm.getTelephone();
        String emailUserSaisi = editUserForm.getEmail();

        if (!(nomUser.equals(nomUserSaisi)) || !(prenomUser.equals(prenomUserSaisi)) || !(telephoneUser.equals(telephoneUserSaisi)) || !(emailUser.equals(emailUserSaisi)) && nouveauPassword.length() == 0 && confirmPassword.length() == 0 && passwordSaisi.length() == 0) {
            editUser.setUserName(nomUserSaisi);
            editUser.setUserPrenom(prenomUserSaisi);
            editUser.setUserMail(emailUserSaisi);
            editUser.setUserPhone(telephoneUserSaisi);

            metier.updateUser(editUser,TYPE_MAIL.GENERATOR_PASSWORD);
            editUserForm.setMsg("Modification des informations passées avec succés");
            editUserForm.setResultat(BasicResponse.RETOUR_OK);
            return editUserForm;
        }

        if (nouveauPassword.length() > 0 && confirmPassword.length() > 0 && passwordSaisi.length() > 0 && nouveauPassword.equals(confirmPassword) && actuelPassword.equals(passwordSaisiCrypte)) {
            String newPassword = PasswordGenerator.crypter(confirmPassword);
            compte.setPassword(newPassword);
            editUser.setUserName(nomUserSaisi);
            editUser.setUserPrenom(prenomUserSaisi);
            editUser.setUserMail(emailUserSaisi);
            editUser.setUserPhone(telephoneUserSaisi);
            compte.setUser(editUser);
            metier.updateCompte(compte);
            editUserForm.setMsg("Modification du mot de passe est passée avec succés.");
            editUserForm.setResultat(BasicResponse.RETOUR_OK_REDIRECT);
            request.getSession().invalidate();
            return editUserForm;
        } else {
            editUserForm.setMsg("Modification du mot de passe echouée.");
            editUserForm.setResultat(BasicResponse.RETOUR_DEMANDE_ECHEC);
            return editUserForm;
        }

    }

    @RequestMapping(value = "user/regenerePassword", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO editUser(@RequestParam(value = "loginUser") String loginUser) {
        Compte compte = metier.findAccountByLogin(loginUser);
        String passwordGenerate = PasswordGenerator.genererDefaultPassword();

        String passwordGenerateCrypted = PasswordGenerator.crypter(passwordGenerate);
        compte.setPassword(passwordGenerateCrypted);
        User user = metier.findUserByIdCompte(compte.getIdCompte());
        metier.updateCompte(compte);

        metier.send(user, "Changement de mot de passe", passwordGenerate);
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.setMsg("Password regénéré avec succés et envoyé à votre email.");
        editUserDTO.setResultat(BasicResponse.RETOUR_OK);
        return editUserDTO;
    }
    //Bloquer un utilisateur

    @RequestMapping(value = "user/bloquer", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO bloquerCompteUser(@RequestParam(value = "idUser") Long idUser) {

        EditUserDTO editUser = new EditUserDTO();
        User user = metier.findUserById(idUser);
        if (user == null) {
            editUser.setMsg("Le client son adresse ne doivent pas etre null.");
            editUser.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editUser;
        }
        user.getCompte().setEtatCompte(0);
        metier.updateUser(user,TYPE_MAIL.ACCOUNT_DESABLE);
        idUserSaisi = idUser;
        editUser.setMsg("Blocage passé avec succés");
        return editUser;
    }
    //Bloquer un utilisateur

    @RequestMapping(value = "user/debloquer", method = RequestMethod.GET)
    public @ResponseBody
    EditUserDTO debloquerCompteUser(@RequestParam(value = "idUser") Long idUser) {

        EditUserDTO editUser = new EditUserDTO();
        User user = metier.findUserById(idUser);
        if (user == null) {
            editUser.setMsg("Le client son adresse ne doivent pas etre null.");
            editUser.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editUser;
        }
        user.getCompte().setEtatCompte(1);
        metier.updateUser(user,TYPE_MAIL.ACCOUNT_ENABLE);
        idUserSaisi = idUser;
        editUser.setMsg("Déblocage passé avec succés");
        return editUser;
    }

    //Filtrage des utilisateurs
    @RequestMapping(value = "/actifuserslist")
    public String actifUserListForm(Model model) {
        Subscriber subscriber = new Subscriber();
        subscriber.setRoles(metier.findAllRoles());
        model.addAttribute("subscriber", subscriber);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("nbUsers", metier.findAllUsersWithoutUserConnected(login).size() + 1);
        model.addAttribute("nbUsersActif", metier.findUSersByEtat(1).size());
        model.addAttribute("nbUsersInactif", metier.findUSersByEtat(0).size());
        model.addAttribute("users", metier.findUSersByEtat(1));
        return "userslist";
    }

    @RequestMapping(value = "/inactifuserslist")
    public String inactifUserListForm(Model model) {
        Subscriber subscriber = new Subscriber();
        subscriber.setRoles(metier.findAllRoles());
        model.addAttribute("subscriber", subscriber);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("nbUsers", metier.findAllUsersWithoutUserConnected(login).size() + 1);
        model.addAttribute("nbUsersActif", metier.findUSersByEtat(1).size());
        model.addAttribute("nbUsersInactif", metier.findUSersByEtat(0).size());
        model.addAttribute("users", metier.findUSersByEtat(0));
        return "userslist";
    }

    @RequestMapping(value = "user/regenererPassword", method = RequestMethod.POST)
    public @ResponseBody
    RecupPasswordDTO regenererPassword(@RequestBody @Valid RecupPasswordDTO generePasswordForm) {
        String emailsaisi = generePasswordForm.getEmail();
        String loginsaisi = generePasswordForm.getLogin();
        User userFind = metier.isUserWithLoginAndEmailExist(loginsaisi, emailsaisi);
        if (userFind != null) {
            String passwordClair = metier.genererPassword();
            String passwordCrypte = PasswordGenerator.crypter(passwordClair);
            userFind.getCompte().setPassword(passwordCrypte);
            userFind.getCompte().setPasswordClair(passwordClair);
            metier.updateUser(userFind,TYPE_MAIL.GENERATOR_PASSWORD);
            generePasswordForm.setResultat(BasicResponse.RETOUR_OK);
            generePasswordForm.setMsg("Votre nouveau mot de passe vous a été envoyé sur cette adresse [" + emailsaisi + "]");
            System.out.println("cet utilisateur exite");
            return generePasswordForm;
        } else {
            generePasswordForm.setResultat(BasicResponse.RETOUR_OBJECT_NOT_FOUND);
            generePasswordForm.setMsg("Utilisateur de login [" + loginsaisi + "] et d'adresse email [" + emailsaisi + "] n'existe pas");
            System.out.println("cet utilisateur n'existe pas");
            return generePasswordForm;
        }

    }
}
