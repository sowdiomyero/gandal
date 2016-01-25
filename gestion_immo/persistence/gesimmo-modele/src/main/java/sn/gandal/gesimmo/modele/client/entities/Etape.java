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
@DiscriminatorValue(value="ETAPE")
@NamedQueries({
    @NamedQuery(name = Etape.FIND_ETAPE_BY_ID, query = "SELECT e FROM Etape e where e.idActivite = :idEtape and e.etat <> :etat"),
    @NamedQuery(name = Etape.FIND_ETAPE_BY_NOM, query = "SELECT e FROM Etape e where e.nomActivite = :nomEtape and e.parent.idActivite = :idParent and e.etat <> :etat"),
    @NamedQuery(name = Etape.FIND_ETAPES_BY_ETAT, query = "SELECT e FROM Etape e where e.etat = :etat and e.parent.idActivite = :idParent"),
    @NamedQuery(name = Etape.IF_ETAPE_EXIST_WITH_NAME, query = "SELECT e FROM Etape e where e.nomActivite = :nomEtape and e.idActivite <> :idEtape and e.parent.idActivite = :idParent and e.etat <> :etat")})
public class Etape extends Activite{
    private static final long serialVersionUID = 1L;
    public static final String FIND_ETAPE_BY_ID = "findEtapeById";
    public static final String FIND_ETAPE_BY_NOM = "findEtapeByNom";
    public static final String FIND_ETAPES_BY_ETAT = "findEtapesByEtat";
    public static final String IF_ETAPE_EXIST_WITH_NAME = "findEtapesWithName";

    public Etape() {
        super();
    }
}
