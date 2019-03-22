package com.prueba.utilidades2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class FireMissilesDialogFragment extends DialogFragment {
    Double result;
   public FireMissilesDialogFragment(){

   }
   @SuppressLint("ValidFragment")
   public  FireMissilesDialogFragment(Double resultado){
        result = resultado;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Calculos Terminados, tus Calorias Recomendadas son : " + result +" Kcal" + "\n" + " Para bajar medio kilo deberias consumir estas calorias semanalmente " + String.valueOf(result-500) + " Kcal"  + "\n" + " Para Bajar 1 kilo a la semana tienes que consumir estas calorias " + String.valueOf(result-1000) + " KCal")
                .setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
        // Create the AlertDialog object and return it

    }

}
