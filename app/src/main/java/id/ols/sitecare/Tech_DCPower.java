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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.ols.dialogs.DialogConfirmation;
import id.ols.models.PojoManufactureDCPower;
import id.ols.models.PojoManufactureDCPowerModel;
import id.ols.models.PojoManufactureGpon;
import id.ols.models.PojoManufactureGponModel;
import id.ols.models.PojoResponseInsert;
import id.ols.models.RowData_Manufactur;
import id.ols.models.RowData_Model;
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
public class Tech_DCPower extends AppCompatActivity {
    SharedPreferences spf;
    String id_site;
    String url_file00,url_file01,url_file02,url_file03;
    File sourceFile00, sourceFile01,sourceFile02, sourceFile03;
    RequestBody body00, body01,body02, body03;
    List<RowData_Manufactur> name_manufactur;
    List<RowData_Model> name_model;
    String idManufacturParent, idModelParent,
    idManufacturParent_RecSystem, idModelParent_RecSystem;

    Activity activity;
    boolean isSukses = false;
    String message = "";

    @Bind(R.id.spinner_manufactur_controller)Spinner spinner_manufactur_controller;
    @Bind(R.id.spinner_model_controller)Spinner spinner_model_controller;
    @Bind(R.id.spinner_manufacture_rec_system)Spinner spinner_manufacture_rec_system;
    @Bind(R.id.spinner_model_rec_system)Spinner spinner_model_rec_system;

    @Bind(R.id.pg_manufactur_controller)ProgressBar pg_manufactur_controller;
    @Bind(R.id.pg_model_controller)ProgressBar pg_model_controller;
    @Bind(R.id.pg_manufacture_rec_system)ProgressBar pg_manufacture_rec_system;
    @Bind(R.id.pg_model_rec_system)ProgressBar pg_model_rec_system;

    @Bind(R.id.ed_model) EditText ed_model;

    @Bind(R.id.ed_manufactur_controller)EditText ed_manufactur_controller;
    @Bind(R.id.ed_model_controller)EditText ed_model_controller;

    @Bind(R.id.ed_rectifier_manufactur) EditText ed_rectifier_manufactur;
    @Bind(R.id.ed_rectifier_model) EditText ed_rectifier_model;
    @Bind(R.id.ed_rectifier_rating) EditText ed_rectifier_rating;
    @Bind(R.id.ed_rectifier_kuantiti) EditText ed_rectifier_kuantiti;
    @Bind(R.id.ed_rectifier_spareslot) EditText ed_rectifier_spareslot;

    @Bind(R.id.ed_arrester_manufactur) EditText ed_arrester_manufactur;
    @Bind(R.id.ed_arrester_model) EditText ed_arrester_model;

    @Bind(R.id.ed_ac_input_breaker_type) EditText ed_ac_input_breaker_type;
    @Bind(R.id.ed_ac_input_rating) EditText ed_ac_input_rating;

    @Bind(R.id.ed_dc_input_breaker_rating) EditText ed_dc_input_breaker_rating;
    @Bind(R.id.ed_dc_input_breaker_rating_2) EditText ed_dc_input_breaker_rating_2;
    @Bind(R.id.ed_dc_input_breaker_rating_3) EditText ed_dc_input_breaker_rating_3;
    @Bind(R.id.ed_dc_input_breaker_rating_4) EditText ed_dc_input_breaker_rating_4;
    @Bind(R.id.ed_dc_input_breaker_rating_5) EditText ed_dc_input_breaker_rating_5;

    @Bind(R.id.ed_dc_input_breaker_kuantiti) EditText ed_dc_input_breaker_kuantiti;
    @Bind(R.id.ed_dc_input_breaker_kuantiti_2) EditText ed_dc_input_breaker_kuantiti_2;
    @Bind(R.id.ed_dc_input_breaker_kuantiti_3) EditText ed_dc_input_breaker_kuantiti_3;
    @Bind(R.id.ed_dc_input_breaker_kuantiti_4) EditText ed_dc_input_breaker_kuantiti_4;
    @Bind(R.id.ed_dc_input_breaker_kuantiti_5) EditText ed_dc_input_breaker_kuantiti_5;

    @Bind(R.id.ed_ac_loads) EditText ed_ac_loads;

