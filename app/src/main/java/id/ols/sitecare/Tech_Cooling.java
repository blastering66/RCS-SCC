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
public class Tech_Cooling extends AppCompatActivity {
    @Bind(R.id.ed_cooling_name)EditText ed_cooling_name;
    @Bind(R.id.ed_cooling_model)EditText ed_cooling_model;
    @Bind(R.id.ed_rating)EditText ed_rating;
    @Bind(R.id.ed_dc_loads)EditText ed_dc_loads;
    @Bind(R.id.ed_condition)EditText ed_condition;
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
        String cooling_name = ed_cooling_name.getText().toString();
        String cooling_model = ed_cooling_model.getText().toString();
        String rating = ed_rating.getText().toString();
        String dc_loads = ed_dc_loads.getText().toString();
        String condition = ed_condition.getText().toString();

        final CustomProgressDialog pDialog;
        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        RequestBody _siteidvisit = RequestBody.create(MediaType.parse("text/plain"), id_site);
        RequestBody _cooling_name = RequestBody.create(MediaType.parse("text/plain"), cooling_name);
        RequestBody _cooling_model = RequestBody.create(MediaType.parse("text/plain"), cooling_model);
        RequestBody _rating = RequestBody.create(MediaType.parse("text/plain"), rating);
        RequestBody _dc_loads = RequestBody.create(MediaType.parse("text/plain"), dc_loads);
        RequestBody _condition = RequestBody.create(MediaType.parse("text/plain"), condition);

        API_Adapter adapter = PublicFunctions.initRetrofit();

        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        if(url_file00 != null){
            sourceFile00 = new File(url_file00);
            body00 = RequestBody.create(MediaType.parse("image/*"), sourceFile00);
        }


        Observable<PojoResponseInsert> observable = adapter.insert_tech_coolingcabinet(apikey,authkey,
                ParameterCollections.EXE.INSERT,ParameterCollections.KIND.MOBILE,ParameterCollections.KIND.TECH_COOLING,
                _siteidvisit,_cooling_name,_cooling_model,_rating,_dc_loads,_condition,body00);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();
                        if (isSukses) {
                            DialogConfirmation pDialog_comfirm = new DialogConfirmation();
                            pDialog_comfirm.setContext(getApplicationContext());
                            pDialog_comfirm.setText("Add Additional Cooling Cabinet");
                            pDialog_comfirm.setFrom(14);

                            pDialog_comfirm.show(getSupportFragmentManager(), "");
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed! Error = " + message, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error = " , "Something Wrong");
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
        setContentView(R.layout.fragment_cooling);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Cooling Cabinet");
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
