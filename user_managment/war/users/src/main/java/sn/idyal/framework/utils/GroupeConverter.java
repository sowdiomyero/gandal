package sn.idyal.framework.utils;

import sn.idyal.framework.users.Groupe;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.HashMap;

@FacesConverter("sn.idyal.framework.utils.GroupeConverter")
public class GroupeConverter implements Converter {

    private static HashMap<String, Groupe> map = new HashMap<String, Groupe>();
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Groupe groupe = map.get(value);
        return groupe;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Groupe groupe = (Groupe)value;
        map.put(groupe.getId().toString(), groupe);
        return groupe.getId().toString();
    }
}
