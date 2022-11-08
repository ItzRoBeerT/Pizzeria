package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Servicio;

public class ConfirmacionActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_confirmacion);



    }
}