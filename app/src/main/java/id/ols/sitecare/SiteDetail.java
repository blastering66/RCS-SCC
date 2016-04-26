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
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.ols.models.PojoRegions;
import id.ols.models.PojoResponseInsert;
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
    List<String> name_regions;

    String site_nameenginer, site_emailenginer, site_phoneenginer;

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

        getRegionsData();
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
                if (url_file00 != null) {
                    sendData();
                } else {
                    Toast.makeText(getApplicationContext(), "Please take picture or site first", Toast.LENGTH_LONG).show();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void getRegionsData() {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        Observable<PojoRegions> observable = adapter.get_regions(apikey);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoRegions>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_regions.size() > 0 || name_regions != null) {
                            ArrayAdapter<String> adapter_regions = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, name_regions);
                            adapter_regions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner_Regions.setAdapter(adapter_regions);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoRegions pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getRegionName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_regions = new ArrayList<String>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_regions.add(pojoRegions.getData().get(i).getRegionName());
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
//        site_idweathercondition = String.valueOf(spinner_Weather.getSelectedItemPosition());
        site_idweathercondition = "1";

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

        Retrofit retrofit_test = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ParameterCollections.URL_BASE).build();
        API_Adapter adapter = retrofit_test.create(API_Adapter.class);

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        sourceFile00 = new File(url_file00);
        body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);

        Observable<PojoResponseInsert> observable = adapter.insert_site_detail(
                apikey, authkey, ParameterCollections.EXE.INSERT,
                ParameterCollections.KIND.MOBILE, ParameterCollections.KIND.SITE,
                codeid, name, mobileno, _type,
                keeper, idcluster, location, altitude, timesurveystarted,
                externaltemperature, idweathercondition, nameenginer, emailenginer, phoneenginer, body00
        );

//

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
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
                        Toast.makeText(getApplicationContext(), "Something wrong, Try Again = ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(PojoResponseInsert pojoResponseInsert) {
                        if (pojoResponseInsert.getJsonCode() != null) {
                            if (pojoResponseInsert.getAct().getInsert() == 1) {
                                isSukses = true;
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
