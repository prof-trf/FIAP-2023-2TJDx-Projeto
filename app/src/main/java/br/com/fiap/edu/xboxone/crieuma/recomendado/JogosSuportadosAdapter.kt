package br.com.fiap.edu.xboxone.crieuma.recomendado

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.databinding.ItemJogoASelecionarBinding

class JogosSuportadosAdapter(val recomendados: List<Recomendados>):
    RecyclerView.Adapter<JogosSuportadosAdapter.JogosSuportadosViewHolder>() {

    class JogosSuportadosViewHolder(val binding: ItemJogoASelecionarBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogosSuportadosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJogoASelecionarBinding.inflate(inflater, parent, false)

        return JogosSuportadosViewHolder(binding)
    }

    override fun getItemCount(): Int = recomendados.size

    override fun onBindViewHolder(holder: JogosSuportadosViewHolder, position: Int) {
        val jogo = recomendados[position]

        holder.binding.imgCapa.setImageResource(jogo.imagem)
        holder.binding.ckbRecomendado.setOnCheckedChangeListener { _, checked ->
            jogo.recomendado = checked
        }
    }

}