package sn.gandal.gesimmo.mail.sender;


import sn.gandal.gesimmo.mail.sender.EmailSenderImpl.TYPE_MAIL;
 import sn.gandal.gesimmo.modele.client.entities.ParametreMail;
import sn.gandal.gesimmo.modele.client.entities.User;
 
/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 23/02/15
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */

public interface IEmailSender {
    public void send(User user, String subject,String passwordGenerated,TYPE_MAIL typemail, ParametreMail params);
        public void test();


}
