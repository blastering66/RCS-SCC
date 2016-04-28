package id.ols.sitecare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ols.util.CameraCapture;
import id.ols.util.ParameterCollections;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_Generators_Detail_2_General extends AppCompatActivity {
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

    SharedPreferences spf;
    String id_site;
    String manufactur_name, model, phase, remote_start, primary_rating, standby_rating, url_file00_generator = "";
    String engine_manufactur_name, engine_model, engine_fitted, controller_manufactur_name, controller_model_name,
            controller_condition, battery_fitted, battery_controller_manufactur_name,battery_model_name,
            battery_condition, battery_voltast, url_file_controller, url_file_battery= "";
    String url_file_ats,url_file_amf, url_file_amf_capacity, url_file_fullprobe;

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

        spf.edit().putBoolean(ParameterCollections.SH_GENERATOR_SUBMITTED, true).commit();
        setResult(RESULT_OK);
        finish();
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

        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);

        manufactur_name = getIntent().getStringExtra("manufactur_name");
        model= getIntent().getStringExtra("model");
        phase= getIntent().getStringExtra("phase");
        remote_start= getIntent().getStringExtra("remote_start");
        primary_rating= getIntent().getStringExtra("primary_rating");
        standby_rating= getIntent().getStringExtra("manufactstandby_ratingur_name");
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
