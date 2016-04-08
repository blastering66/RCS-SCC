package id.ols.sitecare;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ols.util.ParameterCollections;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_Generators_Detail_2_General extends AppCompatActivity {
    SharedPreferences spf;

    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        spf.edit().putBoolean(ParameterCollections.SH_GENERATOR_SUBMITTED, true).commit();
        setResult(RESULT_OK);
        finish();
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
