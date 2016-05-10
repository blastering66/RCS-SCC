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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.ols.models.PojoCluster;
import id.ols.models.PojoRegions;
import id.ols.models.PojoResponseInsert;
import id.ols.models.PojoResponseInsertSite;
import id.ols.models.PojoSubRegions;
import id.ols.models.PojoWeather;
import id.ols.models.RowData_Regions;
import id.ols.models.RowData_Weather;
import id.ols.rest_adapter.API_Adapter;
import id.ols.rest_adapter.HeaderInterceptor;
import id.ols.util.CameraCapture;
import id.ols.util.ParameterCollections;
import id.ols.util.PublicFunctions;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.internal.util.RxThreadFactory;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.schedulers.Schedulers;

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
    @Bind(R.id.pg_regions)ProgressBar pg_regions;
    @Bind(R.id.pg_regions_sub)ProgressBar pg_regions_sub;
    @Bind(R.id.pg_regions_cluster)ProgressBar pg_regions_cluster;
    @Bind(R.id.pg_weather)ProgressBar pg_weather;

    @OnClick(R.id.img)
    void OnClickIMG() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }

    String lat_now, longi_now, time_now;
    Activity activity;
    SharedPreferences spf;
    String mUrl_Img_00;
    String url_file00;
    File sourceFile00;
    RequestBody body00;

    boolean isSukses = false;
    String message = "";
    List<RowData_Regions> name_regions, name_sub_regions,name_cluster_regions;
    List<RowData_Weather> name_weather;

    String site_nameenginer, site_emailenginer, site_phoneenginer;
    String idRegionParent, idRegionParent_Sub, idRegionParent_Cluster;
    String idWeatherSelected ="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitedetail);
        ButterKnife.bind(this);
        activity = this;

        getSupportActionBar().setTitle("Site Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);


        site_nameenginer = getIntent().getStringExtra("site_nameenginer");
        site_emailenginer = getIntent().getStringExtra("site_emailenginer");
        site_phoneenginer = getIntent().getStringExtra("site_phoneenginer");

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        String formattedDate = df.format(c.getTime());
        time_now = formattedDate;
        ed_time.setText(formattedDate);

        getRegionsData();
        getWeatherData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_next_refresh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_refresh:
                getRegionsData();
                getWeatherData();
                break;
            case R.id.action_next:
                if (url_file00 != null) {
                    sendData();
                } else {
                    Toast.makeText(getApplicationContext(), "Please take picture or site first", Toast.LENGTH_LONG).show();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void getWeatherData() {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoWeather> observable = adapter.get_weather(apikey, authkey);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoWeather>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Get Regions", "Completed");
                        if (name_weather.size() > 0 || name_weather != null) {
                            List<String> array_weather = new ArrayList<String>();

                            for(int i=0; i < name_weather.size(); i++){
                                array_weather.add(name_weather.get(i).name);
                            }

                            pg_weather.setVisibility(View.GONE);
                            spinner_Weather.setVisibility(View.VISIBLE);
                            ArrayAdapter<String> adapter_weather = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, array_weather);
                            adapter_weather.setDropDownViewResource(R.layout.spinner_item);
                            spinner_Weather.setAdapter(adapter_weather);

                            spinner_Weather.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idWeatherSelected = name_weather.get(position).id;
                                    Log.e("idParent", idWeatherSelected);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    idWeatherSelected = name_weather.get(0).id;
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Something wrong");

                    }

                    @Override
                    public void onNext(PojoWeather pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getRegionName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_weather = new ArrayList<RowData_Weather>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_weather.add(new RowData_Weather(pojoRegions.getData().get(i).getRegionId(),
                                            pojoRegions.getData().get(i).getRegionName()));
                                }
                            }
                        }

                    }
                });

    }

    private void getRegionsData() {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoRegions> observable = adapter.get_regions(apikey,authkey);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoRegions>() {
                    boolean isSukses= false;
                    String message= "Something Wrong";
                    @Override
                    public void onCompleted() {
                        Log.e("Get Regions", "Completed");
                        if(isSukses){
                            if (name_regions.size() > 0 || name_regions != null) {
                                List<String> array_regions = new ArrayList<String>();

                                for(int i=0; i < name_regions.size(); i++){
                                    array_regions.add(name_regions.get(i).name);
                                }

                                pg_regions.setVisibility(View.GONE);
                                spinner_Regions.setVisibility(View.VISIBLE);
                                ArrayAdapter<String> adapter_regions = new ArrayAdapter<String>(getApplicationContext(),
                                        R.layout.spinner_item, array_regions);
                                adapter_regions.setDropDownViewResource(R.layout.spinner_item);
                                spinner_Regions.setAdapter(adapter_regions);

                                spinner_Regions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        idRegionParent = name_regions.get(position).id;
                                        Log.e("idParent", idRegionParent);
                                        getSubRegionsData(idRegionParent);

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        getSubRegionsData(name_regions.get(0).id);
                                    }
                                });
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Something wrong");

                    }

                    @Override
                    public void onNext(PojoRegions pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getRegionName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_regions = new ArrayList<RowData_Regions>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_regions.add(new RowData_Regions(pojoRegions.getData().get(i).getRegionId(),
                                            pojoRegions.getData().get(i).getRegionName()));
                                    isSukses = true;
                                }
                            }else{
                                message = pojoRegions.getResponseMessage();
                            }
                        }

                    }
                });

    }

    private void getSubRegionsData(String idRegions) {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoSubRegions> observable = adapter.get_sub_regions(apikey, authkey,idRegions);
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoSubRegions>() {
                    boolean isSukses= false;
                    String message= "Something Wrong";
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if(isSukses){
                            if (name_sub_regions.size() > 0 || name_sub_regions != null) {

                                List<String> array_sub_regions = new ArrayList<String>();
                                for(int i=0; i < name_sub_regions.size(); i++){
                                    array_sub_regions.add(name_sub_regions.get(i).name);
                                }

                                ArrayAdapter<String> adapter_sub_regions = new ArrayAdapter<String>(getApplicationContext(),
                                        R.layout.spinner_item, array_sub_regions);
                                adapter_sub_regions.setDropDownViewResource(R.layout.spinner_item);
                                spinner_Regions_Sub.setAdapter(adapter_sub_regions);

                                pg_regions_sub.setVisibility(View.GONE);
                                spinner_Regions_Sub.setVisibility(View.VISIBLE);

                                spinner_Regions_Sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        idRegionParent_Sub = name_sub_regions.get(position).id;
                                        getClusterData(idRegionParent_Sub);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoSubRegions pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getRegionName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_sub_regions = new ArrayList<RowData_Regions>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_sub_regions.add(new RowData_Regions(pojoRegions.getData().get(i).getRegionId(),
                                            pojoRegions.getData().get(i).getRegionName()));
                                    isSukses = true;
                                }
                            }else{
                                message = pojoRegions.getResponseMessage();
                            }
                        }

                    }
                });

    }

    private void getClusterData(String idSubRegions) {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoCluster> observable = adapter.get_cluster_regions(apikey,authkey, idSubRegions);
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoCluster>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");

                        if (name_cluster_regions.size() > 0 || name_cluster_regions != null) {

                            List<String> array_cluster_regions = new ArrayList<String>();
                            for(int i=0; i < name_cluster_regions.size(); i++){
                                array_cluster_regions.add(name_cluster_regions.get(i).name);
                            }

                            ArrayAdapter<String> adapter_cluster_regions = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, array_cluster_regions);
                            adapter_cluster_regions.setDropDownViewResource(R.layout.spinner_item);
                            spinner_Regions_Sub_Cluster.setAdapter(adapter_cluster_regions);

                            pg_regions_cluster.setVisibility(View.GONE);
                            spinner_Regions_Sub_Cluster.setVisibility(View.VISIBLE);

                            spinner_Regions_Sub_Cluster.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idRegionParent_Cluster = name_cluster_regions.get(position).id;
//                                    getClusterData(idRegionParent_Sub);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    idRegionParent_Cluster = name_cluster_regions.get(0).id;
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoCluster pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getRegionName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_cluster_regions= new ArrayList<RowData_Regions>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_cluster_regions.add(new RowData_Regions(pojoRegions.getData().get(i).getRegionId(),
                                            pojoRegions.getData().get(i).getRegionName()));
                                }
                            }
                        }

                    }
                });

    }

    private void sendData() {
        final CustomProgressDialog pDialog;

        //param value yg dikirim
        String site_codeid, site_name, site_keeper, site_mobileno, site_type,
                site_idregion, site_idcluster, site_location, site_altitude,
                site_timesurveystarted, site_externaltemperature, site_idweathercondition;

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
        } else {
            site_type = "Mixed";
        }

        site_idcluster = idRegionParent_Cluster;
        lat_now = spf.getString(ParameterCollections.TAG_LATITUDE_NOW, "0.0");
        longi_now = spf.getString(ParameterCollections.TAG_LONGITUDE_NOW, "0.0");

        site_location = lat_now + "," + longi_now;
        site_altitude = ed_altitude.getText().toString();


        site_timesurveystarted = time_now;

        site_externaltemperature = ed_external_temp.getText().toString();
