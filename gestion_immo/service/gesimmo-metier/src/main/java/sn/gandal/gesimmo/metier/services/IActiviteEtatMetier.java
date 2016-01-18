
package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.ActiviteEtat;

/**
 *
 * @author isene
 */
public interface IActiviteEtatMetier {
public List<ActiviteEtat> getActiviteEtatByIdActivite(Long idActivite);
     public ActiviteEtat saveActiviteEtat(ActiviteEtat activiteEtat);
}
