package com.prueba.utilidades2;


import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class Main2Activity extends AppCompatActivity  {
    Spinner spinner3,actividades;
    Double valorActividad ;
    EditText Altura,Altura3;
    EditText Peso,peso2;
    EditText edad,edad2;
    Double Altura2;
    Double Peso2;
    Double Edad2;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        CargarAnuncio();
        TabHost tabs = findViewById(R.id.Tablas);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("mi tab 1");
        spec.setContent(R.id.Hombre);
        spec.setIndicator(" Hombre ");
        Altura = findViewById(R.id.editText);
        Peso = findViewById(R.id.editText3);
        edad = findViewById(R.id.editText4);
        Altura3 = findViewById(R.id.Altura);
        peso2 = findViewById(R.id.PesoF);
        edad2 = findViewById(R.id.EdadF);
        TabHost.TabSpec spec2 = tabs.newTabSpec("mi tab 2");
        spec2.setContent(R.id.Mujer);
        spec2.setIndicator(" Mujer ");
        tabs.addTab(spec);
        tabs.addTab(spec2);
        actividades = findViewById(R.id.Actividades);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Tipo_de_Actividad, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actividades.setAdapter(adapter2);
        actividades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0 :
                        valorActividad = 1.2;
                        System.out.println(valorActividad);
                        break;
                    case 1 :
                        valorActividad=1.37;
                        System.out.println(valorActividad);
                        break;
                    case 2 :
                        valorActividad=1.54;
                        System.out.println(valorActividad);
                        break;
                    case 3 :
                        valorActividad=1.72;
                        System.out.println(valorActividad);
                        break;
                    case 4 :
                        valorActividad=1.9;
                        System.out.println(valorActividad);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3 = findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.Tipo_de_Actividad, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0 :
                        valorActividad = 1.2;
                        System.out.println(valorActividad);
                        break;
                    case 1 :
                        valorActividad=1.37;
                        System.out.println(valorActividad);
                        break;
                    case 2 :
                        valorActividad=1.54;
                        System.out.println(valorActividad);
                        break;
                    case 3 :
                        valorActividad=1.72;
                        System.out.println(valorActividad);
                        break;
                    case 4 :
                        valorActividad=1.9;
                        System.out.println(valorActividad);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void Calcular(final View v) {
        MostrarAnuncio();
        CargarAnuncio();
        Altura2 = Double.parseDouble(Altura.getText().toString());
        Peso2 = Double.parseDouble(Peso.getText().toString());
        Edad2 = Double.parseDouble(edad.getText().toString());
        double calculator = (10 * Peso2) + (6.25 * Altura2) -(5 * Edad2) + 5;
        double  mantener = calculator * valorActividad;
        DialogFragment newFragment = new FireMissilesDialogFragment(mantener);
        newFragment.show(getSupportFragmentManager(), "missiles");
        System.out.println(mantener);
    }
    public void CalcularM ( View v){
        MostrarAnuncio();
        CargarAnuncio();
        Altura2 = Double.parseDouble(Altura3.getText().toString());
        Peso2 = Double.parseDouble(peso2.getText().toString());
        Edad2 = Double.parseDouble(edad2.getText().toString());
        double calculator = (10 * Peso2) + (6.25 * Altura2) -(5 * Edad2) - 161;
        double  mantener = calculator * valorActividad;
        DialogFragment newFragment = new FireMissilesDialogFragment(mantener);
        newFragment.show(getSupportFragmentManager(), "missiles");
        System.out.println(mantener);
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
