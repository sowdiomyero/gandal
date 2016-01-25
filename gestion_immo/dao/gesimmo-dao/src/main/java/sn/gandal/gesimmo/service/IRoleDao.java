/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Role;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sniang
 */
public interface IRoleDao extends CrudRepository<Role, Long> {

    public Role findRoleByName(String nameRole);
    
    public Role findRoleByName(String nameRole, Long idRole);

    public void updateRole(Role role);

    public List<Role> findAllRoles();

}
