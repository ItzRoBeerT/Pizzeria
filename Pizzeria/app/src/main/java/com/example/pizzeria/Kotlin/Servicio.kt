package com.example.pizzeria.Kotlin

import com.example.pizzeria.Clases.Pizza
import com.example.pizzeria.Clases.Usuario
import com.example.pizzeria.DAO.DaoPizzeria
import java.util.ArrayList

class Servicio {
    var usuarios: ArrayList<Usuario>? = null
        private set
    private var pizzas: ArrayList<Pizza>? = null
    var pizzasPredet: ArrayList<Pizza>? = null
        private set

    init {
        inicializar()
    }

    fun inicializar() {
        usuarios = DaoPizzeria.getInstance().usuarios
        pizzas = DaoPizzeria.getInstance().pizzas
        pizzasPredet = DaoPizzeria.getInstance().pizzasPredeterminadas
    }

    fun getPizzaID(id: Int): Pizza? {
        var pizzaSeleccionada: Pizza? = null
        for (p in pizzas!!) {
            if (p.idPizza === id) {
                pizzaSeleccionada = p
            }
        }
        return pizzaSeleccionada
    }

    fun getPizzaIDPredet(id: Int): Pizza? {
        var pizzaSeleccionada: Pizza? = null
        for (p in pizzasPredet!!) {
            if (p.idPizza === id) {
                pizzaSeleccionada = p
            }
        }
        return pizzaSeleccionada
    }

    fun getPizzaPredet(nombre: String): Pizza? {
        var pizza: Pizza? = null
        for (p in pizzasPredet!!) {
            if (p.nombre == nombre) {
                pizza = p
            }
        }
        return pizza
    }

    fun addPizza(p: Pizza?) {
        DaoPizzeria.getInstance().addPizza(p)
        inicializar()
    }
}