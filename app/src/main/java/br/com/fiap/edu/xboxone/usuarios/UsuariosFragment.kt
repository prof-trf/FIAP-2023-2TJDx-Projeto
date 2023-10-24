package br.com.fiap.edu.xboxone.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.databinding.FragmentUsuariosBinding
import br.com.fiap.edu.xboxone.usuarios.view.ItemUsuariosView


class UsuariosFragment: Fragment(), ItemClicadoListener {

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
        setListaUsuriosUI()
        setBotaoVoltarUI()
        setBotaoRegistrarUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setListaUsuriosUI() {
        val usuarios = XboxApplication.database.getUserDao().getUsuarios().map {
            ItemUsuariosView(it.username, it.active == 1)
        }
        binding.recyclerView.adapter = UsuariosAdapter(usuarios, this)
    }

    private fun setBotaoVoltarUI() {
        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setBotaoRegistrarUI() {
        binding.btnRegistrar.setOnClickListener {
            val action = UsuariosFragmentDirections.actionUsuariosFragmentToCadastroUsuarioFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun itemClicado(email: String) {
        val action = UsuariosFragmentDirections.actionUsuariosFragmentToCadastroUsuarioFragment(email)
        findNavController().navigate(action)
    }


}