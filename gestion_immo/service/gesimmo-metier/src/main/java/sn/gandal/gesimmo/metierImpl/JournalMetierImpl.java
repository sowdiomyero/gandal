/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metierImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sn.gandal.gesimmo.metier.services.IJournalMetier;
import sn.gandal.gesimmo.modele.client.entities.Journal;
import sn.gandal.gesimmo.modele.client.entities.Programme;
import sn.gandal.gesimmo.service.IJournalDao;
import sn.gandal.gesimmo.service.IProgrammeDao;

/**
 *
 * @author ddiaw
 */
@Service
public class JournalMetierImpl implements IJournalMetier {

    @Autowired
    IJournalDao dao;
      @Autowired
    IProgrammeDao programmeDao;

    @Override
    public Journal findJournalById(Long idJournal) {
        return dao.findJournalById(idJournal);
    }

    @Override
    public List<Journal> findJournalsByIdActivite(Long idActivite) {
        return dao.findJournalsByIdActivite(idActivite);
    }

    @Override
    public void updateJournal(Journal journal) {
        dao.updateJournal(journal);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void delete(Journal t) {
        dao.delete(t);
    }

    @Override
    public void creteJournal(Journal t) {
        dao.save(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Journal createJournal(Long idProgramme, String libelle, String description) {
         Journal journal =new Journal();
        try {
            Programme programme =programmeDao.getProgrammeById(idProgramme);
            journal.setActivite(programme);
            journal.setDateCreation();
            journal.setEtat(1);
            journal.setLibelle(libelle);
            journal.setDescription(description);
            dao.save(journal);
            return journal;
        } catch (Exception e) {
            return journal;
        }
    }

}
