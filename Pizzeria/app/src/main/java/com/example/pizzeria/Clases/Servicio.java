package com.example.pizzeria.Clases;

import com.example.pizzeria.DAO.DaoPizzeria;

import java.util.ArrayList;

public class Servicio {

    private ArrayList<Usuario> usuarios;
    public Servicio(){
       inicializar();
    }

    public ArrayList<Usuario> getUsuarios(){
        return  usuarios;
    }

    public void inicializar(){
        usuarios = DaoPizzeria.getInstance().getUsuarios();
    }
}
