package id.ols.sitecare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.ols.dialogs.DialogConfirmation;
import id.ols.models.PojoResponseInsert;
import id.ols.rest_adapter.API_Adapter;
import id.ols.util.CameraCapture;
import id.ols.util.ParameterCollections;
import id.ols.util.PublicFunctions;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_Generators_Detail_2_General extends AppCompatActivity {
    Activity activity;
    @Bind(R.id.tipe_ats_yes)RadioButton tipe_ats_yes;
    @Bind(R.id.tipe_ats_no)RadioButton tipe_ats_no;
    @Bind(R.id.ed_ats_manufactur_name)EditText ed_ats_manufactur_name;
    @Bind(R.id.ed_ats_model)EditText ed_ats_model;
    @Bind(R.id.ed_ats_condition)EditText ed_ats_condition;

    @Bind(R.id.tipe_amf_yes)RadioButton tipe_amf_yes;
    @Bind(R.id.tipe_amf_no)RadioButton tipe_amf_no;
    @Bind(R.id.ed_amf_manufactur_name)EditText ed_amf_manufactur_name;
    @Bind(R.id.ed_amf_model_name)EditText ed_amf_model_name;
    @Bind(R.id.ed_amf_condition)EditText ed_amf_condition;

    @Bind(R.id.ed_amf_capacity)EditText ed_amf_capacity;
    @Bind(R.id.ed_amf_capacity_condition)EditText ed_amf_capacity_condition;

    @Bind(R.id.tipe_fullprobe_fitted_yes)RadioButton tipe_fullprobe_fitted_yes;
    @Bind(R.id.tipe_fullprobe_fitted_no)RadioButton tipe_fullprobe_fitted_no;
    @Bind(R.id.ed_fullprobe_manufactur_name)EditText ed_fullprobe_manufactur_name;
    @Bind(R.id.ed_fullprobe_model_name)EditText ed_fullprobe_model_name;
    @Bind(R.id.ed_fullprobe_condition)EditText ed_fullprobe_condition;

    @Bind(R.id.img_ats)ImageView img_ats;
    @Bind(R.id.img_amf)ImageView img_amf;
    @Bind(R.id.img_amf_capacity)ImageView img_amf_capacity;
    @Bind(R.id.img_fullprobe)ImageView img_fullprobe;

    File sourceFile_generatorphoto, sourceFile_controllerphoto,sourceFile_atsphoto, sourceFile_amfphoto,sourceFile_amfcapacityphoto,sourceFile_fullprobephoto;
    RequestBody body_generatorphoto, body_controllerphoto,body_atsphoto, body_amfphoto, body_amfcapacityphoto,body_fullprobe;

    SharedPreferences spf;
    String id_site;
    String manufactur_name, model, phase, remote_start, primary_rating, standby_rating, url_file00_generator = "";
    String engine_manufactur_name, engine_model, engine_fitted, controller_manufactur_name, controller_model_name,
            controller_condition, battery_fitted, battery_controller_manufactur_name,battery_model_name,
            battery_condition, battery_voltast, url_file_controller, url_file_battery= "";
    String url_file_ats,url_file_amf, url_file_amf_capacity, url_file_fullprobe;
    String radio_speed, radio_freq, radio_voltase,l1_connections ,l2_connections,l3_connections,
            l1_loads, l2_loads,l3_loads, running_hours, condition;

    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        String ats_presented = "";
        if(tipe_ats_yes.isChecked()){
            ats_presented = getResources().getString(R.string.option_yes_smallcase);
        }else{
            ats_presented = getResources().getString(R.string.option_no_smallcase);
        }
        String ats_manufactur_name = ed_ats_manufactur_name.getText().toString();
        String ats_model = ed_ats_model.getText().toString();
        String ats_condition = ed_ats_condition.getText().toString();

        String amf_presented = "";
        if(tipe_amf_yes.isChecked()){
            amf_presented = getResources().getString(R.string.option_yes_smallcase);
        }else{
            amf_presented = getResources().getString(R.string.option_no_smallcase);
        }
        String amf_manufactur_name = ed_amf_manufactur_name.getText().toString();
        String amf_model_name = ed_amf_model_name.getText().toString();
        String amf_condition = ed_amf_condition.getText().toString();

        String amf_capacity = ed_amf_capacity.getText().toString();
        String amf_capacity_condition = ed_amf_capacity_condition.getText().toString();

        String fullprobe_presented = "";
        if(tipe_fullprobe_fitted_yes.isChecked()){
            fullprobe_presented = getResources().getString(R.string.option_yes_smallcase);
        }else{
            fullprobe_presented = getResources().getString(R.string.option_no_smallcase);
        }
        String fullprobe_manufactur_name = ed_fullprobe_manufactur_name.getText().toString();
        String fullprobe_model_name = ed_fullprobe_model_name.getText().toString();
        String fullprobe_condition = ed_fullprobe_condition.getText().toString();

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        final CustomProgressDialog pDialog;

        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        RequestBody _idsitevisit = RequestBody.create(MediaType.parse("text/plain"), id_site);
        RequestBody _generatordetail_manufactur = RequestBody.create(MediaType.parse("text/plain"),manufactur_name);
        RequestBody _generatordetail_model = RequestBody.create(MediaType.parse("text/plain"), model);
        RequestBody _generatordetail_phase = RequestBody.create(MediaType.parse("text/plain"),phase);
        RequestBody _generatordetail_remote_start = RequestBody.create(MediaType.parse("text/plain"), remote_start);
        RequestBody _generatordetail_primary_rating = RequestBody.create(MediaType.parse("text/plain"),primary_rating);
        RequestBody _generatordetail_standby_rating = RequestBody.create(MediaType.parse("text/plain"), standby_rating);

        RequestBody _radio_speed = RequestBody.create(MediaType.parse("text/plain"),radio_speed);
        RequestBody _radio_freq = RequestBody.create(MediaType.parse("text/plain"), radio_freq);
        RequestBody _radio_voltase = RequestBody.create(MediaType.parse("text/plain"), radio_voltase);
        RequestBody _l1_connections = RequestBody.create(MediaType.parse("text/plain"),l1_connections);
        RequestBody _l2_connections = RequestBody.create(MediaType.parse("text/plain"), l2_connections);
        RequestBody _l3_connections = RequestBody.create(MediaType.parse("text/plain"), l3_connections);
        RequestBody _l1_loads = RequestBody.create(MediaType.parse("text/plain"),l1_loads);
        RequestBody _l2_loads = RequestBody.create(MediaType.parse("text/plain"), l2_loads);
        RequestBody _l3_loads = RequestBody.create(MediaType.parse("text/plain"), l3_loads);
        RequestBody _running_hours = RequestBody.create(MediaType.parse("text/plain"), running_hours);
        RequestBody _condition = RequestBody.create(MediaType.parse("text/plain"), condition);

        //Gambarnya
        sourceFile_generatorphoto = new File(url_file00_generator);
        body_generatorphoto = RequestBody.create(MediaType.parse("image/*"), sourceFile_generatorphoto);

        RequestBody _engine_manufactur_name = RequestBody.create(MediaType.parse("text/plain"),engine_manufactur_name);
        RequestBody _engine_model = RequestBody.create(MediaType.parse("text/plain"), engine_model);
        RequestBody _altenator_controller_manufactur_name = RequestBody.create(MediaType.parse("text/plain"), controller_manufactur_name);
        RequestBody _altenator_controller_model_name = RequestBody.create(MediaType.parse("text/plain"), controller_model_name);
//        RequestBody _altenator_controller_condition = RequestBody.create(MediaType.parse("text/plain"), controller_condition);
        RequestBody _altenator_controller_engine_fitted = RequestBody.create(MediaType.parse("text/plain"), engine_fitted);
        //Gambarnya
        sourceFile_controllerphoto = new File(url_file_controller);
        body_controllerphoto = RequestBody.create(MediaType.parse("image/*"), sourceFile_controllerphoto);
        // Field photo BATTERY ga ada di DB
//        sourceFile_batteryphoto = new File(url_file_battery);
//        body_batteryphoto = RequestBody.create(MediaType.parse("image/*"), sourceFile_batteryphoto);

        RequestBody _battery_fitted = RequestBody.create(MediaType.parse("text/plain"), battery_fitted);
        RequestBody _battery_manufactur_name = RequestBody.create(MediaType.parse("text/plain"),battery_controller_manufactur_name);
        RequestBody _battery_model = RequestBody.create(MediaType.parse("text/plain"), battery_model_name);
        RequestBody _battery_condition = RequestBody.create(MediaType.parse("text/plain"), battery_condition);
        RequestBody _battery_voltase = RequestBody.create(MediaType.parse("text/plain"), battery_voltast);

        RequestBody _ats_presented = RequestBody.create(MediaType.parse("text/plain"), ats_presented);
        RequestBody _ats_manufactur_name = RequestBody.create(MediaType.parse("text/plain"),ats_manufactur_name);
        RequestBody _ats_model = RequestBody.create(MediaType.parse("text/plain"), ats_model);
        RequestBody _ats_condition = RequestBody.create(MediaType.parse("text/plain"), ats_condition);
        //Gambarnya
        sourceFile_atsphoto = new File(url_file_ats);
        body_atsphoto = RequestBody.create(MediaType.parse("image/*"), sourceFile_atsphoto);

        RequestBody _amf_presented = RequestBody.create(MediaType.parse("text/plain"), amf_presented);
        RequestBody _amf_manufactur_name = RequestBody.create(MediaType.parse("text/plain"),amf_manufactur_name);
        RequestBody _amf_model = RequestBody.create(MediaType.parse("text/plain"), amf_model_name);
        RequestBody _amf_condition = RequestBody.create(MediaType.parse("text/plain"), amf_condition);
        //Gambarnya
        sourceFile_amfphoto = new File(url_file_amf);
        body_amfphoto = RequestBody.create(MediaType.parse("image/*"), sourceFile_amfphoto);

        RequestBody _capacity = RequestBody.create(MediaType.parse("text/plain"),amf_capacity);
        RequestBody _capacity_condition = RequestBody.create(MediaType.parse("text/plain"), amf_capacity_condition);
        //Gambarnya
        sourceFile_amfcapacityphoto = new File(url_file_amf_capacity);
        body_amfcapacityphoto = RequestBody.create(MediaType.parse("image/*"), sourceFile_amfcapacityphoto);


        RequestBody _fullprobe_fitted = RequestBody.create(MediaType.parse("text/plain"), fullprobe_presented);
        RequestBody _fullprobe_manufactur_name = RequestBody.create(MediaType.parse("text/plain"),fullprobe_manufactur_name);
        RequestBody _fullprobe_model = RequestBody.create(MediaType.parse("text/plain"), fullprobe_model_name);
        RequestBody _fullprobe_condition = RequestBody.create(MediaType.parse("text/plain"), fullprobe_condition);
        //Gambarnya
        sourceFile_fullprobephoto = new File(url_file_fullprobe);
        body_fullprobe = RequestBody.create(MediaType.parse("image/*"), sourceFile_fullprobephoto);

        API_Adapter adapter = PublicFunctions.initRetrofit();
        Observable<PojoResponseInsert> observable = adapter.insert_tech_generator(apikey,authkey,
                ParameterCollections.EXE.INSERT,ParameterCollections.KIND.MOBILE,ParameterCollections.KIND.TECH_GENERATOR,
                _idsitevisit,_generatordetail_manufactur,_generatordetail_model,_generatordetail_phase,
                _generatordetail_remote_start,_generatordetail_primary_rating, _generatordetail_standby_rating,
                _radio_speed,_radio_freq , _radio_voltase,_l1_connections,_l2_connections, _l3_connections,
                _l1_loads, _l2_loads, _l3_loads,_running_hours,_condition,body_generatorphoto,_engine_manufactur_name,
                _engine_model,_altenator_controller_manufactur_name, _altenator_controller_model_name,
                _altenator_controller_engine_fitted,body_controllerphoto,_battery_fitted,_battery_manufactur_name,
                _battery_model, _battery_condition,_battery_voltase,_ats_presented, _ats_manufactur_name, _ats_model,
                _ats_condition, body_atsphoto,_amf_presented, _amf_manufactur_name, _amf_model, _amf_condition,
                body_amfphoto, _capacity, _capacity_condition, body_amfcapacityphoto, _fullprobe_fitted,
                _fullprobe_manufactur_name, _fullprobe_model, _fullprobe_condition, body_fullprobe);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    String message = "";
                    boolean isSukses = false;

                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();

                        if (isSukses) {
                            DialogConfirmation pDialog_comfirm = new DialogConfirmation();
                            pDialog_comfirm.setContext(getApplicationContext());
                            pDialog_comfirm.setText("Add Additional Generator");
                            pDialog_comfirm.setSh(spf);
                            pDialog_comfirm.setFrom(0);

                            pDialog_comfirm.show(getSupportFragmentManager(), "");
                        } else {
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Something Wrong");

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

    @OnClick(R.id.img_ats)
    void OnClickIMGats() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }
    @OnClick(R.id.img_amf)
    void OnClickIMGamf() {
        startActivityForResult(new Intent(this, CameraCapture.class), 112);
    }
    @OnClick(R.id.img_amf_capacity)
    void OnClickIMGamf_capacity() {
        startActivityForResult(new Intent(this, CameraCapture.class), 113);
    }
    @OnClick(R.id.img_fullprobe)
    void OnClickIMGfullprobe() {
        startActivityForResult(new Intent(this, CameraCapture.class), 114);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_generator_detail_general);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Generator Detail - General");
        ac.setDisplayHomeAsUpEnabled(true);

        activity = this;
        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
        id_site = spf.getString(ParameterCollections.SH_ID_SITE, "1");

        manufactur_name = getIntent().getStringExtra("manufactur_name");
        model= getIntent().getStringExtra("model");
        phase= getIntent().getStringExtra("phase");
        remote_start= getIntent().getStringExtra("remote_start");
        primary_rating= getIntent().getStringExtra("primary_rating");
        standby_rating= getIntent().getStringExtra("standby_rating");
        url_file00_generator= getIntent().getStringExtra("url_file_generator");

        engine_manufactur_name = getIntent().getStringExtra("engine_manufactur_name");
        engine_model= getIntent().getStringExtra("engine_model");
        engine_fitted= getIntent().getStringExtra("engine_fitted");
        controller_manufactur_name= getIntent().getStringExtra("controller_manufactur_name");
        controller_model_name= getIntent().getStringExtra("controller_model_name");
        controller_condition= getIntent().getStringExtra("controller_condition");

        battery_fitted= getIntent().getStringExtra("battery_fitted");
        battery_controller_manufactur_name = getIntent().getStringExtra("battery_controller_manufactur_name");
        battery_model_name= getIntent().getStringExtra("battery_model_name");
        battery_condition= getIntent().getStringExtra("battery_condition");
        battery_voltast= getIntent().getStringExtra("battery_voltast");
        url_file_controller= getIntent().getStringExtra("url_file_controller");
        url_file_battery= getIntent().getStringExtra("url_file_battery");


        radio_speed = getIntent().getStringExtra("radio_speed");
        radio_freq= getIntent().getStringExtra("radio_freq");
        radio_voltase= getIntent().getStringExtra("radio_voltase");
        l1_connections= getIntent().getStringExtra("l1_connections");
        l2_connections= getIntent().getStringExtra("l2_connections");
        l3_connections= getIntent().getStringExtra("l3_connections");
        l1_loads = getIntent().getStringExtra("l1_loads");
        l2_loads= getIntent().getStringExtra("l2_loads");
        l3_loads= getIntent().getStringExtra("l3_loads");
        running_hours= getIntent().getStringExtra("running_hours");
        condition= getIntent().getStringExtra("condition");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 111) {
                url_file_ats = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file_ats), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file_ats).asBitmap().into(img_ats);
//                img_Site.setImageBitmap(bitmap);
            }

            if (requestCode == 112) {
                url_file_amf = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file_amf), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file_amf).asBitmap().into(img_amf);
//                img_Site.setImageBitmap(bitmap);
            }

            if (requestCode == 113) {
                url_file_amf_capacity = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file_amf_capacity), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file_amf_capacity).asBitmap().into(img_amf_capacity);
//                img_Site.setImageBitmap(bitmap);
            }

            if (requestCode == 114) {
                url_file_fullprobe= data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file_fullprobe), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file_fullprobe).asBitmap().into(img_fullprobe);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }

}
