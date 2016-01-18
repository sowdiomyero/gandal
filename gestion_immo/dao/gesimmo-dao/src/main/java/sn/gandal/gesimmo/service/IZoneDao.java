/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sn.gandal.gesimmo.modele.client.entities.User;
import sn.gandal.gesimmo.modele.client.entities.Zone;

import java.util.List;

/**
 *
 * @author sniang
 */
@Repository
public interface IZoneDao extends CrudRepository<Zone, Long> {

    public List<Zone> findAllZone();
}
