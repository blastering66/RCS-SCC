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

    @Bind(R.id.img)ImageView img;
    @Bind(R.id.img_rating)ImageView img_rating;

    SharedPreferences spf;
    String id_site;
    String url_file00,url_file01;
    File sourceFile00, sourceFile01;
    RequestBody body00, body01;

    Activity activity;
    boolean isSukses = false;
    String message = "";

    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.img)
    void OnClickIMG() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }
    @OnClick(R.id.img_rating)
    void OnClickIMGRating() {
        startActivityForResult(new Intent(this, CameraCapture.class), 112);
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
}
