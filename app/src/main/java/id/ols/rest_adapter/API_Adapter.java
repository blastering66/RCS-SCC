package id.ols.rest_adapter;

import com.squareup.okhttp.RequestBody;

import java.util.List;

import id.ols.models.PojoCluster;
import id.ols.models.PojoManufactureDCPower;
import id.ols.models.PojoManufactureDCPowerModel;
import id.ols.models.PojoManufactureDwdm;
import id.ols.models.PojoManufactureDwdmModel;
import id.ols.models.PojoManufactureGpon;
import id.ols.models.PojoManufactureGponModel;
import id.ols.models.PojoManufactureIp;
import id.ols.models.PojoManufactureIpModel;
import id.ols.models.PojoManufactureMicrowave;
import id.ols.models.PojoManufactureMicrowaveModel;
import id.ols.models.PojoManufactureMidi;
import id.ols.models.PojoManufactureMidiModel;
import id.ols.models.PojoManufactureMpls;
import id.ols.models.PojoManufactureMplsModel;
import id.ols.models.PojoManufactureRAN;
import id.ols.models.PojoManufactureRANModel;
import id.ols.models.PojoManufactureSuperWifi;
import id.ols.models.PojoManufactureSuperWifiModel;
import id.ols.models.PojoManufactureVsat;
import id.ols.models.PojoManufactureVsatModel;
import id.ols.models.PojoRegions;
import id.ols.models.PojoResponseInsert;
import id.ols.models.PojoResponseInsertSite;
import id.ols.models.PojoResponseLogin;
import id.ols.models.PojoSite;
import id.ols.models.PojoSubCluster;
import id.ols.models.PojoSubRegions;
import id.ols.models.PojoWeather;
import id.ols.util.ParameterCollections;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by macbook on 4/18/16.
 */
public interface API_Adapter {

    @FormUrlEncoded
    @POST("API.php?exe=login&type=mobile")
    Call<PojoResponseLogin> login(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Field("username")String username,
            @Field("password")String password
    );

