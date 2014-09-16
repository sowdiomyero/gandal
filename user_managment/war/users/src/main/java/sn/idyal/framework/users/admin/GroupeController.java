package sn.idyal.framework.users.admin;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import sn.idyal.framework.manager.user.account.IAccountManager;
import sn.idyal.framework.users.Role;
import sn.idyal.framework.users.User;

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

 // BEL-53
@Named("groupeController")
@SessionScoped
public class GroupeController implements Serializable{

    private static Logger logger = Logger.getLogger(GroupeController.class.getName());
    private boolean skip;
    private boolean showUnitDetailPanel;
    private List<User> users;
    private List<Role> roleList;
    private User user ;
    private User newUser = new User();
 //   private List<User> userToDisplay ;

    @Inject
    IAccountManager accountManager;
    private boolean onShow=false;

    @PostConstruct
    public void init(){
        users= accountManager.getAllUser();
        roleList= accountManager.getAllRole();
        user= users.size()>0 ? users.get(1) : new User();
        initNewUser();
    }


    private void initNewUser() {
        newUser= new User();
        newUser.setRoles(accountManager.getDefaultRole());
    }
    // BEL-45
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isOnShow() {
        return onShow;
    }

    public void setOnShow(boolean onShow) {
        this.onShow = onShow;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getNewUser() {
         return newUser;
    }

    public void setNewUser(User newUser) {
         this.newUser = newUser;
    }

    public boolean isShowUnitDetailPanel() {
        return showUnitDetailPanel;
    }

    public void setShowUnitDetailPanel(boolean showUnitDetailPanel) {
        this.showUnitDetailPanel = showUnitDetailPanel;
    }


    public void updateUser() {
        accountManager.update(user);
        
        refreshTableAndStaySameRow();
        FacesMessage msg = new FacesMessage("Successful", "Mise à jour effectuée avec succès ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void refreshTable() {
        int index= getIndexSelected(user);
        System.out.println("index de position : "+index);
        users= accountManager.getAllUser();
        System.out.println("index de position : "+index);
        user= users.size()>0 ? users.get(index-1) : new User();
    }

    private void refreshTableAndStaySameRow() {
        int index= getIndexSelected(user);
        System.out.println("index de position : "+index);
        users= accountManager.getAllUser();
        System.out.println("index de position : "+index);
        user= users.size()>0 ? users.get(index) : new User();
    }

    private int getIndexSelected(User user) {
        for(int i=0; i<users.size(); i++){
          if(users.get(i).getLogin().equalsIgnoreCase(user.getLogin()))
             return i;
        }
        return 0;
    }

    public void deleteUser(ActionEvent actionEvent) {
        //Persist user
        if(user.getId()==1 || user.getLogin().equalsIgnoreCase("admin")){
            FacesMessage msg = new FacesMessage("Attention .... ", "L'administrateur par defaut ne peut être supprimé de la liste des utilisateurs ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else{
            accountManager.delete(user);
            refreshTable();
            FacesMessage msg = new FacesMessage("Successful", "Welcome Test Phase .... "+actionEvent.getSource().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void debugButton() {
        System.out.println("Selection sur un Bouton  ......");
        onShow=true;
        //user = (User) selectEvent.getObject();
       // System.out.println("Button Selected : [ "+selectEvent.getComponent().getRendererType()+" ]");
    }
    public void  handleAjaxRequest(SelectEvent selectEvent) {
        System.out.println("onRowSelected event ......");
        user = (User) selectEvent.getObject();
        System.out.println("User Selected : [ "+user.getLogin()+" - Surveillé ? "+user.isLogged()+" ]");
    }


    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());

        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();

        }
    }

    public void createUser(){
        accountManager.add(newUser);
        newUser=new User();
        initNewUser();
        refreshTable();
        onShow=false;
        FacesMessage msg = new FacesMessage("Successful", "User created ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void surveiller(){
        user.setLogged(true);
        accountManager.update(user);
        refreshTable();
        FacesMessage msg = new FacesMessage("Successful", "Utilisateur dans la liste à surveiller");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void bloquer(){
        user.setActif(false);
        accountManager.update(user);
        refreshTable();
        FacesMessage msg = new FacesMessage("Successful", "Le Compte de Cet Utilisateur est maintenant Bloqué");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public void debloquer(){
        user.setActif(true);
        accountManager.update(user);
        refreshTable();
        FacesMessage msg = new FacesMessage("Successful", "Le compte de cet Utilisateur a été débloqué");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



}
