package br.com.fiap.edu.xboxone.login

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.FragmentLoginBinding
import br.com.fiap.edu.xboxone.login.contrato.IValidacaoUsuarioView
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

    /* Primeiro método a ser executado quando montada a tela */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    /* Método executado após a tela ser montada (lido o xml) */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireActivity(), "onPostCreate", Toast.LENGTH_SHORT).show()

        setupBotaoVoltarUI() /* configura o componente botão voltar */
        setupBotaoProximoUI() /* configura o componente botão proximo */
        setupCaixaTextoEmailUI() /* configura o componente caixa de texto */

        setupTextoCrieUmaUI()

        /* Classe que realiza o contador regressivo */
        countDownTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("onTick", millisUntilFinished.toString())
            }

            override fun onFinish() {
            }
        }
    }

    /* Chamada realizada após a tela retornar de background ou seja tornar ativa novamente */
    override fun onResume() {
        super.onResume()
        Toast.makeText(requireActivity(), "onResume", Toast.LENGTH_SHORT).show()
    }

    /* Chamada realizada após a tela ficar e background */
    override fun onPause() {
        super.onPause()
        Toast.makeText(requireActivity(), "onPause", Toast.LENGTH_SHORT).show()
        countDownTimer?.start()
    }

    /* Chamada realizada apos o fechamento da tela */
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

        Toast.makeText(requireActivity(), "onDestroy", Toast.LENGTH_SHORT).show()
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

    private fun setupTextoCrieUmaUI() {
        binding.txtCrieUma.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToCrieUmaFragment()
            findNavController().navigate(action)
        }
    }

    //// IValidacaoUsuarioView implementacao
    override fun pesquisandoUsuario() {
        binding.progressBar.visibility = View.VISIBLE /* aparece o progressbar */
        binding.txtMessageErro.visibility = View.GONE
    }

    override fun usuarioLocalizadoNaBaseDeDados(usuario: String) {
        binding.progressBar.visibility = View.GONE /* esconde o progressbar */

        /* recupera a referencia para nevegar de tela */
        val directions = LoginFragmentDirections.actionLoginFragmentToSenhaFragment(usuario)
        findNavController().navigate(directions) /* realiza o start da tela que iremos navegar */
    }

    override fun erroNaPesquisaDaBaseDedados(error: Exception) {
        binding.progressBar.visibility = View.GONE /* esconde o progressbar */

        binding.txtMessageErro.visibility = View.VISIBLE
        binding.txtMessageErro.text = error.message

        /* muda a cor da linha da caixa de texto */
        binding.edtEmail.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.red)
    }
}