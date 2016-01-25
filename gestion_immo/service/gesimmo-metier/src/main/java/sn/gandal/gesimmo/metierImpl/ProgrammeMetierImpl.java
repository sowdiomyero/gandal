/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metierImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IProgrammeMetier;
import sn.gandal.gesimmo.modele.client.entities.Programme;
import sn.gandal.gesimmo.service.IProgrammeDao;

/**
 *
 * @author kcisse
 */
@Service
public class ProgrammeMetierImpl implements IProgrammeMetier {

    @Autowired
    IProgrammeDao programmeDao;


    @Override
    public List<Programme> getAllProgrammes() {
        return programmeDao.getAllProgrammes();
    }

    @Override
    public Programme getProgrammeById(Long idProgramme) {
        try {
            return programmeDao.getProgrammeById(idProgramme);
        } catch (Exception e) {
            Programme p = new Programme();
            p.setNomActivite("Inconnu");
            return p;
        }

    }

    @Override
    public Programme getProgrammeByName(String nomProjet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isProgrammeNameExist(String nomProjet, Long idProjet) {
        return programmeDao.isProgrammeNameExist(nomProjet, idProjet);
    }

    @Override
    public List<Programme> getProgrammeByEtat(int etat) {
        return programmeDao.getProgrammeByEtat(etat);
    }

    @Override
    public List<Programme> getProgrammesByRechercheAvancee(int etat, Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Programme> getProgrammesByRechercheAvanceeAllEtat(Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Programme saveProgramme(Programme programme) {
        return programmeDao.save(programme);
    }

    @Override
    public Programme updateProgramme(Programme programme) {
        return programmeDao.updateProgramme(programme);
    }

}
