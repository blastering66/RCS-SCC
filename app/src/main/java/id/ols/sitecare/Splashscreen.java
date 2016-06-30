
package id.ols.sitecare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import id.ols.dialogs.DialogConfirmation;
import id.ols.dialogs.DialogConfirmationLoc;
import id.ols.util.GPSTracker;
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
        boolean Loc_Retrived;

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(1500);
                sp = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);

            }catch (Exception e){

            }

            return null;
        }

        public void cek(){
            boolean isLogged = sp.getBoolean(ParameterCollections.SH_LOGGED, false);
            boolean isVisitFinished = sp.getBoolean(ParameterCollections.SH_VISIT_FINISHED, false);
            boolean isSiteInputed = sp.getBoolean(ParameterCollections.SH_SITEVISIT_INPUTED, false);

            if(isLogged){

                if(isVisitFinished){
                    startActivity(new Intent(getApplicationContext(), SiteDetail_New.class));
                    finish();
                }else{

                    if(isSiteInputed){
                        startActivity(new Intent(getApplicationContext(), MainActivity_Button.class));
                        finish();
                    }else{
                        startActivity(new Intent(getApplicationContext(), SiteDetail_New.class));
                        finish();

                    }
                }

            }else{
                startActivity(new Intent(getApplicationContext(), LoginForm.class));
                finish();
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Loc_Retrived = getLocationNow(getApplicationContext(), sp);

            if(Loc_Retrived){
                cek();
            }else{
                DialogConfirmationLoc pDialog = new DialogConfirmationLoc();
                pDialog.setContext(getApplicationContext());
                pDialog.setText("Can not get current Locations, Try again ?");
                pDialog.setSh(sp);
                pDialog.show(getSupportFragmentManager(),"");
            }


        }


    }

    public static boolean getLocationNow(Context context, SharedPreferences sh) {
        GPSTracker gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

//            now_latitude = String.valueOf(latitude);
//            now_longitude = String.valueOf(longitude);

            Log.e("Longitude", String.valueOf(longitude));
            Log.e("Latitude", String.valueOf(latitude));

            sh.edit()
                    .putString(ParameterCollections.TAG_LATITUDE_NOW,
                            String.valueOf(latitude)).commit();

            sh.edit()
                    .putString(ParameterCollections.TAG_LONGITUDE_NOW,
                            String.valueOf(longitude)).commit();

            return true;

        } else {
            return false;
        }
    }
}
