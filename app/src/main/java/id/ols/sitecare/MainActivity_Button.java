package id.ols.sitecare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.ols.util.ParameterCollections;

/**
 * Created by macbook on 4/3/16.
 */
public class MainActivity_Button extends AppCompatActivity implements View.OnClickListener {
    View btn_generator,btn_grid, btn_battery, btn_earthing, btn_dc_power, btn_ran, btn_microwave, btn_ip,
            btn_dwdm, btn_superwifi, btn_mpls, btn_vsat, btn_midi, btn_gpon, btn_coolingcabinet, btn_additional;

    @Bind({R.id.img_done_generator, R.id.img_done_grid, R.id.img_done_battery, R.id.img_done_earthing,
    R.id.img_done_dc, R.id.img_done_ran, R.id.img_done_microwave, R.id.img_done_ip,R.id.img_done_dwdm,
            R.id.img_done_superwifi, R.id.img_done_mpls, R.id.img_done_vsat, R.id.img_done_midi,
            R.id.img_done_gpon, R.id.img_done_cooling, R.id.img_done_additional})
    List<ImageView> images_Done;
    SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        spf = getSharedPreferences(ParameterCollections.SH_NAME, MODE_PRIVATE);
        spf.edit().clear().commit();

        btn_generator = (View) findViewById(R.id.btn_generator);
        btn_grid = (View) findViewById(R.id.btn_grid);
        btn_battery = (View) findViewById(R.id.btn_battery);
        btn_earthing = (View) findViewById(R.id.btn_earthing);
        btn_dc_power = (View) findViewById(R.id.btn_dc_power);
        btn_ran = (View) findViewById(R.id.btn_ran);
        btn_microwave = (View) findViewById(R.id.btn_microwave);
        btn_ip = (View) findViewById(R.id.btn_ip);
        btn_dwdm = (View) findViewById(R.id.btn_dwdm);
        btn_superwifi = (View) findViewById(R.id.btn_superwifi);
        btn_mpls = (View) findViewById(R.id.btn_mpls);
        btn_vsat = (View) findViewById(R.id.btn_vsat);
        btn_midi = (View) findViewById(R.id.btn_midi);
        btn_gpon = (View) findViewById(R.id.btn_gpon);
        btn_coolingcabinet = (View) findViewById(R.id.btn_coolingcabinet);
        btn_additional = (View) findViewById(R.id.btn_additional);

        btn_generator.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_battery.setOnClickListener(this);
        btn_earthing.setOnClickListener(this);
        btn_dc_power.setOnClickListener(this);
        btn_ran.setOnClickListener(this);
        btn_microwave.setOnClickListener(this);
        btn_ip.setOnClickListener(this);
        btn_dwdm.setOnClickListener(this);
        btn_superwifi.setOnClickListener(this);
        btn_mpls.setOnClickListener(this);
        btn_vsat.setOnClickListener(this);
        btn_midi.setOnClickListener(this);
        btn_gpon.setOnClickListener(this);
        btn_coolingcabinet.setOnClickListener(this);
        btn_additional.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_generator:
                boolean generator_submited = spf.getBoolean(ParameterCollections.SH_GENERATOR_SUBMITTED, false);

                if(!generator_submited){

                }else{

                }
                Intent intentTech_Generators = new Intent(getApplicationContext(), Tech_Generators.class);
                startActivityForResult(intentTech_Generators, 99);

