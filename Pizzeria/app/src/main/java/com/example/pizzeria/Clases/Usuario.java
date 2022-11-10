package com.example.pizzeria.Clases;

import java.util.ArrayList;

public class Usuario {
    private String user,password;
    private static ArrayList<Pizza> pizzasPedidas;
    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
        this.pizzasPedidas=new ArrayList<>();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Pizza> getPizzasPedidas() {
        return pizzasPedidas;
    }
    public void addPizzaPedida(Pizza p){
        pizzasPedidas.add(p);
    }

    public Pizza calcularPizzaFav(){

        Pizza pizzaFavorita=null;
        int contador=0,maxApariciones=0;
        for(Pizza p: this.pizzasPedidas){
            for(Pizza p2: this.pizzasPedidas){
                if (p.getNombre().equals(p2.getNombre())){
                    contador++;
                }
            }
            if (contador>maxApariciones){
                maxApariciones=contador;
                pizzaFavorita=p;
            }
        }
        return pizzaFavorita;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
