/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metierImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.gandal.gesimmo.metier.services.IUserActiviteMetier;
import sn.gandal.gesimmo.modele.client.entities.UserActivite;
import sn.gandal.gesimmo.service.IUserActiviteDao;

/**
 *
 * @author msall
 */
@Service
public class UserActiviteMetierImpl implements IUserActiviteMetier{
    @Autowired
    IUserActiviteDao dao;

    @Override
    public void addUserActivite(UserActivite userActivite) {
       dao.addUserActivite(userActivite);
    }

    @Override
    public void deleteUserActivite(UserActivite userActivite) {
        dao.deleteUserActivite(userActivite);
    }

    @Override
    public void updateUserActivite(UserActivite userActivite) {
       dao.updateUserActivite(userActivite);
    }
    
}