    @GET("API.php?exe=get&type=mobile&kind=weather")
    Observable<PojoWeather> get_weather(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=region")
    Observable<PojoRegions> get_regions(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=subregion")
    Observable<PojoSubRegions> get_sub_regions(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("region_id") String id

    );

    @GET("API.php?exe=get&type=mobile&kind=cluster")
    Observable<PojoCluster> get_cluster_regions(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("subregion_id") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=subcluster")
    Observable<PojoSubCluster> get_sub_cluster_regions(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("cluster_id") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=search_siteid")
    Observable<PojoSite> get_site_name_id(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("subcluster_id") String id,
            @Query("site_codeid") String site_codeid
    );

    @GET("API.php?exe=get&type=mobile&kind=ran_model")
    Observable<PojoManufactureRANModel> get_cluster(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idsubregions") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=ran_manufacturer")
    Observable<PojoManufactureRAN> get_manufacture_ran(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=ran_model")
    Observable<PojoManufactureRANModel> get_manufacture_ran_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=microwave_manufacturer")
    Observable<PojoManufactureMicrowave> get_manufacture_microwave(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=microwave_model")
    Observable<PojoManufactureMicrowaveModel> get_manufacture_microwave_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=ip_manufacturer")
    Observable<PojoManufactureIp> get_manufacture_ip(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=ip_model")
    Observable<PojoManufactureIpModel> get_manufacture_ip_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=dwdm_manufacturer")
    Observable<PojoManufactureDwdm> get_manufacture_dwdm(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=dwdm_model")
    Observable<PojoManufactureDwdmModel> get_manufacture_dwdm_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=superwifi_manufacturer")
    Observable<PojoManufactureSuperWifi> get_manufacture_superwifi(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=superwifi_model")
    Observable<PojoManufactureSuperWifiModel> get_manufacture_superwifi_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=mpls_manufacturer")
    Observable<PojoManufactureMpls> get_manufacture_mpls(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=mpls_model")
    Observable<PojoManufactureMplsModel> get_manufacture_mpls_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=vsat_manufacturer")
    Observable<PojoManufactureVsat> get_manufacture_vsat(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=vsat_model")
    Observable<PojoManufactureVsatModel> get_manufacture_vsat_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=midi_manufacturer")
    Observable<PojoManufactureMidi> get_manufacture_midi(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=midi_model")
    Observable<PojoManufactureMidiModel> get_manufacture_midi_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=gpon_manufacturer")
    Observable<PojoManufactureGpon> get_manufacture_gpon(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=gpon_model")
    Observable<PojoManufactureGponModel> get_manufacture_gpon_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @GET("API.php?exe=get&type=mobile&kind=dcpower_manufacturer")
    Observable<PojoManufactureDCPower> get_manufacture_dcpower(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey
    );

    @GET("API.php?exe=get&type=mobile&kind=dcpower_model")
    Observable<PojoManufactureDCPowerModel> get_manufacture_dcpower_model(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("idmanufacturer") String id
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsertSite> insert_site_detail(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("sitevisit_codeid")RequestBody site_codeid,
            @Part("sitevisit_name")RequestBody site_name,
            @Part("sitevisit_mobileno")RequestBody site_mobileno ,
            @Part("sitevisit_type")RequestBody site_type,
            @Part("sitevisit_keeper")RequestBody site_keeper,
            //idRegons ??
            @Part("sitevisit_latitude")RequestBody sitevisit_latitude,
            @Part("sitevisit_longitude")RequestBody sitevisit_longitude,
//            @Part("sitevisit_altitude")RequestBody site_altitude,
            @Part("sitevisit_timesurveystarted")RequestBody site_timesurveystarted,
            @Part("sitevisit_externaltemperature")RequestBody site_externaltemperature,
            @Part("sitevisit_idweathercondition")RequestBody site_idweathercondition,

//            @Part("sitevisit_nameengineer")RequestBody sitevisit_nameengineer,
//            @Part("sitevisit_emailengineer")RequestBody sitevisit_emailengineer,
//            @Part("sitevisit_phoneengineer")RequestBody sitevisit_phoneengineer,

            @Part("sitevisit_ownership")RequestBody sitevisit_ownership,
            @Part("sitevisit_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsertSite> insert_site_detail_test(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("site_codeid")RequestBody site_codeid,
            @Part("site_name")RequestBody site_name,
            @Part("site_mobileno")RequestBody site_mobileno ,
            @Part("site_type")RequestBody site_type,
            @Part("site_keeper")RequestBody site_keeper,
            //idRegons ??
            @Part("site_idregion")RequestBody site_idcluster,
            @Part("site_location")RequestBody site_location,
            @Part("site_altitude")RequestBody site_altitude,
            @Part("site_timesurveystarted")RequestBody site_timesurveystarted,
            @Part("site_externaltemperature")RequestBody site_externaltemperature,
            @Part("site_idweathercondition")RequestBody site_idweathercondition,

            @Part("site_nameengineer")RequestBody site_nameenginer,
            @Part("site_emailengineer")RequestBody site_emailenginer,
            @Part("site_phoneengineer")RequestBody site_phoneenginer
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=earthing_n_grounding")
    Observable<PojoResponseInsert> insert_tech_earthing_grounding(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("eartingngrounding_idsitevisit")RequestBody site_codeid,
            @Part("eartingngrounding_busbarphoto\"; filename=\"img0.png\" ")RequestBody img0,
            @Part("eartingngrounding_earthrunsphoto\"; filename=\"img1.png\" ")RequestBody img1,
            @Part("eartingngrounding_connectionsphoto\"; filename=\"img2.png\" ")RequestBody img2
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=ran")
    Observable<PojoResponseInsert> insert_tech_ran(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("ran_idsitevisit")RequestBody ran_idsitevisit,
            @Part("ran_idmanufacturer")RequestBody ran_idmanufacturer,
            @Part("ran_idmodel")RequestBody ran_idmodel,
            @Part("ran_frequency")RequestBody ran_frequency,
            @Part("ran_configuration")RequestBody ran_configuration,
            @Part("ran_dcload")RequestBody ran_dcload,
            @Part("ran_voltase")RequestBody ran_voltase,
            @Part("ran_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=microwave")
    Observable<PojoResponseInsert> insert_tech_microwave(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("microwave_idsitevisit")RequestBody microwave_idsite,
            @Part("microwave_idmanufacturer")RequestBody microwave_idmanufacturer,
            @Part("microwave_idmodel")RequestBody microwave_idmodel,
            @Part("microwave_voltase")RequestBody microwave_voltase,
            @Part("microwave_dcload")RequestBody microwave_dcload,
            @Part("microwave_photo\"; filename=\"img0.png\" ")RequestBody img0
    );



    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=ip")
    Observable<PojoResponseInsert> insert_tech_ip(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("ip_idsitevisit")RequestBody idsite,
            @Part("ip_idmanufacturer")RequestBody idmanufacturer,
            @Part("ip_idmodel")RequestBody idmodel,
            @Part("ip_voltase")RequestBody voltase,
            @Part("ip_loads")RequestBody dcload,
            @Part("ip_current")RequestBody ip_current,
            @Part("ip_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=dwdm")
    Observable<PojoResponseInsert> insert_tech_dwdm(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("dwdm_idsitevisit")RequestBody idsite,
            @Part("dwdm_idmanufacturer")RequestBody idmanufacturer,
            @Part("dwdm_idmodel")RequestBody idmodel,
            @Part("dwdm_voltase")RequestBody voltase,
            @Part("dwdm_dcload")RequestBody dcload,
            @Part("dwdm_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=superwifi")
    Observable<PojoResponseInsert> insert_tech_superwifi(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("superwifi_idsitevisit")RequestBody idsite,
            @Part("superwifi_idmanufacturer")RequestBody idmanufacturer,
            @Part("superwifi_idmodel")RequestBody idmodel,
            @Part("superwifi_voltase")RequestBody voltase,
            @Part("superwifi_loads")RequestBody superwifi_loads,
            @Part("superwifi_current")RequestBody superwifi_current,
            @Part("superwifi_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=mpls")
    Observable<PojoResponseInsert> insert_tech_mpls(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("mpls_idsitevisit")RequestBody microwave_idsite,
            @Part("mpls_idmanufacturer")RequestBody idmanufacturer,
            @Part("mpls_idmodel")RequestBody idmodel,
            @Part("mpls_voltase")RequestBody voltase,
            @Part("mpls_dcload")RequestBody dcload,
            @Part("mpls_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=vsat")
    Observable<PojoResponseInsert> insert_tech_vsat(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("vsat_idsitevisit")RequestBody idsite,
            @Part("vsat_idmanufacturer")RequestBody idmanufacturer,
            @Part("vsat_idmodel")RequestBody idmodel,
            @Part("vsat_voltase")RequestBody voltase,
            @Part("vsat_dcload")RequestBody dcload,
            @Part("vsat_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=midi")
    Observable<PojoResponseInsert> insert_tech_midi(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("midi_idsitevisit")RequestBody idsite,
            @Part("midi_idmanufacturer")RequestBody idmanufacturer,
            @Part("midi_idmodel")RequestBody idmodel,
            @Part("midi_voltase")RequestBody voltase,
            @Part("midi_dcload")RequestBody dcload,
            @Part("midi_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=gpon")
    Observable<PojoResponseInsert> insert_tech_gpon(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("gpon_idsitevisit")RequestBody idsite,
            @Part("gpon_idmanufacturer")RequestBody idmanufacturer,
            @Part("gpon_idmodel")RequestBody idmodel,
            @Part("gpon_voltase")RequestBody voltase,
            @Part("gpon_dcload")RequestBody dcload,
            @Part("gpon_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_coolingcabinet(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("coolingcabinet_idsitevisit")RequestBody coolingcabinet_idsite,
            @Part("coolingcabinet_manufacturer")RequestBody coolingcabinet_manufacturer,
            @Part("coolingcabinet_model")RequestBody coolingcabinet_model,
            @Part("coolingcabinet_bturating")RequestBody coolingcabinet_bturating,
            @Part("coolingcabinet_dcload")RequestBody coolingcabinet_dcload,
            @Part("coolingcabinet_condition")RequestBody coolingcabinet_condition,
            @Part("coolingcabinet_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_battery(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("batterydetail_idsitevisit")RequestBody batterydetail_idsitevisit,
            @Part("batterydetail_manufacturer")RequestBody batterydetail_manufacturer,
            @Part("batterydetail_model")RequestBody batterydetail_model,
            @Part("batterydetail_sitebatterytype")RequestBody batterydetail_sitebatterytype,
            @Part("batterydetail_battery2vcells")RequestBody batterydetail_battery2vcells,
            @Part("batterydetail_batteryblock")RequestBody batterydetail_batteryblock,
            @Part("batterydetail_batterycapacity")RequestBody batterydetail_batterycapacity,
            @Part("batterydetail_voltage")RequestBody batterydetail_voltage,
            @Part("batterydetail_batterytemperature")RequestBody batterydetail_batterytemperature,
            @Part("batterydetail_batteryconditions")RequestBody batterydetail_batteryconditions,
            @Part("batterydetail_batteryphoto\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_dcpower(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("dcpower_idsitevisit")RequestBody dcpower_idsitevisit,
            @Part("dcpower_idmanufacturer")RequestBody dcpower_idmanufacturer,
            @Part("dcpower_idmodel")RequestBody dcpower_idmodel,
//            @Part("dcpower_controllermanufacturer")RequestBody dcpower_controllermanufacturer,
//            @Part("dcpower_controllermodel")RequestBody dcpower_controllermodel,
            @Part("dcpower_rectifiermodmanufacturer")RequestBody dcpower_rectifiermodmanufacturer,
            @Part("dcpower_rectifiermodmodel")RequestBody dcpower_rectifiermodmodel,
            @Part("dcpower_rectifiermodoutputrating")RequestBody dcpower_rectifiermodoutputrating,
            @Part("dcpower_rectifiermodqtyinstaled")RequestBody dcpower_rectifiermodqtyinstaled,
            @Part("dcpower_rectifiermodnumberspareslot")RequestBody dcpower_rectifiermodnumberspareslot,

            @Part("dcpower_arrestermanufacturer")RequestBody dcpower_arrestermanufacturer,
            @Part("dcpower_arrestermodel")RequestBody dcpower_arrestermodel,
            @Part("dcpower_acinputbreakertype")RequestBody dcpower_acinputbreakertype,
            @Part("dcpower_acinputbreakerrating")RequestBody dcpower_acinputbreakerrating,

            @Part("dcpower_dcbreakersrating")RequestBody dcpower_dcbreakersrating,
            @Part("dcpower_dcbreakersquantity")RequestBody dcpower_dcbreakersquantity,

            @Part("dcpower_dcload")RequestBody dcpower_dcload,
            @Part("dcpower_dccabinetcoolingmanufacturer")RequestBody dcpower_dccabinetcoolingmanufacturer,
            @Part("dcpower_dccabinetcoolingmodel")RequestBody dcpower_dccabinetcoolingmodel,

            @Part("dcpower_controllerphoto\"; filename=\"img0.png\" ")RequestBody img0,
            @Part("dcpower_acinputbreakerphoto\"; filename=\"img1.png\" ")RequestBody img1,
            @Part("dcpower_dcbreakersphoto\"; filename=\"img2.png\" ")RequestBody img2,
            @Part("dcpower_dccabinetcoolingphoto\"; filename=\"img2.png\" ")RequestBody img3
    );

//    @Multipart
//    @POST("API.php?")
//    Observable<PojoResponseInsert> insert_tech_additional(
//            @Header("Api-Key") String apikey,
//            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
//            @Part("additionalloads_idsite")RequestBody additionalloads_idsite,
//
//            @Part("additionalloadsawl_option")RequestBody additionalloadsawl_option,  /* Yes,No */
//            @Part("additionalloadsawl_current")RequestBody additionalloadsawl_option2,     /* AC,DC */
//            @Part("additionalloadsawl_load")RequestBody additionalloadsawl_load,     /* float */
//
//            @Part("additionalloadsesl_option")RequestBody additionalloadsesl_option,  /* Yes,No */
//            @Part("additionalloadsesl_current")RequestBody additionalloadsesl_option2,     /* AC,DC */
//            @Part("additionalloadsesl_load")RequestBody additionalloadsesl_load,     /* float */
//
//            @Part("additionalloadsffe_option")RequestBody additionalloadsffe_option,  /* Yes,No */
//            @Part("additionalloadsffe_current")RequestBody additionalloadsffe_option2,     /* AC,DC */
//            @Part("additionalloadsffe_load")RequestBody additionalloadsffe_load,     /* float */
//
//            @Part("additionalloadssse_option")RequestBody additionalloadssse_option,  /* Yes,No */
//            @Part("additionalloadssse_current")RequestBody additionalloadssse_option2,     /* AC,DC */
//            @Part("additionalloadssse_load")RequestBody additionalloadssse_load,     /* float */
//
//            @Part("additionalloadsoe_option")RequestBody additionalloadsoe_option,  /* Yes,No */
//            @Part("additionalloadsoe_current")RequestBody additionalloadsoe_option2,     /* AC,DC */
//            @Part("additionalloadsoe_load")RequestBody additionalloadsoe_load     /* float */
//
//    );

    @Multipart
    @POST("API.php?exe=insert&type=mobile&kind=griddetail")
    Observable<PojoResponseInsert> insert_tech_grid_detail(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Part("griddetail_idsitevisit")RequestBody griddetail_idsitevisit,
            @Part("griddetail_plnsiteid")RequestBody griddetail_plnsiteid,
            @Part("griddetail_plnmetertype")RequestBody griddetail_plnmetertype ,
            @Part("griddetail_plnmetermanufacturer")RequestBody griddetail_plnmetermanufacturer,
            @Part("griddetail_plnmetermodel")RequestBody griddetail_plnmetermodel,
            //idRegons ??
            @Part("griddetail_plnmetercondition")RequestBody griddetail_plnmetercondition,
//            @Part("griddetail_plnmeterdistancerectifier")RequestBody griddetail_plnmeterdistancerectifier,
            @Part("griddetail_plnmeternumberofphases")RequestBody griddetail_plnmeternumberofphases,
            @Part("griddetail_plnphasemeasurement")RequestBody griddetail_plnphasemeasurement,
            @Part("griddetail_plnphasemeasurement3r")RequestBody griddetail_plnphasemeasurement3r,
            @Part("griddetail_plnphasemeasurement3s")RequestBody griddetail_plnphasemeasurement3s,

            @Part("griddetail_plnphasemeasurement3t")RequestBody griddetail_plnphasemeasurement3t,
            @Part("griddetail_plndayabreakerrating")RequestBody griddetail_plndayabreakerrating,
            @Part("griddetail_plnsurgeprotdevmanufacturer")RequestBody griddetail_plnsurgeprotdevmanufacturer,
//            @Part("griddetail_plnsurgeprotdevtype")RequestBody griddetail_plnsurgeprotdevtype,
            @Part("griddetail_plnsurgeprotdevcondition")RequestBody griddetail_plnsurgeprotdevcondition,
            @Part("griddetail_plnsurgeprotdevmodel")RequestBody griddetail_plnsurgeprotdevmodel,
            @Part("griddetail_plnacpdbmanufacturer")RequestBody griddetail_plnacpdbmanufacturer,
            @Part("griddetail_plnacpdbmodel")RequestBody griddetail_plnacpdbmodel,
            @Part("griddetail_plnphaseacpdbcondition")RequestBody griddetail_plnphaseacpdbcondition,
            @Part("griddetail_plnacpdbdistancepln")RequestBody griddetail_plnacpdbdistancepln,

            @Part("griddetail_plnmeterphoto\"; filename=\"img0.png\" ")RequestBody img0,
            @Part("griddetail_plnmeterphoto_initial\"; filename=\"img4.png\" ")RequestBody img4,
            @Part("griddetail_plndayabreakerphoto\"; filename=\"img1.png\" ")RequestBody img1,
            @Part("griddetail_plnsurgeprotdevphoto\"; filename=\"img2.png\" ")RequestBody img2,
            @Part("griddetail_plnphaseacpdbphoto\"; filename=\"img3.png\" ")RequestBody img3
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_generator(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("generatordetail_idsitevisit")RequestBody generatordetail_idsitevisit,
            @Part("generatordetail_manufacturer")RequestBody generatordetail_manufacturer,
            @Part("generatordetail_model")RequestBody generatordetail_model,
            @Part("generatordetail_phases")RequestBody generatordetail_phases,
            @Part("generatordetail_remotestart")RequestBody generatordetail_remotestart,
            @Part("generatordetail_primaryrating")RequestBody generatordetail_primaryrating,
            @Part("generatordetail_standbyrating")RequestBody generatordetail_standbyrating,
            @Part("generatordetail_speed")RequestBody generatordetail_speed,
            @Part("generatordetail_frequency")RequestBody generatordetail_frequency,
            @Part("generatordetail_outputvoltage")RequestBody generatordetail_outputvoltage,

            @Part("generatordetail_connectionsl1")RequestBody generatordetail_connectionsl1,
            @Part("generatordetail_connectionsl2")RequestBody generatordetail_connectionsl2,
            @Part("generatordetail_connectionsl3")RequestBody generatordetail_connectionsl3,

            @Part("generatordetail_loadsl1")RequestBody generatordetail_loadsl1,
            @Part("generatordetail_loadsl2")RequestBody generatordetail_loadsl2,
            @Part("generatordetail_loadsl3")RequestBody generatordetail_loadsl3,

            @Part("generatordetail_runninghours")RequestBody generatordetail_runninghours,
            @Part("generatordetail_condition")RequestBody generatordetail_condition,
            @Part("generatordetail_generatorphoto\"; filename=\"img0.png\" ")RequestBody img0,

            @Part("generatordetail_enginemanufacturer")RequestBody generatordetail_enginemanufacturer,
            @Part("generatordetail_enginemodel")RequestBody generatordetail_enginemodel,

            @Part("generatordetail_altenatormanufacturer")RequestBody generatordetail_altenatormanufacturer,
            @Part("generatordetail_altenatormodel")RequestBody generatordetail_altenatormodel,

            @Part("generatordetail_controllerfitted")RequestBody generatordetail_controllerfitted,
            @Part("generatordetail_controllerphoto\"; filename=\"img1.png\" ")RequestBody img1,

            @Part("generatordetail_batteryfitted")RequestBody generatordetail_batteryfitted,
            @Part("generatordetail_batterymanufacturer")RequestBody generatordetail_batterymanufacturer,
            @Part("generatordetail_batterymodel")RequestBody generatordetail_batterymodel,
            @Part("generatordetail_batterycondition")RequestBody generatordetail_batterycondition,
            @Part("generatordetail_voltase")RequestBody generatordetail_voltase,

            @Part("generatordetail_atspresent")RequestBody generatordetail_atspresent,
            @Part("generatordetail_atsmanufacturer")RequestBody generatordetail_atsmanufacturer,
            @Part("generatordetail_atsmodel")RequestBody generatordetail_atsmodel,
            @Part("generatordetail_atscondition")RequestBody generatordetail_atscondition,
            @Part("generatordetail_atsphoto\"; filename=\"img2.png\" ")RequestBody img2,

            @Part("generatordetail_amfpresent")RequestBody generatordetail_amfpresent,
            @Part("generatordetail_amfmanufacturer")RequestBody generatordetail_amfmanufacturer,
            @Part("generatordetail_amfmodel")RequestBody generatordetail_amfmodel,
            @Part("generatordetail_amfcondition")RequestBody generatordetail_amfcondition,
            @Part("generatordetail_amfphoto\"; filename=\"img3.png\" ")RequestBody img3,

            @Part("generatordetail_capacity")RequestBody generatordetail_capacity,
            @Part("generatordetail_capacitycondition")RequestBody generatordetail_capacitycondition,
            @Part("generatordetail_capacityphoto\"; filename=\"img4.png\" ")RequestBody img4,

            @Part("generatordetail_fullprobefitted")RequestBody generatordetail_fullprobefitted,
            @Part("generatordetail_fullprobemanufacturer")RequestBody generatordetail_fullprobemanufacturer,
            @Part("generatordetail_fullprobemodel")RequestBody generatordetail_fullprobemodel,
            @Part("generatordetail_fullprobecondition")RequestBody generatordetail_fullprobecondition,
            @Part("generatordetail_fullprobephoto\"; filename=\"img5.png\" ")RequestBody img5
    );

    @FormUrlEncoded
    @POST("API.php?exe=insert&kind=additional_loads&type=mobile")
    Observable<PojoResponseInsert> insert_tech_additional_test(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
            @Field("additionalloads_idsitevisit")String additionalloads_idsite,

            @Field("additionalloadsawl_option")String additionalloadsawl_option,  /* Yes,No */
            @Field("additionalloadsawl_current")String additionalloadsawl_option2,     /* AC,DC */
            @Field("additionalloadsawl_load")String additionalloadsawl_load,     /* float */

            @Field("additionalloadsesl_option")String additionalloadsesl_option,  /* Yes,No */
            @Field("additionalloadsesl_current")String additionalloadsesl_option2,     /* AC,DC */
            @Field("additionalloadsesl_load")String additionalloadsesl_load,     /* float */

            @Field("additionalloadsffe_option")String additionalloadsffe_option,  /* Yes,No */
            @Field("additionalloadsffe_current")String additionalloadsffe_option2,     /* AC,DC */
            @Field("additionalloadsffe_load")String additionalloadsffe_load,     /* float */

            @Field("additionalloadssse_option")String additionalloadssse_option,  /* Yes,No */
            @Field("additionalloadssse_current")String additionalloadssse_option2,     /* AC,DC */
            @Field("additionalloadssse_load")String additionalloadssse_load,     /* float */

            @Field("additionalloadsoe_option")String additionalloadsoe_option,  /* Yes,No */
            @Field("additionalloadsoe_current")String additionalloadsoe_option2,     /* AC,DC */
            @Field("additionalloadsoe_load")String additionalloadsoe_load     /* float */

    );
}
