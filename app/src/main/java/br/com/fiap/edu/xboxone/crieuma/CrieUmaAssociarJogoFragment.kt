package br.com.fiap.edu.xboxone.crieuma

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.crieuma.jogos.Jogos
import br.com.fiap.edu.xboxone.crieuma.jogos.JogosAdapter
import br.com.fiap.edu.xboxone.databinding.FragmentAssociarJogoAoUsuarioBinding
import br.com.fiap.edu.xboxone.databinding.FragmentCrieumaBinding

class CrieUmaAssociarJogoFragment: Fragment(), CrieUmaContract {
    private var _binding: FragmentAssociarJogoAoUsuarioBinding? = null
    private val binding get() = _binding!!

    private val controller = CrieUmaAssociarJogoController()
    private val jogos = Jogos.getJogosRecomendado()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAssociarJogoAoUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupUI() {
        setupJogosUI() // monta a listagem dos jogos que tem no APP XBox
        setupButtonVoltarUI()
        setupButtonRegistarUI()
    }

    private fun setupJogosUI() {
        binding.rcvJogos.adapter = JogosAdapter(jogos) // Classe responsavel por desenha a lista bem como todos os itens
    }

    private fun setupButtonVoltarUI() {
        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupButtonRegistarUI() {
        binding.btnRegistrar.setOnClickListener {
            val usuario = arguments?.getString("usuario") ?: ""
            controller.associarJogos(usuario, jogos, this) // realiza a inserça dos jogos selecionados
        }
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