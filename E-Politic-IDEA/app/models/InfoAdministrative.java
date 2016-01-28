package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 02/06/15
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
@Embeddable
public class InfoAdministrative{

    @Column(name = "INFO_ADM_POPULATION")
    public int population;
    @Column(name = "INFO_ADM_NB_INSCRITS")   // les inscriptions dans la campagne courantequi seront ajouté dans le fichier electoral
    public int nbInscrits;
    @Column(name = "INFO_ADM_NB_ELECTEURS")  // ce sont les personnes inscrite dans le fichier electoral de la campagne precedente.
    public int nbElecteurs;

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getNbInscrits() {
        return nbInscrits;
    }

    public void setNbInscrits(int nbInscrits) {
        this.nbInscrits = nbInscrits;
    }

    public int getNbElecteurs() {
        return nbElecteurs;
    }

    public void setNbElecteurs(int nbElecteurs) {
        this.nbElecteurs = nbElecteurs;
    }
}
