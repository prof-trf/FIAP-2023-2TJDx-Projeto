package br.com.fiap.edu.xboxone.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.edu.xboxone.databinding.FragmentUsuariosBinding
import br.com.fiap.edu.xboxone.usuarios.contract.ListaUsuarioContract

class UsuariosFragment: Fragment(), ItemUsuarioListener {
    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!

    private val controller = UsuariosController()

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
        controller.getUsuarios(object: ListaUsuarioContract {
            override fun sucesso(users: List<ItemUsuario>) {
                binding.recyclerView.adapter = UsuariosAdapter(users, this@UsuariosFragment)
            }
        })
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