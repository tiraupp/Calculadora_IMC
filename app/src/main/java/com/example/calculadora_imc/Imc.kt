package com.example.calculadora_imc

import java.io.Serializable

class Imc(
    var nome:String,
    var peso: Float,
    var altura: Float
) : Serializable {
    var imc: Float = 0.0f
    fun calcular() : String{

        val alt =altura/100
        val calc = peso / (alt*alt)
        var msg = when (calc){
            in 0.0..16.0  -> "Magreza grave"
            in 16.0..17.0 ->"Magreza moderada"
            in 17.0..19.0 ->"Magreza leve"
            in 19.0..25.0 ->"Saúdavel"
            in 25.0..30.0 ->"Sobrepeso"
            in 30.0..35.0 ->"Obsesidade I"
            in 35.0..40.0 ->"Obsesidade II"
            else -> "Obsedidade Morbida."
        }
        imc = calc
        return msg
    }
}
