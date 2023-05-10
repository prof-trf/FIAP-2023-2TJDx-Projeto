package br.com.fiap.edu.xboxone.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding
import br.com.fiap.edu.xboxone.senha.SenhaActivity

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

        setupBotaoVoltarUI() /* configura o componente botão voltar */
        setupBotaoProximoUI() /* configura o componente botão proximo */
        setupCaixaTextoEmailUI() /* configura o componente caixa de texto */
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
            val usuario = binding.edtEmail.text.toString() /* recupera os dados da caixa de texto */

            val validadorUsuario = controller.validarUsuario(usuario) /* metodo para validar o usuario digitado */
            if (validadorUsuario) {
                navegarParaTelaSenha(usuario) /* navega para a tela de senha */
            } else {
                binding.edtEmail.backgroundTintList =
                    AppCompatResources.getColorStateList(baseContext, R.color.red)
            }
        }
    }

    private fun setupCaixaTextoEmailUI() {
        /* addTextChangedListener -> metodo usado para capturar os caracteres teclado pelo usuário */
        binding.edtEmail.addTextChangedListener {
            val text = it.toString() /* recupera os dados da caixa de texto */
            if (controller.temEmail(text)) { /* valida se é um email */
                binding.edtEmail.backgroundTintList =
                    AppCompatResources.getColorStateList(baseContext, R.color.light_gray) /* altera a cor da linha da caixa de texto */
            }
        }
    }

    private fun navegarParaTelaSenha(usuario: String) {
        val intent = SenhaActivity.navegar(this@LoginActivity, usuario) /* recupera a referencia para nevegar de tela */
        startActivity(intent) /* realiza o start da tela que iremos navegar */
    }
}