package id.ols.sitecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by macbook on 4/3/16.
 */
public class MainActivity_Button extends AppCompatActivity implements View.OnClickListener {
    Button btn_generator, btn_battery, btn_earthing, btn_dc_power, btn_ran, btn_microwave, btn_ip,
            btn_dwdm, btn_superwifi, btn_mpls, btn_vsat, btn_midi, btn_gpon, btn_coolingcabinet, btn_additional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);

        btn_generator = (Button) findViewById(R.id.btn_generator);
        btn_battery = (Button) findViewById(R.id.btn_battery);
        btn_earthing = (Button) findViewById(R.id.btn_earthing);
        btn_dc_power = (Button) findViewById(R.id.btn_dc_power);
        btn_ran = (Button) findViewById(R.id.btn_ran);
        btn_microwave = (Button) findViewById(R.id.btn_microwave);
        btn_ip = (Button) findViewById(R.id.btn_ip);
        btn_dwdm = (Button) findViewById(R.id.btn_dwdm);
        btn_superwifi = (Button) findViewById(R.id.btn_superwifi);
        btn_mpls = (Button) findViewById(R.id.btn_mpls);
        btn_vsat = (Button) findViewById(R.id.btn_vsat);
        btn_midi = (Button) findViewById(R.id.btn_midi);
        btn_gpon = (Button) findViewById(R.id.btn_gpon);
        btn_coolingcabinet = (Button) findViewById(R.id.btn_coolingcabinet);
        btn_additional = (Button) findViewById(R.id.btn_additional);

        btn_generator.setOnClickListener(this);
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
                Intent intentTech_Generators = new Intent(getApplicationContext(), Tech_Generators.class);
                startActivity(intentTech_Generators);
                break;
            case R.id.btn_battery:
                Intent intentTech_Battery = new Intent(getApplicationContext(), Tech_Battery.class);
                startActivity(intentTech_Battery);
                break;
            case R.id.btn_earthing:
                Intent intentTech_Earthing = new Intent(getApplicationContext(), Tech_Earthing.class);
                startActivity(intentTech_Earthing);
                break;
            case R.id.btn_dc_power:
                Intent intentTech_DCPower= new Intent(getApplicationContext(), Tech_DCPower.class);
                startActivity(intentTech_DCPower);
                break;
            case R.id.btn_ran:
                Intent intentTech_RAN= new Intent(getApplicationContext(), Tech_RAN.class);
                startActivity(intentTech_RAN);
                break;
            case R.id.btn_microwave:
                Intent intentTech_Microwave =new Intent(getApplicationContext(), Tech_Microwave.class);
                startActivity(intentTech_Microwave);
                break;
            case R.id.btn_ip:
                Intent intentTech_IP= new Intent(getApplicationContext(), Tech_IP.class);
                startActivity(intentTech_IP);
                break;
            case R.id.btn_dwdm:
                Intent intentTech_DWDM= new Intent(getApplicationContext(), Tech_DWDM.class);
                startActivity(intentTech_DWDM);
                break;
            case R.id.btn_superwifi:
                Intent intentTech_SuperWifi= new Intent(getApplicationContext(), Tech_SuperWifi.class);
                startActivity(intentTech_SuperWifi);
                break;
            case R.id.btn_mpls:
                Intent intentTech_Mpls= new Intent(getApplicationContext(), Tech_Mpls.class);
                startActivity(intentTech_Mpls);
                break;
            case R.id.btn_vsat:
                Intent intentTech_Vsat= new Intent(getApplicationContext(), Tech_Vsat.class);
                startActivity(intentTech_Vsat);
                break;
            case R.id.btn_midi:
                Intent intentTech_Midi= new Intent(getApplicationContext(), Tech_Midi.class);
                startActivity(intentTech_Midi);
                break;
            case R.id.btn_gpon:
                Intent intentTech_Gpon= new Intent(getApplicationContext(), Tech_Gpon.class);
                startActivity(intentTech_Gpon);
                break;
            case R.id.btn_coolingcabinet:
                Intent intentTech_Cooling= new Intent(getApplicationContext(), Tech_Cooling.class);
                startActivity(intentTech_Cooling);
                break;
            case R.id.btn_additional:
                Intent intentTech_AdditionalLoads= new Intent(getApplicationContext(), Tech_AdditionalLoads.class);
                startActivity(intentTech_AdditionalLoads);
                break;
        }

    }
}