package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pizzeria.Clases.Herramientas;

public class RegistroActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_registro);
    }
}