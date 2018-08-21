package com.example.android.lite;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.Sqlite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    TextView tvRegistrese;
    Button btnIngresar;
    Sqlite_OpenHelper helper=new Sqlite_OpenHelper(this,"BD1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegistrese=(TextView)findViewById(R.id.tvRegistrese);
        tvRegistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Registro.class);
                startActivity(i);
            }
        });

        btnIngresar=(Button)findViewById(R.id.Registro);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtusu=(EditText)findViewById(R.id.txtUsuario);
                EditText txtpass=(EditText)findViewById(R.id.txtPass);

                try{
                    boolean vacio=false;
                Cursor cursor=helper.ConsultarUsuario(txtusu.getText().toString()
                ,txtpass.getText().toString());

                    if (txtusu.getText().toString().equals("") && txtpass.getText().toString().equals("")){

                        Toast.makeText(getApplicationContext(),"Campos Vacios",Toast.LENGTH_LONG).show();
                        vacio=true;
                    }
                        if(cursor.getCount()>0 && !vacio) {
                            Intent i=new Intent(getApplicationContext(),Principal.class);
                            startActivity(i);

                            vacio=false;
                        }
                        if(cursor.getCount()<=0) {
                            Toast.makeText(getApplicationContext(),"Usuario y contraseña" +
                                    "incorrectos",Toast.LENGTH_LONG).show();
                        }
                        txtusu.setText("");
                        txtpass.setText("");
                        txtusu.findFocus();
               }


                catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menup) {
        getMenuInflater().inflate(R.menu.menup, menup);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.SalirMain) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
