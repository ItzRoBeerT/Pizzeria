package com.example.pizzeria.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class RealDaoPizzeria extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String NOMBREBBDD = "pizzeria.db";

    public RealDaoPizzeria(Context context) {
        super(context, NOMBREBBDD, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"pizza\" (\n" +
                "\t\"id_pizza\"\tINTEGER UNIQUE,\n" +
                "\t\"nombre\"\tTEXT,\n" +
                "\t\"precio\"\tNUMERIC,\n" +
                "\t\"tiempo_preparacion\"\tINTEGER,\n" +
                "\t\"Ingredientes\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id_pizza\" AUTOINCREMENT)\n" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Usuario\" (\n" +
                "\t\"id_usuario\"\tINTEGER UNIQUE,\n" +
                "\t\"nick\"\tTEXT UNIQUE,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id_usuario\"AUTOINCREMENT)\n" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"pizza_usuario\" (\n" +
                "\t\"id_pizza_usuario\"\tINTEGER UNIQUE,\n" +
                "\t\"id_pizza\"\tINTEGER,\n" +
                "\t\"id_usuario\"\tINTEGER,\n" +
                "\tFOREIGN KEY(\"id_usuario\") REFERENCES \"Usuario\"(\"id_usuario\"),\n" +
                "\tFOREIGN KEY(\"id_pizza\") REFERENCES \"pizza\"(\"id_pizza\"),\n" +
                "\tPRIMARY KEY(\"id_pizza_usuario\")\n" +
                ")");

        System.out.println("Se creó la BBDD");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public long registrarUsuario(String usu, String contraseña){
        long id = 0;
        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nick",usu);
            values.put("password",contraseña);
            id=bbdd.insert("Usuario",null,values);

            System.out.println("Se ");
        } catch (Exception ex){
            System.err.println("Algo falló");
        }
        return id;
    }
    //Así se realiza un select
    public HashMap<String, String> buscarUsuarios(){
        SQLiteDatabase bbdd = this.getWritableDatabase();
        HashMap<String,String> mapaUsuario= new HashMap<>();
        Cursor c = bbdd.rawQuery("Select * from Usuario",null);

        //Así podemos recorrer un cursor
        while(c.isAfterLast()==false){
            mapaUsuario.put(c.getString(0),c.getString(1));
            c.moveToNext();
        }

        c.close();
        return mapaUsuario;
    }
}
