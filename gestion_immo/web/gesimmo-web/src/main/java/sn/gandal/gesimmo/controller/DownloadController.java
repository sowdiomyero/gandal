package sn.gandal.gesimmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sn.gandal.gesimmo.metier.services.IGesimmoMetier;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 13/03/15
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DownloadController {

    @Autowired
    IGesimmoMetier metier;

    Logger LOG = Logger.getLogger(DownloadController.class.getName());

    @RequestMapping(value = "/download/rapport.csv", method = RequestMethod.GET)
    public void downloadCSV(HttpServletResponse response) throws IOException {


    }

    @RequestMapping(value = "/download/rapport_failed.csv", method = RequestMethod.GET)
    public void downloadFailedTransactions(HttpServletResponse response) throws IOException {


    }
    
     @RequestMapping(value = "/downloadssss/rapport.pdf", method = RequestMethod.GET)
    public void downloadPDF(HttpServletResponse response){


     }
 
    @RequestMapping(value = "/download/rapport_failed.pdf", method = RequestMethod.GET)
    public void downloadFailedPDF(HttpServletResponse response){


    }
}
