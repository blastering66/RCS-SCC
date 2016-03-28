package id.ols.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.ols.dialogs.DialogConfirmation;
import id.ols.dialogs.DialogFragmentProgress;
import id.ols.sitecare.R;

/**
 * Created by macbook on 3/27/16.
 */
public class RAN extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_generator, null);

        Button btn = (Button)v.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Asynctask_SaveData().execute();
            }
        });
        return v;
    }

    private class Asynctask_SaveData extends AsyncTask<Void,Void,Void> {
        DialogFragmentProgress pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new DialogFragmentProgress();
            pDialog.show(getFragmentManager(),"");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(2000);
            }catch (Exception e){

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();
            getFragmentManager().popBackStack();

            DialogConfirmation dialog = new DialogConfirmation();
            dialog.setText("Does This Have Addtional Grids connection to ADD ?");
            dialog.show(getFragmentManager(),"");

        }
    }
}
