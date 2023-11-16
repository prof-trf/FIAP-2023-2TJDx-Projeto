package br.com.fiap.edu.xboxone.cp.feriados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday
import br.com.fiap.edu.xboxone.databinding.FragmentCpFeriadosBinding
import java.util.ArrayList
import java.util.Locale

class FeriadosFragment: Fragment(), FeriadoContractView {

    private var _binding: FragmentCpFeriadosBinding? = null
    private val binding get() = _binding!!

    private val controller = FeriadoController()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCpFeriadosBinding.inflate(inflater, container, false)
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
        setupButtonPesquisar()
    }

    private fun setupButtonVoltar() {
        binding.btnVoltar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupButtonPesquisar() {
       binding.btnPesquisar.setOnClickListener {
           if(!binding.progressBar2.isVisible) {
               val ano = binding.edtAno.text.toString()
               val estado = binding.edtEstado.text.toString()
               controller.pesquisarFeriado(ano, estado.uppercase(Locale.ROOT), this)
           } else {
               Toast.makeText(requireActivity(), "Estamos já buscando os feriados para você, aguarde...", Toast.LENGTH_LONG).show()
           }
       }
    }

    //Contrato
    override fun feriadoCarregadoComSucesso(holidays: ArrayList<Holiday>) {
        binding.progressBar2.isVisible = false

        binding.recyclerView2.adapter = FeriadosAdapter(holidays)
    }

    override fun falhaCarregamentoFeriado(message: String) {
        binding.progressBar2.isVisible = false
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    override fun localizandoListaFeriados() {
        binding.progressBar2.isVisible = true
    }

}