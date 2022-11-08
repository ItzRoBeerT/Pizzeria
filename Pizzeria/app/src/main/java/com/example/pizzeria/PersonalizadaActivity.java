package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Pizza;

import java.util.ArrayList;

public class PersonalizadaActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_personalizada);

        radioGroup= findViewById(R.id.miRadioGroup);


        //Realizar Pedido
        Button btnValidar = findViewById(R.id.btnValidar);
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txt = findViewById(R.id.txtOpciones);
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                txt.setText("Opcion: "+radioButton.getText());
            }
        });
    }

    public void generarPizza(){
        Pizza pizza =null;
        ArrayList<String> ingredientes = new ArrayList<>();



    }
}