    @Bind(R.id.ed_dc_cooling_fan_manufactur) EditText ed_dc_cooling_fan_manufactur;
    @Bind(R.id.ed_dc_cooling_fan_model) EditText ed_dc_cooling_fan_model;

    @Bind(R.id.img_controller) ImageView img_controller;
    @Bind(R.id.img_ac_breaker) ImageView img_ac_breaker;
    @Bind(R.id.img_dc) ImageView img_dc;
    @Bind(R.id.img_dc_cooling_fan) ImageView img_dc_cooling_fan;

    @OnClick(R.id.img_controller)
    void OnClickIMG() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }
    @OnClick(R.id.img_ac_breaker)
    void OnClickIMGRating() {
        startActivityForResult(new Intent(this, CameraCapture.class), 112);
    }
    @OnClick(R.id.img_dc)
    void OnClickIMGMeterSurge() {
        startActivityForResult(new Intent(this, CameraCapture.class), 113);
    }
    @OnClick(R.id.img_dc_cooling_fan)
    void OnClickIMGacpdb() {
        startActivityForResult(new Intent(this, CameraCapture.class), 114);
    }

    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        final CustomProgressDialog pDialog;

        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        String model,manufactur_controller, model_controller, rectifier_manufactur, rectifier_model,rectifier_rating,rectifier_kuantiti, rectifier_spareslot,
                arrester_manufactur, arrester_model,ac_input_breaker_type,ac_input_rating,
                dc_input_breaker_rating, dc_input_breaker_rating_2,dc_input_breaker_rating_3,dc_input_breaker_rating_4, dc_input_breaker_rating_5,
                dc_input_breaker_kuantiti,dc_input_breaker_kuantiti_2, dc_input_breaker_kuantiti_3, dc_input_breaker_kuantiti_4, dc_input_breaker_kuantiti_5,
                ac_loads,dc_cooling_fan_manufactur,dc_cooling_fan_model,
                idmanufacturer, idmodel;

        String _dc_input_breaker_rating = "";
        String _dc_input_breaker_kuantiti = "";

                model = ed_model.getText().toString();
        manufactur_controller = ed_manufactur_controller.getText().toString();
        model_controller= ed_model_controller.getText().toString();

        rectifier_manufactur= idManufacturParent_RecSystem;
        rectifier_model = idModelParent_RecSystem;


        rectifier_rating = ed_rectifier_rating.getText().toString();
        rectifier_kuantiti = ed_rectifier_kuantiti.getText().toString();
        rectifier_spareslot = ed_rectifier_spareslot.getText().toString();
        arrester_manufactur = ed_arrester_manufactur.getText().toString();
        arrester_model = ed_arrester_model.getText().toString();
        ac_input_breaker_type = ed_ac_input_breaker_type.getText().toString();
        ac_input_rating = ed_ac_input_rating.getText().toString();
        dc_input_breaker_rating = ed_dc_input_breaker_rating.getText().toString();
        dc_input_breaker_rating_2 = ed_dc_input_breaker_rating_2.getText().toString();
        dc_input_breaker_rating_3 = ed_dc_input_breaker_rating_3.getText().toString();
        dc_input_breaker_rating_4 = ed_dc_input_breaker_rating_4.getText().toString();
        dc_input_breaker_rating_5 = ed_dc_input_breaker_rating_5.getText().toString();

        dc_input_breaker_kuantiti = ed_dc_input_breaker_kuantiti.getText().toString();
        dc_input_breaker_kuantiti_2 = ed_dc_input_breaker_kuantiti_2.getText().toString();
        dc_input_breaker_kuantiti_3 = ed_dc_input_breaker_kuantiti_3.getText().toString();
        dc_input_breaker_kuantiti_4 = ed_dc_input_breaker_kuantiti_4.getText().toString();
        dc_input_breaker_kuantiti_5 = ed_dc_input_breaker_kuantiti_5.getText().toString();

        if(!dc_input_breaker_rating.equals("")){
            _dc_input_breaker_rating = dc_input_breaker_rating;
        }
        if(!dc_input_breaker_rating_2.equals("")){
            _dc_input_breaker_rating = _dc_input_breaker_rating + ":" + dc_input_breaker_rating_2;
        }
        if(!dc_input_breaker_rating_3.equals("")){
            _dc_input_breaker_rating = _dc_input_breaker_rating + ":" + dc_input_breaker_rating_3;
        }
        if(!dc_input_breaker_rating_4.equals("")){
            _dc_input_breaker_rating = _dc_input_breaker_rating + ":" + dc_input_breaker_rating_4;
        }
        if(!dc_input_breaker_rating_5.equals("")){
            _dc_input_breaker_rating = _dc_input_breaker_rating + ":" + dc_input_breaker_rating_5;
        }

        if(!dc_input_breaker_kuantiti.equals("")){
            _dc_input_breaker_kuantiti= dc_input_breaker_kuantiti;
        }
        if(!dc_input_breaker_kuantiti_2.equals("")){
            _dc_input_breaker_kuantiti= _dc_input_breaker_kuantiti + ":" + dc_input_breaker_kuantiti_2;
        }
        if(!dc_input_breaker_kuantiti_3.equals("")){
            _dc_input_breaker_kuantiti= _dc_input_breaker_kuantiti + ":" + dc_input_breaker_kuantiti_3;
        }
        if(!dc_input_breaker_kuantiti_4.equals("")){
            _dc_input_breaker_kuantiti= _dc_input_breaker_kuantiti + ":" + dc_input_breaker_kuantiti_4;
        }
        if(!dc_input_breaker_kuantiti_5.equals("")){
            _dc_input_breaker_kuantiti= _dc_input_breaker_kuantiti + ":" + dc_input_breaker_kuantiti_5;
        }

        ac_loads = ed_ac_loads.getText().toString();
        dc_cooling_fan_manufactur = ed_dc_cooling_fan_manufactur.getText().toString();
        dc_cooling_fan_model = ed_dc_cooling_fan_model.getText().toString();

        idmanufacturer = idManufacturParent;
        idmodel = idModelParent;
        RequestBody _idmanufacturer = RequestBody.create(MediaType.parse("text/plain"), idmanufacturer);
