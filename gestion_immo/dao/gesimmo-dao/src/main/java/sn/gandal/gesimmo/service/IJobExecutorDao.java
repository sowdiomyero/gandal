/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.service;

import sn.gandal.gesimmo.modele.client.entities.JobExecutorHistory;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sniang
 */
public interface IJobExecutorDao extends CrudRepository<JobExecutorHistory, Long>{

}
