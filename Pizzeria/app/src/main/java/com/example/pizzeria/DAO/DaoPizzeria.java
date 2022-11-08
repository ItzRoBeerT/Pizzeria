package com.example.pizzeria.DAO;

import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Usuario;

import java.util.ArrayList;

public class DaoPizzeria {

    private static DaoPizzeria dao = null;
    private ArrayList<Pizza> pizzas;
    private DaoPizzeria(){
        pizzas = new ArrayList<>();
    }

    public static DaoPizzeria getInstance(){
        if (dao==null){
            dao = new DaoPizzeria();
        }
        return dao;
    }
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario u1 = new Usuario("Roberto","1234");
        usuarios.add(u1);
        return usuarios;
    }

    public ArrayList<Pizza> getPizzas(){
        ArrayList<String> ingredientes1 = new ArrayList<>();
        ingredientes1.add("Queso");
        ingredientes1.add("Atun");
        ingredientes1.add("Jamon");
        Pizza p1 = new Pizza("Prueba",10.0,ingredientes1);
        pizzas.add(p1);
        return pizzas;
    }

    public void addPizza(Pizza p){
        pizzas.add(p);
    }
}
