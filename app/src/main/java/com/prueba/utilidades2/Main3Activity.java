package com.prueba.utilidades2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {
     String [] Capacidad;
     ListView listView;
     String  itemValue;
     double GrasasQuemadas;
    int i = 0;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        CargarAnuncio();

        BaseDeDatos administrador = new BaseDeDatos(this,"pasoshechosf",null,1);
        listView = findViewById(R.id.Lista);

        SQLiteDatabase basededatos = administrador.getWritableDatabase();
        Cursor fila = basededatos.rawQuery("select * from pasoshechosf" , null);
        Cursor count= basededatos.rawQuery("SELECT COUNT(*) FROM pasoshechosf", null);
        count.moveToFirst();
        int itemsCount= count.getInt(0);
        count.close();
        Capacidad = new String[itemsCount];
        if (fila.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

            Capacidad[i] = fila.getString(0) + " Pasos el dia " + fila.getString(1);

            i++;
            } while(fila.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, Capacidad);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                 String [] hola = Capacidad[position].split("P");
                 String [] fecha = hola[1].split("dia");
                 itemValue    = hola[0];
                 GrasasQuemadas = (Double.parseDouble(itemValue) * 450 )/10000;
                DialogFragment newFragment = new DialogoPasos(fecha[1],GrasasQuemadas);
                newFragment.show(getSupportFragmentManager(), "missiles");
                MostrarAnuncio();
                CargarAnuncio();

            }

        });
    }
}
    private void MostrarAnuncio() {
        mInterstitialAd.show();
    }


    private void CargarAnuncio(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

}
