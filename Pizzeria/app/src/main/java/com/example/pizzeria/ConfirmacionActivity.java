package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Servicio;

public class ConfirmacionActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_confirmacion);

        Integer idPizza = tools.recogerIdPizza();
        Pizza miPizza= servicio.getPizzaID(idPizza);

        if(miPizza==null) {
            String nombrePizza = tools.recogerNombrePizzaPredet();
            miPizza= servicio.getPizzaPredet(nombrePizza);
        }

        TextView txt = findViewById(R.id.miTexto);
        txt.setText("Pizza "+miPizza.getNombre()+"\nIngredientes:\n"+miPizza.mostrarIngredientes());

        //BOTON ACEPTAR PEDIDO

        //BOTON CANCELAR
        Button btnCancelar = findViewById(R.id.btnCancelarPed);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }
}