package sn.gandal.gesimmo.form;

import sn.gandal.gesimmo.dto.BasicResponse;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 06/02/15
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class MessagerieForm extends BasicResponse {



    public enum SECURITY_TYPE {
        TLS, SSL, NONE
    }

    private String idMessagerie;
    //@NotNull @NotEmpty
    private Integer port;
    @Email
    private String email;
    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String host;


    private SECURITY_TYPE securite;


    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public SECURITY_TYPE getSecurite() {
        return securite;
    }

    public void setSecurite(SECURITY_TYPE securite) {
        this.securite = securite;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIdMessagerie() {
        return idMessagerie;
    }

    public void setIdMessagerie(String idMessagerie) {
        this.idMessagerie = idMessagerie;
    }
}
