package sn.gandal.gesimmo.utils;

/**
 * Created with IntelliJ IDEA.
 * User: DYSOW
 * Date: 24/11/15
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */
public class RequestMappingUtils {

    public ZonageMapping zoneMapping;

    /**
     * -----------------------------------------------------------
     *  PROJETS
     * ----------------------------------------------------------
     */

    public final class ProjectMapping{

        /*  PROJECT METHODES GET */
        public static final String GET_BY_ID ="/project/{id}/";
        public static final String GET_LIST ="/project/list/";

        /* PROJECT METHODES POST */
        public static final String POST_ADD ="/project/add/";
        public static final String POST_EDIT ="/project/edit/";
    }

    public class ZonageMapping{

        /*  PROJECT METHODES GET */
        public static final String GET_BY_ID ="/zone/{id}/";
        public static final String GET_LIST ="/zone/list/";
        public static final String GET_MAP ="/zone/map/";

        /* PROJECT METHODES POST */
        public static final String POST_ADD ="/zone/add/";
        public static final String POST_EDIT ="/zone/edit/";


    }


    /**
     * -----------------------------------------------------------
     *  ROLES
     * ----------------------------------------------------------
     */

    public final class RoleMapping{
    /*  PROJECT METHODES GET */
    public static final String GET_BY_ID ="/role/{id}/";
    public static final String GET_LIST ="/role/list/";

    /* PROJECT METHODES POST */
    public static final String POST_ADD ="/role/add/";
    public static final String POST_EDIT ="/role/edit/";

    }
    
    /**
     * -----------------------------------------------------------
     *  LOCALISATION
     * ----------------------------------------------------------
     */

    public final class LocalisationMapping{

        /*  LOCALISATION METHODES GET */
        public static final String GET_BY_ID ="/localisation/{id}/";
        public static final String GET_LIST ="/localisation/list/";

        /* LOCALISATION METHODES POST */
        public static final String POST_ADD ="/localisation/add/";
        public static final String POST_EDIT ="/localisation/edit/";
        
        
    }
     
    /**
     * -----------------------------------------------------------
     *  ETAT
     * ----------------------------------------------------------
     */

    public static final class EtatMapping{
        public static final String DELETE_BY_ID ="/etat/delete";
    }
        /**
     * -----------------------------------------------------------
     *  JOURNAL
     * ----------------------------------------------------------
     */
     public static final class JournalMapping{
        public static final String DELETE_BY_ID ="/journal/delete";
          public static final String ADD ="/journal/add";
     }


    public ZonageMapping getZoneMapping() {
        return zoneMapping;
    }

    public void setZoneMapping(ZonageMapping zoneMapping) {
        this.zoneMapping = zoneMapping;
    }
}
