package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.Clases.Usuario;

import java.util.ArrayList;

public class LoggedActivity extends AppCompatActivity {

    private static final String FILE_NAME = "myFile";
    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_logged);

        //Recoger nombre guardado
        SharedPreferences preferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        String username = preferences.getString("usuario","No encontrado");
        //Añadir el nombre
        AutoCompleteTextView auto = findViewById(R.id.autoTxtUsu);
        auto.setText(username);
        ArrayList<String>a=new ArrayList<>();
        a.add("Opciones");
        a.add("Cerrar sesion");
        ArrayAdapter<String> adap = tools.generarAdaptador(a,1);
        cerrarSesion();

        //BOTON WEB
        Button btnWeb = findViewById(R.id.btnWeb);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.abrirURL("https://www.dominospizza.es");
            }
        });

        //BOTON PEDIDO
        Button btnPedido = findViewById(R.id.btnPedido);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.pasarValor(PedidoActivity.class,encontrarUsuario(username));
            }
        });

        //BOTON CONFIGURACION
        Button btnConfig = findViewById(R.id.btnConfiguracion);
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tools.cambiarActividad(ConfiguracionActivity.class);

            }
        });

    }

    public void cerrarSesion(){
        Button btnLogout= findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                finish();
            }
        });
    }

    public Usuario encontrarUsuario(String nombre){
        Usuario usu = null;
        for(Usuario u: servicio.getUsuarios()){
            if(u.getUser().equals(nombre)){
                usu= u;
            }
        }
        return usu;
    }




}