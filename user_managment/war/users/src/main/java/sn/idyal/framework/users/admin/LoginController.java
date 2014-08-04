package sn.idyal.framework.users.admin;

import sn.idyal.framework.manager.user.account.IAccountManager;
import sn.idyal.framework.users.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 05/05/14
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    @Inject
    private  transient Logger logger;
    @Inject
    IAccountManager accountManager;
    private String login;
    private String password;
    private User user=null;

    public LoginController() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void connect(ActionEvent actionEvent) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            String navigateString = "";
            System.out.println(login +"paaswd en calor "+password);


            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
            StringBuilder stringBuilder=  new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Libasse 1 password :  40214c47c949eda6d39eda8b26b8c4c2d253fc17fc2c39b1b6eeb72d79cfb18e");
            System.out.println("Libasse 2 password :  "+stringBuilder.toString());

            request.login(login, password);

            Principal principal = request.getUserPrincipal();

            user=accountManager.getUserByLogin(login);
            if( ! user.isActif())  {
                navigateString = "/desactivatedAccount.xhtml";
                request.getSession().invalidate();
            }else if (request.isUserInRole("Admin")) {
                navigateString = "/admin/home.xhtml";
            } else if (request.isUserInRole("Manager")) {
                navigateString = "/admin/home.xhtml";
            } else if (request.isUserInRole("User")) {
                navigateString = "/admin/home.xhtml";
            }
            try {
               // logger.log(Level.INFO, "User ({0}) loging in #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
                context.getExternalContext().redirect(request.getContextPath() + navigateString);
              //  userLog= (User) userService.findUserLog(login);

            } catch (IOException ex) {
              //  logger.log(Level.SEVERE, "IOException, Login Controller" + "login : " + principal.getName(), ex);
                context.addMessage(null, new FacesMessage("Error!", "Exception occured"));
            }
        } catch (ServletException e) {
           // logger.log(Level.SEVERE, e.toString());
            context.addMessage(null, new FacesMessage("Error!", "The username or password you provided does not match our records."));
        }

    }

    public void logout() {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
       //logger.log(Level.INFO, "User ({0}) loging out #" + DateUtility.getCurrentDateTime(), request.getUserPrincipal().getName());
        if (session != null) {
            session.invalidate();
            //userLog=null;
        }
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/Login.xhtml?faces-redirect=true");
    }
}
