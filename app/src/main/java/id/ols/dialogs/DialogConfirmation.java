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
                if (from == 0) {
                    Intent intent = new Intent(context, Tech_Generators.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 1) {
                    Intent intent = new Intent(context, Tech_Grid.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 2) {
                    Intent intent = new Intent(context, Tech_Battery.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 3) {
                    Intent intent = new Intent(context, Tech_Earthing.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 4) {
                    Intent intent = new Intent(context, Tech_DCPower.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 5) {
                    Intent intent = new Intent(context, Tech_RAN.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 6) {
                    Intent intent = new Intent(context, Tech_Microwave.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 7) {
                    Intent intent = new Intent(context, Tech_IP.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 8) {
                    Intent intent = new Intent(context, Tech_DWDM.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 9) {
                    Intent intent = new Intent(context, Tech_SuperWifi.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 10) {
                    Intent intent = new Intent(context, Tech_Mpls.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 11) {
                    Intent intent = new Intent(context, Tech_Vsat.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 12) {
                    Intent intent = new Intent(context, Tech_Midi.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 13) {
                    Intent intent = new Intent(context, Tech_Gpon.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 14) {
                    Intent intent = new Intent(context, Tech_Cooling.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();
                } else if (from == 15) {
                    Intent intent = new Intent(context, Tech_AdditionalLoads.class);
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    startActivity(intent);
                    getActivity().finish();

                } else if (from == 98) {
                    sh.edit().clear().commit();
                    getActivity().finish();

                } else if (from == 99) {
                    sh.edit().putBoolean(ParameterCollections.SH_VISIT_FINISHED, true).commit();

                    sh.edit().putBoolean(ParameterCollections.SH_GRID_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_BATTERY_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_EARTH_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_DCPOWER_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_RAN_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_MICROWAVE_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_IP_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_DWDM_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_SUPERWIFI_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_MPLS_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_VSAT_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_MIDI_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_GPON_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_COOLINGCABINET_SUBMITTED, false).commit();
                    sh.edit().putBoolean(ParameterCollections.SH_ADDITIONAL_SUBMITTED, false).commit();
                    getActivity().finish();

                }

            }
        });


        if(from == 99){
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });

        }else if(from == 98){
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });
        } else{
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(from == 0){
                        sh.edit().putBoolean(ParameterCollections.SH_GENERATOR_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    }else if (from == 1) {
                        sh.edit().putBoolean(ParameterCollections.SH_GRID_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 2) {
                        sh.edit().putBoolean(ParameterCollections.SH_BATTERY_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 3) {
                        sh.edit().putBoolean(ParameterCollections.SH_EARTH_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 4) {
                        sh.edit().putBoolean(ParameterCollections.SH_DCPOWER_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 5) {
                        sh.edit().putBoolean(ParameterCollections.SH_RAN_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 6) {
                        sh.edit().putBoolean(ParameterCollections.SH_MICROWAVE_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 7) {
                        sh.edit().putBoolean(ParameterCollections.SH_IP_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 8) {
                        sh.edit().putBoolean(ParameterCollections.SH_DWDM_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 9) {
                        sh.edit().putBoolean(ParameterCollections.SH_SUPERWIFI_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 10) {
                        sh.edit().putBoolean(ParameterCollections.SH_MPLS_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 11) {
                        sh.edit().putBoolean(ParameterCollections.SH_VSAT_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 12) {
                        sh.edit().putBoolean(ParameterCollections.SH_MIDI_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 13) {
                        sh.edit().putBoolean(ParameterCollections.SH_GPON_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 14) {
                        sh.edit().putBoolean(ParameterCollections.SH_COOLINGCABINET_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();
                    } else if (from == 15) {
                        sh.edit().putBoolean(ParameterCollections.SH_ADDITIONAL_SUBMITTED, true).commit();
                        getActivity().setResult(Activity.RESULT_OK);
                        getActivity().finish();
                        dismiss();

                    }


                }
            });
        }



        return builder.create();
    }
}
