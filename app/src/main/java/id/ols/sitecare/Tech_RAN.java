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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

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
import id.ols.models.PojoManufactureRAN;
import id.ols.models.PojoManufactureRANModel;
import id.ols.models.PojoRegions;
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
public class Tech_RAN extends AppCompatActivity {
    @Bind(R.id.spinner_manufactur)Spinner spinner_manufactur;
    @Bind(R.id.spinner_model)Spinner spinner_model;

    @Bind(R.id.ed_config)EditText ed_config;

    @Bind(R.id.ed_freq_0)EditText ed_freq_0;
    @Bind(R.id.ed_freq_1)EditText ed_freq_1;
    @Bind(R.id.ed_freq_2)EditText ed_freq_2;

    @Bind(R.id.ed_freq_a)EditText ed_freq_a;
    @Bind(R.id.ed_freq_b)EditText ed_freq_b;
    @Bind(R.id.ed_freq_c)EditText ed_freq_c;

    @Bind(R.id.radio_volt_24)RadioButton radio_volt_24;
    @Bind(R.id.radio_volt_48)RadioButton radio_volt_48;
    @Bind(R.id.radio_volt_110)RadioButton radio_volt_110;

    @Bind(R.id.ed_ac_loads)EditText ed_ac_loads;
    @Bind(R.id.img)ImageView img;

    @Bind(R.id.btn)
    Button btn;

    SharedPreferences spf;
    String id_site;
    String url_file00;
    File sourceFile00;
    RequestBody body00;

    Activity activity;
    boolean isSukses = false;
    String message = "";
    List<String> name_manufactur, name_model;

    @OnClick(R.id.btn)
    void sendData() {
        final CustomProgressDialog pDialog;

        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        String ran_idsitevisit, ran_idmanufacturer, ran_idmodel,ran_frequency,ran_configuration,ran_voltase,
                ran_dcload;

        ran_idsitevisit = id_site;
        ran_idmanufacturer = "1";
        ran_idmodel = "1";
        ran_frequency= "DC 1800";
        ran_configuration = ed_config.getText().toString();

        if(radio_volt_24.isChecked()){
            ran_voltase = getResources().getString(R.string.option_volt_24);
        }else if(radio_volt_48.isChecked()){
            ran_voltase = getResources().getString(R.string.option_volt_48);
        }else {
            ran_voltase = getResources().getString(R.string.option_volt_110);
        }

        ran_dcload = ed_ac_loads.getText().toString();

        RequestBody idsitevisit = RequestBody.create(MediaType.parse("text/plain"), ran_idsitevisit);
        RequestBody idmanufacturer = RequestBody.create(MediaType.parse("text/plain"), ran_idmanufacturer);
        RequestBody idmodel = RequestBody.create(MediaType.parse("text/plain"), ran_idmodel);
        RequestBody frequency = RequestBody.create(MediaType.parse("text/plain"), ran_frequency);
        RequestBody configuration = RequestBody.create(MediaType.parse("text/plain"), ran_configuration);
        RequestBody voltase = RequestBody.create(MediaType.parse("text/plain"), ran_voltase);
        RequestBody dcload = RequestBody.create(MediaType.parse("text/plain"), ran_dcload);

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        sourceFile00 = new File(url_file00);
        body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);

        API_Adapter adapter = PublicFunctions.initRetrofit();

        Observable<PojoResponseInsert> observable = adapter.insert_tech_ran(apikey,authkey,idsitevisit,
                idmanufacturer,idmodel,frequency,configuration,dcload,voltase,body00);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();

                        DialogConfirmation pDialog_comfirm = new DialogConfirmation();
                        pDialog_comfirm.setContext(getApplicationContext());
                        pDialog_comfirm.setText("Add Additional RAN");
                        pDialog_comfirm.setFrom(1);

                        pDialog_comfirm.show(getSupportFragmentManager(), "");
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

    @OnClick(R.id.img)
    void OnClickIMG() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ran);
        ButterKnife.bind(this);
        activity = this;
        ActionBar ac = getSupportActionBar();
        ac.setTitle("RAN");
        ac.setDisplayHomeAsUpEnabled(true);

        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
        id_site = spf.getString(ParameterCollections.SH_ID_SITE, "1");

        getManufactureData();
        getManufacture_Model_Data("1");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
                Glide.with(getApplicationContext()).load(url_file00).asBitmap().into(img);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }

    private void getManufactureData() {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        Observable<PojoManufactureRAN> observable = adapter.get_manufacture_ran(apikey);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureRAN>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_manufactur.size() > 0 || name_manufactur != null) {
                            ArrayAdapter<String> adapter_manufactur= new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, name_manufactur);
                            adapter_manufactur.setDropDownViewResource(R.layout.spinner_item);
                            spinner_manufactur.setAdapter(adapter_manufactur);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoManufactureRAN pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getRanmanufacturerName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_manufactur = new ArrayList<String>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_manufactur.add(pojoRegions.getData().get(i).getRanmanufacturerName());
                                }
                            }
                        }

                    }
                });

    }


    private void getManufacture_Model_Data(String id_manufactur) {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        Observable<PojoManufactureRANModel> observable = adapter.get_manufacture_ran_model(apikey, id_manufactur);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureRANModel>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_model.size() > 0 || name_model != null) {
                            ArrayAdapter<String> adapter_model= new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, name_model);
                            adapter_model.setDropDownViewResource(R.layout.spinner_item);
                            spinner_model.setAdapter(adapter_model);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoManufactureRANModel pojoRegions) {
                        Log.e("Datanya = ", pojoRegions.getData().get(0).getRanmodelName());
                        if (pojoRegions.getJsonCode() == 1) {
                            if (pojoRegions.getAct().getGet() == 1) {
                                name_model = new ArrayList<String>();
                                for (int i = 0; i < pojoRegions.getData().size(); i++) {
                                    name_model.add(pojoRegions.getData().get(i).getRanmodelName());
                                }
                            }
                        }

                    }
                });

    }

}
