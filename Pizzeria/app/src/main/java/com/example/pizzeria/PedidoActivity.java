package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.Clases.Usuario;

public class PedidoActivity extends AppCompatActivity {

    private static final String FILE_NAME = "myFile";
    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
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
                //Recoger nombre guardado
                SharedPreferences preferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
                String username = preferences.getString("usuario","No encontrado");

                Usuario usuarioActual = servicio.encontrarUsuarioNombre(username);
                Pizza pizzaFav= usuarioActual.calcularPizzaFav();
                tools.pasarPizzaPredet(ConfirmacionActivity.class,pizzaFav);
            }
        });
    }
}