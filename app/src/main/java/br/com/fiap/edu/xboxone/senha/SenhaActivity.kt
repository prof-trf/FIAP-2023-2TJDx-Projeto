package br.com.fiap.edu.xboxone.senha

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.databinding.ActivitySenhaBinding

/* Declaração da tela de login */
class SenhaActivity : AppCompatActivity() {
    companion object {
        private const val USERNAME_PARAMS = "username"
        fun navegar(packageContext: Context, usuario: String): Intent {
            val intent = Intent(packageContext, SenhaActivity::class.java) /* configura a intent, tela onde estou e para qual irá navegar */
            intent.putExtra(USERNAME_PARAMS, usuario) /* adiciona um parametro para tela */
            return intent
        }
    }

    private lateinit var binding: ActivitySenhaBinding
    private val controller = SenhaController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySenhaBinding.inflate(layoutInflater) /* realiza a leitura do xml */
        setContentView(binding.root) /* marca a raiz do xml (layout) como interface para a tela */
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupTextUsernameUI() /* configura o componente textview */
        setupBotaoEntrarUI() /* configura o componente button */
        setupBotaoVoltarUI() /* configura o componente button */
        setupCaixaTextoSenhaUI() /* configura o componente caixa de texto */
    }

    private fun setupTextUsernameUI() {
        val username = intent.getStringExtra(USERNAME_PARAMS) /* recupera o parametro enviado da linha 17 */
        binding.txtUsename.text = username /* marca na tela o texto do parametro recuperado */
    }

    private fun setupBotaoEntrarUI() {
        /* setOnClickListener -> metodo usado para capturar o click do botão, no caso entrar */
        binding.btnEntrar.setOnClickListener {
            val senha = binding.edtSenha.text.toString() /* recupera os dados da caixa de texto */
            if (controller.validateSenha(senha)) { /* metodo para validar a senha do usuario */
                navegarParaTelaX()
            } else {
                binding.txtError.visibility = View.VISIBLE /* coloca o texto de erro visivel */
                binding.txtError.text = "Digite uma senha válida" /* adiciona o texto quando ocorrer o erro */
            }
        }
    }

    private fun setupBotaoVoltarUI() {
        /* setOnClickListener -> metodo usado para capturar o click do botão, no caso voltar */
        binding.imvVoltar.setOnClickListener {
            finish() /* fecha a tela, ou seja, retorna para a tela anterior */
        }
    }

    private fun setupCaixaTextoSenhaUI() {
        /* addTextChangedListener -> metodo usado para capturar os caracteres teclado pelo usuário */
        binding.edtSenha.addTextChangedListener {
            val senha = it.toString() /* recupera os dados da caixa de texto */
            if (controller.temSenha(senha)) { /* metodo para validar a senha do usuario */
                binding.txtError.visibility = View.GONE /* coloca o texto de erro invisivel e sem ocupar o tamanho na tela */
            }
        }
    }

    private fun navegarParaTelaX() {

    }

}