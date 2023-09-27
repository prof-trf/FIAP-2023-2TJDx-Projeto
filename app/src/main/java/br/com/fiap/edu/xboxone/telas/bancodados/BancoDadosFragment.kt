package br.com.fiap.edu.xboxone.telas.bancodados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.User
import br.com.fiap.edu.xboxone.databinding.FragmentBancodadosBinding
import br.com.fiap.edu.xboxone.databinding.FragmentTemplateBinding

class BancoDadosFragment: Fragment() {

    private var _binding: FragmentBancodadosBinding? = null
    private val binding get() = _binding!!

    private val database = XboxApplication.database

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBancodadosBinding.inflate(inflater, container, false)
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

    fun setupUI() {
        binding.btnGravar.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val sobrenome = binding.edtSobrenome.text.toString()

            val id = System.currentTimeMillis().toInt()
            val user = User(id, nome, sobrenome)

            database.userDao().inserirUsuario(user)

            Toast.makeText(requireActivity(), "usuario cadastrado", Toast.LENGTH_SHORT).show()
        }
    }

}