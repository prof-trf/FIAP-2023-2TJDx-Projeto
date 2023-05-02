package br.com.fiap.edu.xboxone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.edu.xboxone.databinding.ActivitySenhaBinding

class SenhaActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val username = intent.getStringExtra("username")
        binding.txtUsename.text = username

        val senha = binding.edtSenha.text.toString()
        binding.btnEntrar.setOnClickListener {
            if(validateSenha(senha)) {
                // vamos para proxima tela
            } else {
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = "Digite uma senha válida"
            }
        }
    }

    private fun validateSenha(senha: String): Boolean {
        // senha deve ter no minimo 8 caracteres
        val temTamanho = senha.length >= 8

        // um caracter numerico
        val temNumero = senha.any { it.isDigit() }


        val ehASenha = senha == "EU SOU A LENHA 2023"

        return temTamanho && temNumero && ehASenha
    }
}