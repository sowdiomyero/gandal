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
 * @author MSALL
 */
@Entity
@DiscriminatorValue(value = "PROGRAM")
@NamedQueries({
    @NamedQuery(name = Programme.FIND_PROGRAMME_BY_ID, query = "SELECT p FROM Programme p where p.idActivite = :idProgramme"),
    @NamedQuery(name = Programme.FIND_ALL_PROGRAMMES, query = "SELECT p FROM Programme p where p.etat =:etat"),
    @NamedQuery(name = Programme.FIND_PROGRAMME_BY_NOM, query = "SELECT p FROM Programme p where p.nomActivite = :nomProgramme"),
    @NamedQuery(name = Programme.IF_PROGRAMME_EXIST_WITH_NAME, query = "SELECT p FROM Programme p where p.nomActivite = :nomProgramme and p.idActivite <> :idProgramme"),
    @NamedQuery(name = Programme.FIND_PROGRAMMES_BY_RECHERCHE_AVANCEE,
            query = "SELECT p FROM Programme p where p.etat = :etat and p.dateDebPrevu >= :dateDebPrevu and p.dateFinPrevu <= :dateFinPrevu and p.nomActivite LIKE :nomProgramme and p.budgetPrevu <= :budgetPrevu"),
    @NamedQuery(name = Programme.FIND_PROGRAMMES_BY_RECHERCHE_AVANCEE_ALL_ETAT,
            query = "SELECT p FROM Programme p where p.etat <> :etat and p.dateDebPrevu >= :dateDebPrevu and p.dateFinPrevu <= :dateFinPrevu and p.nomActivite LIKE :nomProgramme and p.budgetPrevu <= :budgetPrevu"),
    @NamedQuery(name = Programme.FIND_PROGRAMMES_BY_ETAT, query = "SELECT p FROM Programme p where p.etat = :etat")})
public class Programme extends Activite {

    private static final long serialVersionUID = 1L;
    public static final String FIND_PROGRAMME_BY_ID = "findProgrammeById";
    public static final String FIND_ALL_PROGRAMMES = "findAllProgrammes";
    public static final String FIND_PROGRAMME_BY_NOM = "findProgrammeByNom";
    public static final String FIND_PROGRAMMES_BY_ETAT = "findProgrammesByEtat";
    public static final String IF_PROGRAMME_EXIST_WITH_NAME = "findProgrammesWithName";
    public static final String FIND_PROGRAMMES_BY_RECHERCHE_AVANCEE = "findProgrammesByRechercheAvancee";
    public static final String FIND_PROGRAMMES_BY_RECHERCHE_AVANCEE_ALL_ETAT = "findProgrammesByRechercheAvanceeAllEtat";
    
    public Programme() {
        super();
    }

    @Override
    public String toString() {
        return "Programme[ id=" + super.getIdActivite() + " ]";
    }


}
