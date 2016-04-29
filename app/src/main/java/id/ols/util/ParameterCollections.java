package id.ols.util;

/**
 * Created by RebelCreative-A1 on 21/03/2016.
 */
public class ParameterCollections {

    public static String SH_NAME = "rcs_life";
    public static String SH_ID_SITE = "id_site_visit";
    public static String SH_AUTHKEY = "rcs_life_auth";
    public static String SH_LOGGED = "rcs_life_islogged";
    public static String TAG_LATITUDE_NOW = "lati_now";
    public static String TAG_LONGITUDE_NOW = "longi_now";

    public static String SH_GENERATOR_SUBMITTED = "rcs_life_generator_submited";

    public static String URL_BASE = "http://cascadiant.dev.onelifesolution.id/";
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
    }

    public static class EXE{
        public static String INSERT = "insert";
        public static String GET = "get";
        public static String UPDATE = "update";
        public static String DELETE = "delete";
    }
}