//        RequestBody _model = RequestBody.create(MediaType.parse("text/plain"), model);
        RequestBody _idmodel = RequestBody.create(MediaType.parse("text/plain"), idmodel);

        RequestBody _idsitevisit = RequestBody.create(MediaType.parse("text/plain"), id_site);
//        RequestBody _controller_manufactur = RequestBody.create(MediaType.parse("text/plain"), manufactur_controller);
//        RequestBody _controller_model = RequestBody.create(MediaType.parse("text/plain"), model_controller);

        RequestBody _rectifier_manufactur = RequestBody.create(MediaType.parse("text/plain"), rectifier_manufactur);
        RequestBody _rectifier_model = RequestBody.create(MediaType.parse("text/plain"), rectifier_model);
        RequestBody _rectifier_rating = RequestBody.create(MediaType.parse("text/plain"), rectifier_rating);
        RequestBody _rectifier_kuantiti= RequestBody.create(MediaType.parse("text/plain"), rectifier_kuantiti);
        RequestBody _rectifier_spareslot = RequestBody.create(MediaType.parse("text/plain"), rectifier_spareslot);
        RequestBody _arrester_manufactur = RequestBody.create(MediaType.parse("text/plain"), arrester_manufactur);
        RequestBody _arrester_model = RequestBody.create(MediaType.parse("text/plain"), arrester_model);
        RequestBody _ac_input_breaker_type = RequestBody.create(MediaType.parse("text/plain"), ac_input_breaker_type);
        RequestBody _ac_input_rating = RequestBody.create(MediaType.parse("text/plain"), ac_input_rating);
        RequestBody _dc_input_breaker_rating_ = RequestBody.create(MediaType.parse("text/plain"), _dc_input_breaker_rating);
        RequestBody _dc_input_breaker_kuantiti_ = RequestBody.create(MediaType.parse("text/plain"), _dc_input_breaker_kuantiti);
