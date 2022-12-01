package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.Clases.Usuario;
import com.example.pizzeria.DAO.RealDaoPizzeria;

public class PedidoActivity extends AppCompatActivity {

    private static final String FILE_NAME = "myFile";
    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
    RealDaoPizzeria dao = new RealDaoPizzeria(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
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
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                //Recoger nombre guardado
                SharedPreferences preferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
                String username = preferences.getString("usuario","No encontrado");

                //servicio.encontrarUsuarioNombre(username)
                Usuario usuarioActual = dao.encontrarUsuarioNombre(username);
                Pizza pizzaFav= usuarioActual.calcularPizzaFav();
                if(pizzaFav ==null){
                    Toast.makeText(PedidoActivity.this, "Todavia no has hecho ningún pedido", Toast.LENGTH_SHORT).show();
                }else
                tools.pasarPizzaPredet(ConfirmacionActivity.class,pizzaFav);
            }
        });
    }
}