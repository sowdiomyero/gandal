/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.service;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import sn.gandal.gesimmo.modele.client.entities.Programme;

/**
 *
 * @author kcisse
 */
public interface IProgrammeDao extends CrudRepository<Programme, Long>{
     public List<Programme> getAllProgrammes();
    
    public Programme getProgrammeById(Long idProgramme);
    
    public Programme updateProgramme(Programme programme);
    
    public Programme getProgrammeByName(String nomProjet);
    
    public boolean isProgrammeNameExist(String nomProjet, Long idProjet);
    
    public List<Programme> getProgrammeByEtat(int etat);
    
    public List<Programme> getProgrammesByRechercheAvancee(int etat, Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu);
    
    public List<Programme> getProgrammesByRechercheAvanceeAllEtat(Date dateDebPrevu, Date dateFinPrevu, String nomProjet, double budgetPrevu);
    
}
