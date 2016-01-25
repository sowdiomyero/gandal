package sn.gandal.gesimmo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.dto.EditRoleDTO;
import sn.gandal.gesimmo.form.RoleForm;
import sn.gandal.gesimmo.form.Subscriber;
import sn.gandal.gesimmo.modele.client.entities.Role;
import sn.gandal.gesimmo.modele.client.entities.User;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 10/02/15 Time: 23:28 To change
 * this template use File | Settings | File Templates.
 */
@Controller
public class RoleController {

    @Autowired
    IGesimmoMetier metier;

    private long idRoleSaisi = 0;
    private String loginSaisi = null;

    Logger LOG = Logger.getLogger(RoleController.class.getName());

//    @ModelAttribute("roles")
//    public Subscriber.Role[] roles() {
//        return Subscriber.Role.values();
//    }
    @RequestMapping(value = "/roleslist")
    public String roleListForm(Model model) {
        //pour initialiser le formulaire create role
        RoleForm roleForm = new RoleForm();
        model.addAttribute("roleForm", roleForm);
        //fin itialisation
        model.addAttribute("roles", metier.findAllRoles());

        //Nbre de user par role
        model.addAttribute("nbr_super_admin", metier.findUsersByNameRole("ROLE_SUPER_ADMIN").size());
        model.addAttribute("nbr_admin", metier.findUsersByNameRole("ROLE_ADMIN").size());
        model.addAttribute("nbr_user", metier.findUsersByNameRole("ROLE_USER").size());
        model.addAttribute("nbr_user_tot", metier.findAllUsers().size());

        return "roleslist";
    }

