package id.ols.util;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import id.ols.rest_adapter.API_Adapter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by macbook on 4/20/16.
 */
public class PublicFunctions {

    public static API_Adapter initRetrofit(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(270, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(270, TimeUnit.SECONDS);

        Retrofit retrofit_test = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .baseUrl(ParameterCollections.URL_BASE).build();
        API_Adapter adapter = retrofit_test.create(API_Adapter.class);
        return adapter;
    }
}
