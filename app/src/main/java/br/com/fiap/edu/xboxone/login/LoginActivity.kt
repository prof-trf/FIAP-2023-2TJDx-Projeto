package br.com.fiap.edu.xboxone.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding
import br.com.fiap.edu.xboxone.senha.SenhaActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val controller = LoginController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupBotaoVoltarUI()
        setupBotaoProximoUI()
        setupCaixaTextoEmailUI()
    }

    private fun setupBotaoVoltarUI() {
        binding.btnVoltar.setOnClickListener { finish() }
    }

    private fun setupBotaoProximoUI() {
        binding.btnProximo.setOnClickListener {
            val usuario = binding.edtEmail.text.toString()
            val validadorUsuario = controller.validarUsuario(usuario)
            if (validadorUsuario) {
                navegarParaTelaSenha(usuario)
            } else {
                binding.edtEmail.backgroundTintList =
                    AppCompatResources.getColorStateList(baseContext, R.color.red)
            }
        }
    }

    private fun setupCaixaTextoEmailUI() {
        binding.edtEmail.addTextChangedListener {
            val text = it.toString()
            if (controller.temEmail(text)) {
                binding.edtEmail.backgroundTintList =
                    AppCompatResources.getColorStateList(baseContext, R.color.light_gray)
            }
        }
    }

    private fun navegarParaTelaSenha(usuario: String) {
        val intent = SenhaActivity.navegar(this@LoginActivity, usuario)
        startActivity(intent)
    }
}