package id.ols.sitecare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.ols.util.ParameterCollections;

public class Splashscreen extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        getSupportActionBar().hide();
        new AsyncTask_LoadData().execute();
    }

    private class AsyncTask_LoadData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(1500);
                sp = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
//                showHashKey();
            }catch (Exception e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            boolean isLogged = sp.getBoolean(ParameterCollections.SH_LOGGED, false);

            if(isLogged){

            }else{
                startActivity(new Intent(getApplicationContext(), LoginForm.class));
                finish();
            }
        }


    }
}
