package br.com.fiap.edu.xboxone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.edu.xboxone.databinding.ActivitySenhaBinding

class SenhaActivity: AppCompatActivity() {

    companion object {
        private const val USERNAME_PARAMS = "username"

        fun navegar(packageContext: Context, username: String): Intent {
            val intent = Intent(packageContext, SenhaActivity::class.java)
            intent.putExtra(USERNAME_PARAMS, username)
            return intent
        }
    }

    private lateinit var binding: ActivitySenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val username = intent.getStringExtra(USERNAME_PARAMS)
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