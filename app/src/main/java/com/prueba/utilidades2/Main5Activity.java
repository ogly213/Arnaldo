package com.prueba.utilidades2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.InterstitialAd;

import static java.lang.Math.round;import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Main5Activity extends AppCompatActivity {

    EditText edi;
    EditText edi2;
    EditText edi3;
    RadioButton rb;
    RadioButton rb2;
    Button boton;
    Button boton2;
    TextView imc;
    TextView expli;
    TextView grasacorporal;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main5);
        edi =(EditText) findViewById(R.id.cm);
        edi2 =(EditText) findViewById(R.id.kg);
        edi3 =(EditText) findViewById(R.id.edad);
        rb =(RadioButton) findViewById(R.id.radioMale);
        rb2 =(RadioButton) findViewById(R.id.radioFemale);
        CargarAnuncio();
        boton = (Button) findViewById(R.id.cal);
        boton2 = (Button) findViewById(R.id.res);
        imc = (TextView) findViewById(R.id.txt5);
        expli = (TextView) findViewById(R.id.explicacion);
        grasacorporal = (TextView) findViewById(R.id.txt7);

    }






public void resetear(View v){
    MostrarAnuncio();
    imc.setText("0.0");
    edi.setText("");
    edi2.setText("");
    edi3.setText("");
    expli.setText("");
    grasacorporal.setText("0.0%");
    rb.setChecked(true);
    CargarAnuncio();
}

    public void calculoIMC(View v){

        if (edi.getText().toString().isEmpty()  || edi2.getText().toString().isEmpty() || edi3.getText().toString().isEmpty()  ) {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Por favor rellena los valores de edad, altura y peso", Toast.LENGTH_SHORT);
            MostrarAnuncio();
            toast1.show();
            CargarAnuncio();

        } else {
            MostrarAnuncio();

            float decimal = Float.parseFloat(edi.getText().toString());
            float decimal2 = Float.parseFloat(edi2.getText().toString());
            float metros = decimal / 100;
            float total = decimal2 / (metros * metros);
            float redodear = Math.round(total * 10) / 10.0F;
            String ultimo = Float.toString(redodear);
            imc.setText(ultimo);


            if (rb.isChecked() == true) {
                float edad = Float.parseFloat(edi3.getText().toString());
                double grasa = (1.2 * redodear) + (0.23 * edad) - (10.8 * 1) - 5.4;

                float grasafinal = Math.round(grasa * 10) / 10.0F;
                String grasaString = Float.toString(grasafinal);
                grasacorporal.setText(grasaString + "%");

            }


            if (rb2.isChecked() == true) {
                float edad = Float.parseFloat(edi3.getText().toString());
                double grasa = (1.2 * redodear) + (0.23 * edad) - (10.8 * 0) - 5.4;

                float grasafinal = Math.round(grasa * 10) / 10.0F;
                String grasaString = Float.toString(grasafinal);
                grasacorporal.setText(grasaString + "%");

            }
            if (redodear < 16) {
                expli.setTextColor(Color.RED);
                expli.setText("Delgadez muy extrema");
            }
            if (redodear >= 16 && redodear <= 16.9) {
                expli.setTextColor(Color.RED);
                expli.setText("Delgadez extrema");
            }
            if (redodear >= 17 && redodear <= 18.4) {
                expli.setTextColor(Color.YELLOW);
                expli.setText("Delgadez");
            }
            if (redodear >= 18.5 && redodear <= 24.9) {
                expli.setTextColor(Color.GREEN);
                expli.setText("Normal");
            }
            if (redodear >= 25 && redodear <= 29.9) {
                expli.setTextColor(Color.YELLOW);
                expli.setText("Sobrepeso");
            }
            if (redodear >= 30 && redodear <= 34.9) {
                expli.setTextColor(Color.RED);
                expli.setText("Obesidad grado 1");
            }
            if (redodear >= 35.0 && redodear <= 39.9) {
                expli.setTextColor(Color.RED);
                expli.setText("Obesidad grado 2");
            }
            if (redodear >= 40.0) {
                expli.setTextColor(Color.RED);
                expli.setText("Obesidad grado 3");
            }
            CargarAnuncio();
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
