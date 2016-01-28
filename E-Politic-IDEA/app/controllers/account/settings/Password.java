package controllers.account.settings;

import controllers.Secured;
import models.Compte;
import models.Token;
import play.Logger;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.account.settings.password;

import java.net.MalformedURLException;

/**
 * Compte: yesnault
 * Date: 15/05/12
 */
@Security.Authenticated(Secured.class)
public class Password extends Controller {

    /**
     * Password Page. Ask the user to change his password.
     *
     * @return index settings
     */
    public static Result index() {
        return ok(password.render(Compte.findByEmail(request().username())));
    }

    /**
     * Send a mail with the reset link.
     *
     * @return password page with flash error or success
     */
    public static Result runPassword() {
        Compte compte = Compte.findByEmail(request().username());
        try {
            Token.sendMailResetPassword(compte);
            flash("success", Messages.get("resetpassword.mailsent"));
            return ok(password.render(compte));
        } catch (MalformedURLException e) {
            Logger.error("Cannot validate URL", e);
            flash("error", Messages.get("error.technical"));
        }
        return badRequest(password.render(compte));
    }
}
