package com.example.pizzeria.DAO;

import com.example.pizzeria.Clases.Pizza;
import com.example.pizzeria.Clases.Usuario;
import com.example.pizzeria.MainActivity;

import java.util.ArrayList;

public class DaoPizzeria {

    private static DaoPizzeria dao = null;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Usuario> usuarios;
    private  RealDaoPizzeria daoReal;
    private DaoPizzeria(){
        pizzas = new ArrayList<>();
        usuarios= new ArrayList<>();
    }

    public static DaoPizzeria getInstance(){
        if (dao==null){
            dao = new DaoPizzeria();
        }
        return dao;
    }
    public ArrayList<Usuario> getUsuarios(){
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

    public ArrayList<Pizza> getPizzasPredeterminadas(){
        ArrayList<Pizza> pizzasPerso= new ArrayList<>();

        ArrayList<String> ingredientes1 = new ArrayList<>();
        ingredientes1.add("Tomate");
        ingredientes1.add("Mozzarela");
        ingredientes1.add("Jamon");
        ArrayList<String> ingredientes2 = new ArrayList<>();
        ingredientes2.add("Queso");
        ingredientes2.add("Gorgonzola");
        ingredientes2.add("Provolone");
        ingredientes2.add("Parmesano");
        ArrayList<String> ingredientes3 = new ArrayList<>();
        ingredientes3.add("Bacon");
        ingredientes3.add("Salsa especial de la casa");
        ingredientes3.add("Carne picada");
        ingredientes3.add("Salsa picante");
        Pizza p1 = new Pizza("Margarita",10.0,ingredientes1);
        pizzasPerso.add(p1);
        Pizza p2 = new Pizza(" 4 Quesos",12.0,ingredientes2);
        pizzasPerso.add(p2);
        Pizza p3 = new Pizza("Barbacoa",15.0, ingredientes3);
        pizzasPerso.add(p3);

        return pizzasPerso;
    }

    public void addPizza(Pizza p){
        pizzas.add(p);
    }
    public void addUsuario(Usuario u){
        usuarios.add(u);

    }
}
