/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sn.gandal.gesimmo.metier.shedules;

import sn.gandal.gesimmo.metier.services.IGesimmoMetier;
import sn.gandal.gesimmo.modele.client.entities.JobExecutorHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sniang
 */
@Service
public class ExecuteTask {

    Logger LOG = Logger.getLogger(ExecuteTask.class.getName());
    @Autowired
    IGesimmoMetier metier;


    
    //@Scheduled(fixedDelay=300000)  // toutes les 5mn pour mes tests
    @Scheduled(cron = "${gesimmo.shedule.frequence.cron}")
    public void demoServiceMethod()
    {
        JobExecutorHistory jobExecutorHistory = new JobExecutorHistory();

        LOG.log(Level.INFO, " ***** BEGIN ExecuteTask : Generating CSV Report..... **** ");
        //ParamAcces defaultParamAcces = metier.getDefaultParamAcces();
        LOG.log(Level.INFO," INFORMATIONS PARAMETRE ACCES  ");
        LOG.log(Level.INFO," 1. ------- INFO MESSAGERIE : ");
        LOG.log(Level.INFO," 2. ------- INFO RAPPORT  :  ");

       /* String csvPath = metier.generateTransactionReport(DocumentType.CSV, Periodicite.TOUS_LES_LUNDIS,null);
        File file= new File(csvPath);
        jobExecutorHistory.setRapportName(file.getName());
        jobExecutorHistory.setRapportPath(file.getAbsolutePath());
        jobExecutorHistory.setRapportGenerationParams("CSV - TOUS_LES_LUNDIS -  : "+file.getName());
        LOG.log(Level.INFO," ***** ExecuteTask : END CSV Report generation .... **** ");
        if(csvPath !=null && defaultParamAcces.getRapportParam().getParamRapportFormat().equalsIgnoreCase("CSV")){
            metier.sendRapport(file.getAbsolutePath());
            metier.logJobExecution(jobExecutorHistory);
        }

        LOG.log(Level.INFO, " ***** BEGIN ExecuteTask : Generating PDF Report..... **** ");
        String pdfPath =metier.generateTransactionReport(DocumentType.PDF, Periodicite.TOUS_LES_LUNDIS,null);
        File pdfFile= new File(pdfPath);
        jobExecutorHistory = new JobExecutorHistory();
        jobExecutorHistory.setRapportName(pdfFile.getName());
        jobExecutorHistory.setRapportPath(pdfFile.getAbsolutePath());
        jobExecutorHistory.setRapportGenerationParams("PDF - TOUS_LES_LUNDIS - "+pdfFile.getName());

        if(pdfPath !=null && defaultParamAcces.getRapportParam().getParamRapportFormat().equalsIgnoreCase("PDF")){
            metier.sendRapport(pdfFile.getAbsolutePath());
            metier.logJobExecution(jobExecutorHistory);
        }*/
        LOG.log(Level.INFO," ***** ExecuteTask : END PDF Report generation ... **** ");

    }

}
