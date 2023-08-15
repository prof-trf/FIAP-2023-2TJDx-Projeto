package br.com.fiap.edu.xboxone.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.FragmentLoginBinding
import br.com.fiap.edu.xboxone.login.contrato.IValidacaoUsuarioView
import br.com.fiap.edu.xboxone.senha.SenhaActivity
import java.lang.Exception

class LoginFragment: Fragment(), IValidacaoUsuarioView {
    /*
        Classe responsável de realizar a leitura do xml de tela
        e prover o suporte para recuperar os componentes.
    */
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    /* Classe controladora da tela de login */
    private val controller = LoginController()

    private var countDownTimer: CountDownTimer? = null
    private var countDownTimerStated = false
    private var isPaused = false

    /* Primeiro método a ser executado quando montada a tela */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(requireActivity(), "onCreateView", Toast.LENGTH_LONG).show()

        _binding = FragmentLoginBinding.inflate(inflater, container, false) /* realiza a leitura do xml */
        return binding.root
    }

    /* Método executado após a tela ser montada (lido o xml) */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireActivity(), "onViewCreated", Toast.LENGTH_LONG).show()

        setupBotaoVoltarUI() /* configura o componente botão voltar */
        setupBotaoProximoUI() /* configura o componente botão proximo */
        setupCaixaTextoEmailUI() /* configura o componente caixa de texto */

        countDownTimer = object: CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                if(isPaused) {
                    Toast.makeText(requireActivity(), (p0/1000).toString(), Toast.LENGTH_SHORT).show()
                } else {
                    binding.edtEmail.setText((p0/1000).toString())
                }
            }

            override fun onFinish() {
                Toast.makeText(requireActivity(), "terminou", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(requireActivity(), "onResume", Toast.LENGTH_LONG).show()
        isPaused = false

        if (!countDownTimerStated) {
            countDownTimerStated = true
            countDownTimer?.start()
        }
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(requireActivity(), "onPause", Toast.LENGTH_LONG).show()
        isPaused = true
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(requireActivity(), "onDestroy", Toast.LENGTH_LONG).show()
        countDownTimer?.cancel()
    }

    private fun setupBotaoVoltarUI() {
        /* setOnClickListener -> metodo usado para capturar o click do botão, no caso voltar */
        binding.btnVoltar.setOnClickListener {
            activity?.finish() /* fecha a tela, ou seja, retorna para a tela anterior */
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
                    AppCompatResources.getColorStateList(requireContext(), R.color.light_gray) /* altera a cor da linha da caixa de texto */
            }
        }
    }

    //// IValidacaoUsuarioView implementacao
    override fun pesquisandoUsuario() {
        binding.progressBar.visibility = View.VISIBLE /* aparece o progressbar */
    }

    override fun usuarioLocalizadoNaBaseDeDados(usuario: String) {
        binding.progressBar.visibility = View.GONE /* esconde o progressbar */

        val intent = SenhaActivity.navegar(requireActivity(), usuario) /* recupera a referencia para nevegar de tela */
        startActivity(intent) /* realiza o start da tela que iremos navegar */
    }

    override fun erroNaPesquisaDaBaseDedados(error: Exception) {
        binding.progressBar.visibility = View.GONE /* esconde o progressbar */

        binding.edtEmail.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.red)
    }
}