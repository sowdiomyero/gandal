package sn.idyal.framework.users.admin;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import sn.idyal.framework.manager.user.account.IAccountManager;
import sn.idyal.framework.users.Action;
import sn.idyal.framework.users.Groupe;
import sn.idyal.framework.users.Role;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 06/05/14
 * Time: 22:49
 * To change this template use File | Settings | File Templates.
 */
@Named("roleController")
@SessionScoped
public class RoleController implements Serializable{
    private static Logger logger = Logger.getLogger(RoleController.class.getName());
    private boolean skip;
    private boolean showUnitDetailPanel;

    private List<Role> roleList;
    private List<Role> roles;
    private Role role ;
    private Role newRole = new Role();
    private List<Action> actionList ;
    private List<Groupe> groupeSource ;
    private List<Groupe> groupeTarget ;
    private DualListModel<Groupe> groupes;

    @Inject
    IAccountManager accountManager;
    private boolean onShow=false;

    @PostConstruct
    public void init(){
        roles= accountManager.getAllRole();
        roleList= accountManager.getAllRole();
        actionList= accountManager.getAll(Action.class);

        role= roles.size()>0 ? roles.get(1) : new Role();
        groupeSource= accountManager.getAll(Groupe.class);
        groupeTarget=role.getGroupes();
        filterSourceGroupe(groupeTarget);
        groupes=new DualListModel<Groupe>(groupeSource,groupeTarget);
        initNewRole();
    }

    private void initNewRole() {
        newRole= new Role();
        //newUser.setRoles(accountManager.getDefaultRole());
    }

    public List<Groupe> getGroupeList() {
        return groupeSource;
    }

    public void setGroupeList(List<Groupe> groupeList) {
        this.groupeSource = groupeList;
    }

    public List<Groupe> getRoleGroupe() {
        return groupeTarget;
    }

    public void setRoleGroupe(List<Groupe> roleGroupe) {
        this.groupeTarget = roleGroupe;
    }

    public DualListModel<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(DualListModel<Groupe> groupes) {
        this.groupes = groupes;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> mRoles) {
        this.roles = mRoles;
    }



    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role mRole) {
        this.role = mRole;
    }


    public Role getNewRole() {
        return newRole;
    }

    public void setNewRole(Role mNewRole) {
        this.newRole = mNewRole;
    }



    public boolean isShowUnitDetailPanel() {
        return showUnitDetailPanel;
    }

    public void setShowUnitDetailPanel(boolean showUnitDetailPanel) {
        this.showUnitDetailPanel = showUnitDetailPanel;
    }


    public void updateRole() {
        for(Groupe g : this.groupes.getTarget()){
            System.out.println("Dans Target Groupe : "+g.getName());
        }
        role.setGroupes(this.groupes.getTarget());
        accountManager.update(role);
        refreshTableAndStaySameRow();
        FacesMessage msg = new FacesMessage("Successful", "Mise à jour effectuée avec succès ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void refreshTable() {
        int index= getIndexSelected(role);
        System.out.println("index de position : "+index);
        roles= accountManager.getAllRole();
        System.out.println("index de position : " + index);
        role= roles.size()>0 ? roles.get(index-1) : new Role();
        groupeSource= accountManager.getAll(Groupe.class);
        groupeTarget=role.getGroupes();
        groupes=new DualListModel<Groupe>(groupeSource,groupeTarget);
    }

    private void refreshTableAndStaySameRow() {
        int index= getIndexSelected(role);
        System.out.println("index de position : "+index);
        roles= accountManager.getAllRole();
        System.out.println("index de position : " + index);
        role= roles.size()>0 ? roles.get(index) : new Role();
        groupeSource= accountManager.getAll(Groupe.class);
        groupeTarget=role.getGroupes();
        groupes=new DualListModel<Groupe>(groupeSource,groupeTarget);
    }

    private int getIndexSelected(Role role) {
        for(int i=0; i<roles.size(); i++){
          if(roles.get(i).getRolename().equalsIgnoreCase(role.getRolename()))
             return i;
        }
        return 0;
    }

    public void deleteRole(ActionEvent actionEvent) {
           // accountManager.delete(role);
            refreshTable();
            FacesMessage msg = new FacesMessage("Attention", "La Suppression de rôles n'est pas permise .... ");
            FacesContext.getCurrentInstance().addMessage(null, msg);

    }
    public void debugButton() {
        System.out.println("Selection sur un Bouton  ......");
        onShow=true;
        //role = (User) selectEvent.getObject();
       // System.out.println("Button Selected : [ "+selectEvent.getComponent().getRendererType()+" ]");
    }
    public void  handleAjaxRequest(SelectEvent selectEvent) {
        System.out.println("onRowSelected event ......");
        role = (Role) selectEvent.getObject();
        groupeTarget=role.getGroupes();
        groupeSource= accountManager.getAll(Groupe.class);
        filterSourceGroupe(groupeTarget);
        groupes=new DualListModel<Groupe>(groupeSource,groupeTarget);
        System.out.println("Role Selected : [ "+role.getRolename()+" ]");
    }
    private void filterSourceGroupe(List<Groupe> targetGroupe){
        for(Groupe gr : targetGroupe){
            if (groupeSource.contains(gr)){
                groupeSource.remove(gr);
            }
        }
    }



    public void createRole(){
        newRole.setGroupes(this.groupes.getTarget());
        accountManager.add(newRole);
        newRole=new Role();
        initNewRole();
        refreshTable();
        FacesMessage msg = new FacesMessage("Successful", "Role Created Successfully..");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }








}
