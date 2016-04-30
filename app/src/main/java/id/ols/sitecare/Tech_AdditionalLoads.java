package id.ols.sitecare;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.blastering99.htmlloader.CustomProgressDialog;
import id.ols.models.PojoResponseInsert;
import id.ols.rest_adapter.API_Adapter;
import id.ols.util.ParameterCollections;
import id.ols.util.PublicFunctions;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.subscriptions.AndroidSubscriptions;
import rx.schedulers.Schedulers;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_AdditionalLoads extends AppCompatActivity {
    @Bind(R.id.tipe_single_awl)
    RadioButton tipe_single_awl;
    @Bind(R.id.tipe_three_awl)
    RadioButton tipe_three_awl;
    @Bind(R.id.tipe_ac_awl)
    RadioButton tipe_ac_awl;
    @Bind(R.id.tipe_dc_awl)
    RadioButton tipe_dc_awl;
    @Bind(R.id.ed_amps_1)
    EditText ed_amps_1_awl;

    @Bind(R.id.tipe_yes_esl)
    RadioButton tipe_yes_esl;
    @Bind(R.id.tipe_no_esl)
    RadioButton tipe_no_esl;
    @Bind(R.id.tipe_ac_esl)
    RadioButton tipe_ac_esl;
    @Bind(R.id.tipe_dc_esl)
    RadioButton tipe_dc_esl;
    @Bind(R.id.ed_amps_2_esl)
    EditText ed_amps_2_esl;

    @Bind(R.id.tipe_yes_ff)
    RadioButton tipe_yes_ff;
    @Bind(R.id.tipe_no_ff)
    RadioButton tipe_no_ff;
    @Bind(R.id.tipe_dc_ff)
    RadioButton tipe_dc_ff;
    @Bind(R.id.tipe_ac_ff)
    RadioButton tipe_ac_ff;
    @Bind(R.id.ed_amps_3_ff)
    EditText ed_amps_3_ff;

    @Bind(R.id.tipe_yes_ss)
    RadioButton tipe_yes_ss;
    @Bind(R.id.tipe_no_ss)
    RadioButton tipe_no_ss;
    @Bind(R.id.tipe_dc_ss)
    RadioButton tipe_dc_ss;
    @Bind(R.id.tipe_ac_ss)
    RadioButton tipe_ac_ss;
    @Bind(R.id.ed_amps_4_ss)
    EditText ed_amps_4_ss;

    @Bind(R.id.tipe_yes_oe)
    RadioButton tipe_yes_oe;
    @Bind(R.id.tipe_no_oe)
    RadioButton tipe_no_oe;
    @Bind(R.id.tipe_dc_oe)
    RadioButton tipe_dc_oe;
    @Bind(R.id.tipe_ac_oe)
    RadioButton tipe_ac_oe;
    @Bind(R.id.ed_amps_5_oe)
    EditText ed_amps_5_oe;

    boolean isSukses = false;
    String message = "";
    Activity activity;
    SharedPreferences spf;
    String id_site;
    String awl_option, awl_current, awl_loads,
            esl_option, esl_current, esl_loads,
            ff_option, ff_current, ff_loads,
            ss_option, ss_current, ss_loads,
            oe_option, oe_current, oe_loads;

    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        final CustomProgressDialog pDialog;
        pDialog = new CustomProgressDialog(activity, R.style.SpotsDialogDefault);
        pDialog.setLoaderType(CustomProgressDialog.SPINNING_SQUARE);
        pDialog.show();

        String apiKey = getResources().getString(R.string.api_key);
        String authKey = spf.getString(ParameterCollections.SH_AUTHKEY,"");

        //AWL
        if(tipe_single_awl.isChecked()){
            awl_option = getResources().getString(R.string.option_yes);
        }else{
            awl_option = getResources().getString(R.string.option_no);
        }
        if(tipe_ac_awl.isChecked()){
            awl_current = getResources().getString(R.string.current_ac);
        }else{
            awl_current = getResources().getString(R.string.current_dc);
        }
        awl_loads = ed_amps_1_awl.getText().toString();

        //ESL
        if(tipe_yes_esl.isChecked()){
            esl_option = getResources().getString(R.string.option_yes);
        }else{
            esl_option = getResources().getString(R.string.option_no);
        }
        if(tipe_ac_esl.isChecked()){
            esl_current = getResources().getString(R.string.current_ac);
        }else{
            esl_current = getResources().getString(R.string.current_dc);
        }
        esl_loads = ed_amps_2_esl.getText().toString();

        //FF
        if(tipe_yes_ff.isChecked()){
            ff_option = getResources().getString(R.string.option_yes);
        }else{
            ff_option = getResources().getString(R.string.option_no);
        }
        if(tipe_ac_ff.isChecked()){
            ff_current = getResources().getString(R.string.current_ac);
        }else{
            ff_current = getResources().getString(R.string.current_dc);
        }
        ff_loads = ed_amps_3_ff.getText().toString();

        //SS
        if(tipe_yes_ss.isChecked()){
            ss_option = getResources().getString(R.string.option_yes);
        }else{
            ss_option = getResources().getString(R.string.option_no);
        }
        if(tipe_ac_ss.isChecked()){
            ss_current = getResources().getString(R.string.current_ac);
        }else{
            ss_current = getResources().getString(R.string.current_dc);
        }
        ss_loads = ed_amps_4_ss.getText().toString();

        //OE
        if(tipe_yes_oe.isChecked()){
            oe_option  = getResources().getString(R.string.option_yes);
        }else{
            oe_option  = getResources().getString(R.string.option_no);
        }
        if(tipe_ac_oe.isChecked()){
            oe_current  = getResources().getString(R.string.current_ac);
        }else{
            oe_current = getResources().getString(R.string.current_dc);
        }
        oe_loads = ed_amps_5_oe.getText().toString();


        final API_Adapter adapter = PublicFunctions.initRetrofit();
        Observable<PojoResponseInsert> observable = adapter.insert_tech_additional_test(apiKey, authKey,
                id_site, awl_option, awl_current, awl_loads, esl_option, esl_current, esl_loads,
                ff_option, ff_current, ff_loads, ss_option,ss_current,ss_loads, oe_option, oe_current,oe_loads);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PojoResponseInsert>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_LONG).show();
                        Log.e("Task", "Complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Something Wrong Please Try Again", Toast.LENGTH_LONG).show();
                        Log.e("Task", "Error");
                    }

                    @Override
                    public void onNext(PojoResponseInsert pojoResponseInsert) {
                        if (pojoResponseInsert.getJsonCode() == 1) {
                            Log.e("Sukses", "");
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_additional);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("Additional Loads");
        ac.setDisplayHomeAsUpEnabled(true);

        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
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
}

