package controllers.account.settings;

import controllers.Secured;
import models.Compte;
import models.Token;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.account.settings.email;
import views.html.account.settings.emailValidate;

import java.net.MalformedURLException;

import static play.data.Form.form;

/**
 * Settings -> Email page.
 * <p/>
 * Compte: yesnault
 * Date: 23/06/12
 */
@Security.Authenticated(Secured.class)
public class Email extends Controller {

    public static class AskForm {
        @Constraints.Required
        public String email;
        public AskForm() {}
        AskForm(String email) {
            this.email = email;
        }
    }

    /**
     * Password Page. Ask the user to change his password.
     *
     * @return index settings
     */
    public static Result index() {
        Compte compte = Compte.findByEmail(request().username());
        Form<AskForm> askForm = form(AskForm.class);
        askForm = askForm.fill(new AskForm(compte.email));
        return ok(email.render(Compte.findByEmail(request().username()), askForm));
    }

    /**
     * Send a mail to confirm.
     *
     * @return email page with flash error or success
     */
    public static Result runEmail() {
        Form<AskForm> askForm = form(AskForm.class).bindFromRequest();
        Compte compte = Compte.findByEmail(request().username());

        if (askForm.hasErrors()) {
            flash("error", Messages.get("signup.valid.email"));
            return badRequest(email.render(compte, askForm));
        }

        try {
            String mail = askForm.get().email;
            Token.sendMailChangeMail(compte, mail);
            flash("success", Messages.get("changemail.mailsent"));
            return ok(email.render(compte, askForm));
        } catch (MalformedURLException e) {
            Logger.error("Cannot validate URL", e);
            flash("error", Messages.get("error.technical"));
        }
        return badRequest(email.render(compte, askForm));
    }

    /**
     * Validate a email.
     *
     * @return email page with flash error or success
     */
    public static Result validateEmail(String token) {
        Compte compte = Compte.findByEmail(request().username());

        if (token == null) {
            flash("error", Messages.get("error.technical"));
            return badRequest(emailValidate.render(compte));
        }

        Token resetToken = Token.findByTokenAndType(token, Token.TypeToken.email);
        if (resetToken == null) {
            flash("error", Messages.get("error.technical"));
            return badRequest(emailValidate.render(compte));
        }

        if (resetToken.isExpired()) {
            resetToken.delete();
            flash("error", Messages.get("error.expiredmaillink"));
            return badRequest(emailValidate.render(compte));
        }

        compte.email = resetToken.email;
        compte.save();

        session("email", resetToken.email);

        flash("success", Messages.get("account.settings.email.successful", compte.email));

        return ok(emailValidate.render(compte));
    }
}
