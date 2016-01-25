package sn.gandal.gesimmo.service;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Etat;

/**
 *
 * @author isene
 */
public interface IEtatDao extends CrudRepository<Etat, Long> {

    public Etat findEtatByNom(String nom);
     public List<Etat> findAllEtat();
      public Etat updateEtat(Etat etat);
        public boolean deleteEtat(Long id);

}
