package com.prueba.utilidades2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.prueba.utilidades2.R;

import static java.lang.Math.round;

public class Main6Activity extends AppCompatActivity {
    EditText edi;
    EditText edi2;
    EditText edi3;
    RadioButton rb;
    RadioButton rb2;
    Button boton;
    Button boton2;
    TextView imc;
    TextView expli;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main6);
        edi =(EditText) findViewById(R.id.cm);
        edi2 =(EditText) findViewById(R.id.kg);
        edi3 =(EditText) findViewById(R.id.edad);
        rb =(RadioButton) findViewById(R.id.radioMale);
        rb2 =(RadioButton) findViewById(R.id.radioFemale);
        boton = (Button) findViewById(R.id.cal);
        boton2 = (Button) findViewById(R.id.res);
        imc = (TextView) findViewById(R.id.txt5);
        expli = (TextView) findViewById(R.id.explicacion);
        CargarAnuncio();
    }

    private void MostrarAnuncio() {
        mInterstitialAd.show();
    }


    private void CargarAnuncio(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }




    public void resetearMasa(View v){
        imc.setText("0.0");
        edi.setText("");
        edi2.setText("");
        edi3.setText("");
        imc.setText("0.0 Kg");
        rb.setChecked(true);
        MostrarAnuncio();
        CargarAnuncio();
    }

    public void calculoMasa(View v){
        MostrarAnuncio();
        CargarAnuncio();
        if (edi.getText().toString().isEmpty()  || edi2.getText().toString().isEmpty() || edi3.getText().toString().isEmpty()  ) {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Por favor rellena los valores de edad, altura y peso", Toast.LENGTH_SHORT);

            toast1.show();


        } else {
            MostrarAnuncio();
            CargarAnuncio();

            float decimal = Float.parseFloat(edi.getText().toString());
            float decimal2 = Float.parseFloat(edi2.getText().toString());
            float metros = decimal / 100;
            float total = decimal2 / (metros * metros);
            float redodear = Math.round(total * 10) / 10.0F;


            if (rb.isChecked() == true) {
                float edad = Float.parseFloat(edi3.getText().toString());
                double grasa = (1.2 * redodear) + (0.23 * edad) - (10.8 * 1) - 5.4;
                float grasafinal = Math.round(grasa * 10) / 10.0F;
                float porcentage = 100 - grasafinal;
                float diferencia  = porcentage /100;
                float diferencia2 = diferencia * decimal2;
                String grasaString = Float.toString(diferencia2);
                imc.setText(grasaString+" Kg");
            }
            if (rb2.isChecked() == true) {
                float edad = Float.parseFloat(edi3.getText().toString());
                double grasa = (1.2 * redodear) + (0.23 * edad) - (10.8 * 0) - 5.4;
                float grasafinal = Math.round(grasa * 10) / 10.0F;
                float porcentage = 100 - grasafinal;
                float diferencia  = porcentage /100;
                float diferencia2 = diferencia * decimal2;
                String grasaString = Float.toString(diferencia2);
                imc.setText(grasaString+" Kg");

            }


        }
    }







}
