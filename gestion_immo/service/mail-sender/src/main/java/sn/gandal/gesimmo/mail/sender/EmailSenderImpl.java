package sn.gandal.gesimmo.mail.sender;

import org.springframework.scheduling.annotation.Async;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import sn.gandal.gesimmo.modele.client.entities.ParametreMail;
import sn.gandal.gesimmo.modele.client.entities.User;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA. User: DYSOW Date: 23/02/15 Time: 13:10 To change
 * this template use File | Settings | File Templates.
 */
@Service
public class EmailSenderImpl implements IEmailSender {

    private Session session;
    Logger log = Logger.getLogger(EmailSenderImpl.class.getName());

    @Override
    public void test() {

    }

    public static enum TYPE_MAIL {

        ACCOUNT_CREATION,
        ACCOUNT_UPDATE,
        GENERATOR_PASSWORD,
        ACCOUNT_DESABLE,
        ACCOUNT_ENABLE,

    }

    @Async
    public void send(User user, String subject, String passwordGenerated, TYPE_MAIL typemail, ParametreMail params) {
        try {
            sendMessage(user, subject, typemail, passwordGenerated, params);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private void sendMessage(User user, String object, TYPE_MAIL typeMail, String passwordGenerated, ParametreMail params) throws MessagingException {
        String status = "MAIL_SENT_OK";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", params.getServeurMail());
        props.put("mail.smtp.port", params.getPortSmtp());

        final String username = params.getNomUtilisateur();
        final String password = params.getPassword();

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        Logger.getLogger(EmailSenderImpl.class.getName()).log(Level.INFO, "TEST++++++++++++++++++++++++++++++++Recuperation propriete avec suucces PASSWORD DAME:::::::  " + params.getPassword());

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(params.getNomUtilisateur()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getUserMail()));
            message.setSubject("[gesimmo]" + object, "UTF-8");

            message.setContent(buildHTMLText(user, passwordGenerated, typeMail), "text/html; charset=UTF-8");

            Transport.send(message);
            log.log(Level.INFO, "Mail Envoye avec succes apres transport");
        } catch (MessagingException e) {
            status = "MAIL_SENT_NOK";
            log.log(Level.INFO, "Une exception pendant l'envoi du Mail : " + e.getMessage());
            // Enregistrer dans la base de données toutes les notifications ayant échouées
        } catch (Exception e) {
            status = "MAIL_SENT_NOK";
            log.log(Level.SEVERE, "Une exception pendant l'envoi du Mail : " + e.getMessage());
            // Enregistrer dans la base de données toutes les notifications ayant échouées
        } finally {
            log.log(Level.SEVERE, "ENVOI DE MAIL : ETAT =  " + status);
        }

    }

    private String buildHTMLText(User user, String passwordGenerated, TYPE_MAIL typeMail) {
        String message = "";
        switch (typeMail) {
            case ACCOUNT_CREATION: {
                message += "<h3>Bonjour " + user.getUserPrenom() + " " + user.getUserName() + "\n,<br/> " + "\n<br/> "
                        + "  Bienvenue dans gesimmo : <br/>" + "\n<br/> "
                        + "  Votre compte a été crée avec succés." + "\n<br/> " + "\n<br/> "
                        + "  Vous trouverez ci dessous vos param�tres de connexion : <br/>" + "\n<br/> "
                        + "  Login : " + user.getCompte().getLogin() + "\n<br/> "
                        + "  Mot de passe : " + passwordGenerated + "<br/>" + "\n<br/> "
                        + "Bonne Reception.</h3>";
            }
            break;
            case ACCOUNT_DESABLE: {
                message += "<h3>Bonjour " + user.getUserPrenom() + " " + user.getUserName() + "\n,<br/> " + "\n<br/> "
                        + "  Bienvenue dans gesimmo : <br/>" + "\n<br/> "
                        + "  Votre compte a été crée avec désactivé." + "\n<br/> " + "\n<br/> "
                        + "  Veuillez prendre contact avec votre adminstrateur : <br/>" + "\n<br/> "
                        + "Bonne Reception.</h3>";
            }
            break;
            case ACCOUNT_ENABLE: {
                message += "<h3>Bonjour " + user.getUserPrenom() + " " + user.getUserName() + "\n,<br/> " + "\n<br/> "
                        + "  Bienvenue dans gesimmo : <br/>" + "\n<br/> "
                        + "  Votre compte a été crée avec réactiver." + "\n<br/> " + "\n<br/> "
                        + "  Veuillez vous connecter avec vos identifiants   : <br/>" + "\n<br/> "
                        + "Bonne Reception.</h3>";
            }
            break;
            case ACCOUNT_UPDATE: {
                message += "<h3>  Bienvenue dans gesimmo : <br/>" + "\n<br/> "
                        + "  Votre compte a été mis à jour avec succés." + "\n<br/> " + "<h3>";

            }
            break;
            case GENERATOR_PASSWORD: {
                message += "<h3>  Bienvenue dans gesimmo : <br/>" + "\n<br/> "
                        + "  Votre mot de passe a été   regénéré avec succés: <br/>" + "\n<br/> "
                        + "  Voici le nouveau mot de passe : " + passwordGenerated + "<br/>" + "\n<br/> "
                        + "  le login reste le meme.: <br/>" + "\n<br/> "
                        + "Bonne Reception.</h3>";
            }
            break;

            default: {
                message += "<h3>  Bienvenue dans gesimmo : <br/>" + "\n<br/> "
                        + " Des modifications ont été   apportées sur votre profil gesimmo." + "\n<br/> "
                        + "Vous devez vous logguer pour constater les modifications. " + "\n<br/> "
                        + "Vos paramétres de connexion restent les memes.  <br/>" + "\n<br/> " + "<h3>";
            }
        }

        return message;
    }

}
