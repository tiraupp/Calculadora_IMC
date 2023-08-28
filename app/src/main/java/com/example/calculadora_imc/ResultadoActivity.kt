package com.example.calculadora_imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculadora_imc.databinding.ActivityMainBinding
import com.example.calculadora_imc.databinding.ResultadoActivityBinding


class ResultadoActivity : AppCompatActivity()  {

    private lateinit var binding: ResultadoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultadoActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.btnFecharResultados.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            })
        val imc = intent.getSerializableExtra("imc") as Imc
        binding.titleDensidadeCorporal.text = imc.nome.toString()
        binding.textViewHeaderDensidadeCorporal.text = imc.calcular()
        binding.textViewResultadoDensidadeCorporal.text = imc.imc.toString()


    }
}