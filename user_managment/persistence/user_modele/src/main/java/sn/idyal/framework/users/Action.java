package sn.idyal.framework.users;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = Action.ALL, query = "SELECT a FROM Action a"),
                @NamedQuery(name = Action.FIND_BY_NAME, query = "SELECT a FROM Action a where a.name=:name")
        }
)
public class Action extends BaseEntity implements Serializable {

    public final static String ALL = "Action.getAll";
    public final static String FIND_BY_NAME = "Action.getDefaultActions";

    private String name;
    private String description;
    private String display;

    public Action() {
    }

    public Action(Integer id, String name) {
        this.name = name;
    }
    public Action(String rolename) {
        this.name = rolename;
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

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}