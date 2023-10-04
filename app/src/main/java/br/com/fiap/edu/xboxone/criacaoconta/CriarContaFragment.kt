package br.com.fiap.edu.xboxone.criacaoconta

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.databinding.FragmentCriarcontaBinding

class CriarContaFragment: Fragment(), CriarContaContract {
    private var _binding: FragmentCriarcontaBinding? = null
    private val binding get() = _binding!!

    private val controller = CriarContaController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCriarcontaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonVoltarUI()
        setupButtonGravarUI()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupButtonVoltarUI() {
        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    private fun setupButtonGravarUI() {
        binding.btnGravar.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val senha = binding.edtSenha.text.toString()

            controller.gravarUsuario(email, senha, this)
        }
    }

    // Metodos do contrato
    override fun gravandoInformacao() {
        binding.progressBar.isVisible = true
    }

    override fun usuarioGravadoComSucess() {
        binding.progressBar.isVisible = false

        val alert = AlertDialog.Builder(requireActivity())
            .setTitle("Usuario Gravado com sucesso")
            .setMessage("Um usuário foi inserido na base de dados")
            .setPositiveButton("Ok") { dialog, _ ->
                requireActivity().onBackPressed()
            }
            .create()

        alert.show()
    }

    override fun erroAoGravarUsuario(message: String) {
        binding.progressBar.isVisible = false

        val alert = AlertDialog.Builder(requireActivity())
            .setTitle("Usuario não foi gravado")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        alert.show()
    }
}