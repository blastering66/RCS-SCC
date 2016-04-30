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

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_Generators_Detail_1_Controller extends AppCompatActivity {
    @Bind(R.id.ed_engine_manufactur_name)EditText ed_engine_manufactur_name;
    @Bind(R.id.ed_engine_model)EditText ed_engine_model;
    @Bind(R.id.tipe_controller_fitted_yes)RadioButton tipe_controller_fitted_yes;
    @Bind(R.id.tipe_controller_fitted_no)RadioButton tipe_controller_fitted_no;

    @Bind(R.id.ed_controller_manufactur_name)EditText ed_controller_manufactur_name;
    @Bind(R.id.ed_controller_model_name)EditText ed_controller_model_name;
    @Bind(R.id.ed_controller_condition)EditText ed_controller_condition;

    @Bind(R.id.tipe_battery_fitted_yes)RadioButton tipe_battery_fitted_yes;
    @Bind(R.id.tipe_battery_fitted_no)RadioButton tipe_battery_fitted_no;
    @Bind(R.id.ed_battery_manufactur_name)EditText ed_battery_manufactur_name;
    @Bind(R.id.ed_battery_condition)EditText ed_battery_condition;
    @Bind(R.id.ed_battery_model_name)EditText ed_battery_model_name;
    @Bind(R.id.ed_battery_voltast)EditText ed_battery_voltast;

    @Bind(R.id.img_controller)ImageView img_controller;
    @Bind(R.id.img_battery)ImageView img_battery;

    SharedPreferences spf;
    String id_site;
    String url_file00, url_file01;

    @Bind(R.id.btn)
    Button btn;

    @OnClick(R.id.img_controller)
    void OnClickIMGController() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }
    @OnClick(R.id.img_battery)
    void OnClickIMGBattery() {
        startActivityForResult(new Intent(this, CameraCapture.class), 112);
    }

    @OnClick(R.id.btn) void onClick(){
        Intent intent = new Intent(getApplicationContext(), Tech_Generators_Detail_2_General.class);

        String engine_manufactur_name = ed_engine_manufactur_name.getText().toString();
        String engine_model = ed_engine_model.getText().toString();
        String engine_fitted = "";

        if(tipe_controller_fitted_yes.isChecked()){
            engine_fitted = getResources().getString(R.string.option_yes_smallcase);
        }else{
            engine_fitted = getResources().getString(R.string.option_no_smallcase);
        }

        String controller_manufactur_name = ed_controller_manufactur_name.getText().toString();
        String controller_model_name = ed_controller_model_name.getText().toString();
        String controller_condition = ed_controller_condition.getText().toString();

        String battery_fitted = "";

        if(tipe_battery_fitted_yes.isChecked()){
            battery_fitted = getResources().getString(R.string.option_yes_smallcase);
        }else{
            battery_fitted = getResources().getString(R.string.option_no_smallcase);
        }

        String battery_controller_manufactur_name = ed_battery_manufactur_name.getText().toString();
        String battery_model_name = ed_battery_model_name.getText().toString();
        String battery_condition = ed_battery_condition.getText().toString();
        String battery_voltast = ed_battery_voltast.getText().toString();

        intent.putExtra("engine_manufactur_name",engine_manufactur_name);
        intent.putExtra("engine_model",engine_model);
        intent.putExtra("engine_fitted",engine_fitted);
        intent.putExtra("controller_manufactur_name",controller_manufactur_name);
        intent.putExtra("controller_model_name",controller_model_name);
        intent.putExtra("controller_condition",controller_condition);
//        intent.putExtra("engine_fitted",engine_fitted);
        intent.putExtra("battery_fitted",battery_fitted);
        intent.putExtra("battery_controller_manufactur_name",battery_controller_manufactur_name);
        intent.putExtra("battery_model_name",battery_model_name);
        intent.putExtra("battery_condition",battery_condition);
        intent.putExtra("battery_voltast",battery_voltast);
        intent.putExtra("url_file_controller",url_file00);
        intent.putExtra("url_file_battery",url_file01);

        //Dari Event Sebelumnya
        intent.putExtra("manufactur_name",manufactur_name);
        intent.putExtra("model",model);
        intent.putExtra("phase",phase);
        intent.putExtra("remote_start",remote_start);
        intent.putExtra("primary_rating",primary_rating);
        intent.putExtra("standby_rating",standby_rating);
        intent.putExtra("url_file_generator",url_file00_generator);


        intent.putExtra("radio_speed",radio_speed);
        intent.putExtra("radio_freq",radio_freq);
        intent.putExtra("radio_voltase",radio_voltase);
        intent.putExtra("l1_connections",l1_connections);
        intent.putExtra("l2_connections",l2_connections);
        intent.putExtra("l3_connections",l3_connections);
        intent.putExtra("l1_loads",l1_loads);
        intent.putExtra("l2_loads",l2_loads);
        intent.putExtra("l3_loads",l3_loads);
        intent.putExtra("running_hours",running_hours);
        intent.putExtra("condition",condition);

        startActivity(intent);
        finish();
    }

    String manufactur_name, model, phase, remote_start, primary_rating, standby_rating, url_file00_generator = "";
    String radio_speed, radio_freq, radio_voltase, l1_connections, l2_connections, l3_connections,
            l1_loads,l2_loads,l3_loads,running_hours,condition= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_generator_detail_controller);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Generator Detail - Controller");
        ac.setDisplayHomeAsUpEnabled(true);

        manufactur_name = getIntent().getStringExtra("manufactur_name");
        model= getIntent().getStringExtra("model");
        phase= getIntent().getStringExtra("phase");
        remote_start= getIntent().getStringExtra("remote_start");
        primary_rating= getIntent().getStringExtra("primary_rating");
        standby_rating= getIntent().getStringExtra("standby_rating");

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

        url_file00_generator= getIntent().getStringExtra("url_file_generator");
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
                Glide.with(getApplicationContext()).load(url_file01).asBitmap().into(img_battery);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }
}
