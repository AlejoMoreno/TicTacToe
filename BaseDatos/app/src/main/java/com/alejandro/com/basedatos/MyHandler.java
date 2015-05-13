package com.alejandro.com.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GSN on 01/05/2015.
 */
public class MyHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "ejercicio.db";
    private static final String TABLE_USUARIOS = "empresas";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMPRESA = "empresa";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PRODUCTO = "producto";
    public static final String COLUMN_CLASIFICACION = "clasificacion";

    public MyHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_USUARIOS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_EMPRESA + ","
                + COLUMN_URL + "," + COLUMN_TELEFONO + "," + COLUMN_EMAIL + "," + COLUMN_PRODUCTO
                + "," + COLUMN_CLASIFICACION + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    public void addEmpresa(Empresa empresa) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPRESA, empresa.getNombre());
        values.put(COLUMN_URL, empresa.getUrl());
        values.put(COLUMN_TELEFONO, empresa.getTelefono());
        values.put(COLUMN_EMAIL, empresa.getEmail());
        values.put(COLUMN_PRODUCTO, empresa.getProductos());
        values.put(COLUMN_CLASIFICACION, empresa.getClasificacion());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USUARIOS, null, values);
        db.close();
    }

    public Empresa findUser(String nombre) {
        String query = "Select * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_EMAIL + " =  \"" + nombre + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Empresa usuario = new Empresa();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            usuario.setId(Integer.parseInt(cursor.getString(0)));
            usuario.setNombre(cursor.getString(1));
            usuario.setUrl(cursor.getString(2));
            usuario.setTelefono(cursor.getString(3));
            usuario.setEmail(cursor.getString(4));
            usuario.setProductos(cursor.getString(5));
            usuario.setClasificacion(cursor.getString(6));
            cursor.close();
        } else {
            usuario = null;
        }
        db.close();
        return usuario;
    }

    public Empresa findUserid(int id) {
        String query = "Select * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_ID + " =  \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Empresa usuario = new Empresa();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            usuario.setId(Integer.parseInt(cursor.getString(0)));
            usuario.setNombre(cursor.getString(1));
            usuario.setUrl(cursor.getString(2));
            usuario.setTelefono(cursor.getString(3));
            usuario.setEmail(cursor.getString(4));
            usuario.setProductos(cursor.getString(5));
            usuario.setClasificacion(cursor.getString(6));
            cursor.close();
        } else {
            usuario = null;
        }
        db.close();
        return usuario;
    }

    public boolean deleteUser(String nombre) {

        boolean result = false;
        String query = "Select * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_EMAIL + " =  \"" + nombre + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Empresa usuario = new Empresa();
        if (cursor.moveToFirst()) {
            usuario.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USUARIOS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(usuario.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
