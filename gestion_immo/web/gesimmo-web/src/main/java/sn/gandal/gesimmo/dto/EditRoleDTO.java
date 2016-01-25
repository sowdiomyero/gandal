package sn.gandal.gesimmo.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 06/02/15
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class EditRoleDTO {

   
    @NotNull @NotEmpty
    private String nameRole;
    @NotNull @NotEmpty
    private String roleDesc;
    private String msg;
    private int resultat;
    private long idRole;
    
    public EditRoleDTO() {
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    public long getIdRole() {
        return idRole;
    }

    public void setIdRole(long idRole) {
        this.idRole = idRole;
    }
  
 
}
