/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.metier.services;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Journal;

/**
 *
 * @author ddiaw
 */
public interface IJournalMetier {

    public Journal findJournalById(Long idJournal);

    public List<Journal> findJournalsByIdActivite(Long idActivite);

    public void updateJournal(Journal journal);

    public void delete(Long id);

    public void delete(Journal t);

    public void creteJournal(Journal t);

    public Journal createJournal(Long idProgramme, String libelle, String description);

 }
