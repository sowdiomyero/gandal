/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author ddiaw
 */
@Entity
@Table(name = "localisation_indicateur")
@NamedQueries({
    @NamedQuery(name = LocalisationIndicateur.FIND_LOCALISATION_INDICATEUR_BY_ID_LOC, query = "SELECT li FROM LocalisationIndicateur li where li.localisation.idLocalisation = :idLocalisation"),
     @NamedQuery(name = LocalisationIndicateur.FIND_LOCALISATION_INDICATEUR_BY_ID, query = "SELECT li FROM LocalisationIndicateur li where li.idLocalisationIndicateur = :idLocalisationIndicateur")
    
})
public class LocalisationIndicateur extends AbstractDateEntity implements Serializable {

    public static final String FIND_LOCALISATION_INDICATEUR_BY_ID_LOC = "findLocalisationIndicateurByIdLoc";
    public static final String FIND_LOCALISATION_INDICATEUR_BY_ID = "findLocalisationIndicateurById";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_localisation_indicateur")
    private Long idLocalisationIndicateur;
    @Column(name = "valeur")
    private String valeur;
    @ManyToOne
    @JoinColumn(name = "id_localisation")
    private Localisation localisation;
    @ManyToOne
    @JoinColumn(name = "id_indicateur")
    private Indicateur indicateur;

    public LocalisationIndicateur() {
    }

    public Long getIdLocalisationIndicateur() {
        return idLocalisationIndicateur;
    }

    public void setIdLocalisationIndicateur(Long idLocalisationIndicateur) {
        this.idLocalisationIndicateur = idLocalisationIndicateur;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }

}