//        RequestBody _dc_input_breaker_rating_2 = RequestBody.create(MediaType.parse("text/plain"), dc_input_breaker_rating_2);
//        RequestBody _dc_input_breaker_rating_3 = RequestBody.create(MediaType.parse("text/plain"), dc_input_breaker_rating_3);
//        RequestBody _dc_input_breaker_rating_4 = RequestBody.create(MediaType.parse("text/plain"), dc_input_breaker_rating_4);
//        RequestBody _dc_input_breaker_rating_5 = RequestBody.create(MediaType.parse("text/plain"), dc_input_breaker_rating_5);
        RequestBody _ac_loads = RequestBody.create(MediaType.parse("text/plain"), ac_loads);
        RequestBody _dc_cooling_fan_manufactur = RequestBody.create(MediaType.parse("text/plain"), dc_cooling_fan_manufactur);
        RequestBody _dc_cooling_fan_model = RequestBody.create(MediaType.parse("text/plain"), dc_cooling_fan_model);

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        sourceFile00 = new File(url_file00);
        body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);

        sourceFile01 = new File(url_file01);
        body01 = RequestBody.create(MediaType.parse("image/*"), sourceFile01);

        sourceFile02 = new File(url_file02);
        body02 = RequestBody.create(MediaType.parse("image/*"), sourceFile02);

        sourceFile03 = new File(url_file03);
        body03 = RequestBody.create(MediaType.parse("image/*"), sourceFile03);

        API_Adapter adapter = PublicFunctions.initRetrofit();
        Observable<PojoResponseInsert> observable = adapter.insert_tech_dcpower(apikey, authkey,ParameterCollections.EXE.INSERT,
                ParameterCollections.KIND.MOBILE, ParameterCollections.KIND.TECH_DCPOWER,_idsitevisit,_idmanufacturer,_idmodel,
                //_controller_manufactur ,_controller_model,
                _rectifier_manufactur, _rectifier_model, _rectifier_rating, _rectifier_kuantiti, _rectifier_spareslot,
                _arrester_manufactur, _arrester_model, _ac_input_breaker_type,_ac_input_rating,
                _dc_input_breaker_rating_, _dc_input_breaker_kuantiti_, _ac_loads,
                _dc_cooling_fan_manufactur, _dc_cooling_fan_model, body00, body01, body02, body03 );

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    boolean isSukses = false;
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();

                        if(isSukses){
                            DialogConfirmation pDialog_comfirm = new DialogConfirmation();
                            pDialog_comfirm.setContext(getApplicationContext());
                            pDialog_comfirm.setText("Add Additional DCPower");
                            pDialog_comfirm.setFrom(4);
                            pDialog_comfirm.setSh(spf);
                            pDialog_comfirm.show(getSupportFragmentManager(), "");
                        }else{
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Something Wrong");
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Something Wrong, = " + e.getMessage().toString(), Toast.LENGTH_LONG).show();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dc_power);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("DC Rectifier System");
        ac.setDisplayHomeAsUpEnabled(true);

        activity = this;
        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
        id_site = spf.getString(ParameterCollections.SH_ID_SITE, "1");

        getManufactureData();
        getManufactureData_Rectifier();
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
                url_file00 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file00), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file00).asBitmap().into(img_controller);
//                img_Site.setImageBitmap(bitmap);
            }
            if (requestCode == 112) {
                url_file01 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file01), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file01).asBitmap().into(img_ac_breaker);
//                img_Site.setImageBitmap(bitmap);
            }
            if (requestCode == 113) {
                url_file02 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file02), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file02).asBitmap().into(img_dc);
//                img_Site.setImageBitmap(bitmap);
            }
            if (requestCode == 114) {
                url_file03 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file03), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file03).asBitmap().into(img_dc_cooling_fan);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }

    private void getManufactureData() {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoManufactureDCPower> observable = adapter.get_manufacture_dcpower(apikey, authkey);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureDCPower>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_manufactur.size() > 0 || name_manufactur != null) {
                            List<String> array_manufactur = new ArrayList<String>();

                            for (int i = 0; i < name_manufactur.size(); i++) {
                                array_manufactur.add(name_manufactur.get(i).name);
                            }

                            pg_manufactur_controller.setVisibility(View.GONE);
                            spinner_manufactur_controller.setVisibility(View.VISIBLE);

                            ArrayAdapter<String> adapter_manufactur = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, array_manufactur);
                            adapter_manufactur.setDropDownViewResource(R.layout.spinner_item);
                            spinner_manufactur_controller.setAdapter(adapter_manufactur);

                            spinner_manufactur_controller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idManufacturParent = name_manufactur.get(position).id;
                                    getManufacture_Model_Data(idManufacturParent);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    //Sementara
                                    idManufacturParent = name_manufactur.get(0).id;
                                    getManufacture_Model_Data("1");
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoManufactureDCPower pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getManufacturerName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_manufactur = new ArrayList<RowData_Manufactur>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_manufactur.add(new RowData_Manufactur(pojoRegions.getData().get(i).getManufacturerId(),
                                            pojoRegions.getData().get(i).getManufacturerName()));
                                }
                            }
                        }

                    }
                });

    }

