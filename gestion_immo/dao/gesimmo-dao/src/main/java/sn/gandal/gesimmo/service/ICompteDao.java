/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gandal.gesimmo.service;

import java.util.List;
import sn.gandal.gesimmo.modele.client.entities.Compte;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sniang
 */
public interface ICompteDao extends CrudRepository<Compte, Long> {

    public boolean isAccountWithLoginExist(String login);

    public Compte findAccountByLogin(String login);

    public void updateCompte(Compte compte);

    public List<Compte> findComptesByEtat(int etat);

}
