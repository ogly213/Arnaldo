package com.prueba.utilidades2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDeDatos extends SQLiteOpenHelper {
    private final Context contexto;

    public BaseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table pasoshechosf(pasos text, fecha text)");
        //db.execSQL("Create table medicos(dnim text primary key,nombrem text,especialidad text,localidad2 text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
