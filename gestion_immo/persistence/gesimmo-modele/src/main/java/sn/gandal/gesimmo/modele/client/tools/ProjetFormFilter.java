package sn.gandal.gesimmo.modele.client.tools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author isene
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjetFormFilter {

    private String recherche;
    private String message;
    private String nomProjet;
    private String description;
    private String etatActuel = "TOUT";
    private double budgetPrevuMin = 0;
    private double budgetPrevuMax = 0;
    private double nbreEmploiPrevuMin = 0;
    private double nbreEmploiPrevuMax = 0;
    private double ponderationMin = 0;
    private double ponderationMax = 100;
    private double tauxMin = 0;
    private double tauxMax = 100;

    private String dateDebutMin  ;
    private String dateDebutMax ;
    private String dateFinMin ;
    private String dateFinMax ;
    private String localite;
    private String typeLocalite;
    private List<String> typeIncidentOuLocalite = new ArrayList<String>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecherche() {
        return recherche;
    }

    public void setRecherche(String recherche) {
        this.recherche = recherche;
    }
    

    
    public List<String> getTypeIncidentOuLocalite() {
        return typeIncidentOuLocalite;
    }

    public void setTypeIncidentOuLocalite(List<String> typeIncidentOuLocalite) {
        this.typeIncidentOuLocalite = typeIncidentOuLocalite;
    }
    
    public String getTypeLocalite() {
        return typeLocalite;
    }

    public void setTypeLocalite(String typeLocalite) {
        this.typeLocalite = typeLocalite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateDebutMin() {
        return dateDebutMin;
    }

    public void setDateDebutMin(String dateDebutMin) {
        this.dateDebutMin = dateDebutMin;
    }

    public String getDateDebutMax() {
        return dateDebutMax;
    }

    public void setDateDebutMax(String dateDebutMax) {
        this.dateDebutMax = dateDebutMax;
    }

    public String getDateFinMin() {
        return dateFinMin;
    }

    public void setDateFinMin(String dateFinMin) {
        this.dateFinMin = dateFinMin;
    }

    public String getDateFinMax() {
        return dateFinMax;
    }

    public void setDateFinMax(String dateFinMax) {
        this.dateFinMax = dateFinMax;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getEtatActuel() {
        return etatActuel;
    }

    public void setEtatActuel(String etatActuel) {
        this.etatActuel = etatActuel;
    }

    public double getBudgetPrevuMin() {
        return budgetPrevuMin;
    }

    public void setBudgetPrevuMin(double budgetPrevuMin) {
        this.budgetPrevuMin = budgetPrevuMin;
    }

    public double getBudgetPrevuMax() {
        return budgetPrevuMax;
    }

    public void setBudgetPrevuMax(double budgetPrevuMax) {
        this.budgetPrevuMax = budgetPrevuMax;
    }

    public double getNbreEmploiPrevuMin() {
        return nbreEmploiPrevuMin;
    }

    public void setNbreEmploiPrevuMin(double nbreEmploiPrevuMin) {
        this.nbreEmploiPrevuMin = nbreEmploiPrevuMin;
    }

    public double getNbreEmploiPrevuMax() {
        return nbreEmploiPrevuMax;
    }

    public void setNbreEmploiPrevuMax(double nbreEmploiPrevuMax) {
        this.nbreEmploiPrevuMax = nbreEmploiPrevuMax;
    }

    public double getPonderationMin() {
        return ponderationMin;
    }

    public void setPonderationMin(double ponderationMin) {
        this.ponderationMin = ponderationMin;
    }

    public double getPonderationMax() {
        return ponderationMax;
    }

    public void setPonderationMax(double ponderationMax) {
        this.ponderationMax = ponderationMax;
    }

    public double getTauxMin() {
        return tauxMin;
    }

    public void setTauxMin(double tauxMin) {
        this.tauxMin = tauxMin;
    }

    public double getTauxMax() {
        return tauxMax;
    }

    public void setTauxMax(double tauxMax) {
        this.tauxMax = tauxMax;
    }

}
