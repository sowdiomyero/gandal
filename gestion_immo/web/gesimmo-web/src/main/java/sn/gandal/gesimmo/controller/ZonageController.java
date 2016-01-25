package sn.gandal.gesimmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.gandal.gesimmo.dto.BasicResponse;
import sn.gandal.gesimmo.form.EditLocalisationForm;
import sn.gandal.gesimmo.form.ZoneFormCollector;
import sn.gandal.gesimmo.modele.client.entities.*;
import sn.gandal.gesimmo.modele.client.tools.ParamEntity;
import sn.gandal.gesimmo.utils.RequestMappingUtils;
import sn.gandal.gesimmo.utils.RequestMappingUtils.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sn.gandal.gesimmo.dto.ZonesDTO;
import sn.gandal.gesimmo.metier.GesImmoServiceManager;


/**
 *
 * @author ddiaw
 */
@Controller
@RequestMapping(value = "/zone")
public class ZonageController {

//    @Autowired
//    ILocalisationMetier localisationMetier;
//
//    @Autowired
//    IGesimmoMetier gesimmoMetier;
//    
    @Autowired
    GesImmoServiceManager manager;

    Logger LOG = Logger.getLogger(ZonageController.class.getName());

    @RequestMapping(value = "/map")
    public String localisationListForm(Model model) {
        EditLocalisationForm localisationForm = new EditLocalisationForm();
        List<Localisation> locs = new ArrayList<Localisation>();
        locs.addAll(manager.getLocalisationService().findAllLocalisationByEtat(Localisation.ETAT.FONCTIONNEL));
        locs.add(0, new BatimentLocalite());
        localisationForm.setRattachements(locs);
        model.addAttribute("localisationForm", localisationForm);

        ZoneFormCollector zoneFormCollector = new ZoneFormCollector();
        //localisationFormFilter.setdType(gesimmoMetier.getTypeLicenceLocalite());
        model.addAttribute("zoneFormCollector", zoneFormCollector);
        String ZoneMapping = RequestMappingUtils.ZonageMapping.POST_ADD;
        model.addAttribute("zoneRoute", ZoneMapping);


        return "zonepage";

    }



    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
    public @ResponseBody
    BasicResponse addNewZone(@RequestBody @Valid ZoneFormCollector zoneFormCollector, HttpServletRequest request, Model m) {
        /* ENREGISTREMENT DES DONNEES DANS LA BASE */

        BasicResponse response = new BasicResponse();
        response.setResultat(BasicResponse.RETOUR_OK);
        try {

            String[] gps = zoneFormCollector.getLatLongs().split(";");

            gps = gps[1].split("\\)");
            gps = gps[0].split(",");
            System.out.println("GPS Data Splitted : 0 : " + gps[0] + " 1 :" + gps[1]);

            String latitude = gps[0];
            String longitude = gps[1];

            Zone zone = new Zone();
            zone.setCode(zoneFormCollector.getCodeZone());
            zone.setDescription(zoneFormCollector.getDescription());
            zone.setNomZoneGoogle(zoneFormCollector.getNomZone());
            zone.setPolygone(zoneFormCollector.getLatLongs());
            zone.setCouleur(zoneFormCollector.getCouleur());
            zone= (Zone) manager.getGesimmoMetierService().saveObject(zone);
            response.setMsg("La zone a été ajoutée avec succès : " +zone.getIdZone());

            String ZoneMapping = ZonageMapping.POST_ADD;
            m.addAttribute("zoneRoute", ZoneMapping);

        } catch (Exception ex) {
            response.setResultat(BasicResponse.RETOUR_EXCEPTION);
            response.setMsg("Une exception s'est produite pendant le traitement");
            LOG.log(Level.SEVERE, "Exception : "+ex.getMessage());
        }

        return  response;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody List<ZonesDTO> getAllZone() {
        
        List<ZonesDTO> result = new ArrayList<ZonesDTO>();
        
        List<Zone> zones = manager.getGesimmoMetierService().findAllZones();
        
        for(Zone z : zones){
            ZonesDTO dto = new ZonesDTO();
            dto.setNomZone(z.getNomZoneGoogle());
            dto.setCouleur(z.getCouleur());
            dto.setPoints(Arrays.asList(z.getPolygone().split(";")));
            result.add(dto);
        }
        LOG.log(Level.INFO, "Tailles des zones recuperées : "+zones.size());
        return result;
    }
    
     @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String [][] getListZone() {
        List<Zone> zones = manager.getGesimmoMetierService().findAllZones();
        String [][] poly = new String[zones.size()][];
        int i = 0;
        for(Zone z : zones){
           poly[i] = z.getPolygone().split(";");
           i++;
        }
        LOG.log(Level.INFO, "Tailles des zones recuperées : "+zones.size());
        return poly;
    }

}
