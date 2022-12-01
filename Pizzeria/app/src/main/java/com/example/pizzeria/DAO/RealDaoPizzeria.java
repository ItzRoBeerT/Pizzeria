package com.example.pizzeria.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pizzeria.Clases.Pizza;

import java.util.ArrayList;
import java.util.HashMap;

public class RealDaoPizzeria extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String NOMBREBBDD = "pizzeria.db";

    public RealDaoPizzeria(Context context) {
        super(context, NOMBREBBDD, null, VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create Table pizza" + "(" +
                "id_pizza INTEGER Primary Key AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "precio NUMERIC NOT NULL," +
                "ingredientes TEXT NOT NULL," +
                "tiempo_preparacion INTEGER NOT NULL" + ")");

        sqLiteDatabase.execSQL("Create Table Usuario" + "(" +
                "id_usuario INTEGER Primary Key AUTOINCREMENT," +
                "nick TEXT NOT NULL," +
                "password TEXT NOT NULL" + ")");

        sqLiteDatabase.execSQL("CREATE TABLE \"pizza_usuario\" (\n" +
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
    public ArrayList<Pizza> obtenerPizzas(){

        SQLiteDatabase bbdd = this.getWritableDatabase();
        ArrayList<Pizza> listaPizzas = new ArrayList<Pizza>();
        Pizza pizza;
        Cursor cursorPizza = bbdd.rawQuery("Select * from Pizza",null);

        if(cursorPizza.moveToFirst()){
            do{
                pizza = new Pizza(cursorPizza.getString(2), cursorPizza.getDouble(3), cursorPizza.getString(3));
                listaPizzas.add(pizza);
            }while(cursorPizza.moveToNext());
        }
        cursorPizza.close();
        return listaPizzas;
    }
}
