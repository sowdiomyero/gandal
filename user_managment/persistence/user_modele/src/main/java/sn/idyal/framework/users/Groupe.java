package sn.idyal.framework.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = Groupe.ALL, query = "SELECT g FROM Groupe g"),
                @NamedQuery(name = Groupe.FIND_BY_NAME, query = "SELECT g FROM Groupe g where g.name=:name")
        }
)
public class Groupe extends BaseEntity implements Serializable {

    public final static String ALL = "Groupe.populateRoles";
    public final static String FIND_BY_NAME = "Groupe.getDefaultRole";
    public final static String DEFAULT_USER = "User";

    private String description;
    private String name;
    private String display;

    @ManyToMany
    @JoinTable(name = "groupe_action",
            joinColumns = {
            @JoinColumn(name = "groupe_id")}, inverseJoinColumns = {
            @JoinColumn(name = "action_id")
    }
    )
    private List<Action> actions;


    public Groupe() {
    }

    public Groupe(Integer id, String rolename) {
        this.name = rolename;
    }
    public Groupe(String rolename) {
        this.name = rolename;
    }


    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Groupe)) return false;
        if (!super.equals(o)) return false;

        Groupe groupe = (Groupe) o;

        if (this.getId() != groupe.getId()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + display.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  this.getDisplay() ;
    }
}