                break;
            case R.id.btn_grid:
                Intent intentTech_Grid = new Intent(getApplicationContext(), Tech_Grid.class);
                startActivityForResult(intentTech_Grid, 1);
                break;
            case R.id.btn_battery:
                Intent intentTech_Battery = new Intent(getApplicationContext(), Tech_Battery.class);
                startActivityForResult(intentTech_Battery, 2);
                break;
            case R.id.btn_earthing:
                Intent intentTech_Earthing = new Intent(getApplicationContext(), Tech_Earthing.class);
                startActivityForResult(intentTech_Earthing, 3);
                break;
            case R.id.btn_dc_power:
                Intent intentTech_DCPower= new Intent(getApplicationContext(), Tech_DCPower.class);
                startActivityForResult(intentTech_DCPower, 4);
                break;
            case R.id.btn_ran:
                Intent intentTech_RAN= new Intent(getApplicationContext(), Tech_RAN.class);
                startActivityForResult(intentTech_RAN, 5);
                break;
            case R.id.btn_microwave:
                Intent intentTech_Microwave =new Intent(getApplicationContext(), Tech_Microwave.class);
                startActivityForResult(intentTech_Microwave, 6);
                break;
            case R.id.btn_ip:
                Intent intentTech_IP= new Intent(getApplicationContext(), Tech_IP.class);
                startActivityForResult(intentTech_IP, 7);
                break;
            case R.id.btn_dwdm:
                Intent intentTech_DWDM= new Intent(getApplicationContext(), Tech_DWDM.class);
                startActivityForResult(intentTech_DWDM, 8);
                break;
            case R.id.btn_superwifi:
                Intent intentTech_SuperWifi= new Intent(getApplicationContext(), Tech_SuperWifi.class);
                startActivityForResult(intentTech_SuperWifi, 9);
                break;
            case R.id.btn_mpls:
                Intent intentTech_Mpls= new Intent(getApplicationContext(), Tech_Mpls.class);
                startActivityForResult(intentTech_Mpls, 10);
                break;
            case R.id.btn_vsat:
                Intent intentTech_Vsat= new Intent(getApplicationContext(), Tech_Vsat.class);
                startActivityForResult(intentTech_Vsat, 11);
                break;
            case R.id.btn_midi:
                Intent intentTech_Midi= new Intent(getApplicationContext(), Tech_Midi.class);
                startActivityForResult(intentTech_Midi, 12);
                break;
            case R.id.btn_gpon:
                Intent intentTech_Gpon= new Intent(getApplicationContext(), Tech_Gpon.class);
                startActivityForResult(intentTech_Gpon, 13);
                break;
            case R.id.btn_coolingcabinet:
                Intent intentTech_Cooling= new Intent(getApplicationContext(), Tech_Cooling.class);
                startActivityForResult(intentTech_Cooling, 14);
                break;
            case R.id.btn_additional:
                Intent intentTech_AdditionalLoads= new Intent(getApplicationContext(), Tech_AdditionalLoads.class);
                startActivityForResult(intentTech_AdditionalLoads, 15);
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean generator_submited = spf.getBoolean(ParameterCollections.SH_GENERATOR_SUBMITTED, false);

        if(generator_submited){
            images_Done.get(0).setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){

            switch (requestCode){
                case 1:
                    images_Done.get(1).setVisibility(View.VISIBLE);
                    break;
                case 2:
                    images_Done.get(2).setVisibility(View.VISIBLE);
                    break;
                case 3:
                    images_Done.get(3).setVisibility(View.VISIBLE);
                    break;
                case 4:
                    images_Done.get(4).setVisibility(View.VISIBLE);
                    break;
                case 5:
                    images_Done.get(5).setVisibility(View.VISIBLE);
                    break;
                case 6:
                    images_Done.get(6).setVisibility(View.VISIBLE);
                    break;
                case 7:
                    images_Done.get(7).setVisibility(View.VISIBLE);
                    break;
                case 8:
                    images_Done.get(8).setVisibility(View.VISIBLE);
                    break;
                case 9:
                    images_Done.get(9).setVisibility(View.VISIBLE);
                    break;
                case 10:
                    images_Done.get(10).setVisibility(View.VISIBLE);
                    break;
                case 11:
                    images_Done.get(11).setVisibility(View.VISIBLE);
                    break;
                case 12:
                    images_Done.get(12).setVisibility(View.VISIBLE);
                    break;
                case 13:
                    images_Done.get(13).setVisibility(View.VISIBLE);
                    break;
                case 14:
                    images_Done.get(14).setVisibility(View.VISIBLE);
                    break;
                case 15:
                    images_Done.get(15).setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}