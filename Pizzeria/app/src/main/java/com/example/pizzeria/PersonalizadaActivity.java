package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
                Pizza p = generarPizza();

                if(!validarcheck()){
                   Toast.makeText(PersonalizadaActivity.this,"Debe seleccionar al menos 3 ingredientes",Toast.LENGTH_SHORT).show();
                }else
                txt.setText(radioButton.getText()+ "Pizza "+p.getNombre()+"\nIngredientes: \n"+ p.mostrarIngredientes());
            }
        });
    }

    public boolean validarcheck(){
        int contador=0;
        CheckBox chkQueso = findViewById(R.id.chkQueso);
        CheckBox chkJamon = findViewById(R.id.chkJamon);
        CheckBox chkParmesano = findViewById(R.id.chkParmesano);
        CheckBox chkSalchichas = findViewById(R.id.chkSalchichas);
        CheckBox chkGorgonzola = findViewById(R.id.chkGorgonzola);
        CheckBox chkAtun = findViewById(R.id.chkAtun);
        CheckBox chkBacon = findViewById(R.id.chkBacon);
        CheckBox chkPepperoni = findViewById(R.id.chkPeperoni);
        CheckBox chkPollo= findViewById(R.id.chkPollo);

        if (chkQueso.isChecked()){
            contador++;
        }
        if (chkJamon.isChecked()){
            contador++;        }
        if (chkParmesano.isChecked()){
            contador++;        }
        if (chkSalchichas.isChecked()){
            contador++;        }
        if (chkGorgonzola.isChecked()){
            contador++;        }
        if (chkAtun.isChecked()){
            contador++;        }
        if (chkBacon.isChecked()){
            contador++;        }
        if (chkPepperoni.isChecked()){
            contador++;        }
        if (chkPollo.isChecked()){
            contador++;        }

        if(contador>=3){
            return true;
        }else
        return false;
    }

    public Pizza generarPizza(){
        ArrayList<String> ingredientes = new ArrayList<>();

        CheckBox chkQueso = findViewById(R.id.chkQueso);
        CheckBox chkJamon = findViewById(R.id.chkJamon);
        CheckBox chkParmesano = findViewById(R.id.chkParmesano);
        CheckBox chkSalchichas = findViewById(R.id.chkSalchichas);
        CheckBox chkGorgonzola = findViewById(R.id.chkGorgonzola);
        CheckBox chkAtun = findViewById(R.id.chkAtun);
        CheckBox chkBacon = findViewById(R.id.chkBacon);
        CheckBox chkPepperoni = findViewById(R.id.chkPeperoni);
        CheckBox chkPollo= findViewById(R.id.chkPollo);

        if (chkQueso.isChecked()){
            ingredientes.add(chkQueso.getText().toString());
        }
        if (chkJamon.isChecked()){
            ingredientes.add(chkJamon.getText().toString());
        }
        if (chkParmesano.isChecked()){
            ingredientes.add(chkParmesano.getText().toString());
        }
        if (chkSalchichas.isChecked()){
            ingredientes.add(chkSalchichas.getText().toString());
        }
        if (chkGorgonzola.isChecked()){
            ingredientes.add(chkGorgonzola.getText().toString());
        }
        if (chkAtun.isChecked()){
            ingredientes.add(chkAtun.getText().toString());
        }
        if (chkBacon.isChecked()){
            ingredientes.add(chkBacon.getText().toString());
        }
        if (chkPepperoni.isChecked()){
            ingredientes.add(chkPepperoni.getText().toString());
        }
        if (chkPollo.isChecked()){
            ingredientes.add(chkPollo.getText().toString());
        }
        Pizza pizza =new Pizza("Personalizada",3.0,ingredientes);
        return   pizza;
    }
}