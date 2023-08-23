package br.com.fiap.edu.xboxone.senha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.databinding.FragmentSenhaBinding

/* Declaração da tela de login */
class SenhaFragment : Fragment() {

    private var _binding: FragmentSenhaBinding? = null
    private val binding get() = _binding!!

    private val controller = SenhaController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSenhaBinding.inflate(inflater, container, false) /* realiza a leitura do xml */
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextUsernameUI() /* configura o componente textview */
        setupBotaoEntrarUI() /* configura o componente button */
        setupBotaoVoltarUI() /* configura o componente button */
        setupCaixaTextoSenhaUI() /* configura o componente caixa de texto */
    }

    private fun setupTextUsernameUI() {
        val username = arguments?.getString("usuario") /* recupera o parametro enviado da linha 17 */
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
            activity?.finish() /* fecha a tela, ou seja, retorna para a tela anterior */
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