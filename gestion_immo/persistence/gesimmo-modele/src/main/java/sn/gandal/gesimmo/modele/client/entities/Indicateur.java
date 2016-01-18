/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ddiaw
 */
@Entity
@Table(name = "indicateur")
@NamedQueries({
    @NamedQuery(name = Indicateur.FIND_INDICATEUR_BY_ID, query = "SELECT i FROM Indicateur i where i.idIndicateur = :idIndicateur"),
    @NamedQuery(name = Indicateur.FIND_ALL_INDICATEUR, query = "SELECT i FROM Indicateur i"),
    @NamedQuery(name = Indicateur.FIND_INDICATEUR_BY_ETAT, query = "SELECT i FROM Indicateur i where i.etat = :etat"),
    @NamedQuery(name = Indicateur.FIND_INDICATEUR_BY_LIBELLE, query = "SELECT i FROM Indicateur i where i.libelle = :libelle"),
    @NamedQuery(name = Indicateur.FIND_INDICATEUR_BY_NAME, query = "SELECT i FROM Indicateur i where i.nomIndicateur = :nomIndicateur"),
    @NamedQuery(name = Indicateur.FIND_INDICATEUR_BY_NAME_EXCEPT_ME, query = "SELECT i FROM Indicateur i where i.nomIndicateur = :nomIndicateur and i.idIndicateur <> :idIndicateur")
})

public class Indicateur extends AbstractDateEntity implements Serializable {

    public static final String FIND_INDICATEUR_BY_ID = "findIndicateurById";
    public static final String FIND_ALL_INDICATEUR = "findAllIndicateurs";
    public static final String FIND_INDICATEUR_BY_ETAT = "findIndicateursByEtat";
    public static final String FIND_INDICATEUR_BY_LIBELLE = "findIndicateursByLibelle";
    public static final String FIND_INDICATEUR_BY_NAME = "findIndicateursByName";
    public static final String FIND_INDICATEUR_BY_NAME_EXCEPT_ME = "findIndicateursByNameExceptMe";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_indicateur")
    private Long idIndicateur;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "nom_indicateur")
    private String nomIndicateur;
    @Column(name = "unite_indicateur")
    private String uniteIndicateur;
    @Column(name = "etat", length = 2)
    private int etat;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "indicateur")
    public List<LocalisationIndicateur> localisationIndicateurs = new ArrayList<LocalisationIndicateur>();

    public Indicateur() {
    }

    public Long getIdIndicateur() {
        return idIndicateur;
    }

    public void setIdIndicateur(Long idIndicateur) {
        this.idIndicateur = idIndicateur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public List<LocalisationIndicateur> getLocalisationIndicateurs() {
        return localisationIndicateurs;
    }

    public void setLocalisationIndicateurs(List<LocalisationIndicateur> localisationIndicateurs) {
        this.localisationIndicateurs = localisationIndicateurs;
    }

    public String getNomIndicateur() {
        return nomIndicateur;
    }

    public void setNomIndicateur(String nomIndicateur) {
        this.nomIndicateur = nomIndicateur;
    }

    public String getUniteIndicateur() {
        return uniteIndicateur;
    }

    public void setUniteIndicateur(String uniteIndicateur) {
        this.uniteIndicateur = uniteIndicateur;
    }

    @Override
    public String toString() {
        return this.libelle;
    }

 

}
