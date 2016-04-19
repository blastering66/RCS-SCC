package id.ols.sitecare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.ols.models.PojoResponseInsert;
import id.ols.rest_adapter.API_Adapter;
import id.ols.rest_adapter.HeaderInterceptor;
import id.ols.util.CameraCapture;
import id.ols.util.ParameterCollections;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by macbook on 3/27/16.
 */
public class SiteDetail extends AppCompatActivity {
    @Bind(R.id.spinner_regions)
    Spinner spinner_Regions;
    @Bind(R.id.spinner_regions_sub)
    Spinner spinner_Regions_Sub;
    @Bind(R.id.spinner_regions_sub_cluster)
    Spinner spinner_Regions_Sub_Cluster;
    @Bind(R.id.ed_site_name)
    EditText ed_site_name;
    @Bind(R.id.ed_site_id)
    EditText ed_site_id;
    @Bind(R.id.ed_site_keeper)
    EditText ed_site_keeper;
    @Bind(R.id.ed_phone)
    EditText ed_phone;
    @Bind(R.id.tipe_indoor)
    RadioButton radio_tipe_indoor;
    @Bind(R.id.tipe_outdoor)
    RadioButton radio_tipe_outdoor;
    @Bind(R.id.tipe_mixed)
    RadioButton radio_tipe_mixed;
    @Bind(R.id.tv_lat)
    TextView tv_lat;
    @Bind(R.id.tv_longi)
    TextView tv_longi;
    @Bind(R.id.ed_altitude)
    EditText ed_altitude;
    @Bind(R.id.ed_time)
    TextView ed_time;
    @Bind(R.id.ed_external_temp)
    EditText ed_external_temp;
    @Bind(R.id.spinner_weather)
    Spinner spinner_Weather;
    @Bind(R.id.img)
    ImageView img_Site;
    @OnClick(R.id.img) void OnClickIMG(){
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }

