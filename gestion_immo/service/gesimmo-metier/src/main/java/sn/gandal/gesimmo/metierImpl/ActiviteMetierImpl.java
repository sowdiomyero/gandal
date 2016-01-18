package sn.gandal.gesimmo.metierImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.metier.services.IActiviteMetier;
import sn.gandal.gesimmo.modele.client.entities.Activite;
import sn.gandal.gesimmo.modele.client.entities.ActiviteEtat;
import sn.gandal.gesimmo.modele.client.entities.Etat;
import sn.gandal.gesimmo.modele.client.tools.ProjetFormFilter;
import sn.gandal.gesimmo.service.IActiviteDao;
import sn.gandal.gesimmo.service.IEtatDao;

/**
 *
 * @author msall
 */
@Service

public class ActiviteMetierImpl implements IActiviteMetier, Serializable {

    @Autowired
    IActiviteDao activiteDao;
    @Autowired
    IEtatDao etatDao;

    @Override
    public List<Activite> getAllActivite() {
        return activiteDao.getAllActivite();
    }

    @Override
    public List<Activite> getActiviteByTypeAndEtat(String type, int etat) {
        return activiteDao.getActiviteByTypeAndEtat(type, etat);
    }

    @Override
    public Activite getActivitesByName(String nomActivite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Activite findActivitesByTypeAndByNom(String type, String name) {
        return activiteDao.findActivitesByTypeAndByNom(type, name);
    }

    @Override
    public Activite deleteActivite(Activite a) {
        activiteDao.deleteActivite(a);
        return a;
    }

    @Override
    public Activite addActivite(Activite activite) {
        activiteDao.addActivite(activite);
        return activite;
    }

    @Override
    public void updateActivite(Activite activite) {
        activiteDao.updateActivite(activite);
    }

    @Override
    public Activite getActiviteById(Long idActivite) {
        return activiteDao.getActiviteById(idActivite);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateEtat(Long idActivite, String nomEtat) {
        try {
            Activite activite = activiteDao.getActiviteById(idActivite);
            Etat etat ;
            if (activite.getEtatActuel() == null && nomEtat.equalsIgnoreCase("PLANIFIE")) {
                etat = etatDao.findEtatByNom("PLANIFIE");
            } else {
                etat = etatDao.findEtatByNom(nomEtat);
                if (activite.getEtatActuel().getNom().equalsIgnoreCase("TERMINE") || activite.getEtatActuel().getNom().equalsIgnoreCase("ABANDONNE")) {
                    return false;
                }
                if (activite.getEtatActuel().getNom().equalsIgnoreCase("PLANIFIE") && !nomEtat.equalsIgnoreCase("DEMARRE") && !nomEtat.equalsIgnoreCase("ABANDONNE")) {
                    return false;
                }
                if (activite.getEtatActuel().getNom().equalsIgnoreCase("DEMARRE") && !nomEtat.equalsIgnoreCase("TERMINE") && !nomEtat.equalsIgnoreCase("SUSPENDU") && !nomEtat.equalsIgnoreCase("ABANDONNE")) {
                    return false;
                }
                if (activite.getEtatActuel().getNom().equalsIgnoreCase("SUSPENDU") && !nomEtat.equalsIgnoreCase("REDEMARRE") && !nomEtat.equalsIgnoreCase("ABANDONNE")) {
                    return false;
                }
                if (activite.getEtatActuel().getNom().equalsIgnoreCase("REDEMARRE") && !nomEtat.equalsIgnoreCase("SUSPENDU") && !nomEtat.equalsIgnoreCase("ABANDONNE") && !nomEtat.equalsIgnoreCase("TERMINE")) {
                    return false;
                }
            }
            activite.setEtatActuel(etat);
            ActiviteEtat activiteEtat = new ActiviteEtat();
            activiteEtat.setDate(new Date());
            activiteEtat.setEtat(etat);
            activiteEtat.setActivite(activite);
            activite.addActiviteEtat(activiteEtat);
            activiteDao.updateActivite(activite);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Activite> findActivitesByCriteres(ProjetFormFilter projetFormFilter , String type) {
       
        List<Activite> result = new ArrayList<Activite>();
        
        for (Activite a : activiteDao.findActivitesByCriteres(projetFormFilter) ){
            if (a.getType().equalsIgnoreCase(type)) {
                result.add(a);
            }
 
        }
            return result;
       
    }

    @Override
    public List<Activite> findActivitesByCriteresExacts(ProjetFormFilter projetFormFilter, String type) {
        
        
         List<Activite> result = new ArrayList<Activite>();
        
        for (Activite a : activiteDao.findActivitesByCriteresExacts(projetFormFilter) ){
            if (a.getType().equalsIgnoreCase(type)) {
                result.add(a);
            }
 
        }
            return result;
    }

}
