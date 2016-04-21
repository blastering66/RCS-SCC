package id.ols.util;

import id.ols.rest_adapter.API_Adapter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by macbook on 4/20/16.
 */
public class PublicFunctions {

    public static API_Adapter initRetrofit(){
        Retrofit retrofit_test = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ParameterCollections.URL_BASE).build();
        API_Adapter adapter = retrofit_test.create(API_Adapter.class);
        return adapter;
    }
}
