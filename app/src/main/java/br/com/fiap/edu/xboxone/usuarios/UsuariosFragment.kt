package br.com.fiap.edu.xboxone.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.databinding.FragmentUsuariosBinding

class UsuariosFragment: Fragment(), ItemUsuarioListener {
    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsuariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListaUsuariosUI()
    }

    private fun setupListaUsuariosUI() {
        val usuarios = XboxApplication.database.getUserDao().getUsuarios()
        val users = usuarios.map { ItemUsuario(it.username, it.active == 1) }

        binding.recyclerView.adapter = UsuariosAdapter(users, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun usuarioSelecionado(email: String) {
        println(email)
        val action = UsuariosFragmentDirections.actionUsuariosFragmentToFormUsuarioFragment(email)
        findNavController().navigate(action)
    }
}