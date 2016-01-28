package controllers;

import models.Compte;
import models.Membre;
import models.Membre.ACTIF;
import models.utils.AppException;
import play.Logger;
import play.data.Form;
import play.data.validation.Constraints;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.membre.*;
import static play.data.Form.form;

/**
 * Login and Logout. Compte: yesnault
 */
public class Application extends Controller {

	public static Result GO_DASHBOARD = redirect(routes.Localisation
			.cartographie());
	public static Result GO_HOME = redirect(routes.Application.index());
	public static Result GO_ESPACE_MEMBRE = redirect(routes.DashboardMembre
			.dashboard());

	/**
	 * Display the login page or dashboard if connected
	 * 
	 * @return login page or dashboard
	 */
	public static Result index() {
		// Check that the email matches a confirmed user before we redirect
		String email = ctx().session().get("email");
		if (email != null) {
			Compte compte = Compte.findByEmail(email);
			if (compte != null && compte.validated) {
				return GO_DASHBOARD;

			} else {
				Logger.debug("Clearing invalid session credentials");
				session().clear();
			}
		}

		return ok(index.render(form(Register.class), form(Login.class)));
	}

	/**
	 * Login class used by Login Form.
	 */
	public static class Login {

		@Constraints.Required
		public String email;
		@Constraints.Required
		public String password;

		/**
		 * Validate the authentication.
		 * 
		 * @return null if validation ok, string with details otherwise
		 */
		public String validate() {

			Compte compte = null;
			try {
				compte = Compte.authenticate(email, password);
			} catch (AppException e) {
				return Messages.get("error.technical");
			}

			if (compte == null) {
				return Messages.get("invalid.compte.or.password");
			} else if (!compte.validated) {
				return Messages.get("account.not.validated.check.mail");
			} else {
				if (compte.getDisabled() == true)
					return Messages.get("account.disabled");
			}
			return null;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}

	public static class Register {

		@Constraints.Required
		public String email;

		@Constraints.Required
		public String fullname;

		@Constraints.Required
		public String inputPassword;

		/**
		 * Validate the authentication.
		 * 
		 * @return null if validation ok, string with details otherwise
		 */
		public String validate() {
			if (isBlank(email)) {
				return "Email is required";
			}

			if (isBlank(fullname)) {
				return "Full name is required";
			}

			if (isBlank(inputPassword)) {
				return "Password is required";
			}

			return null;
		}

		private boolean isBlank(String input) {
			return input == null || input.isEmpty() || input.trim().isEmpty();
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFullname() {
			return fullname;
		}

		public void setFullname(String flname) {
			this.fullname = flname;
		}

		public String getInputPassword() {
			return inputPassword;
		}

		public void setInputPassword(String pwd) {
			this.inputPassword = pwd;
		}

	}

	/**
	 * Handle login form submission.
	 * 
	 * @return Dashboard if auth OK and profil=ADMIN or Espace Membre if auth ok
	 *         and profil=RESPONSABLE or login form if auth KO
	 */
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();

		Form<Register> registerForm = form(Register.class);

		if (loginForm.hasErrors()) {
			return badRequest(index.render(registerForm, loginForm));
		} else {
			session("email", loginForm.get().email);
			Compte compte = Compte.findByEmail(loginForm.get().email);

			if (compte.isAdmin()) {
				return GO_DASHBOARD;
			} else {
				if (compte.isResponsable()) {
					return GO_ESPACE_MEMBRE;

				} else {
					session().clear();
					flash("success", Messages.get("not.profil"));
					return GO_HOME;
				}

			}

		}
	}

	/**
	 * Logout and clean the session.
	 * 
	 * @return Index page
	 */
	public static Result logout() {
		String email = ctx().session().get("email");
		if (email != null) {
			Logger.info("Email user : " + email);
			Compte compte = Compte.findByEmail(email);
			Logger.info("Compte trouvé ? : " + compte == null ? "NON" : "OUI");
			Membre membre = compte.getMembre();
			if (membre != null)
				compte.getMembre().setStatus(Membre.STATUS.OFFLINE);
			compte.update();
		}
		session().clear();
		flash("success", Messages.get("youve.been.logged.out"));
		return GO_HOME;
	}

}