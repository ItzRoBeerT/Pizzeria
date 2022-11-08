package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Servicio;

public class PedidoActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        //BOTON PERSONALIZADA
        Button btnPerso = findViewById(R.id.btnPersonalizada);
        btnPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.cambiarActividad(PersonalizadaActivity.class);
            }
        });

        //BOTON PREDETERMIANDA
        Button btnPredet = findViewById(R.id.btnPredeterminada);
        btnPredet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.cambiarActividad(PredeterminadasActivity.class);
            }
        });

        //BOTON FAVORITA
        Button btnFav = findViewById(R.id.btnFav);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}