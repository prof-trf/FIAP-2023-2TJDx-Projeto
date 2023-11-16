package br.com.fiap.edu.xboxone.cp.feriados

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday
import br.com.fiap.edu.xboxone.databinding.ItemFeriadosBinding

class FeriadosAdapter(val feriados: ArrayList<Holiday>): RecyclerView.Adapter<FeriadosAdapter.FeriadosViewHolder>() {

    class FeriadosViewHolder(val binding: ItemFeriadosBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeriadosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFeriadosBinding.inflate(layoutInflater, parent, false)
        return FeriadosViewHolder(binding)
    }

    override fun getItemCount(): Int = feriados.size

    override fun onBindViewHolder(holder: FeriadosViewHolder, position: Int) {
        val feriado = feriados[position]

        holder.binding.txtDate.text = feriado.date
        holder.binding.txtLevel.text = feriado.level
        holder.binding.txtName.text = feriado.name
        holder.binding.txtType.text = feriado.type
    }

}