    String lat_now, longi_now, time_now;
    Activity activity;
    SharedPreferences spf;
    String mUrl_Img_00;
    String url_file00;
    File sourceFile00;
    RequestBody body00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitedetail);
        ButterKnife.bind(this);
        activity = this;
        getSupportActionBar().setTitle("Site Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_next, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_next:
                new AsyncTask_Submit().execute();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private class AsyncTask_Submit extends AsyncTask<Void, Void, Void> {
        CustomProgressDialog pDialog;
        boolean isSukses = false;
        String message = "";

        //param value yg dikirim
        String site_codeid, site_name, site_keeper, site_mobileno, site_type,
                site_idregion, site_idcluster, site_location, site_altitude,
                site_timesurveystarted, site_externaltemperature, site_idweathercondition;
//        public $view_sitephoto

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
            pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
            pDialog.show();

            site_codeid = ed_site_id.getText().toString();
            site_name = ed_site_name.getText().toString();
            site_keeper = ed_site_keeper.getText().toString();
            site_mobileno = ed_phone.getText().toString();

            if (radio_tipe_indoor.isChecked()) {
                site_type = "Indoor";
            } else if (radio_tipe_outdoor.isChecked()) {
                site_type = "Outdoor";
            } else if (radio_tipe_mixed.isChecked()) {
                site_type = "Mixed";
            }

            //Sementara
            site_idregion = "1";
            site_idcluster = "1";
            lat_now = spf.getString(ParameterCollections.TAG_LATITUDE_NOW, "0.0");
            longi_now = spf.getString(ParameterCollections.TAG_LONGITUDE_NOW, "0.0");

            site_location = lat_now + "," + longi_now;
            site_altitude = ed_altitude.getText().toString();

            Calendar c = Calendar.getInstance();
            site_timesurveystarted = c.getTime().toString();
            site_externaltemperature = ed_external_temp.getText().toString();
            site_idweathercondition = String.valueOf(spinner_Weather.getSelectedItemPosition());
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
//                OkHttpClient client = new OkHttpClient();
//                HeaderInterceptor interceptor = new HeaderInterceptor();
//                String apikey = getResources().getString(R.string.api_key);
//                String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
//                interceptor.setAPIKey(apikey);
//                interceptor.setAuthKey(authkey);
//                client.interceptors().add(interceptor);

                final String apikey = getResources().getString(R.string.api_key);
                final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
//                OkHttpClient client = new OkHttpClient();
//                client.interceptors().add(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        request = request.newBuilder()
//                                .addHeader("Api-Key", apikey)
//                                .addHeader("Auth-Key", authkey)
//                                .build();
//                        Response response = chain.proceed(request);
//                        return response;
//                    }
//                });

                url_file00 = "";
                sourceFile00 = new File(url_file00);
                body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);

                RequestBody exe = RequestBody.create(MediaType.parse("text/plain"), ParameterCollections.EXE.INSERT);
                RequestBody type = RequestBody.create(MediaType.parse("text/plain"), ParameterCollections.KIND.MOBILE);
                RequestBody kind = RequestBody.create(MediaType.parse("text/plain"), ParameterCollections.KIND.SITE);
                RequestBody codeid = RequestBody.create(MediaType.parse("text/plain"), site_codeid);
                RequestBody name = RequestBody.create(MediaType.parse("text/plain"), site_name);
                RequestBody mobileno = RequestBody.create(MediaType.parse("text/plain"), site_mobileno);
                RequestBody _type = RequestBody.create(MediaType.parse("text/plain"), site_type);
                RequestBody keeper = RequestBody.create(MediaType.parse("text/plain"), site_keeper);
                RequestBody idcluster = RequestBody.create(MediaType.parse("text/plain"), site_idcluster);
                RequestBody location = RequestBody.create(MediaType.parse("text/plain"), site_location);
                RequestBody altitude = RequestBody.create(MediaType.parse("text/plain"), site_altitude);
                RequestBody timesurveystarted = RequestBody.create(MediaType.parse("text/plain"), site_timesurveystarted);
                RequestBody externaltemperature = RequestBody.create(MediaType.parse("text/plain"), site_externaltemperature);
                RequestBody idweathercondition = RequestBody.create(MediaType.parse("text/plain"), site_idweathercondition);

                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(ParameterCollections.URL_BASE).build();
                API_Adapter adapter = retrofit.create(API_Adapter.class);

//                Call<PojoResponseInsert> call = adapter.insert_site_detail(exe,type,kind,codeid,name,mobileno,_type,
//                        keeper,idcluster, location, altitude, timesurveystarted, externaltemperature, idweathercondition
//                        );


                Call<PojoResponseInsert> call = adapter.insert_site_detail(apikey, authkey,ParameterCollections.EXE.INSERT,
                        ParameterCollections.KIND.MOBILE,ParameterCollections.KIND.SITE,
                        site_codeid,site_name,site_mobileno,site_type,
                        site_keeper,site_idcluster, site_location, site_altitude, site_timesurveystarted,
                        site_externaltemperature, site_idweathercondition
                );

//                Call<PojoResponseInsert> call = adapter.insert_site_detail(apikey, authkey,ParameterCollections.EXE.INSERT,
//                        ParameterCollections.KIND.MOBILE,ParameterCollections.KIND.SITE,codeid,name,mobileno,_type,
//                        keeper,idcluster, location, altitude, timesurveystarted, externaltemperature, idweathercondition
//                );

                retrofit.Response<PojoResponseInsert> response = call.execute();

                if(response.isSuccess()){
                    if(response.body() != null ){

                    }
                }
            } catch (IOException e) {
                Log.e("Error", e.getMessage().toString());

            } catch (Exception e) {
                Log.e("Error", e.getMessage().toString());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();
            if (isSukses) {
                startActivity(new Intent(getApplicationContext(), MainActivity_Button.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 111){
                url_file00 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file00), 100,150, true);
                Glide.with(getApplicationContext()).load(url_file00).asBitmap().into(img_Site);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }
}
