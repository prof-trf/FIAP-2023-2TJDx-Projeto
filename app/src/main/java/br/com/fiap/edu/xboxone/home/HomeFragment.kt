package br.com.fiap.edu.xboxone.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.crieuma.recomendado.JogosSuportados
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
        val username = arguments?.getString("usuario") ?: ""

        val database = XboxApplication.database
        val jogos = database.getGamePassDao().getGames(username)

        val imagens = jogos.map { JogosSuportados.getImageJogo(it.game) }
        binding.rclGamePass.adapter = GamePassAdapter(imagens)
    }

    private fun setupListPopularUI() {
        binding.rclPopular.adapter = PopularAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}