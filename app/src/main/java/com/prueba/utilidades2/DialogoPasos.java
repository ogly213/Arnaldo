package com.prueba.utilidades2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogoPasos  extends DialogFragment  {
    String Fecha;
    Double GrasasQuemadas;
    public DialogoPasos(){

    }
    @SuppressLint("ValidFragment")
    public DialogoPasos(String fecha,Double GrasasQuemadas){
        Fecha = fecha;
        this.GrasasQuemadas = GrasasQuemadas;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("El dia " + Fecha + " Quemaste Caminando Estas Calorias " + GrasasQuemadas + " Kcal" )
                .setPositiveButton("Vale", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}
