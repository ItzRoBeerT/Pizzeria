package com.example.pizzeria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.Clases.AdapterDatos;
import com.example.pizzeria.Clases.Herramientas;
import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Servicio;
import com.example.pizzeria.DAO.RealDaoPizzeria;

import java.util.ArrayList;

public class PredeterminadasActivity extends AppCompatActivity {

    Herramientas tools = new Herramientas(this);
    Servicio servicio = new Servicio();
    RealDaoPizzeria dao = new RealDaoPizzeria(this);
    // recylcer
    private ArrayList<Pizza> listaPizzas;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tools.quitarTitulo();
        setContentView(R.layout.activity_predeterminadas);

        //recylcer
        listaPizzas= new ArrayList<>();
        recyclerView = findViewById(R.id.miRecycler);

        obtenerPizzasPredet();


        //BOTON SELECION PREDETERMINADA

        /*
        Button btn = findViewById(R.id.btnSelecionPredet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String pizzaPredet = menuPredet.getSelectedItem().toString();
                servicio.getPizzaPredet(pizzaPredet)
                Pizza p = dao.encontrarPizzaPred(pizzaPredet);
                tools.pasarPizzaPredet(ConfirmacionActivity.class,p);
            }
        });*/

    }
    public void obtenerPizzasPredet(){
        ArrayList<String> pizzas = new ArrayList<>();
        //servicio.getPizzasPredet()
        ArrayList<Pizza> pizzasSelect= new ArrayList<>();
        for(Pizza p: dao.obtenerPizzas()){
            if (!p.getNombre().equalsIgnoreCase("Personalizada")){
                pizzas.add(p.getNombre());
                pizzasSelect.add(p);
            }

        }
        setAdapter(pizzasSelect);
        //return pizzas;
    }


    public void setAdapter(ArrayList<Pizza> listaPizzas){
        AdapterDatos adapter = new AdapterDatos(listaPizzas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        //evento
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<adapter.getItemCount();i++)
                {
                    View nview=recyclerView.getChildAt(i); // This will give you entire row(child) from RecyclerView
                    if(view!=null)
                    {
                        TextView textView= (TextView) nview.findViewById(R.id.txtTitulo);
                        String text=textView.getText().toString();
                        if (text.equals(listaPizzas.get(i).getNombre())){
                            System.out.println("Entro");
                            String pizzaPredet = text;
                            Pizza p = dao.encontrarPizzaPred(pizzaPredet);
                            tools.pasarPizzaPredet(ConfirmacionActivity.class,p);
                        }
                    }
                }
            }
        });
    }


}