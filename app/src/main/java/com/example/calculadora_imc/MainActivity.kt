package com.example.calculadora_imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculadora_imc.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import java.text.MessageFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCalc.setOnClickListener(View.OnClickListener {
            if( validarCamposBasicos()){
                val name= binding.editNome.text.toString()
                val peso= binding.editPeso.text.toString().toFloat()
                val altura= binding.editAltura.text.toString().toFloat()
                val imc = Imc(name,peso,altura)
                val intent = Intent(this,ResultadoActivity::class.java)
                intent.putExtra("imc", imc)
                startActivity(intent)
            }

        })
        binding.editNome.addTextChangedListener(clearErrorMessage(binding.layoutEditNome))
        binding.editAltura.addTextChangedListener(clearErrorMessage(binding.layoutEditAltura))
        binding.editPeso.addTextChangedListener(clearErrorMessage(binding.layoutEditPeso))

        findViewById<Button>(R.id.btnCalc).setOnLongClickListener(){
            Toast.makeText(this,"Clique longo no botão",Toast.LENGTH_LONG).show()
            true
        }

    }



    private fun exibirMensagemErro(editText: TextView, textViewMessage: TextInputLayout, mensagem: String ) {
        textViewMessage.error = mensagem
        editText.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        editText.requestFocus();
    }

    fun exibirMensagemErroNome(){
        val mensagem = formatarMensagem("Campo")
        exibirMensagemErro(binding.editNome, binding.layoutEditNome, mensagem)
    }

    fun exibirMensagemErroPeso(){
        val mensagem = formatarMensagem("Campo")
        exibirMensagemErro(binding.editPeso, binding.layoutEditPeso, mensagem)
    }

    fun exibirMensagemErroAltura(){
        val mensagem = formatarMensagem("Campo")
        exibirMensagemErro(binding.editAltura, binding.layoutEditAltura, mensagem)
    }

    private fun validarCamposBasicos(): Boolean {
        Log.e("ERRO", binding.editNome.toString()+"dsbhfkjsdhf")
        clearErrorMessage(binding.layoutEditNome)
        val tam =binding.editNome.text.toString().length
        if (isEmpty(binding.editNome.text.toString()) || tam < 3) {
            exibirMensagemErroNome()
            return false
        }
        clearErrorMessage(binding.layoutEditPeso)

        if (isEmpty(binding.editPeso.text.toString())) {
            exibirMensagemErroPeso()
            return false
        }
        clearErrorMessage(binding.layoutEditAltura)

        if (isEmpty(binding.editAltura.text.toString())) {
            exibirMensagemErroAltura()
            return false
        }
        return true
    }

    private fun isEmpty(valor: String) = valor == ""

    private fun formatarMensagem(campo:String) : String{
        val message = "Informe o campo"
        return  MessageFormat.format(message, campo)
    }

    private fun clearErrorMessage(text: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int ) { }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int ) {
                text.error = ""
            }
        }

    }
}