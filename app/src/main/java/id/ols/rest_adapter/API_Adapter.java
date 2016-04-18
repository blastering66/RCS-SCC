package id.ols.rest_adapter;

import id.ols.models.PojoResponseLogin;
import id.ols.util.ParameterCollections;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
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
}
