package views.forms;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 02/06/15
 * Time: 11:31
 * To change this template use File | Settings | File Templates.
 */
public class ProjetForm {

    private String nomProjet;

    private String descProjet;

    private Date dateDebut;

    private Date dateFin;

    private String contact;

    private String autorite;

    public ProjetForm() {
    }
    private boolean isBlank(String input) {
        return input == null || input.isEmpty() || input.trim().isEmpty();
    }
    public String validate() {
        if (isBlank(nomProjet)) {
            return "Project Name is required";
        }

        if (isBlank(descProjet)) {
            return "Project desciption is required";
        }

        if (isBlank(contact)) {
            return "Project Contact is required";
        }

        return null;
    }
    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getDescProjet() {
        return descProjet;
    }

    public void setDescProjet(String descProjet) {
        this.descProjet = descProjet;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAutorite() {
        return autorite;
    }

    public void setAutorite(String autorite) {
        this.autorite = autorite;
    }
}
