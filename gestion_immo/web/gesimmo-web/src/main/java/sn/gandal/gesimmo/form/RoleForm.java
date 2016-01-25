package sn.gandal.gesimmo.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import sn.gandal.gesimmo.dto.BasicResponse;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 06/02/15
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class RoleForm extends BasicResponse {   

    @NotNull @NotEmpty
    private String roleDesc;
    @NotNull @NotEmpty
    private String nameRole;
    private Long idRole;
    private String dateCreation;
    private String dateUpdated;
    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public RoleForm() {
    }

    
    @Override
    public String toString() {
        return "Ajout du role [ " + nameRole  + "]";
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

   
}
