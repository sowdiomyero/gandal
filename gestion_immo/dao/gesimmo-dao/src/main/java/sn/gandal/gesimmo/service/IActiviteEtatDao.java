
package sn.gandal.gesimmo.service;
import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.ActiviteEtat;

/**
 *
 * @author isene
 */
public interface IActiviteEtatDao {

    public List<ActiviteEtat> getActiviteEtatByIdActivite(Long idActivite);
     public ActiviteEtat saveActiviteEtat(ActiviteEtat activiteEtat);

}
