package id.ols.rest_adapter;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by macbook on 4/19/16.
 */
public class HeaderInterceptor implements Interceptor {
    String apiKey, authKey;

    public void setAPIKey(String apiKey){
        this.apiKey = apiKey;
    }

    public void setAuthKey(String authKey){
        this.authKey = authKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Api-Key", apiKey)
                .addHeader("Auth-Key", authKey)
                .build();
        Response response = chain.proceed(request);
        return response;
    }

}
