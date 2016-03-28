package id.ols.sitecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONObject;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ols.util.*;

public class LoginForm extends AppCompatActivity {

    private String user_id, user_email, user_fullname, user_foto, user_gender;
    @Bind(R.id.ed_username)
    EditText ed_Username;
    @Bind(R.id.ed_password) EditText ed_Password;
    @Bind(R.id.btn)
    Button btn;

    @OnClick(R.id.btn) public void click(){
        startActivity(new Intent(getApplicationContext(), EngineerDetail.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