//
    private void getManufacture_Model_Data(String id_manufactur) {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoManufactureDCPowerModel> observable = adapter.get_manufacture_dcpower_model(apikey, authkey, id_manufactur);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureDCPowerModel>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_model.size() > 0 || name_model != null) {

                            List<String> array_model = new ArrayList<String>();
                            for(int i=0; i < name_model.size(); i++){
                                array_model.add(name_model.get(i).name);
                            }


                            ArrayAdapter<String> adapter_model= new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, array_model);
                            adapter_model.setDropDownViewResource(R.layout.spinner_item);
                            spinner_model_controller.setAdapter(adapter_model);

                            pg_model_controller.setVisibility(View.GONE);
                            spinner_model_controller.setVisibility(View.VISIBLE);

                            spinner_model_controller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idModelParent = name_model.get(position).id;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    idModelParent = name_model.get(0).id;
                                }
                            });

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoManufactureDCPowerModel pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getModelName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_model = new ArrayList<RowData_Model>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_model.add(new RowData_Model(pojoRegions.getData().get(i).getRanmodelId(),
                                            pojoRegions.getData().get(i).getModelName()));
                                }
                            }
                        }

                    }
                });
    }


    private void getManufactureData_Rectifier() {

        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoManufactureDCPower> observable = adapter.get_manufacture_dcpower(apikey, authkey);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureDCPower>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_manufactur.size() > 0 || name_manufactur != null) {
                            List<String> array_manufactur = new ArrayList<String>();

                            for (int i = 0; i < name_manufactur.size(); i++) {
                                array_manufactur.add(name_manufactur.get(i).name);
                            }

                            pg_manufacture_rec_system.setVisibility(View.GONE);
                            spinner_manufacture_rec_system.setVisibility(View.VISIBLE);

                            ArrayAdapter<String> adapter_manufactur = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, array_manufactur);
                            adapter_manufactur.setDropDownViewResource(R.layout.spinner_item);
                            spinner_manufacture_rec_system.setAdapter(adapter_manufactur);

                            spinner_manufacture_rec_system.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idManufacturParent_RecSystem = name_manufactur.get(position).id;
                                    getManufacture_Model_Data_Rectifier(idManufacturParent_RecSystem);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    //Sementara
                                    idManufacturParent_RecSystem = name_manufactur.get(0).id;
                                    getManufacture_Model_Data_Rectifier("1");
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoManufactureDCPower pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getManufacturerName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_manufactur = new ArrayList<RowData_Manufactur>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_manufactur.add(new RowData_Manufactur(pojoRegions.getData().get(i).getManufacturerId(),
                                            pojoRegions.getData().get(i).getManufacturerName()));
                                }
                            }
                        }

                    }
                });

    }

    //
    private void getManufacture_Model_Data_Rectifier(String id_manufactur) {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoManufactureDCPowerModel> observable = adapter.get_manufacture_dcpower_model(apikey, authkey, id_manufactur);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureDCPowerModel>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_model.size() > 0 || name_model != null) {

                            List<String> array_model = new ArrayList<String>();
                            for (int i = 0; i < name_model.size(); i++) {
                                array_model.add(name_model.get(i).name);
                            }


                            ArrayAdapter<String> adapter_model = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, array_model);
                            adapter_model.setDropDownViewResource(R.layout.spinner_item);
                            spinner_model_rec_system.setAdapter(adapter_model);

                            pg_model_rec_system.setVisibility(View.GONE);
                            spinner_model_rec_system.setVisibility(View.VISIBLE);

                            spinner_model_rec_system.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idModelParent_RecSystem = name_model.get(position).id;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    idModelParent_RecSystem = name_model.get(0).id;
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoManufactureDCPowerModel pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getModelName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_model = new ArrayList<RowData_Model>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_model.add(new RowData_Model(pojoRegions.getData().get(i).getRanmodelId(),
                                            pojoRegions.getData().get(i).getModelName()));
                                }
                            }
                        }

                    }
                });
    }

}
