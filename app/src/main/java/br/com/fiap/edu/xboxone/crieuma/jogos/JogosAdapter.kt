package br.com.fiap.edu.xboxone.crieuma.jogos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.databinding.ItemJogoASelecionarBinding

class JogosAdapter(private val jogos: Array<Jogos.Jogo>):
    RecyclerView.Adapter<JogosAdapter.JogosViewHolder>() {

    class JogosViewHolder(val binding: ItemJogoASelecionarBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogosViewHolder {
        // crie a referencia para o item
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemJogoASelecionarBinding.inflate(layoutInflater, parent, false)
        return JogosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        // quantos itens
        return jogos.size
    }

    override fun onBindViewHolder(holder: JogosViewHolder, position: Int) {
        // como será configurado o item
        val jogo = jogos[position]

        val binding = holder.binding

        binding.imgCapa.setImageResource(jogo.imagem)
        binding.stcRecomendado.setOnCheckedChangeListener { _, checked ->
            jogo.recomendado = checked
        }
    }

}