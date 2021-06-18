package com.example.appeventossyscati2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String nombre, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table paquete (cod integer primary key, fotografia integer, usb text, pbook text, cuadro1 text, cuadro2 text, cuadro3 text, locacion text, pelicula text)");
        db.execSQL("create table evento (cod integer primary key, cliente text, tipo text, paquete text, fecha text, hora text, direccion text, precio integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists paquete");
        db.execSQL("drop table if exists evento");
        db.execSQL("create table paquete (cod integer primary key, fotografia integer, usb text, pbook text, cuadro1 text, cuadro2 text, cuadro3 text, locacion text, pelicula text)");
        db.execSQL("create table evento (cod integer primary key, cliente text, tipo text, paquete text, fecha text, hora text, direccion text, precio integer)");

    }

}
