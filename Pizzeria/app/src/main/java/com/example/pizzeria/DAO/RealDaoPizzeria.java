package com.example.pizzeria.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Usuario;

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
                "ingrediente1 TEXT NOT NULL," +
                "ingrediente2 TEXT NOT NULL," +
                "ingrediente3 TEXT NOT NULL," +
                "ingrediente4 TEXT," +
                "ingrediente5 TEXT," +
                "tiempo_preparacion INTEGER NOT NULL" + ")");

        sqLiteDatabase.execSQL("Create Table usuario" + "(" +
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

        sqLiteDatabase.execSQL("INSERT INTO usuario(nick, password) VALUES('admin','1234')");
        sqLiteDatabase.execSQL("INSERT INTO pizza(nombre, precio,ingrediente1,ingrediente2,ingrediente3,ingrediente4,ingrediente5,tiempo_preparacion) VALUES('margarita',12.50,'jamon','queso','tomate','aceituna','mozzarela',5)");
        System.out.println("Se creó la BBDD");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //insertar datos
    public   void inicializarPizza(){
        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre","Margarita");
            values.put("precio",15);
            values.put("ingrediente1","jamon");
            values.put("ingrediente2","mozzarela");
            values.put("ingrediente3","bacon");
            bbdd.insert("pizza",null,values);

            System.out.println("Se ");
        } catch (Exception ex){
            System.err.println("Algo falló");
        }
    }
    public   void inicializarUsuario(){
        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nick","admin");
            values.put("password","1234");
            bbdd.insert("usuario",null,values);

            System.out.println("Se ");
        } catch (Exception ex){
            System.err.println("Algo falló");
        }
    }

    public long insertarPizzas (Pizza pizza){
        long id = 0;
        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre",pizza.getNombre());
            values.put("precio",pizza.getPrecio());


            if (pizza.getIngredientes().size()==5){
                values.put("ingrediente1",pizza.getIngredientes().get(0));
                values.put("ingrediente2",pizza.getIngredientes().get(1));
                values.put("ingrediente3",pizza.getIngredientes().get(2));
                values.put("ingrediente4",pizza.getIngredientes().get(3));
                values.put("ingrediente5",pizza.getIngredientes().get(4));
            }else if (pizza.getIngredientes().size()==4){
                values.put("ingrediente1",pizza.getIngredientes().get(0));
                values.put("ingrediente2",pizza.getIngredientes().get(1));
                values.put("ingrediente3",pizza.getIngredientes().get(2));
                values.put("ingrediente4",pizza.getIngredientes().get(3));
            }else{
                values.put("ingrediente1",pizza.getIngredientes().get(0));
                values.put("ingrediente2",pizza.getIngredientes().get(1));
                values.put("ingrediente3",pizza.getIngredientes().get(2));
            }
            values.put("tiempo_preparacion",pizza.getTiempoPrep());

            id=bbdd.insert("pizza",null,values);

            System.out.println("Se ");
        } catch (Exception ex){
            System.err.println("Algo falló");
        }
        return id;
    }

    public  void insertarUsuario(Usuario usu){
        long id=0;
        try {
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nick",usu.getUser());
            values.put("password",usu.getPassword());
            id=bbdd.insert("usuario",null,values);

            System.out.println("Se ");
        } catch (Exception ex){
            System.err.println("Algo falló");
        }
    }



    //Así se realiza un select
    public ArrayList<Pizza> obtenerPizzas(){

        SQLiteDatabase bbdd = this.getWritableDatabase();
        ArrayList<Pizza> listaPizzas = new ArrayList<Pizza>();
        ArrayList<String> ingredientes = new ArrayList<>();
        Pizza pizza;
        Cursor cursorPizza = bbdd.rawQuery("Select * from pizza",null);

        if(cursorPizza.moveToFirst()){
            do{
                ingredientes.add(cursorPizza.getString(3));
                ingredientes.add(cursorPizza.getString(4));
                ingredientes.add(cursorPizza.getString(5));
                ingredientes.add(cursorPizza.getString(6));
                ingredientes.add(cursorPizza.getString(7));
                pizza = new Pizza(cursorPizza.getString(1), cursorPizza.getDouble(2), ingredientes);
                listaPizzas.add(pizza);
            }while(cursorPizza.moveToNext());
        }
        cursorPizza.close();
        return listaPizzas;
    }

    public ArrayList<Usuario> obtenerUsuarios(){

        SQLiteDatabase bbdd = this.getWritableDatabase();
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Usuario usu;
        Cursor cursorUsu = bbdd.rawQuery("Select * from usuario",null);

        if(cursorUsu.moveToFirst()){
            do{
                usu = new Usuario(cursorUsu.getString(1),cursorUsu.getString(2));
                listaUsuarios.add(usu);
            }while(cursorUsu.moveToNext());
        }
        cursorUsu.close();
        return listaUsuarios;
    }
    public Usuario encontrarUsuarioNombre(String nombre){
        SQLiteDatabase bbdd = this.getWritableDatabase();
        Usuario usu=null;
        Cursor cursorUsu = bbdd.rawQuery("Select * from usuario WHERE nick='"+nombre+"'",null);

        if(cursorUsu.moveToFirst()){
            do{

                usu = new Usuario(cursorUsu.getString(1),cursorUsu.getString(2));
            }while(cursorUsu.moveToNext());
        }
        cursorUsu.close();
        return usu;
    }

    public Pizza encontrarPizzaPred(String nom){
        SQLiteDatabase bbdd = this.getWritableDatabase();
        Pizza pizza=null;
        Cursor cursorPizza = bbdd.rawQuery("Select * from pizza WHERE nombre= '"+nom+"'",null);
        ArrayList<String> ingredientes = new ArrayList<>();
        if(cursorPizza.moveToFirst()){
            do{
                ingredientes.add(cursorPizza.getString(3));
                ingredientes.add(cursorPizza.getString(4));
                ingredientes.add(cursorPizza.getString(5));
                ingredientes.add(cursorPizza.getString(6));
                ingredientes.add(cursorPizza.getString(7));
                pizza = new Pizza(cursorPizza.getString(1),cursorPizza.getDouble(2),ingredientes);
            }while(cursorPizza.moveToNext());
        }
        cursorPizza.close();
        return pizza;
    }
    public Integer devolverIdPizza(String nom){
        SQLiteDatabase bbdd = this.getWritableDatabase();
        Integer id =0;
        Cursor cursorPizza = bbdd.rawQuery("Select * from pizza WHERE nombre= '"+nom+"'",null);
        ArrayList<String> ingredientes = new ArrayList<>();
        if(cursorPizza.moveToFirst()){
            do{
                id= cursorPizza.getInt(0);
            }while(cursorPizza.moveToNext());
        }
        cursorPizza.close();
        return id;
    }

    public Pizza encontrarPizzaID(Integer id){
        SQLiteDatabase bbdd = this.getWritableDatabase();
        Pizza pizza=null;
        Cursor cursorPizza = bbdd.rawQuery("Select * from pizza WHERE id_pizza='"+id+"'",null);
        ArrayList<String> ingredientes = new ArrayList<>();
        if(cursorPizza.moveToFirst()){
            do{
                ingredientes.add(cursorPizza.getString(3));
                ingredientes.add(cursorPizza.getString(4));
                ingredientes.add(cursorPizza.getString(5));
                ingredientes.add(cursorPizza.getString(6));
                ingredientes.add(cursorPizza.getString(7));
                pizza = new Pizza(cursorPizza.getString(1),cursorPizza.getDouble(2),ingredientes);
            }while(cursorPizza.moveToNext());
        }
        cursorPizza.close();
        return pizza;
    }




}
