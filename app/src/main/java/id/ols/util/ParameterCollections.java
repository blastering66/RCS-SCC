package id.ols.util;

/**
 * Created by RebelCreative-A1 on 21/03/2016.
 */
public class ParameterCollections {

    public static String SH_NAME = "rcs_life";
    public static String SH_ID_SITE = "id_site_visit";
    public static String SH_AUTHKEY = "rcs_life_auth";
    public static String SH_LOGGED = "rcs_life_islogged";
    public static String SH_VISIT_FINISHED = "visit_finished";
    public static String SH_SITEVISIT_INPUTED = "sitevisit_inputed";

    public static String TAG_LATITUDE_NOW = "lati_now";
    public static String TAG_LONGITUDE_NOW = "longi_now";

    public static String SH_GENERATOR_SUBMITTED = "rcs_life_generator_submited";
    public static String SH_GRID_SUBMITTED = "rcs_life_grid_submited";
    public static String SH_BATTERY_SUBMITTED = "rcs_life_battery_submited";
    public static String SH_EARTH_SUBMITTED = "rcs_life_earthing_submited";
    public static String SH_DCPOWER_SUBMITTED = "rcs_life_dcpower_submited";
    public static String SH_RAN_SUBMITTED = "rcs_life_ran_submited";
    public static String SH_MICROWAVE_SUBMITTED = "rcs_life_microwave_submited";
    public static String SH_IP_SUBMITTED = "rcs_life_ip_submited";
    public static String SH_DWDM_SUBMITTED = "rcs_life_dwdm_submited";
    public static String SH_SUPERWIFI_SUBMITTED = "rcs_life_superwifi_submited";
    public static String SH_MPLS_SUBMITTED = "rcs_life_mpls_submited";
    public static String SH_VSAT_SUBMITTED = "rcs_life_vsat_submited";
    public static String SH_MIDI_SUBMITTED = "rcs_life_midi_submited";
    public static String SH_GPON_SUBMITTED = "rcs_life_gpon_submited";
    public static String SH_COOLINGCABINET_SUBMITTED = "rcs_life_cooling_submited";
    public static String SH_ADDITIONAL_SUBMITTED = "rcs_life_additional_submited";


//    public static String URL_BASE = "http://cascadiant.dev.onelifesolution.id/";
    public static String URL_BASE = "http://api.cascadiant.onelifesolution.id/";

    public static String URL_FOLDER_IMG = "/SCC/temp/";

    public static class KIND{
        public static String TRUE = "true";
        public static String MOBILE = "mobile";
        public static String LOGIN = "login";
        public static String SITE = "site";
        public static String SITE_VISIT = "site_visit";
        public static String TECH_COOLING = "cooling_cabinet";
        public static String TECH_BATTERY = "battery";
        public static String TECH_DCPOWER = "dcpower";
        public static String TECH_GENERATOR = "generatordetail";
    }

    public static class EXE{
        public static String INSERT = "insert";
        public static String GET = "get";
        public static String UPDATE = "update";
        public static String DELETE = "delete";
    }
}
