package id.ols.sitecare;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import id.ols.adapters.RecyclerAdapter_Slider;
import id.ols.models.RowData_Slider;

/**
 * Created by macbook on 3/27/16.
 */
public class MainActivity extends AppCompatActivity {
    RecyclerView  rv_slider;
    RecyclerView.Adapter  adapter_slider;
    RecyclerView.LayoutManager layoutManager_slider;
    RecyclerView.ItemDecoration decoration;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout drawer;
    Bundle currentState;
    Activity activity;
    List<RowData_Slider> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ac = getSupportActionBar();
        ac.setDisplayShowHomeEnabled(true);
        ac.setHomeButtonEnabled(true);
        ac.setTitle("  Site Care 1.0");
        ac.setIcon(R.mipmap.ic_launcher);
        ac.setLogo(R.mipmap.ic_launcher);

        rv_slider = (RecyclerView) findViewById(R.id.slider_content);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerToggle = new ActionBarDrawerToggle(this,drawer,R.mipmap.ic_launcher,
//                R.string.app_name,R.string.app_name);

        layoutManager_slider = new LinearLayoutManager(this);
        rv_slider.setLayoutManager(layoutManager_slider);

        if (savedInstanceState == null) {
            currentState = savedInstanceState;
            FragmentManager fm = getSupportFragmentManager();
//            Fragment fragment = new Olx_Fragment_MenuUtama();
//            fm.beginTransaction().replace(R.id.frame_container, fragment)
//                    .commit();
            adapter_slider = new RecyclerAdapter_Slider(this,getSupportFragmentManager(), drawer,initDataSlider());
            rv_slider.setAdapter(adapter_slider);
        }

        drawer.openDrawer(Gravity.START);
    }

    private List<RowData_Slider> initDataSlider(){
        data = new ArrayList<RowData_Slider>();
        data.add(new RowData_Slider("0", "Generator", false));
        data.add(new RowData_Slider("0", "Battery", false));
        data.add(new RowData_Slider("0", "Earthing and Grounding", false));
        data.add(new RowData_Slider("0", "DC Power", false));
        data.add(new RowData_Slider("0", "RAN", false));
        data.add(new RowData_Slider("0", "Microwave", false));
        data.add(new RowData_Slider("0", "IP", false));
        data.add(new RowData_Slider("0", "DWDM", false));
        data.add(new RowData_Slider("0", "SuperWifi", false));
        data.add(new RowData_Slider("0", "MPLS", false));
        data.add(new RowData_Slider("0", "VSAT", false));
        data.add(new RowData_Slider("0", "MIDI", false));
        data.add(new RowData_Slider("0", "GPON", false));
        data.add(new RowData_Slider("0", "Cooling Cabinet", false));
        data.add(new RowData_Slider("0", "Additional Loads", false));
        return data;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if(drawer.isDrawerOpen(Gravity.START)){
                    drawer.closeDrawer(Gravity.START);
                }else{
                    drawer.openDrawer(Gravity.START);
                }

                break;
        }
        return false;
    }
}
