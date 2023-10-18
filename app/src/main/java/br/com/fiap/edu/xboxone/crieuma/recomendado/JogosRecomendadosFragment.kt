package br.com.fiap.edu.xboxone.crieuma.recomendado

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.crieuma.CrieUmaContract
import br.com.fiap.edu.xboxone.databinding.FragmentJogosRecomendadosBinding

class JogosRecomendadosFragment: Fragment(), CrieUmaContract {

    private var _binding: FragmentJogosRecomendadosBinding? = null
    private val binding get() = _binding!!

    private val controller = JogosRecomendadosController()
    private val jogos = JogosSuportados.getJogos().map { Recomendados(it.key, it.value) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJogosRecomendadosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListaJogosUI()
        setupButtonRegistrarUI()
    }

    private fun setupListaJogosUI() {
        binding.rcvJogos.adapter = JogosSuportadosAdapter(jogos)
    }

    private fun setupButtonRegistrarUI() {
        val username = arguments?.getString("usuario") ?: ""

        binding.btnRegistrar.setOnClickListener {
            val jogosFiltrado = jogos.filter { it.recomendado }
            controller.registrarGamePass(username, jogosFiltrado, this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun loadingCadastrandoUsuario() {
        binding.progressBar.isVisible = true
    }

    override fun usuarioCadastradoComSucesso() {
        binding.progressBar.isVisible = false

        val alert = AlertDialog.Builder(requireActivity())
            .setTitle("Usuario Gravado com Sucesso")
            .setMessage("Um usuario foi inserido com sucesso na base de dados")
            .setPositiveButton("Ok") { _, _ ->
                requireActivity().onBackPressed()
            }
            .create()
        alert.show()
    }

    override fun usuarioFalhaNoCadastro(message: String) {
        binding.progressBar.isVisible = false

        val alert = AlertDialog.Builder(requireActivity())
            .setTitle("Falha ao gravar o usuario")
            .setMessage("Um usuario não foi inserido na base de dados")
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alert.show()
    }

}