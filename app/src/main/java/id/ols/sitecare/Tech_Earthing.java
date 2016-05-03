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
import android.widget.ImageView;

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
public class Tech_Earthing extends AppCompatActivity {
    @Bind(R.id.img_busbar) ImageView img_busbar;
    @Bind(R.id.img_earthruns) ImageView img_earthruns;
    @Bind(R.id.img_connections)
    ImageView img_connections;

    SharedPreferences spf;
    String id_site;
    String url_file00,url_file01,url_file02,url_file03;
    File sourceFile00, sourceFile01,sourceFile02, sourceFile03;
    RequestBody body00, body01,body02, body03;

    Activity activity;
    boolean isSukses = false;
    String message = "";
    CustomProgressDialog  pDialog;

    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        RequestBody idsitevisit = RequestBody.create(MediaType.parse("text/plain"), id_site);

        sourceFile01 = new File(url_file01);
        body01 = RequestBody.create(MediaType.parse("image/*"), sourceFile01);

        sourceFile02 = new File(url_file02);
        body02 = RequestBody.create(MediaType.parse("image/*"), sourceFile02);

        sourceFile03 = new File(url_file03);
        body03 = RequestBody.create(MediaType.parse("image/*"), sourceFile03);

        API_Adapter adapter = PublicFunctions.initRetrofit();
        final String apikey = getResources().getString(R.string.api_key);
        final String authkey = spf.getString(ParameterCollections.SH_AUTHKEY, "");

        Observable<PojoResponseInsert> observable = adapter.insert_tech_earthing_grounding(apikey,authkey,idsitevisit,body01,
                body02,body03);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();

                        DialogConfirmation pDialog_comfirm = new DialogConfirmation();
                        pDialog_comfirm.setContext(getApplicationContext());
                        pDialog_comfirm.setText("Add Additional Earthing");
                        pDialog_comfirm.setFrom(3);
                        pDialog_comfirm.setSh(spf);
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


    @OnClick(R.id.img_busbar)
    void OnClickIMGRating() {
        startActivityForResult(new Intent(this, CameraCapture.class), 111);
    }
    @OnClick(R.id.img_earthruns)
    void OnClickIMGMeterSurge() {
        startActivityForResult(new Intent(this, CameraCapture.class), 112);
    }
    @OnClick(R.id.img_connections)
    void OnClickIMGacpdb() {
        startActivityForResult(new Intent(this, CameraCapture.class), 113);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_earthing);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Earthing & Grounding");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 111) {
                url_file01 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file01), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file01).asBitmap().into(img_busbar);
//                img_Site.setImageBitmap(bitmap);
            }
            if (requestCode == 112) {
                url_file02 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file02), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file02).asBitmap().into(img_earthruns);
//                img_Site.setImageBitmap(bitmap);
            }
            if (requestCode == 113) {
                url_file03 = data.getStringExtra("url_img");
                Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(url_file03), 100, 150, true);
                Glide.with(getApplicationContext()).load(url_file03).asBitmap().into(img_connections);
//                img_Site.setImageBitmap(bitmap);
            }
        }
    }

}
