package id.ols.rest_adapter;

import com.squareup.okhttp.RequestBody;

import java.util.List;

import id.ols.models.PojoRegions;
import id.ols.models.PojoResponseInsert;
import id.ols.models.PojoResponseLogin;
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
            @Field("username")String username,
            @Field("password")String password
    );

    @GET("API.php?exe=get&type=mobile&kind=region")
    Observable<PojoRegions> get_regions(
            @Header("Api-Key") String apikey
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_site_detail(
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
            @Part("site_phoneengineer")RequestBody site_phoneenginer,
            @Part("site_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_earthing_grounding(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("eartingngrounding_idsite")RequestBody site_codeid,
            @Part("eartingngrounding_busbarphoto\"; filename=\"img0.png\" ")RequestBody img0,
            @Part("eartingngrounding_earthrunsphoto\"; filename=\"img1.png\" ")RequestBody img1,
            @Part("eartingngrounding_connectionsphoto\"; filename=\"img2.png\" ")RequestBody img2
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_microwave(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("microwave_idsite")RequestBody microwave_idsite,
            @Part("microwave_idmanufacturer")RequestBody microwave_idmanufacturer,
            @Part("microwave_idmodel")RequestBody microwave_idmodel,
            @Part("microwave_voltase")RequestBody microwave_voltase,
            @Part("microwave_dcload")RequestBody microwave_dcload,
            @Part("microwave_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_ip(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("ip_idsite")RequestBody idsite,
            @Part("ip_idmanufacturer")RequestBody idmanufacturer,
            @Part("ip_idmodel")RequestBody idmodel,
            @Part("ip_voltase")RequestBody voltase,
            @Part("ip_dcload")RequestBody dcload,
            @Part("ip_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_dwdm(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("dwdm_idsite")RequestBody idsite,
            @Part("dwdm_idmanufacturer")RequestBody idmanufacturer,
            @Part("dwdm_idmodel")RequestBody idmodel,
            @Part("dwdm_voltase")RequestBody voltase,
            @Part("dwdm_dcload")RequestBody dcload,
            @Part("dwdm_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_superwifi(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("superwifi_idsite")RequestBody idsite,
            @Part("superwifi_idmanufacturer")RequestBody idmanufacturer,
            @Part("superwifi_idmodel")RequestBody idmodel,
            @Part("superwifi_voltase")RequestBody voltase,
            @Part("superwifi_dcload")RequestBody dcload,
            @Part("superwifi_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_mpls(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("mpls_idsite")RequestBody microwave_idsite,
            @Part("mpls_idmanufacturer")RequestBody idmanufacturer,
            @Part("mpls_idmodel")RequestBody idmodel,
            @Part("mpls_voltase")RequestBody voltase,
            @Part("mpls_dcload")RequestBody dcload,
            @Part("mpls_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_vsat(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("vsat_idsite")RequestBody idsite,
            @Part("vsat_idmanufacturer")RequestBody idmanufacturer,
            @Part("vsat_idmodel")RequestBody idmodel,
            @Part("vsat_voltase")RequestBody voltase,
            @Part("vsat_dcload")RequestBody dcload,
            @Part("vsat_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_midi(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("midi_idsite")RequestBody idsite,
            @Part("midi_idmanufacturer")RequestBody idmanufacturer,
            @Part("midi_idmodel")RequestBody idmodel,
            @Part("midi_voltase")RequestBody voltase,
            @Part("midi_dcload")RequestBody dcload,
            @Part("midi_photo\"; filename=\"img0.png\" ")RequestBody img0
    );

    @Multipart
    @POST("API.php?")
    Observable<PojoResponseInsert> insert_tech_gpon(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("gpon_idsite")RequestBody idsite,
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
            @Part("coolingcabinet_idsite")RequestBody coolingcabinet_idsite,
            @Part("coolingcabinet_manufacturer")RequestBody coolingcabinet_manufacturer,
            @Part("coolingcabinet_model")RequestBody coolingcabinet_model,
            @Part("coolingcabinet_bturating")RequestBody coolingcabinet_bturating,
            @Part("coolingcabinet_dcload")RequestBody coolingcabinet_dcload,
            @Part("coolingcabinet_condition")RequestBody coolingcabinet_condition,
            @Part("gpon_photo\"; filename=\"img0.png\" ")RequestBody img0
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
