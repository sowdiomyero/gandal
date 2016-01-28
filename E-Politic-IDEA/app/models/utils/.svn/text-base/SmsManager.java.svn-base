package models.utils;

import models.Membre;
import models.Personne;
import play.Configuration;
import play.Logger;
import play.libs.Akka;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Send a mail with PlayStartApp.
 * <p/>
 * Compte: yesnault
 * Date: 24/01/12
 */
public class SmsManager {

    /**
     * 1 second delay on sending emails
     */
    private static final int DELAY = 1;

    /**
     * Envelop to prepare.
     */
    public static class Envelop {
        private String message;
        private List<? extends Personne> toEmails ;

        /**
         * Constructor of Envelop.
         *
         * @param message  a message
         * @param toEmails list of emails adress
         */
        public Envelop(String message, List<? extends Personne> toEmails) {

            this.message = message;
            this.toEmails = toEmails;
        }

      /*  public Envelop(String message, Personne membre) {
            this.message = message;
            this.toEmails = new ArrayList<Personne>();
            this.toEmails.add(membre);
        }*/
    }

    /**
     * Send a email, using Akka to offload it to an actor.
     *
     * @param envelop envelop to send
     */
    public static void sendSms(SmsManager.Envelop envelop) {
        EnvelopJob envelopJob = new EnvelopJob(envelop);
        final FiniteDuration delay = Duration.create(DELAY, TimeUnit.SECONDS);
        Akka.system().scheduler().scheduleOnce(delay, envelopJob, Akka.system().dispatcher());
    }

    static class EnvelopJob implements Runnable {
        SmsManager.Envelop envelop;

        public EnvelopJob(SmsManager.Envelop envelop) {
            this.envelop = envelop;
        }

        public void run() {
            final Configuration conf = Configuration.root();
            String kannelServer = conf.getString("kannel.full.host");
            String kannelUsername = conf.getString("kannel.username");
            String kannelPassword = conf.getString("kannel.password");

            Logger.info("Reception d'une demande de contact du serveur kannel pour : Nb Personnes "+envelop.toEmails.size());
            for (Personne membre : envelop.toEmails) {

                StringBuffer param= new StringBuffer();
                try {
                    param.append(kannelServer)
                            .append(URLEncoder.encode("username", "UTF-8")).append("=").append(URLEncoder.encode(kannelUsername,"UTF-8"))
                            .append("&").append(URLEncoder.encode("password","UTF-8")).append("=").append(URLEncoder.encode(kannelPassword,"UTF-8"))
                            .append("&").append(URLEncoder.encode("to","UTF-8")).append("=").append(URLEncoder.encode(membre.getTelephone(),"UTF-8"))
                            .append("&").append(URLEncoder.encode("text","UTF-8")).append("=").append(URLEncoder.encode(envelop.message,"UTF-8").replace("+", "%20"));
                } catch (UnsupportedEncodingException e) {
                    Logger.error("Une exception pendant l'appel au serveur Kannel : " + param.toString());
                    e.printStackTrace();
                }

                WSResponse response=  WS.url(param.toString()).get().get(5000);
                Logger.info("Requete envoyée au serveur : "+param.toString());

                Logger.debug("Mail.sendMail: Mail will be sent to " + membre.getNom()+" Tel : "+membre.getTelephone());
            }

                       Logger.debug("SMS sent - KANNEL:" + kannelServer
                    + ":" + conf.getString("kannel.port")
                    + " user:" + kannelUsername
                    + " password:" + kannelPassword);
        }
    }
}
