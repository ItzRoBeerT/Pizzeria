package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.Clases.Usuario;
import com.example.pizzeria.DAO.RealDaoPizzeria;

public class RegistroActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
   // Servicio servicio = new Servicio();
    RealDaoPizzeria dao = new RealDaoPizzeria(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_registro);

        Button btnAddUsuario = findViewById(R.id.btnConfirmarUsuario);
        btnAddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView usuario = findViewById(R.id.txtRegistrarUsuario);
                TextView contra  = findViewById(R.id.txtRegistrarContra);

                Usuario nuevoUsu = new Usuario(usuario.getText().toString(),contra.getText().toString());
               // servicio.addUsuario(nuevoUsu);
                dao.insertarUsuario(nuevoUsu);
                finish();
            }
        });

    }
}