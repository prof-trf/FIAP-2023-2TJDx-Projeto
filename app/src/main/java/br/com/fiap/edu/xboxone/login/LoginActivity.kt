package br.com.fiap.edu.xboxone.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.SenhaActivity
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val controller = LoginController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupEmailUI()
        setupBotaoVoltarUI()
        setupBotaoProximoUI()
    }

    private fun setupEmailUI() {
        binding.edtEmail.addTextChangedListener {
            if(controller.temEmail(it.toString())) {
                binding.txtError.visibility = View.GONE
            }
        }
    }

    private fun setupBotaoVoltarUI() {
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun setupBotaoProximoUI() {
        binding.btnProximo.setOnClickListener {
            val username = binding.edtEmail.text.toString()
            if(controller.validateUsername(username)) {
                // chamar a proxima activity
                val intent = SenhaActivity.navegar(this@LoginActivity, username)
                startActivity(intent)

            } else {
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = "Username inválido"
            }
        }
    }

}