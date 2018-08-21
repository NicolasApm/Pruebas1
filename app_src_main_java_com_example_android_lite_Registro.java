package com.example.android.lite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.Sqlite_OpenHelper;

public class Registro extends AppCompatActivity {

    Button btnGrabarUsu;
    EditText txtNomUsu,txtCiudadusu,txtCorUsu,txtPasUsu;
    Sqlite_OpenHelper helper=new Sqlite_OpenHelper(this,"BD1", null,1);

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.SalirApli) {
            int p = android.os.Process.myPid();
            android.os.Process.killProcess(p);
            return true;
        }
        if (itemThatWasClickedId == R.id.Volver) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnGrabarUsu=(Button)findViewById(R.id.RegistroUsu);
        txtNomUsu=(EditText)findViewById(R.id.txtnomusu);
        txtCiudadusu=(EditText)findViewById(R.id.txtciudadusu);
        txtCorUsu=(EditText)findViewById(R.id.txtcorreousu);
        txtPasUsu=(EditText)findViewById(R.id.txtpassusu);

        btnGrabarUsu.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                helper.abrirdb();
                helper.insertarReg(String.valueOf(txtNomUsu.getText()),
                        String.valueOf(txtCiudadusu.getText()),
                        String.valueOf(txtCorUsu.getText()),
                        String.valueOf(txtPasUsu.getText()));

                helper.cerrardb();

                Toast.makeText(getApplicationContext(),"Registro almacenado"
                ,Toast.LENGTH_LONG).show();

                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);


            }
        });



    }

}
