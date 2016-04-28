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
public class Tech_Grid extends AppCompatActivity {
    @Bind(R.id.ed_pln_id )EditText ed_pln_id;
    @Bind(R.id.radio_plnmeter_type_digital )RadioButton radio_plnmeter_type_digital;
    @Bind(R.id.radio_plnmeter_type_analog )RadioButton radio_plnmeter_type_analog;
    @Bind(R.id.ed_manufactur_name )EditText ed_manufactur_name;
    @Bind(R.id.ed_model )EditText ed_model;
    @Bind(R.id.ed_condition )EditText ed_condition;
    @Bind(R.id.ed_distance )EditText ed_distance;

    @Bind(R.id.tipe_single )RadioButton tipe_single;
    @Bind(R.id.tipe_three )RadioButton tipe_three;
    @Bind(R.id.ed_value_phase )EditText ed_value_phase;
    @Bind(R.id.ed_pln_measurement_3r )EditText ed_pln_measurement_3r;
    @Bind(R.id.ed_pln_measurement_3s )EditText ed_pln_measurement_3s;
    @Bind(R.id.ed_pln_measurement_3t )EditText ed_pln_measurement_3t;
    @Bind(R.id.ed_rating )EditText ed_rating;

    @Bind(R.id.ed_acpdb_manufactur_name )EditText ed_acpdb_manufactur_name;
    @Bind(R.id.ed_acpdb_model_name )EditText ed_acpdb_model_name;
    @Bind(R.id.ed_acpdb_condition )EditText ed_acpdb_condition;

    @Bind(R.id.img)ImageView img;
    @Bind(R.id.img_rating)ImageView img_rating;
    @Bind(R.id.img_meter_surge)ImageView img_meter_surge;
    @Bind(R.id.img_acpdb)ImageView img_acpdb;

    SharedPreferences spf;
    String id_site;
    String url_file00,url_file01,url_file02,url_file03;
    File sourceFile00, sourceFile01,sourceFile02, sourceFile03;
    RequestBody body00, body01,body02, body03;

    Activity activity;
    boolean isSukses = false;
    String message = "";


    @OnClick(R.id.img)
    void OnClickIMG() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }
    @OnClick(R.id.img_rating)
    void OnClickIMGRating() {
        startActivityForResult(new Intent(this, CameraCapture.class), 112);
    }
    @OnClick(R.id.img_meter_surge)
    void OnClickIMGMeterSurge() {
        startActivityForResult(new Intent(this, CameraCapture.class), 113);
    }
    @OnClick(R.id.img_acpdb)
    void OnClickIMGacpdb() {
        startActivityForResult(new Intent(this, CameraCapture.class), 114);
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
            if (requestCode == 112) {
                url_file01 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file01), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file01).asBitmap().into(img_rating);
//                img_Site.setImageBitmap(bitmap);
            }
            if (requestCode == 113) {
                url_file02 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file02), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file02).asBitmap().into(img_meter_surge);
//                img_Site.setImageBitmap(bitmap);
            }
            if (requestCode == 114) {
                url_file03 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file03), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file03).asBitmap().into(img_acpdb);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_grid);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("GRID");
        ac.setDisplayHomeAsUpEnabled(true);

        activity = this;
        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
        id_site = spf.getString(ParameterCollections.SH_ID_SITE, "1");
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

    @OnClick(R.id.btn)
    void sendData() {
        final CustomProgressDialog pDialog;

        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        String pln_id, manufactur_name, model,condition,distance,value_phase,
                pln_measurement_3r, pln_measurement_3s,pln_measurement_3t,rating;

        pln_id = ed_pln_id.getText().toString();
        manufactur_name= ed_manufactur_name.getText().toString();
        model= ed_model.getText().toString();
        condition= ed_condition.getText().toString();
        distance = ed_distance.getText().toString();
        value_phase = ed_value_phase.getText().toString();
        pln_measurement_3r = ed_pln_measurement_3r.getText().toString();
        pln_measurement_3s = ed_pln_measurement_3s.getText().toString();
        pln_measurement_3t = ed_pln_measurement_3t.getText().toString();
        rating = ed_rating.getText().toString();

        String plnmeter_type ="";
        if(radio_plnmeter_type_digital.isChecked()){
            plnmeter_type = getResources().getString(R.string.option_digital);
        }else {
            plnmeter_type = getResources().getString(R.string.option_digital);
        }

        String phase_type ="";
        if(tipe_single.isChecked()){
            phase_type = getResources().getString(R.string.option_phase_single_bigcase);
        }else {
            phase_type = getResources().getString(R.string.option_phase_three_bigcase);
        }

        RequestBody _pln_id = RequestBody.create(MediaType.parse("text/plain"), pln_id);
        RequestBody _manufactur_name = RequestBody.create(MediaType.parse("text/plain"), manufactur_name);
        RequestBody _model = RequestBody.create(MediaType.parse("text/plain"), model);
        RequestBody _condition = RequestBody.create(MediaType.parse("text/plain"), condition);
        RequestBody _distance = RequestBody.create(MediaType.parse("text/plain"), distance);

        RequestBody _value_phase = RequestBody.create(MediaType.parse("text/plain"), value_phase);
        RequestBody _pln_measurement_3r = RequestBody.create(MediaType.parse("text/plain"), pln_measurement_3r);
        RequestBody _pln_measurement_3s = RequestBody.create(MediaType.parse("text/plain"), pln_measurement_3s);
        RequestBody _pln_measurement_3t = RequestBody.create(MediaType.parse("text/plain"), pln_measurement_3t);
        RequestBody _rating = RequestBody.create(MediaType.parse("text/plain"), rating);

        RequestBody _plnmeter_type = RequestBody.create(MediaType.parse("text/plain"), plnmeter_type);
        RequestBody _phase_type = RequestBody.create(MediaType.parse("text/plain"), phase_type);

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

//        Observable<PojoResponseInsert> observable = adapter.insert_tech_insert_tech_grid_detail(apikey, authkey, idsitevisit,
//                idmanufacturer, idmodel, voltase,dcload,body00);
//        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PojoResponseInsert>() {
//                    @Override
//                    public void onCompleted() {
//                        pDialog.dismiss();
//
//                        DialogConfirmation pDialog_comfirm = new DialogConfirmation();
//                        pDialog_comfirm.setContext(getApplicationContext());
//                        pDialog_comfirm.setText("Add Additional Microwave");
//                        pDialog_comfirm.setFrom(1);
//
//                        pDialog_comfirm.show(getSupportFragmentManager(), "");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("Error", "Something Wrong");
//
//                    }
//
//                    @Override
//                    public void onNext(PojoResponseInsert pojoResponseInsert) {
//                        if (pojoResponseInsert.getJsonCode() != null) {
//                            if (pojoResponseInsert.getAct().getInsert() == 1) {
//                                isSukses = true;
//                            } else {
//                                message = pojoResponseInsert.getResponseMessage();
//                            }
//                        }
//
//                    }
//                });


    }
}
