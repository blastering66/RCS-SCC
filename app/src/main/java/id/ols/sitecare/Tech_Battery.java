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
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_Battery extends AppCompatActivity{
    @Bind(R.id.ed_manufactur_name)EditText ed_manufactur_name;
    @Bind(R.id.ed_model)EditText ed_model;
    @Bind(R.id.ed_jumlah_2v)EditText ed_jumlah_2v;
    @Bind(R.id.ed_jumlah_block)EditText ed_jumlah_block;
    @Bind(R.id.ed_capacity)EditText ed_capacity;
    @Bind(R.id.ed_voltase_disconnected)EditText ed_voltase_disconnected;
    @Bind(R.id.ed_temprature)EditText ed_temprature;
    @Bind(R.id.ed_condition)EditText ed_condition;

    @Bind(R.id.radio_volt_2)RadioButton radio_volt_2;
    @Bind(R.id.radio_volt_6)RadioButton radio_volt_6;
    @Bind(R.id.radio_volt_12)RadioButton radio_volt_12;
    @Bind(R.id.radio_volt_48)RadioButton radio_volt_48;

    @Bind(R.id.img)ImageView img;

    Activity activity;
    SharedPreferences spf;
    String mUrl_Img_00;
    String url_file00;
    File sourceFile00;
    RequestBody body00;

    boolean isSukses = false;
    String message = "";
    String id_site;


    @OnClick(R.id.img)
    void OnClickIMG() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }

    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        String manufactur_name = ed_manufactur_name.getText().toString();
        String model = ed_model.getText().toString();
        String jumlah_2v = ed_jumlah_2v.getText().toString();
        String jumlah_block = ed_jumlah_block.getText().toString();
        String capacity = ed_capacity.getText().toString();
        String voltase_disconnected = ed_voltase_disconnected.getText().toString();
        String temprature = ed_temprature.getText().toString();
        String condition = ed_condition.getText().toString();

        String voltase = "";
        if(radio_volt_2.isChecked()){
            voltase = "2V";
        }else if(radio_volt_6.isChecked()){
            voltase = "6V";
        }else if(radio_volt_12.isChecked()){
            voltase = "12V";
        }else{
            voltase = "48V";
        }

        final CustomProgressDialog pDialog;
        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        RequestBody _siteidvisit = RequestBody.create(MediaType.parse("text/plain"), id_site);
        RequestBody _manufactur_name = RequestBody.create(MediaType.parse("text/plain"), manufactur_name);
        RequestBody _model = RequestBody.create(MediaType.parse("text/plain"), model);
        RequestBody _jumlah_2v = RequestBody.create(MediaType.parse("text/plain"), jumlah_2v);
        RequestBody _jumlah_block = RequestBody.create(MediaType.parse("text/plain"), jumlah_block);
        RequestBody _capacity = RequestBody.create(MediaType.parse("text/plain"), capacity);
        RequestBody _voltase_disconnected = RequestBody.create(MediaType.parse("text/plain"), voltase_disconnected);
        RequestBody _temprature = RequestBody.create(MediaType.parse("text/plain"), temprature);
        RequestBody _condition = RequestBody.create(MediaType.parse("text/plain"), condition);
        RequestBody _voltase = RequestBody.create(MediaType.parse("text/plain"), voltase);

        Retrofit retrofit_test = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ParameterCollections.URL_BASE).build();
        API_Adapter adapter = retrofit_test.create(API_Adapter.class);

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        if(url_file00 != null){
            sourceFile00 = new File(url_file00);
            body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);
        }


        Observable<PojoResponseInsert> observable = adapter.insert_tech_battery(apikey,authkey,ParameterCollections.EXE.INSERT,
                ParameterCollections.KIND.MOBILE,ParameterCollections.KIND.TECH_BATTERY,_siteidvisit,_manufactur_name,
                _model,_voltase,_jumlah_2v,_jumlah_block,_capacity,_voltase_disconnected,_temprature,_condition,body00);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();
                        if (isSukses) {
                            DialogConfirmation pDialog_comfirm = new DialogConfirmation();
                            pDialog_comfirm.setContext(getApplicationContext());
                            pDialog_comfirm.setText("Add Additional Battery");
                            pDialog_comfirm.setFrom(1);

                            pDialog_comfirm.show(getSupportFragmentManager(), "");
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed! Error = " + message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error = ", "Something Wrong");
                        Toast.makeText(getApplicationContext(), "Timeout,Please Try Again.", Toast.LENGTH_LONG).show();

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
        setContentView(R.layout.fragment_battery);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Battery");
        ac.setDisplayHomeAsUpEnabled(true);

        activity = this;

        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
        id_site = spf.getString(ParameterCollections.SH_ID_SITE, "0");
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
}
