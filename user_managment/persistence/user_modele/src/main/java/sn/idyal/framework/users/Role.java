package sn.idyal.framework.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = Role.ALL, query = "SELECT r FROM Role r"),
                @NamedQuery(name = Role.FIND_BY_ROLENAME, query = "SELECT r FROM Role r where r.rolename=:rolename")
        }
)
public class Role extends BaseEntity implements Serializable {

    public final static String ALL = "Role.populateRoles";
    public final static String FIND_BY_ROLENAME = "Role.getDefaultRole";
    public final static String DEFAULT_USER = "User";

    private String roledesc;
    private String rolename;

    @ManyToMany
    @JoinTable(name = "role_action",
            joinColumns = {
                    @JoinColumn(name = "role_id")}, inverseJoinColumns = {
                    @JoinColumn(name = "action_id")
    }
    )
    private List<Action> actions;

    @ManyToMany
    @JoinTable(name = "role_groupe",
            joinColumns = {
                    @JoinColumn(name = "role_id")}, inverseJoinColumns = {
            @JoinColumn(name = "groupe_id")
    }
    )
    private List<Groupe> groupes;

    public Role() {
    }

    public Role(Integer roleid, String rolename) {
        this.rolename = rolename;
    }
    public Role(String rolename) {
        this.rolename = rolename;
    }

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public String getRoledesc() {
        return this.roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public String getRolename() {
        return this.rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }
}