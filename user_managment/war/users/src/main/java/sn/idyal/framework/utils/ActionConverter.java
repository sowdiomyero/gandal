package sn.idyal.framework.utils;

import sn.idyal.framework.users.Action;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.HashMap;

@FacesConverter("sn.idyal.framework.utils.ActionConverter")
public class ActionConverter implements Converter {

    private static HashMap<String, Action> map = new HashMap<String, Action>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Action role = map.get(value);
        return role;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Action action = (Action)value;
        map.put(action.getId().toString(), action);
        return action.getId().toString();
    }
}
