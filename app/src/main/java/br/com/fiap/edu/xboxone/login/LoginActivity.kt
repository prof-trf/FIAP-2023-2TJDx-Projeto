package br.com.fiap.edu.xboxone.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.SenhaActivity
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding
/* Declaração da tela de login */
class LoginActivity : AppCompatActivity() {

    /*
        Classe responsável de realizar a leitura do xml de tela
        e prover o suporte para recuperar os componentes.
    */
    private lateinit var binding: ActivityLoginBinding

    /* Classe controladora da tela de login */
    private val controller = LoginController()

    /* Primeiro método a ser executado quando montada a tela */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater) /* realiza a leitura do xml */
        setContentView(binding.root) /* marca a raiz do xml (layout) como interface para a tela */
    }

    /* Método executado após a tela ser montada (lido o xml) */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupEmailUI() /* configura o componente caixa de texto */
        setupBotaoVoltarUI() /* configura o componente botão voltar */
        setupBotaoProximoUI() /* configura o componente botão proximo */
    }

    private fun setupEmailUI() {
        /* addTextChangedListener -> metodo usado para capturar os caracteres teclado pelo usuário */
        binding.edtEmail.addTextChangedListener {
            val text = it.toString() /* recupera os dados da caixa de texto */
            if (controller.temEmail(text)) { /* valida se é um email */
                binding.txtError.visibility = View.GONE /* coloca o texto de erro invisivel e sem ocupar o tamanho na tela */
            }
        }
    }


    private fun setupBotaoVoltarUI() {
        /* setOnClickListener -> metodo usado para capturar o click do botão, no caso voltar */
        binding.btnVoltar.setOnClickListener {
            finish() /* fecha a tela, ou seja, retorna para a tela anterior */
        }

    }

    private fun setupBotaoProximoUI() {
        /* setOnClickListener -> metodo usado para capturar o click do botão, no caso proximo */
        binding.btnProximo.setOnClickListener {
            val username = binding.edtEmail.text.toString() /* recupera os dados da caixa de texto */
            if(controller.validateUsername(username)) { /* metodo para validar o usuario digitado */
                // chamar a proxima activity
                val intent = SenhaActivity.navegar(this@LoginActivity, username) /* recupera a referencia para nevegar de tela */
                startActivity(intent) /* realiza o start da tela que iremos navegar */

            } else {
                binding.txtError.visibility = View.VISIBLE /* coloca o texto de erro visivel */
                binding.txtError.text = "Username inválido" /* adiciona o texto quando ocorrer o erro */
            }
        }
    }

}