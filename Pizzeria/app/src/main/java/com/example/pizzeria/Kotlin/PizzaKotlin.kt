package com.example.pizzeria.Kotlin

import java.util.ArrayList

class PizzaKotlin(var nombre: String, var precio: Double, var ingredientes: ArrayList<String>) {
    var tiempoPrep: Int? = null
    val idPizza: Int

    init {
        idPizza = idP
        idP++
    }

    fun mostrarIngredientes(): String {
        var txt = ""
        for (ingrediente in ingredientes) {
            txt += """
                $ingrediente
                
                """.trimIndent()
        }
        return txt
    }

    companion object {
        private var idP = 1
    }
}