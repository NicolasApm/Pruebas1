package com.example.android.lite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import OpenHelper.Sqlite_OpenHelper;
import OpenHelper.Usuarios;

public class MostrarActivity extends AppCompatActivity {

    private static ArrayList<Usuarios> usuarios = new ArrayList<>();
    private static ListView lista;
    private static int usuarioSelecionado = -1;
    private static Object mActionMode;

    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_principal);


    }






}
