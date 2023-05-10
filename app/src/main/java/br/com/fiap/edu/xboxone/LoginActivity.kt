package br.com.fiap.edu.xboxone

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding


class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.btnVoltar.setOnClickListener { finish() }

        binding.btnProximo.setOnClickListener {
            val usuario = binding.edtEmail.text.toString()
            val validadorUsuario = validarUsuario(usuario)
            if(validadorUsuario) {
                //vamos pra tela de senha
                val intent = Intent(this@LoginActivity, SenhaActivity::class.java)
                intent.putExtra("username", usuario)
                startActivity(intent)
            } else {
                binding.edtEmail.backgroundTintList =
                    AppCompatResources.getColorStateList(baseContext, R.color.red)
            }
        }

        binding.edtEmail.addTextChangedListener {
            val text = it.toString()
            if(text.isNotEmpty()) {
                binding.edtEmail.backgroundTintList =
                    AppCompatResources.getColorStateList(baseContext, R.color.light_gray)
            }
        }
    }

    private fun validarUsuario(usuario: String): Boolean {
        // eh e-mail
        if(usuario.contains("@")) {
            return true
        }

        // eh telefone
        val procuraLetra = usuario.find { !it.isDigit() }
        if(usuario.length == 11 && procuraLetra == null) {
            return true
        }

        // eh skype
        if(usuario.isNotEmpty() && usuario.length >= 5) {
            return true
        }

        return false
    }
}