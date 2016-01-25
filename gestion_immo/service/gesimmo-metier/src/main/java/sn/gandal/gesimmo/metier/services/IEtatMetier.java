
package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Etat;

/**
 *
 * @author isene
 */
public interface IEtatMetier {
 
    public Etat findEtatByNom(String nom);
     public List<Etat> findAllEtat(); 
     public Etat updateEtat( Etat etat);
          public boolean deleteEtat(Long id);
           public Etat findById(Long id);
           
}
