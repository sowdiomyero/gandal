/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.modele.client.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 *
 * @author msall
 */
@Entity
@DiscriminatorValue(value = "PROJET")
@NamedQueries({
    @NamedQuery(name = Projet.FIND_PROJET_BY_ID, query = "SELECT p FROM Projet p where p.idActivite = :idProjet"),
    @NamedQuery(name = Projet.FIND_ALL_PROJETS, query = "SELECT p FROM Projet p where p.etat <> :etat"),
    @NamedQuery(name = Projet.FIND_PROJET_BY_NOM, query = "SELECT p FROM Projet p where p.nomActivite = :nomProjet"),
    @NamedQuery(name = Projet.IF_PROJET_EXIST_WITH_NAME, query = "SELECT p FROM Projet p where p.nomActivite = :nomProjet and p.idActivite <> :idProjet"),
    @NamedQuery(name = Projet.FIND_PROJETS_BY_RECHERCHE_AVANCEE,
            query = "SELECT p FROM Projet p where p.etat = :etat and p.dateDebPrevu >= :dateDebPrevu and p.dateFinPrevu <= :dateFinPrevu and p.nomActivite LIKE :nomProjet and p.budgetPrevu <= :budgetPrevu"),
    @NamedQuery(name = Projet.FIND_PROJETS_BY_RECHERCHE_AVANCEE_ALL_ETAT,
            query = "SELECT p FROM Projet p where p.etat <> :etat and p.dateDebPrevu >= :dateDebPrevu and p.dateFinPrevu <= :dateFinPrevu and p.nomActivite LIKE :nomProjet and p.budgetPrevu <= :budgetPrevu"),
    @NamedQuery(name = Projet.FIND_PROJETS_BY_ETAT, query = "SELECT p FROM Projet p where p.etat = :etat")})
public class Projet extends Activite {

    private static final long serialVersionUID = 1L;
    public static final String FIND_PROJET_BY_ID = "findProjetById";
    public static final String FIND_ALL_PROJETS = "findAllProjets";
    public static final String FIND_PROJET_BY_NOM = "findProjetByNom";
    public static final String FIND_PROJETS_BY_ETAT = "findProjetsByEtat";
    public static final String IF_PROJET_EXIST_WITH_NAME = "findProjetsWithName";
    public static final String FIND_PROJETS_BY_RECHERCHE_AVANCEE = "findProjetsByRechercheAvancee";
    public static final String FIND_PROJETS_BY_RECHERCHE_AVANCEE_ALL_ETAT = "findProjetsByRechercheAvanceeAllEtat";

    public Projet() {
        super();
    }

    @Override
    public String toString() {
        return this.getNomActivite();
    }



}
