package id.ols.sitecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by macbook on 3/27/16.
 */
public class EngineerDetail extends AppCompatActivity {
    @Bind(R.id.ed_username)
    EditText ed_username;
    @Bind(R.id.ed_email)
    EditText ed_email;
    @Bind(R.id.ed_phone)
    EditText ed_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineerdetail);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Engineer Details");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_next, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_next:
                if (!ed_username.getText().toString().equals("") &&
                        !ed_email.getText().toString().equals("") &&
                        !ed_phone.getText().toString().equals("")) {
//                    Intent intent = new Intent(getApplicationContext(), SiteDetail.class);
                    Intent intent = new Intent(getApplicationContext(), SiteDetail_New.class);
                    intent.putExtra("site_nameenginer", ed_username.getText().toString());
                    intent.putExtra("site_emailenginer", ed_email.getText().toString());
                    intent.putExtra("site_phoneenginer", ed_phone.getText().toString());
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(getApplicationContext(), "Please Fill All Field", Toast.LENGTH_LONG).show();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
