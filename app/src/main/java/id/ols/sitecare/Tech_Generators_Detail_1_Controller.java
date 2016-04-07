package id.ols.sitecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_Generators_Detail_1_Controller extends AppCompatActivity {
    @Bind(R.id.btn)
    Button btn;

    @OnClick(R.id.btn) void onClick(){
        startActivity(new Intent(getApplicationContext(), Tech_Generators_Detail_2_General.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_generator_detail_controller);
        ButterKnife.bind(this);
    }
}
