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

    public Usuario encontrarUsuarioNombre(String nombre){
        Usuario usuario=null;
        for(Usuario u: usuarios){
            if (u.getUser().equals(nombre)){
                usuario= u;
            }
        }
        return usuario;
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
    public Pizza getPizzaIDPredet(Integer id){
        Pizza pizzaSeleccionada = null;
        for(Pizza p: pizzasPredet){
            if (p.getIdPizza()==id){
                pizzaSeleccionada=p;
            }
        }
        return pizzaSeleccionada;
    }
    public Pizza getPizzaPredet(String nombre){
        Pizza pizza = null;
        for(Pizza p: pizzasPredet){
            if(p.getNombre().equals(nombre)){
                pizza= p;
            }
        }
        return pizza;
    }
    public void addPizza(Pizza p){
        DaoPizzeria.getInstance().addPizza(p);
        inicializar();
    }

    public void addUsuario(Usuario u){
        DaoPizzeria.getInstance().addUsuario(u);
        inicializar();
    }

    public ArrayList<Pizza> getPizzasPredet() {
        return pizzasPredet;
    }
}
