package com.example.pizzeria.Clases;

import java.util.ArrayList;

public class Pizza {
    private String nombre;
    private Double precio;
    private Integer tiempoPrep;
    private ArrayList<String> ingredientes;

    public Pizza(String nombre, Double precio, ArrayList<String> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getTiempoPrep() {
        return tiempoPrep;
    }

    public void setTiempoPrep(Integer tiempoPrep) {
        this.tiempoPrep = tiempoPrep;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
