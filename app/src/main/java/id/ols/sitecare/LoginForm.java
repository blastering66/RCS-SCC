package id.ols.sitecare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ols.models.PojoResponseLogin;
import id.ols.rest_adapter.API_Adapter;
import id.ols.util.*;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginForm extends AppCompatActivity {

    private String user_id, user_email, user_fullname, user_foto, user_gender;
    @Bind(R.id.ed_username)
    EditText ed_Username;
    @Bind(R.id.ed_password) EditText ed_Password;
    @Bind(R.id.btn)
    Button btn;

    @OnClick(R.id.btn) public void click(){
        new AsycnTask_Login().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private class AsycnTask_Login extends AsyncTask<Void,Void,Void>{
        String username, password, authKey, message;
        boolean isSukses = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            username = ed_Username.getText().toString();
            password = ed_Password.getText().toString();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(ParameterCollections.URL_BASE).build();

                API_Adapter adapter = retrofit.create(API_Adapter.class);
                String apikey = getResources().getString(R.string.api_key);
                Call<PojoResponseLogin> call = adapter.login(apikey, ParameterCollections.KIND.LOGIN,
                        ParameterCollections.KIND.MOBILE,username, password);
                Response<PojoResponseLogin> response = call.execute();
                if(response.isSuccess()){
                    if(response.body() != null){
                        if(response.body().getJsonCode().equals("1")){
                            authKey = response.body().getAuthKey();
                            SharedPreferences spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
                            spf.edit().putString(ParameterCollections.SH_AUTHKEY, authKey).commit();
                            isSukses = true;
                            message = response.body().getResponseMessage();
                        }else{
                            message = response.body().getResponseMessage();

                        }

                    }


                }else{
                    message = response.errorBody().toString();
                }
            }catch (IOException e){
                message = e.getMessage().toString();
                Log.e("Error", e.getMessage().toString());
            }catch (Exception e){
                message = e.getMessage().toString();
                Log.e("Error", e.getMessage().toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(isSukses){
                startActivity(new Intent(getApplicationContext(), EngineerDetail.class));
                finish();
            }else{
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
