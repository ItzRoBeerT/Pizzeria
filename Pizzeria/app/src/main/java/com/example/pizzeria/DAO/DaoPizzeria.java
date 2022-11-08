package com.example.pizzeria.DAO;

import com.example.pizzeria.Clases.Usuario;

import java.util.ArrayList;

public class DaoPizzeria {

    private static DaoPizzeria dao = null;

    private DaoPizzeria(){

    }

    public static DaoPizzeria getInstance(){
        if (dao==null){
            dao = new DaoPizzeria();
        }
        return dao;
    }

    public static void getPizzas(){

    }

    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario u1 = new Usuario("Roberto","1234");
        usuarios.add(u1);

        return usuarios;
    }
}
