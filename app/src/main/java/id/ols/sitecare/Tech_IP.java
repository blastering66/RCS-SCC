package id.ols.sitecare;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by macbook on 4/3/16.
 */
public class Tech_IP extends AppCompatActivity {
    @Bind(R.id.btn)
    Button btn;
    @OnClick(R.id.btn) void sendData(){
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ip);
        ButterKnife.bind(this);
        ActionBar ac = getSupportActionBar();
        ac.setTitle("IP");
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
