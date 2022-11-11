package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.Clases.Usuario;

import java.nio.file.Files;

public class ConfirmacionActivity extends AppCompatActivity {

    private static final String FILE_NAME = "myFile";
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

        TextView txtPrecio = findViewById(R.id.txtPrecio);
        txtPrecio.setText("PRECIO: "+miPizza.getPrecio()+" $");

        //BOTON CANCELAR
        Button btnCancelar = findViewById(R.id.btnCancelarPed);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.cambiarActividad(LoggedActivity.class);
                finish();
            }
        });


        //BOTON DE CONFIRMAR PEDIDO
        Button btnConfirmar = findViewById(R.id.btnConfirmarPedido);
        Pizza finalMiPizza = miPizza;
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recoger nombre guardado
                SharedPreferences preferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
                String username = preferences.getString("usuario","No encontrado");

                Usuario usuarioActual = servicio.encontrarUsuarioNombre(username);

                usuarioActual.addPizzaPedida(finalMiPizza);
                tools.cambiarActividad(LoggedActivity.class);
                Toast.makeText(ConfirmacionActivity.this, "Preparando la pizza, prepara la cerveza", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}