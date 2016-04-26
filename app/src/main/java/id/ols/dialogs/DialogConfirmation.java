package id.ols.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import id.ols.sitecare.R;
import id.ols.sitecare.Tech_RAN;

/**
 * Created by macbook on 3/28/16.
 */
public class DialogConfirmation extends DialogFragment{
    private SharedPreferences sh;
    Context context;
    String text;
    int from;


    public void setSh(SharedPreferences sh) {
        this.sh = sh;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyLocationDialogTheme);
        builder.setMessage(text);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if(from == 0){

                }else if(from == 1){
                    Intent intent = new Intent(context, Tech_RAN.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                }else if(from == 9){

                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
                dismiss();
            }
        });

        return builder.create();
    }
}
