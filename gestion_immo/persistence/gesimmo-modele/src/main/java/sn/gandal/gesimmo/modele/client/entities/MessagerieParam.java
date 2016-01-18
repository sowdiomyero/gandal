package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 10/03/15
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "messagerie_param")
public class MessagerieParam extends AbstractDateEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_param")
    private Long idParamSmtp;

    @Column(name="param_smtp_username")
    private String paramSmtpUsername;
    @Column(name="param_smtp_password")
    private String paramSmtpPassword;
    @Column(name="param_smtp_transport")
    private String paramSmtpTransport;
    @Column(name="param_smtp_email")
    private String paramSmtpEmail;
    @Column(name="param_smtp_ssl")
    private boolean paramSmtpSsl;
    @Column(name="param_smtp_host")
    private String paramSmtpHost;
    @Column(name="param_smtp_port")
    private Integer paramSmtpPort;



    public MessagerieParam() {
    }

    public String getParamSmtpUsername() {
        return paramSmtpUsername;
    }

    public void setParamSmtpUsername(String paramSmtpUsername) {
        this.paramSmtpUsername = paramSmtpUsername;
    }

    public String getParamSmtpPassword() {
        return paramSmtpPassword;
    }

    public void setParamSmtpPassword(String paramSmtpPassword) {
        this.paramSmtpPassword = paramSmtpPassword;
    }

    public String getParamSmtpTransport() {
        return paramSmtpTransport;
    }

    public void setParamSmtpTransport(String paramSmtpTransport) {
        this.paramSmtpTransport = paramSmtpTransport;
    }

    public String getParamSmtpEmail() {
        return paramSmtpEmail;
    }

    public void setParamSmtpEmail(String paramSmtpEmail) {
        this.paramSmtpEmail = paramSmtpEmail;
    }

    public boolean isParamSmtpSsl() {
        return paramSmtpSsl;
    }

    public void setParamSmtpSsl(boolean paramSmtpSsl) {
        this.paramSmtpSsl = paramSmtpSsl;
    }

    public String getParamSmtpHost() {
        return paramSmtpHost;
    }

    public void setParamSmtpHost(String paramSmtpHost) {
        this.paramSmtpHost = paramSmtpHost;
    }

    public Integer getParamSmtpPort() {
        return paramSmtpPort;
    }

    public void setParamSmtpPort(Integer paramSmtpPort) {
        this.paramSmtpPort = paramSmtpPort;
    }

    public Long getIdParamSmtp() {
        return idParamSmtp;
    }





    @Override
    public String toString() {
        return "MessagerieParam {" +
                "idParamSmtp=" + idParamSmtp +
                ", paramSmtpUsername='" + paramSmtpUsername + '\'' +
                ", paramSmtpPassword='" + paramSmtpPassword + '\'' +
                ", paramSmtpTransport='" + paramSmtpTransport + '\'' +
                ", paramSmtpEmail='" + paramSmtpEmail + '\'' +
                ", paramSmtpSsl=" + paramSmtpSsl +
                ", paramSmtpHost='" + paramSmtpHost + '\'' +
                ", paramSmtpPort=" + paramSmtpPort +
                '}';
    }
}
