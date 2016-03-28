package id.ols.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.ols.fragment.Generator;
import id.ols.models.RowData_Slider;
import id.ols.sitecare.R;

/**
 * Created by macbook on 3/28/16.
 */
public class RecyclerAdapter_Slider extends RecyclerView.Adapter<RecyclerAdapter_Slider.ViewHolder>{
    public Activity activity;
    public FragmentManager fm;
    public List<RowData_Slider> data;
    public DrawerLayout drawer;

    public RecyclerAdapter_Slider(Activity activity, FragmentManager fm,DrawerLayout drawer,List<RowData_Slider> data) {
        this.activity = activity;
        this.data = data;
        this.fm = fm;
        this.drawer = drawer;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RowData_Slider item = data.get(position);
        if(item.done){
            holder.img.setImageResource(R.drawable.img_checkbox_true);
        }else{
//            holder.img.setVisibility(View.INVISIBLE);
            holder.img.setImageResource(R.drawable.img_checkbox_false);
        }
        holder.tv.setText(item.title);
        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm.beginTransaction().addToBackStack("0").replace(R.id.frame_container, new Generator()).commit();
                drawer.closeDrawer(Gravity.START);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false);
        ViewHolder holder = new ViewHolder(v, this.activity);
        return holder;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public ImageView img;
        public View wrapper;
        public Activity activity;

        public ViewHolder(View v, Activity activity) {
            super(v);
            tv = (TextView)v.findViewById(R.id.tv_title);
            img = (ImageView)v.findViewById(R.id.img_checkbox);
            wrapper = (View)v.findViewById(R.id.wrapper);
            this.activity = activity;
        }

    }
}
