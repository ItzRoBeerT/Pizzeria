package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.DAO.RealDaoPizzeria;

import java.util.ArrayList;

public class PredeterminadasActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
    RealDaoPizzeria dao = new RealDaoPizzeria(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_predeterminadas);

        Spinner menuPredet= findViewById(R.id.spìnnerPredet);
        ArrayList<String> pizzas = obtenerPizzasPredet();
        ArrayAdapter<String> adaptador = tools.generarAdaptador(pizzas,0);
        menuPredet.setAdapter(adaptador);

        //BOTON SELECION PREDETERMINADA
        Button btn = findViewById(R.id.btnSelecionPredet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pizzaPredet = menuPredet.getSelectedItem().toString();
                //servicio.getPizzaPredet(pizzaPredet)
                Pizza p = dao.encontrarPizzaPred(pizzaPredet);
                tools.pasarPizzaPredet(ConfirmacionActivity.class,p);
            }
        });
    }
    public ArrayList<String> obtenerPizzasPredet(){

        ArrayList<String> pizzas = new ArrayList<>();
        //servicio.getPizzasPredet()
        for(Pizza p: dao.obtenerPizzas()){
            if (!p.getNombre().equalsIgnoreCase("Personalizada")) pizzas.add(p.getNombre());
        }
        return pizzas;
    }
}