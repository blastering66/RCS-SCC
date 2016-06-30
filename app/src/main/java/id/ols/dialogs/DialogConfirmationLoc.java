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

import id.ols.sitecare.LoginForm;
import id.ols.sitecare.MainActivity_Button;
import id.ols.sitecare.R;
import id.ols.sitecare.SiteDetail_New;
import id.ols.sitecare.Tech_AdditionalLoads;
import id.ols.sitecare.Tech_Battery;
import id.ols.sitecare.Tech_Cooling;
import id.ols.sitecare.Tech_DCPower;
import id.ols.sitecare.Tech_DWDM;
import id.ols.sitecare.Tech_Earthing;
import id.ols.sitecare.Tech_Generators;
import id.ols.sitecare.Tech_Gpon;
import id.ols.sitecare.Tech_Grid;
import id.ols.sitecare.Tech_IP;
import id.ols.sitecare.Tech_Microwave;
import id.ols.sitecare.Tech_Midi;
import id.ols.sitecare.Tech_Mpls;
import id.ols.sitecare.Tech_RAN;
import id.ols.sitecare.Tech_SuperWifi;
import id.ols.sitecare.Tech_Vsat;
import id.ols.util.ParameterCollections;

/**
 * Created by macbook on 3/28/16.
 */
public class DialogConfirmationLoc extends DialogFragment {
    private SharedPreferences sp;
    Context context;
    String text;
    int from;


    public void setSh(SharedPreferences sh) {
        this.sp = sh;
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
                cek();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
                getActivity().finish();
            }
        });


        return builder.create();
    }

    public void cek(){
        boolean isLogged = sp.getBoolean(ParameterCollections.SH_LOGGED, false);
        boolean isVisitFinished = sp.getBoolean(ParameterCollections.SH_VISIT_FINISHED, false);
        boolean isSiteInputed = sp.getBoolean(ParameterCollections.SH_SITEVISIT_INPUTED, false);

        if(isLogged){

            if(isVisitFinished){
                startActivity(new Intent(context, SiteDetail_New.class));
                getActivity().finish();
            }else{

                if(isSiteInputed){
                    startActivity(new Intent(context, MainActivity_Button.class));
                    getActivity().finish();
                }else{
                    startActivity(new Intent(context, SiteDetail_New.class));
                    getActivity().finish();

                }
            }

        }else{
            startActivity(new Intent(context, LoginForm.class));
            getActivity().finish();
        }

    }
}
