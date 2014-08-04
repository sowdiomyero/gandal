package sn.idyal.framework.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@FacesConverter(value = "sn.idyal.framework.utils.SHAConverter")
public class SHAConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if(value.equalsIgnoreCase(""))
            return "";
        else if (value.length()<4){
            FacesContext.getCurrentInstance().addMessage("newPassword", new FacesMessage("le nombre de caractere du mot de passe doit etre superieur à 3!"));
            return value;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(value.getBytes("UTF-8"));
            StringBuilder stringBuilder=  new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println(" passwd en Hash est : "+stringBuilder.toString());
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHAConverter.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            Logger.getLogger(SHAConverter.class.getName()).log(Level.SEVERE, null,"SHAConverter.getAsObject:"+ unsupportedEncodingException);
            return "";
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return "";
    }
}
