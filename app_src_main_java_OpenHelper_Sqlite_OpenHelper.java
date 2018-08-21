package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite_OpenHelper extends SQLiteOpenHelper {


    public Sqlite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table usuario(_ID integer primary key autoincrement,Nombre text, " +
                     "Ciudad text, Correo text, Cotraseña text);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //abrir db
    public void abrirdb(){
        this.getWritableDatabase();

    }

    //cerrar db

    public void cerrardb (){
        this.close();
    }

    /*contentValues: permite agrupar un conjunto de valores
    para que puedan ser procesados*/

    //metodo para insertar registro en tabla usuarios


    public void insertarReg(String Nom,String ciudad, String Cor, String Pass){

       /* Nom="hola";
        ciudad="hola1";
        Cor="hola2";
        Pass="hola3";*/

        ContentValues valores = new ContentValues();
        valores.put("Nombre",Nom);
        valores.put("Ciudad",ciudad);
        valores.put("Correo",Cor);
        valores.put("Cotraseña",Pass);
        this.getWritableDatabase().insert("usuario",null,valores);

    }

    //metodo para validar usuario

     public Cursor ConsultarUsuario (String usu,String pas ) throws SQLException{
     Cursor mcursor =null;
     mcursor=this.getReadableDatabase().query("usuario",new String []{"_ID", "Nombre", "Ciudad", "Correo",
     "Cotraseña"},"Correo like '"+usu+"' and Cotraseña like '" +pas+"'",null,null,null,null);
     return mcursor;
    }



}
