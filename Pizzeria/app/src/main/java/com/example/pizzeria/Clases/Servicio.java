package com.example.pizzeria.Clases;

import com.example.pizzeria.DAO.DaoPizzeria;

import java.util.ArrayList;

public class Servicio {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Pizza> pizzasPredet;

    public Servicio(){
       inicializar();
    }

    public ArrayList<Usuario> getUsuarios(){
        return  usuarios;
    }

    public void inicializar(){
        usuarios = DaoPizzeria.getInstance().getUsuarios();
        pizzas = DaoPizzeria.getInstance().getPizzas();
        pizzasPredet = DaoPizzeria.getInstance().getPizzasPredeterminadas();
    }

    public Pizza getPizzaID(Integer id){
        Pizza pizzaSeleccionada = null;
        for(Pizza p: pizzas){
            if (p.getIdPizza()==id){
                pizzaSeleccionada=p;
            }
        }
        return pizzaSeleccionada;
    }
    public void addPizza(Pizza p){
        DaoPizzeria.getInstance().addPizza(p);
        inicializar();
    }

    public ArrayList<Pizza> getPizzasPredet() {
        return pizzasPredet;
    }
}
