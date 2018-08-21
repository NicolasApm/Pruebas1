package com.example.android.lite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.Sqlite_OpenHelper;

public class EditarActivity extends AppCompatActivity {
    private int usuarioEditar;
    private EditText nombre,ciudad,correo,contraseña;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_editar);
        Bundle extras = this.getIntent().getExtras();
        if(extras!=null){
            usuarioEditar = extras.getInt("id");
        }
        nombre = (EditText) findViewById(R.id.edit_txtnomusu);
        ciudad = (EditText) findViewById(R.id.edit_txtciudadusu);
        correo = (EditText) findViewById(R.id.edit_txtcorreousu);
        contraseña = (EditText) findViewById(R.id.edit_txtpassusu);

        reflejarCampos();
    }
    public void reflejarCampos(){
        Sqlite_OpenHelper bh  = new Sqlite_OpenHelper(EditarActivity.this,"usuario",null,1);
        if(bh!=null){
            SQLiteDatabase db = bh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM usuario WHERE _ID = "+usuarioEditar,null);
            try{
                if(c.moveToNext()){
                    nombre.setText(c.getString(1));
                    ciudad.setText(c.getString(2));
                    correo.setText(c.getString(3));
                    contraseña.setText(c.getString(4));
                }
            }finally {

            }
        }
    }
    public void editar(View v){
        Sqlite_OpenHelper bh = new Sqlite_OpenHelper(EditarActivity.this,"BD1",null,1);
        if(bh!=null){
            SQLiteDatabase db = bh.getWritableDatabase();
            ContentValues val = new ContentValues();
            val.put("Nombre",nombre.getText().toString());
            val.put("Ciudad",ciudad.getText().toString());
            val.put("Correo",correo.getText().toString());
            val.put("Cotraseña",contraseña.getText().toString());
            long response = db.update("usuario",val,"_ID="+usuarioEditar,null);
            if(response>0){
                Toast.makeText(EditarActivity.this,"Editado con exito",Toast.LENGTH_LONG).show();
                nombre.setText("");
                ciudad.setText("");
                correo.setText("");
                contraseña.setText("");
                Intent i=new Intent(getApplicationContext(),Principal.class);
                startActivity(i);


            }else{
                Toast.makeText(EditarActivity.this,"Ocurrio un error",Toast.LENGTH_LONG).show();
            }
        }
    }
}
