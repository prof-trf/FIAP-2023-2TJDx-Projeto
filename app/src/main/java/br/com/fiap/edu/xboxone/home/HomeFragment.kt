package br.com.fiap.edu.xboxone.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.crieuma.jogos.Jogos
import br.com.fiap.edu.xboxone.databinding.FragmentHomeBinding
import br.com.fiap.edu.xboxone.databinding.FragmentTemplateBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListGamePassUI()
        setupListPopularUI()
    }

    private fun setupListGamePassUI() {
        var username = arguments?.getString("usuario") ?: ""
        val recomendados = XboxApplication.database.getRecomendadoDao().getJogosRecomendados(username)

        binding.rclGamePass.adapter = GamePassAdapter(
            recomendados.map { Jogos.converteImagem(it.jogo) }.toTypedArray()
        )
    }

    private fun setupListPopularUI() {
        binding.rclPopular.adapter = PopularAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}