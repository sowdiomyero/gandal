/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.service;

import sn.gandal.gesimmo.modele.client.entities.UserActivite;

/**
 *
 * @author msall
 */
public interface IUserActiviteDao {
    
    public void addUserActivite(UserActivite userActivite);
    
    public void deleteUserActivite(UserActivite userActivite);
    
    public void updateUserActivite(UserActivite userActivite);
}