//        site_idweathercondition = String.valueOf(spinner_Weather.getSelectedItemPosition());
        site_idweathercondition = idWeatherSelected;

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
        RequestBody nameenginer = RequestBody.create(MediaType.parse("text/plain"), site_nameenginer);
        RequestBody emailenginer = RequestBody.create(MediaType.parse("text/plain"), site_emailenginer);
        RequestBody phoneenginer = RequestBody.create(MediaType.parse("text/plain"), site_phoneenginer);


//        RequestBody codeid = RequestBody.create(MediaType.parse("text/plain"), "test");
//        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "test");
//        RequestBody mobileno = RequestBody.create(MediaType.parse("text/plain"), "test");
//        RequestBody _type = RequestBody.create(MediaType.parse("text/plain"), "Indoor");
//        RequestBody keeper = RequestBody.create(MediaType.parse("text/plain"), "test");
//        RequestBody idcluster = RequestBody.create(MediaType.parse("text/plain"), "1");
//        RequestBody location = RequestBody.create(MediaType.parse("text/plain"), "0.00, 0.00");
//        RequestBody altitude = RequestBody.create(MediaType.parse("text/plain"), "test");
//        RequestBody timesurveystarted = RequestBody.create(MediaType.parse("text/plain"), "22:00:00");
//        RequestBody externaltemperature = RequestBody.create(MediaType.parse("text/plain"), "10");
//        RequestBody idweathercondition = RequestBody.create(MediaType.parse("text/plain"), "1");
//        RequestBody nameenginer = RequestBody.create(MediaType.parse("text/plain"), "test");
//        RequestBody emailenginer = RequestBody.create(MediaType.parse("text/plain"), "test");
//        RequestBody phoneenginer = RequestBody.create(MediaType.parse("text/plain"), "021");

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);


        Retrofit retrofit_test = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ParameterCollections.URL_BASE).build();
        API_Adapter adapter = retrofit_test.create(API_Adapter.class);

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        //Sementara
        sourceFile00 = new File(url_file00);
        body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);

        //Sementara biar cpt
        Observable<PojoResponseInsertSite> observable = adapter.insert_site_detail(
                apikey, authkey, ParameterCollections.EXE.INSERT,
                ParameterCollections.KIND.MOBILE, ParameterCollections.KIND.SITE_VISIT,
                codeid, name, mobileno, _type,
                keeper, idcluster, location, altitude, timesurveystarted,
                externaltemperature, idweathercondition, nameenginer, emailenginer, phoneenginer,body00
        );


        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsertSite>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();
                        if (isSukses) {
                            startActivity(new Intent(getApplicationContext(), MainActivity_Button.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed! Error = " + message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.dismiss();
//                        Log.e("onError", e.getMessage().toString());
                        Toast.makeText(getApplicationContext(), "Timeout,Please Try Again.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(PojoResponseInsertSite pojoResponseInsert) {
                        if (pojoResponseInsert.getJsonCode() != null) {
                            if (pojoResponseInsert.getAct().getInsert() == 1) {
                                isSukses = true;
                                String id_site = pojoResponseInsert.getData().getSiteId();
                                spf.edit().putString(ParameterCollections.SH_ID_SITE, id_site).commit();
                            } else {
                                message = pojoResponseInsert.getResponseMessage();
                            }
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 111) {
                url_file00 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file00), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file00).asBitmap().into(img_Site);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }
}
