package br.com.fiap.edu.xboxone.senha

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.databinding.ActivitySenhaBinding

class SenhaActivity : AppCompatActivity() {
    companion object {
        private const val USERNAME_PARAMS = "username"
        fun navegar(packageContext: Context, usuario: String): Intent {
            val intent = Intent(packageContext, SenhaActivity::class.java)
            intent.putExtra(USERNAME_PARAMS, usuario)
            return intent
        }
    }

    private lateinit var binding: ActivitySenhaBinding
    private val controller = SenhaController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupTextUsernameUI()
        setupBotaoEntrarUI()
        setupBotaoVoltarUI()
        setupCaixaTextoSenhaUI()
    }

    private fun setupTextUsernameUI() {
        val username = intent.getStringExtra(USERNAME_PARAMS)
        binding.txtUsename.text = username
    }

    private fun setupBotaoEntrarUI() {
        binding.btnEntrar.setOnClickListener {
            val senha = binding.edtSenha.text.toString()
            if (controller.validateSenha(senha)) {
                navegarParaTelaX()
            } else {
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = "Digite uma senha válida"
            }
        }
    }

    private fun setupBotaoVoltarUI() {
        binding.imvVoltar.setOnClickListener {
            finish()
        }
    }

    private fun setupCaixaTextoSenhaUI() {
        binding.edtSenha.addTextChangedListener {
            val senha = it.toString()
            if (controller.temSenha(senha)) {
                binding.txtError.visibility = View.GONE
            }
        }
    }

    private fun navegarParaTelaX() {

    }

}