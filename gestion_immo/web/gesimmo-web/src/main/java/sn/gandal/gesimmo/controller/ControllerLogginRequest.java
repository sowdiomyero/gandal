/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpStatus;

/**
 *
 * @author DDIAW
 */

@ControllerAdvice
public class ControllerLogginRequest {
 Logger LOG = Logger.getLogger(ControllerLogginRequest.class.getName());

@ExceptionHandler
@ResponseStatus(HttpStatus.BAD_REQUEST)
public void handle(Exception e) {
    LOG.log(Level.WARNING, "Returning HTTP 400 Bad Request", e);
}
}
