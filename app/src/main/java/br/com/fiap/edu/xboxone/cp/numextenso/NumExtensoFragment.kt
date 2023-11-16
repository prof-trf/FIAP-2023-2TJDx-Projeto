package br.com.fiap.edu.xboxone.cp.numextenso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.NumberToWords
import br.com.fiap.edu.xboxone.databinding.FragmentCpNumextensoBinding
import br.com.fiap.edu.xboxone.databinding.FragmentTemplateBinding
import java.util.Locale

class NumExtensoFragment: Fragment(), NumExtensoContractView {

    private val controller =  NumExtensoController()

    private var _binding: FragmentCpNumextensoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCpNumextensoBinding.inflate(inflater, container, false)
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
        setupButtonVoltar()
        setupButtonGerar()
    }

    private fun setupButtonVoltar() {
        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupButtonGerar() {
        binding.btnGerar.setOnClickListener {
            if(!binding.progressBar3.isVisible) {
                val number = binding.editTextNumberDecimal.text.toString()
                controller.gerarNumeroPorExtenso(number, this)
            } else {
                Toast.makeText(requireActivity(), "Estamos gerando o número para você, aguarde...", Toast.LENGTH_LONG).show()
            }
        }
    }

    //Contrato
    override fun numExtensoCarregadoComSucesso(numero: NumberToWords) {
        binding.progressBar3.isVisible = false
        binding.txtNumber.text = numero.text
    }

    override fun falhaCarregamentoNumExtenso(message: String) {
        binding.progressBar3.isVisible = false
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    override fun gerandoNumExtenso() {
        binding.progressBar3.isVisible = true
    }

}