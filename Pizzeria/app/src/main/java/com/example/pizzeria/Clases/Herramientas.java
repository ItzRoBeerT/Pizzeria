package com.example.pizzeria.Clases;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Herramientas {

    AppCompatActivity compAct;

    public   Herramientas(AppCompatActivity compAct){
    this.compAct= compAct;
    }

    public void crearDialogo(String titulo, String mensaje){
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this.compAct);
        dialogo1.setTitle(titulo);
        dialogo1.setMessage(mensaje);
        dialogo1.setCancelable(false);

        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelar();
            }
        });
        dialogo1.show();
    }
    private void aceptar(){
        Toast t = Toast.makeText(this.compAct,"AFA",Toast.LENGTH_SHORT);
    }
    private void cancelar(){

    }

    public ArrayAdapter<String> generarAdaptador(ArrayList<String> datos, int estilo){
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this.compAct, android.R.layout.simple_spinner_item,datos);
        if (estilo !=0){
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }
        return  adaptador;
    }

    public void cambiarActividad(Class a){
        Intent cambiarActividad = new Intent(this.compAct,a);
        this.compAct.startActivity(cambiarActividad);
    }

    public void abrirURL(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        this.compAct.startActivity(i);
    }

    public void pasarValor(Class c, Usuario u){
        Intent i = new Intent(this.compAct,c);
        i.putExtra("key",u.getUser());
        this.compAct.startActivity(i);
    }

    public void pasarPizza(Class c, Pizza p){
        Intent i = new Intent(this.compAct,c);
        i.putExtra("key",p.getIdPizza());
        this.compAct.startActivity(i);
    }

    public void pasarPizzaPredet(Class c, Pizza p){
        Intent i = new Intent(this.compAct,c);
        i.putExtra("key",p.getNombre());
        this.compAct.startActivity(i);
    }

    public String recogerValor(){
        Bundle extras = this.compAct.getIntent().getExtras();
        String valor="";
        if(extras !=null){
           valor = extras.getString("key");
        }
        return valor;
    }
    public Integer recogerIdPizza(){
        Bundle extras = this.compAct.getIntent().getExtras();
        Integer valor=0;
        if(extras !=null){
            valor = extras.getInt("key");
        }
        return valor;
    }
    public String recogerNombrePizzaPredet(){
        Bundle extras = this.compAct.getIntent().getExtras();
        String valor="";
        if(extras !=null){
            valor = extras.getString("key");
        }
        return valor;
    }

    public void quitarTitulo(){
        compAct.requestWindowFeature(Window.FEATURE_NO_TITLE);
        compAct.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        compAct.getSupportActionBar().hide();
    }
}