    @RequestMapping(value = "role/addRole", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RoleForm addRole(@RequestBody @Valid RoleForm roleForm, HttpServletRequest request, Model m) {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++ Nbre roles" + roleForm.getNameRole());

        if (metier.isRoleWithNameExist(roleForm.getNameRole())) {

            roleForm.setMsg("Un role existe avec le meme nom  [" + roleForm.getNameRole() + "] vous devez utiliser un autre nom");

            roleForm.setResultat(RoleForm.RETOUR_OBJECTNAME_INVALID);
            return roleForm;
        }

        try {
            Role role = new Role();

            role.setNameRole(roleForm.getNameRole());
            role.setRoleDesc(roleForm.getRoleDesc());

            Role roleCreated = (Role) metier.saveObject(role);

            roleForm = new RoleForm();
            roleForm.setResultat(RoleForm.RETOUR_OK);
            roleForm.setMsg("Role crée avec succès avec les informations suivantes : [ Nom =  " + roleCreated.getNameRole() + " ]");
            m.addAttribute("roleForm", roleForm);
        } catch (Exception ex) {
            roleForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            roleForm.setMsg("Une erreur est survenue pendant le traitement!!");
        } finally {
            return roleForm;
        }

    }

    @RequestMapping(value = "role/deleteRole", method = RequestMethod.DELETE)
    public @ResponseBody
    BasicResponse deleteRole(@RequestParam(value = "idRole") Long idRole, Model model) {

        BasicResponse response = new BasicResponse();
        Role roleToFind = null;
        try {
            roleToFind = (Role) metier.findEntityById(idRole, Role.class);

             

            if (roleToFind != null) {
                 if (metier.delete(idRole, Role.class) != null) {
                   response.setMsg("Le role [" + roleToFind.getNameRole().toUpperCase() + " ] a été supprimé de la base de données avec succes");
                response.setResultat(BasicResponse.RETOUR_OK);
                } else {
                    response.setMsg("Vous ne pouvez pas supprimer le role SUPER ADMIN");
                    response.setResultat(BasicResponse.RETOUR_ACTION_NOT_ALLOWED);
                }
             
                
            }

            if (roleToFind == null) {
                response.setMsg("Il n'existe aucun role ayant l'id [ " + idRole + " ]");
                response.setResultat(BasicResponse.RETOUR_ID_INVALID);
            }
            
        }
            
        catch (Exception ex) {
            response.setResultat(BasicResponse.RETOUR_EXCEPTION);
            response.setMsg("Une erreur est survenue pendant le traitement!!= " + ex.getMessage());
            LOG.info("++++++++++++++++++++++" + ex.getMessage());
        } 
                 
        finally {
            return response;
        }

    }

    //fiche role
    @RequestMapping(value = "/ficheRole", method = RequestMethod.GET)
    String showFicheRole(Model model, @RequestParam(value = "idRole") Long idRole, RoleForm roleForm) {
        Role role = (Role) metier.findEntityById(idRole, Role.class);
        List<User> users = role.getUsers();

        model.addAttribute("users", users);
        Subscriber subscriber = new Subscriber();
        subscriber.setRole(role);
        subscriber.setRoles(metier.findAllRoles());
        model.addAttribute("subscriber", subscriber);
        roleForm.setNameRole(role.getNameRole());
        roleForm.setRoleDesc(role.getRoleDesc());
        roleForm.setDateCreation(role.getDateCreation().toString());
        roleForm.setDateUpdated(role.getDateUpdated().toString());

        //Nbre de user par role
        model.addAttribute("nbr_super_admin", metier.findUsersByNameRole("ROLE_SUPER_ADMIN").size());
        model.addAttribute("nbr_admin", metier.findUsersByNameRole("ROLE_ADMIN").size());
        model.addAttribute("nbr_user", metier.findUsersByNameRole("ROLE_USER").size());
        model.addAttribute("nbr_user_tot", metier.findAllUsers().size());
        return "ficheRole";
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST, consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RoleForm updateRole(@RequestBody @Valid RoleForm roleForm, HttpServletRequest request, Model m) {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++ Nbre roles" + roleForm.getNameRole());

        if (metier.isRoleWithNameExist(roleForm.getNameRole(), roleForm.getIdRole())) {

            roleForm.setMsg("Un role existe avec le meme nom  [" + roleForm.getNameRole() + "] vous devez utiliser un autre nom");

            roleForm.setResultat(RoleForm.RETOUR_OBJECTNAME_INVALID);
            return roleForm;
        }

        try {
            Role role = (Role) metier.findEntityById(roleForm.getIdRole(), Role.class);

            role.setNameRole(roleForm.getNameRole());
            role.setRoleDesc(roleForm.getRoleDesc());

            metier.updateRole(role);

            roleForm = new RoleForm();
            roleForm.setResultat(RoleForm.RETOUR_OK);
            roleForm.setMsg("Role modifié avec succès avec les informations suivantes : [ Nom =  " + role.getNameRole() + " ]");
            m.addAttribute("roleForm", roleForm);
        } catch (Exception ex) {
            roleForm.setResultat(Subscriber.RETOUR_EXCEPTION);
            roleForm.setMsg("Une erreur est survenue pendant le traitement!!");
        } finally {
            return roleForm;
        }

    }

    @RequestMapping(value = "/userslist_by_role")
    public String userListForm(Model model, String nameRole) {
        //pour initialiser le formulaire create user
        Subscriber subscriber = new Subscriber();
        model.addAttribute("subscriber", subscriber);

        model.addAttribute("users", metier.findUsersByNameRole(nameRole));
        return "userslist";
    }

    //Récupération de l'utilisateur a édité
    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    public @ResponseBody
    EditRoleDTO editRole(@RequestParam(value = "idRole") Long idRole) {

        EditRoleDTO editRole = new EditRoleDTO();
        Role role = (Role) metier.findEntityById(idRole, Role.class);
        if (role == null) {
            editRole.setMsg("Role null");
            editRole.setResultat(BasicResponse.RETOUR_EXCEPTION);
            return editRole;
        }

        idRoleSaisi = idRole;
        editRole.setNameRole(role.getNameRole());
        editRole.setRoleDesc(role.getRoleDesc());
        editRole.setMsg("Visualisation du role passée avec succés");
        return editRole;
    }

    @RequestMapping(value = "/editRole", method = RequestMethod.POST, consumes = "application/json",  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    EditRoleDTO editRoleForm(@RequestBody  EditRoleDTO editRoleForm) {

        try {
            if (editRoleForm != null) {
                String nom = editRoleForm.getNameRole();
                System.out.println("-----------------------------------nom role " + nom);
                String desc = editRoleForm.getRoleDesc();
                System.out.println("roleForm description role " + desc);
                Role editRoleSelect = (Role) metier.findEntityById(idRoleSaisi, Role.class);
                String nomRoleSelected = editRoleSelect.getNameRole();
                String descRoleSelected = editRoleSelect.getRoleDesc();

                if (!(nom.equals(nomRoleSelected)) || !(desc.equals(descRoleSelected))) {

                    if (metier.isRoleWithNameExist(editRoleForm.getNameRole(), idRoleSaisi)) {

                        editRoleForm.setMsg("Un role existe avec le meme nom  [" + editRoleForm.getNameRole() + "] vous devez utiliser un autre nom");

                        editRoleForm.setResultat(RoleForm.RETOUR_OBJECTNAME_INVALID);
                        return editRoleForm;
                    }
                    editRoleSelect.setNameRole(nom);
                    editRoleSelect.setRoleDesc(desc);
                    metier.updateRole(editRoleSelect);
                    // metier.sendAccountUpdated(editUser, "Modification des informations du compte");
                    editRoleForm.setMsg("Modification passée avec succés");
                    editRoleForm.setResultat(Subscriber.RETOUR_OK);
                    return editRoleForm;
                } else {
                    editRoleForm.setMsg("Aucune modification des informations opérée");
                    editRoleForm.setResultat(Subscriber.RETOUR_OK);
                    return editRoleForm;
                }
            } else {
                System.out.println("-----------------------------------null role ");
                return editRoleForm;
            }

        } catch (Exception exception) {
            editRoleForm.setMsg(exception.getMessage());
            return editRoleForm;
        } finally {
            return editRoleForm;
        }
    }
    

}
