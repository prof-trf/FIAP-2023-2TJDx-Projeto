package br.com.fiap.edu.xboxone.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding
import br.com.fiap.edu.xboxone.login.contrato.IValidacaoUsuarioView
import br.com.fiap.edu.xboxone.senha.SenhaActivity
import java.lang.Exception

/* Declaração da tela de login */
class LoginActivity : AppCompatActivity(), IValidacaoUsuarioView {

    /*
        Classe responsável de realizar a leitura do xml de tela
        e prover o suporte para recuperar os componentes.
    */
    private lateinit var binding: ActivityLoginBinding

    /* Classe controladora da tela de login */
    private val controller = LoginController()

    private var countDownTimer: CountDownTimer? = null
    private var countDownTimerStated = false
    private var isPaused = false

    /* Primeiro método a ser executado quando montada a tela */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show()

        binding = ActivityLoginBinding.inflate(layoutInflater) /* realiza a leitura do xml */
        setContentView(binding.root) /* marca a raiz do xml (layout) como interface para a tela */
    }

    /* Método executado após a tela ser montada (lido o xml) */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Toast.makeText(this, "onPostCreate", Toast.LENGTH_LONG).show()

        setupBotaoVoltarUI() /* configura o componente botão voltar */
        setupBotaoProximoUI() /* configura o componente botão proximo */
        setupCaixaTextoEmailUI() /* configura o componente caixa de texto */

        countDownTimer = object: CountDownTimer(1000000, 1000) {
            override fun onTick(p0: Long) {
                if(isPaused) {
                    Toast.makeText(this@LoginActivity, p0.toString(), Toast.LENGTH_SHORT).show()
                } else {
                    binding.edtEmail.setText(p0.toString())
                }
            }

            override fun onFinish() {
                Toast.makeText(this@LoginActivity, "terminou", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show()
        isPaused = false

        if (!countDownTimerStated) {
            countDownTimerStated = true
            countDownTimer?.start()
        }
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show()
        isPaused = true
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show()
        countDownTimer?.cancel()
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
            controller.validateUsername(usuario, this) /* metodo para validar o usuario digitado */
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

    //// IValidacaoUsuarioView implementacao
    override fun pesquisandoUsuario() {
        binding.progressBar.visibility = View.VISIBLE /* aparece o progressbar */
    }

    override fun usuarioLocalizadoNaBaseDeDados(usuario: String) {
        binding.progressBar.visibility = View.GONE /* esconde o progressbar */

        val intent = SenhaActivity.navegar(this@LoginActivity, usuario) /* recupera a referencia para nevegar de tela */
        startActivity(intent) /* realiza o start da tela que iremos navegar */
    }

    override fun erroNaPesquisaDaBaseDedados(error: Exception) {
        binding.progressBar.visibility = View.GONE /* esconde o progressbar */

        binding.edtEmail.backgroundTintList =
            AppCompatResources.getColorStateList(baseContext, R.color.red)
    }
}