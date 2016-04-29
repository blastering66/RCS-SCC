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
import id.ols.models.PojoManufactureMicrowave;
import id.ols.models.PojoManufactureMicrowaveModel;
import id.ols.models.PojoManufactureRAN;
import id.ols.models.PojoManufactureRANModel;
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
public class Tech_Microwave extends AppCompatActivity {
    @Bind(R.id.spinner_manufactur)Spinner spinner_manufactur;
    @Bind(R.id.spinner_model)Spinner spinner_model;

    @Bind(R.id.radio_volt_24)RadioButton radio_volt_24;
    @Bind(R.id.radio_volt_48)RadioButton radio_volt_48;
    @Bind(R.id.radio_volt_110)RadioButton radio_volt_110;

    @Bind(R.id.pg_manufactur)ProgressBar pg_manufactur;
    @Bind(R.id.pg_model)ProgressBar pg_model;

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

    List<RowData_Manufactur> name_manufactur;
    List<RowData_Model> name_model;
    String idManufacturParent, idModelParent;

    @OnClick(R.id.btn)
    void sendData() {
        final CustomProgressDialog pDialog;

        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        String ran_idsitevisit, ran_idmanufacturer, ran_idmodel,ran_frequency,ran_configuration,ran_voltase,
                ran_dcload;

        ran_idsitevisit = id_site;
        ran_idmanufacturer = idManufacturParent;
        ran_idmodel = idModelParent;

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
        RequestBody voltase = RequestBody.create(MediaType.parse("text/plain"), ran_voltase);
        RequestBody dcload = RequestBody.create(MediaType.parse("text/plain"), ran_dcload);

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        sourceFile00 = new File(url_file00);
        body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);

        API_Adapter adapter = PublicFunctions.initRetrofit();

        Observable<PojoResponseInsert> observable = adapter.insert_tech_microwave(apikey,authkey,idsitevisit,
                idmanufacturer,idmodel,voltase,dcload,body00);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();

                        DialogConfirmation pDialog_comfirm = new DialogConfirmation();
                        pDialog_comfirm.setContext(getApplicationContext());
                        pDialog_comfirm.setText("Add Additional Microwave");
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
        setContentView(R.layout.fragment_microwave);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("MICROWAVE");
        ac.setDisplayHomeAsUpEnabled(true);

        activity = this;
        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
        id_site = spf.getString(ParameterCollections.SH_ID_SITE, "1");

        getManufactureData();
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
                Glide.with(getApplicationContext()).load(url_file00).asBitmap().into(img);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }

    private void getManufactureData() {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoManufactureMicrowave> observable = adapter.get_manufacture_microwave(apikey, authkey);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureMicrowave>() {
                    @Override
                    public void onCompleted() {
                        Log.e("Error", "Completed");
                        if (name_manufactur.size() > 0 || name_manufactur != null) {
                            List<String> array_manufactur = new ArrayList<String>();

                            for (int i = 0; i < name_manufactur.size(); i++) {
                                array_manufactur.add(name_manufactur.get(i).name);
                            }

                            pg_manufactur.setVisibility(View.GONE);
                            spinner_manufactur.setVisibility(View.VISIBLE);

                            ArrayAdapter<String> adapter_manufactur = new ArrayAdapter<String>(getApplicationContext(),
                                    R.layout.spinner_item, array_manufactur);
                            adapter_manufactur.setDropDownViewResource(R.layout.spinner_item);
                            spinner_manufactur.setAdapter(adapter_manufactur);

                            spinner_manufactur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    idManufacturParent = name_manufactur.get(position).id;

                                    //Sementara
                                    getManufacture_Model_Data(idManufacturParent);
//                                    getManufacture_Model_Data("1");

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    //Sementara
                                    getManufacture_Model_Data(name_manufactur.get(0).id);
//                                    getManufacture_Model_Data("1");
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Eror");

                    }

                    @Override
                    public void onNext(PojoManufactureMicrowave pojoRegions) {
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


    private void getManufacture_Model_Data(String id_manufactur) {
        final API_Adapter adapter = PublicFunctions.initRetrofit();
        String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");
        Observable<PojoManufactureMicrowaveModel> observable = adapter.get_manufacture_microwave_model(apikey,authkey, id_manufactur);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoManufactureMicrowaveModel>() {
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
                            spinner_model.setAdapter(adapter_model);

                            pg_model.setVisibility(View.GONE);
                            spinner_model.setVisibility(View.VISIBLE);

                            spinner_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    public void onNext(PojoManufactureMicrowaveModel pojoRegions) {
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
