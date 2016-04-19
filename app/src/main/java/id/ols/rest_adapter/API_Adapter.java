package id.ols.rest_adapter;

import com.squareup.okhttp.RequestBody;

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

/**
 * Created by macbook on 4/18/16.
 */
public interface API_Adapter {

    @GET("API.php?")
    Call<PojoResponseLogin> login(
            @Header("Api-Key") String apikey,
            @Query("exe")String exe,
            @Query("kind")String kind,
            @Query("username")String username,
            @Query("password")String password
    );

    @GET("test.php")
    Call<PojoResponseLogin> login_test(
            @Header("Api-Key") String apikey
    );

//    @FormUrlEncoded
//    @POST("insert.php?")
//    Call<PojoResponseInsert> insert_join_event(
//            @Field("kind") String kind,
//            @Field("eventid") String eventid,
//            @Field("userid") String userid
//    );

    @GET("API.php?")
    Call<PojoResponseLogin> get_regions(
            @Header("Api-Key") String apikey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind
    );

//    @Multipart
//    @POST("API.php?")
//    Call<PojoResponseInsert> insert_site_detail(
//            @Header("Api-Key") String apikey,
//            @Header("Auth-Key") String authkey,
//            @Query("exe")String exe,
//            @Query("type")String type,
//            @Query("kind")String kind,
//            @Part("site_codeid")RequestBody site_codeid,
//            @Part("site_name")RequestBody site_name,
//            @Part("site_mobileno")RequestBody site_mobileno ,
//            @Part("site_type")RequestBody site_type,
//            @Part("site_keeper")RequestBody site_keeper,
//            //idRegons ??
//            @Part("site_idregion")RequestBody site_idcluster,
//            @Part("site_location")RequestBody site_location,
//            @Part("site_altitude")RequestBody site_altitude,
//            @Part("site_timesurveystarted")RequestBody site_timesurveystarted,
//            @Part("site_externaltemperature")RequestBody site_externaltemperature,
//            @Part("site_idweathercondition")RequestBody site_idweathercondition
////            @Part("img0\"; filename=\"img0.png\" ")RequestBody img0
//    );

    @Multipart
    @POST("API.php?")
    Call<PojoResponseInsert> insert_site_detail(
            @Header("Api-Key") String apikey,
            @Header("Auth-Key") String authkey,
            @Query("exe")String exe,
            @Query("type")String type,
            @Query("kind")String kind,
            @Part("site_codeid")String site_codeid,
            @Part("site_name")String site_name,
            @Part("site_mobileno")String site_mobileno ,
            @Part("site_type")String site_type,
            @Part("site_keeper")String site_keeper,
            //idRegons ??
            @Part("site_idregion")String site_idcluster,
            @Part("site_location")String site_location,
            @Part("site_altitude")String site_altitude,
            @Part("site_timesurveystarted")String site_timesurveystarted,
            @Part("site_externaltemperature")String site_externaltemperature,
            @Part("site_idweathercondition")String site_idweathercondition
//            @Part("img0\"; filename=\"img0.png\" ")RequestBody img0
    );
}
