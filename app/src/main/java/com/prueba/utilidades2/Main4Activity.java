package com.prueba.utilidades2;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main4Activity extends Activity implements SensorEventListener, StepListener {
    private TextView textView;
    private SimpleStepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Numero De pasos : ";
    private int numSteps;
    String fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        SimpleDateFormat formato =
                new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        fecha = formato.format(new Date());
        textView = new TextView(this);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        setContentView(textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new SimpleStepDetector();
        simpleStepDetector.registerListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        numSteps = 0;
        textView.setText(TEXT_NUM_STEPS + numSteps);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        textView.setText(TEXT_NUM_STEPS + numSteps);
    }

    @Override
    protected void onDestroy() {
        BaseDeDatos administrador = new BaseDeDatos(this,"pasoshechosf",null,1);
        SQLiteDatabase basededatos = administrador.getWritableDatabase();
        String Numerodepasos = String.valueOf(numSteps);
        ContentValues registro = new ContentValues();
        registro.put("pasos",Numerodepasos);
        registro.put("fecha",fecha);
        basededatos.insert("pasoshechosf",null,registro);
        basededatos.close();
        Toast.makeText(this, "Registro de pasos completado", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}



