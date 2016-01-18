/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author msall
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_activite", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "ACTIVITE")
@Table(name = "activite")
@NamedQueries({
    @NamedQuery(name = Activite.FIND_ACTIVITE_BY_ID, query = "SELECT a FROM Activite a where a.idActivite = :idActivite"),
    @NamedQuery(name = Activite.FIND_ALL_ACTIVITES, query = "SELECT a FROM Activite a"),
    @NamedQuery(name = Activite.FIND_ACTIVITE_BY_TYPE_AND_BY_NOM, query = "SELECT a FROM Activite a where a.type = :type AND a.nomActivite = :nomActivite and a.etat <> :etat"),
    @NamedQuery(name = Activite.FIND_ACTIVITES_BY_TYPE_AND_BY_ETAT, query = "SELECT a FROM Activite a where a.type = :type AND a.etat = :etat")})
public class Activite extends AbstractDateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // Noms requ�tes nomm�es
    public static final String FIND_ACTIVITE_BY_ID = "findActiviteById";
    public static final String FIND_ALL_ACTIVITES = "findAllActivites";
    public static final String FIND_ACTIVITE_BY_TYPE_AND_BY_NOM = "findActivitesByTypeAndByNom";
    public static final String FIND_ACTIVITES_BY_TYPE_AND_BY_ETAT = "findActivitesByTypeAndByEtat";
    // Fin d�claration des noms des requ�tes nomm�es

    // Param�tres
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_activite")
    private Long idActivite;
    @ManyToOne
    @JoinColumn(name = "id_parent")
    private Activite parent;
    @Column(name = "activite_nom", length = 60, nullable = false)
    private String nomActivite;
    @Column(name = "description", length = 100, nullable = true)
    private String description;
    @Column(name = "budget_prevu", nullable = true)
    private double budgetPrevu;
    @Column(name = "budget_reel", nullable = true)
    private double budgetReel;
    @Column(name = "nbre_emploi_prevu", nullable = true)
    private int nbreEmploiPrevu;
    @Column(name = "nbre_emploi_rel", nullable = true)
    private int nbreEmploiReel;
    @Column(name = "ponderation", nullable = true)
    private float ponderation;
    @Column(name = "taux_realisation", nullable = true)
    private float tauxRealisation;
    @Column(name = "date_deb_prevu", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebPrevu;
    @Column(name = "date_fin_prevu", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFinPrevu;
    @Column(name = "date_deb_reel", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebReel;
    @Column(name = "date_fin_reel", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFinReel;
    @Column(name = "type", length = 30)
    private String type;
    @Column(name = "etat", length = 2)
    private int etat = 1;
    @Version
    @Column(name = "version", length = 11)
    private int version = 0;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "activite_localisation", joinColumns = @JoinColumn(name = "ID_ACTIVITE"),
            inverseJoinColumns = @JoinColumn(name = "ID_LOCALISATION"))
    private List<Localisation> localisations;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Activite> activiteList;

    @OneToMany(mappedBy = "activite", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Journal> journals;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            mappedBy = "activite")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ActiviteEtat> activiteEtats;
    @ManyToOne
    @JoinColumn(name = "etat_actuel")
    private Etat etatActuel;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "activite", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @Fetch(value = FetchMode.SUBSELECT)
    public List<UserActivite> userActivite;

    public Activite() {
    }

    public Long getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Long id) {
        this.idActivite = id;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudgetPrevu() {
        return budgetPrevu;
    }

    public void setBudgetPrevu(double budgetPrevu) {
        this.budgetPrevu = budgetPrevu;
    }

    public double getBudgetReel() {
        return budgetReel;
    }

    public void setBudgetReel(double budgetReel) {
        this.budgetReel = budgetReel;
    }

    public int getNbreEmploiPrevu() {
        return nbreEmploiPrevu;
    }

    public void setNbreEmploiPrevu(int nbreEmploiPrevu) {
        this.nbreEmploiPrevu = nbreEmploiPrevu;
    }

    public int getNbreEmploiReel() {
        return nbreEmploiReel;
    }

    public void setNbreEmploiReel(int nbreEmploiReel) {
        this.nbreEmploiReel = nbreEmploiReel;
    }

    public float getPonderation() {
        return ponderation;
    }

    public void setPonderation(float ponderation) {
        this.ponderation = ponderation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getTauxRealisation() {
        return tauxRealisation;
    }

    public void setTauxRealisation(float tauxRealisation) {
        this.tauxRealisation = tauxRealisation;
    }

    public Date getDateDebPrevu() {
        return dateDebPrevu;
    }

    public void setDateDebPrevu(Date dateDebPrevu) {
        this.dateDebPrevu = dateDebPrevu;
    }

    public Date getDateFinPrevu() {
        return dateFinPrevu;
    }

    public void setDateFinPrevu(Date dateFinPrevu) {
        this.dateFinPrevu = dateFinPrevu;
    }

    public Date getDateDebReel() {
        return dateDebReel;
    }

    public void setDateDebReel(Date dateDebReel) {
        this.dateDebReel = dateDebReel;
    }

    public Date getDateFinReel() {
        return dateFinReel;
    }

    public void setDateFinReel(Date dateFinReel) {
        this.dateFinReel = dateFinReel;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getVersion() {
        return version;
    }

//    public void setVersion(int version) {
//        this.version = version;
//    }
    public List<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(List<Localisation> localisations) {
        this.localisations = localisations;
    }

    public Activite getParent() {
        return parent;
    }

    public void setParent(Activite parent) {
        this.parent = parent;
    }

    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public List<UserActivite> getUserActivite() {
        return userActivite;
    }

    public void setUserActivite(List<UserActivite> userActivite) {
        this.userActivite = userActivite;
    }

// Fin d�claration Getters and Setters
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivite != null ? idActivite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Activite)) {
            return false;
        }
        Activite other = (Activite) object;
        if ((this.idActivite == null && other.idActivite != null) || (this.idActivite != null && !this.idActivite.equals(other.idActivite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Activite[ id=" + idActivite + " ]";
    }

    public static List<? extends Activite> filterActivitiesByState(List<? extends Activite> projets, int etat) {
        List<Activite> resultat = new ArrayList<Activite>();

        for (Activite p : projets) {
            if (p.getEtat() == etat) {
                resultat.add(p);
            }
        }

        return resultat;
    }

    public List<ActiviteEtat> getActiviteEtats() {
        return activiteEtats;
    }

    public void setActiviteEtats(List<ActiviteEtat> activiteEtats) {
        this.activiteEtats = activiteEtats;
    }

    public Etat getEtatActuel() {
        return etatActuel;
    }

    public void setEtatActuel(Etat etatActuel) {
        this.etatActuel = etatActuel;
    }

    public void addActiviteEtat(ActiviteEtat activiteEtat) {
        this.getActiviteEtats().add(activiteEtat);
        activiteEtat.setActivite(this);
    }

}
