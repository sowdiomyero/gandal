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
 * @author msall
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_localisation", discriminatorType = DiscriminatorType.STRING, length = 15)
@Table(name = "localisation")
@NamedQueries({
    @NamedQuery(name = Localisation.FIND_LOCALISABLE_BY_ID, query = "SELECT l FROM Localisation l where l.id = :idLocalisation"),
    @NamedQuery(name = Localisation.FIND_ALL_LOCALISABLES, query = "SELECT l FROM Localisation l"),
    @NamedQuery(name = Localisation.FIND_LOCALISABLES_BY_TYPE, query = "SELECT l FROM Localisation l where l.type = :type"),
    @NamedQuery(name = Localisation.FIND_LOCALISABLES_BY_ETAT, query = "SELECT l FROM Localisation l where l.etat = :etat"),
    @NamedQuery(name = Localisation.FIND_ALL_LOCALITE_BY_ETAT_AND_TYPE, query = "SELECT l FROM Localisation l where l.etat = :etat and l.typeLocalisation = :type"),
    @NamedQuery(name = Localisation.FIND_RESPONSABLE_FROM_LOCALISATION, query = "SELECT l.attribution FROM Localisation l where l.idLocalisation = :idLocalisation")

})

public abstract class Localisation extends AbstractDateEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    // Noms requ�tes nomm�e
    public static final String FIND_LOCALISABLE_BY_ID = "findLocalisableById";
    public static final String FIND_ALL_LOCALISABLES = "findAllTLocalisables";
    public static final String FIND_LOCALISABLES_BY_TYPE = "findLocalisablesByType";
    //public static final String FIND_ALL_LOCALITE_BY_TYPE_AND_ETAT = "findLocalisablesParTypeEtEtat";
    public static final String FIND_ALL_LOCALITE_BY_ETAT_AND_TYPE = "findLocalisablesParTypeEtEtat";
    public static final String FIND_LOCALISABLES_BY_ETAT = "findLocalisablesByEtat";
    public static final String FIND_RESPONSABLE_FROM_LOCALISATION = "getResponsableLocalisation";

  public enum ETAT{
       FONCTIONNEL,
       EN_RENOVATION,
       EN_CONSTRUCTION,
       VENDU,
       ABANDONNE,
       SUPPRIME
   }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_localisation")
    private Long idLocalisation;
    @Column(name = "local_nom", length = 60, nullable = false)
    private String nomLocalisable;
    @Column(name = "local_nom_google", length = 80, nullable = false)
    private String nomLocalisableGoogle;
    @Column(name = "description", length = 100, nullable = true)
    private String description;
    @Column(name = "longitude", length = 50)
    private String longitude;
    @Column(name = "latitude", length = 50)
    private String latitude;
    
    @Column(name = "type", length = 50)
    protected String type;
    @Column(name = "type_localisation", length = 50, insertable = false, updatable = false)
    private String typeLocalisation;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private ETAT etat;
    @Version
    @Column(name = "version", columnDefinition = "INT(11) default '0'")
    private int version;
    @Column(name = "json_form_view", length = 255)
    private String jsonFormView;

    @ManyToMany(mappedBy = "localisations")
    private List<Activite> activites;
    
    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parent")
    private Localisation parentLocalisation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localisation")
    private List<LocalisationIndicateur> localisationIndicateurs = new ArrayList<LocalisationIndicateur>();
// Fin d�claration param�tres
    @ManyToOne
    @JoinColumn(name = "id_createur")
    public User createur;        
    
    @ManyToOne
    @JoinColumn(name = "id_responsable")
    private User attribution;
    
    // Constructeurs
    public Localisation(String nom) {
        this.nomLocalisable = nom;

    }

    public User getUser() {
        return createur;
    }

    public void setUser(User user) {
        this.createur = user;
    }

    public Localisation() {
    }

    public Localisation(Localisation parentLocalisation) {
        this.parentLocalisation = parentLocalisation;
    }

// FIN d�finition des Constructeurs
// Getters and Setters    
    public Long getIdLocalisation() {
        return idLocalisation;
    }

    public void setIdLocalisation(Long id) {
        this.idLocalisation = id;
    }

    public String getNomLocalisable() {
        return nomLocalisable;
    }

    public void setNomLocalisable(String nomLocalisable) {
        this.nomLocalisable = nomLocalisable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public ETAT getEtat() {
        return etat;
    }

    public void setEtat(ETAT etat) {
        this.etat = etat;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getJsonFormView() {
        return jsonFormView;
    }

    public void setJsonFormView(String jsonFormView) {
        this.jsonFormView = jsonFormView;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public String getNomLocalisableGoogle() {
        return nomLocalisableGoogle;
    }

    public void setNomLocalisableGoogle(String nomLocalisableGoogle) {
        this.nomLocalisableGoogle = nomLocalisableGoogle;
    }

    public Localisation getParentLocalisation() {
        return parentLocalisation;
    }

    public void setParentLocalisation(Localisation parentLocalisation) {
        this.parentLocalisation = parentLocalisation;
    }

    public List<LocalisationIndicateur> getLocalisationIndicateurs() {
        return localisationIndicateurs;
    }

    public void setLocalisationIndicateurs(List<LocalisationIndicateur> localisationIndicateurs) {
        this.localisationIndicateurs = localisationIndicateurs;
    }

    public String getTypeLocalisation() {
        return typeLocalisation;
    }

    public void setTypeLocalisation(String typeLocalisation) {
        this.typeLocalisation = typeLocalisation;
    }

    public User getCreateur() {
        return createur;
    }

    public void setCreateur(User createur) {
        this.createur = createur;
    }

    public User getAttribution() {
        return attribution;
    }

    public void setAttribution(User attribution) {
        this.attribution = attribution;
    }
    
    

// Fin d�claration Getters and Setters
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalisation != null ? idLocalisation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Localisation)) {
            return false;
        }
        Localisation other = (Localisation) object;
        if ((this.nomLocalisable.equalsIgnoreCase(other.nomLocalisable)) && (this.latitude.equalsIgnoreCase(other.latitude)) && (this.longitude.equalsIgnoreCase(other.longitude))) {
            return true;
        } else {
            return false;

        }

    }

    @Override
    public String toString() {

        if (this.parentLocalisation != null) {
            return this.nomLocalisable + " , " + this.parentLocalisation.getNomLocalisable();

        } else {
            return this.nomLocalisable  ;

        }
    }
    
    public abstract String getTypeToString();

    public abstract String getType();
    
    public abstract String getDType();
    
    public abstract List<String> getSpecificTypes();
    
    public abstract void setType(String type);
}
