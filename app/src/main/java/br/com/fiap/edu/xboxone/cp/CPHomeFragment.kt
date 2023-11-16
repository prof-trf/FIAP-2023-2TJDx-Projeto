package br.com.fiap.edu.xboxone.cp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.fiap.edu.xboxone.databinding.FragmentCpHomeBinding
import br.com.fiap.edu.xboxone.login.LoginFragmentDirections

/**
 * Tela Home do Checkpoint
 */
class CPHomeFragment: Fragment() {

    private var _binding: FragmentCpHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCpHomeBinding.inflate(inflater, container, false)
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

    /**
     * Configurando elementos na tela
     */
    private fun setupUI() {
        setupButtonFeriados()
        setupButtonNumeroExtenso()
    }

    /**
     * Configurando evento do botao de Feriados
     */
    private fun setupButtonFeriados() {
        binding.btnListaFeriados.setOnClickListener {
            val action = CPHomeFragmentDirections.actionCPHomeFragmentToFeriadosFragment()
            findNavController().navigate(action)
        }
    }

    /**
     * Configurando evento do botao de Feriados
     */
    private fun setupButtonNumeroExtenso() {
        binding.btnEscritaPorExtenso.setOnClickListener {
            val action = CPHomeFragmentDirections.actionCPHomeFragmentToNumExtensoFragment()
            findNavController().navigate(action)
        }
    }
}