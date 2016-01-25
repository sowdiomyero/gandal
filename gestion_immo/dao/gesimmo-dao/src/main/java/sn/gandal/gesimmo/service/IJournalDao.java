/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import sn.gandal.gesimmo.modele.client.entities.Journal;

/**
 *
 * @author ddiaw
 */
public interface IJournalDao  extends CrudRepository<Journal, Long> {

    public Journal findJournalById(Long idJournal);

    public List<Journal> findJournalsByIdActivite(Long idActivite);

    public void updateJournal(Journal journal);

    

}
