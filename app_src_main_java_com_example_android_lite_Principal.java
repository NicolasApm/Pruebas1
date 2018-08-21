package com.example.android.lite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import OpenHelper.Sqlite_OpenHelper;
import OpenHelper.Usuarios;

public class Principal extends AppCompatActivity {

    private ArrayList<Usuarios> usuarios = new ArrayList<>();
    private ListView listaNombre;
    private int usuarioSelecionado = -1;
    private Object mActionMode;

    Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Ventana();

    }

    public void onResume(){
        super.onResume();
        usuarios.removeAll(usuarios);
        MostrarDatos();
    }

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


    private ActionMode.Callback amc = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.opciones,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId() == R.id.EliminarItem){
                eliminarUsuario();
                mode.finish();
            }else if(item.getItemId() == R.id.EditarItem){
                Usuarios usu = usuarios.get(usuarioSelecionado);
                Intent in = new Intent(Principal.this,EditarActivity.class);
                in.putExtra("id",usu.getIdusuario());
                startActivity(in);
                mode.finish();
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };
       public void MostrarDatos(){

        btnMostrar=(Button)findViewById(R.id.BtnMostrarUsuario);
        btnMostrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Datos Almanenados", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                listaNombre = (ListView) findViewById(R.id.listViewNombre);

                onClick();
                llenarLista();
            }

            public void onClick(){

                listaNombre.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                listaNombre.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        usuarioSelecionado = position;
                        mActionMode = Principal.this.startActionMode(amc);
                        view.setSelected(true);
                        //Toast.makeText(Principal.this,"Seleccionado",Toast.LENGTH_LONG).show();
                        return true;
                    }
                });
            }
        });
    }

    public void Ventana (){

        setContentView(R.layout.activity_principal);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }



    public void llenarLista(){
        Sqlite_OpenHelper bh = new Sqlite_OpenHelper(Principal.this,"BD1",null,1);
        if(bh!=null){
            SQLiteDatabase db = bh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM usuario",null);
            if(c.moveToFirst()){
                do{
                    usuarios.add(new Usuarios(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),
                                           c.getString(4)));
                }while(c.moveToNext());
            }
        }
        String[] arregloNombre= new String[usuarios.size()];


        for (int i = 0;i<arregloNombre.length;i++){

            arregloNombre[i] = usuarios.get(i).getNombre()+" "+usuarios.get(i).getCiudad()+(" ")
                               +usuarios.get(i).getCorreo()+(" ")+usuarios.get(i).getContraseña();
        }

        ArrayAdapter<String> adaptadorNombre = new ArrayAdapter<String>(Principal.this,android.R.layout.simple_list_item_1,arregloNombre);


        listaNombre.setAdapter(adaptadorNombre);

    }

    public void eliminarUsuario(){
        Sqlite_OpenHelper bh = new Sqlite_OpenHelper(Principal.this,"BD1",null,1);
        if(bh!=null){
            SQLiteDatabase db = bh.getReadableDatabase();
            Usuarios usu = usuarios.get(usuarioSelecionado);
            long response = db.delete("usuario","_ID="+usu.getIdusuario(),null);
            if(response>0){
                Toast.makeText(Principal.this,"Eliminado con exito",Toast.LENGTH_LONG).show();
                usuarios.removeAll(usuarios);
                llenarLista();
            }else{
                Toast.makeText(Principal.this,"Fallo",Toast.LENGTH_LONG).show();
            }
        }
    }

}
