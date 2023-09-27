package br.com.fiap.edu.xboxone.telas.boasvindas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fiap.edu.xboxone.databinding.FragmentBoasvindasBinding
import br.com.fiap.edu.xboxone.databinding.FragmentTemplateBinding

class BoasVindasFragment : Fragment() {

    private var _binding: FragmentBoasvindasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoasvindasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupUI()
        binding.button.text = arguments?.getString("usuario")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}