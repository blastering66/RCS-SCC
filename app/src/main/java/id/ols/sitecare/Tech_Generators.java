package id.ols.sitecare;

import android.app.Activity;
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
import com.squareup.okhttp.RequestBody;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ols.util.CameraCapture;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_Generators extends AppCompatActivity {
    @Bind(R.id.ed_manufactur_name)EditText ed_manufactur_name;
    @Bind(R.id.ed_model)EditText ed_model;

    @Bind(R.id.tipe_single)RadioButton tipe_single;
    @Bind(R.id.tipe_three)RadioButton tipe_three;
    @Bind(R.id.radio_remote_yes)RadioButton radio_remote_yes;
    @Bind(R.id.radio_remote_no)RadioButton radio_remote_no;

    @Bind(R.id.ed_primary_rating)EditText ed_primary_rating;
    @Bind(R.id.ed_standby_rating)EditText ed_standby_rating;

    @Bind(R.id.radio_speed_1500)RadioButton radio_speed_1500;
    @Bind(R.id.radio_speed_1800)RadioButton radio_speed_1800;
    @Bind(R.id.radio_freq_50)RadioButton radio_freq_50;
    @Bind(R.id.radio_freq_60)RadioButton radio_freq_60;
    @Bind(R.id.radio_voltase_110)RadioButton radio_voltase_110;
    @Bind(R.id.radio_voltase_220)RadioButton radio_voltase_220;

    @Bind(R.id.ed_l1_connections)EditText ed_l1_connections;
    @Bind(R.id.ed_l2_connections)EditText ed_l2_connections;
    @Bind(R.id.ed_l3_connections)EditText ed_l3_connections;

    @Bind(R.id.ed_l1_loads)EditText ed_l1_loads;
    @Bind(R.id.ed_l2_loads)EditText ed_l2_loads;
    @Bind(R.id.ed_l3_loads)EditText ed_l3_loads;

    @Bind(R.id.ed_running_hours)EditText ed_running_hours;
    @Bind(R.id.ed_condition)EditText ed_condition;

    @Bind(R.id.img_meter_surge)ImageView img_meter_surge;

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

    @OnClick(R.id.btn) void onClick(){
        Intent intent = new Intent(getApplicationContext(), Tech_Generators_Detail_1_Controller.class);

        String manufactur_name = ed_manufactur_name.getText().toString();
        String model = ed_model.getText().toString();
        String phase = "";
        if(tipe_single.isChecked()){
            phase = getResources().getString(R.string.option_phase_single);
        }else{
            phase = getResources().getString(R.string.option_phase_three);
        }

        String remote_start = "";
        if(radio_remote_yes.isChecked()){
            remote_start = getResources().getString(R.string.option_yes_remote);
        }else{
            remote_start = getResources().getString(R.string.option_no_remote);
        }

        String radio_speed = "";
        if(radio_speed_1500.isChecked()){
            radio_speed = getResources().getString(R.string.option_speed_1500);
        }else{
            radio_speed = getResources().getString(R.string.option_speed_1800);
        }

        String radio_freq = "";
        if(radio_freq_50.isChecked()){
            radio_freq = getResources().getString(R.string.option_freq_50);
        }else{
            radio_freq = getResources().getString(R.string.option_freq_60);
        }

        String radio_voltase = "";
        if(radio_voltase_110.isChecked()){
            radio_voltase = getResources().getString(R.string.option_voltase_110);
        }else{
            radio_voltase = getResources().getString(R.string.option_voltase_220);
        }

        String primary_rating = ed_primary_rating.getText().toString();
        String standby_rating = ed_standby_rating.getText().toString();

        String l1_connections = ed_l1_connections.getText().toString();
        String l2_connections = ed_l2_connections.getText().toString();
        String l3_connections = ed_l3_connections.getText().toString();
        String l1_loads = ed_l1_loads.getText().toString();
        String l2_loads = ed_l2_loads.getText().toString();
        String l3_loads = ed_l3_loads.getText().toString();
        String running_hours = ed_running_hours.getText().toString();
        String condition = ed_condition.getText().toString();

        intent.putExtra("manufactur_name",manufactur_name);
        intent.putExtra("model",model);
        intent.putExtra("phase",phase);
        intent.putExtra("remote_start",remote_start);
        intent.putExtra("primary_rating",primary_rating);
        intent.putExtra("standby_rating",standby_rating);

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

        intent.putExtra("url_file_generator",url_file00);

        startActivity(intent);
        finish();
    }

    @OnClick(R.id.img_meter_surge)
    void OnClickIMG() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_generator);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Generator Detail");
        ac.setDisplayHomeAsUpEnabled(true);
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
                Glide.with(getApplicationContext()).load(url_file00).asBitmap().into(img_meter_surge);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }
}
