package br.com.fiap.edu.xboxone.usuarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.databinding.ItemUsuariosBinding
import br.com.fiap.edu.xboxone.usuarios.view.ItemUsuariosView

interface ItemClicadoListener {
    fun itemClicado(email: String)
}

class UsuariosAdapter(val itemUsuariosViews: List<ItemUsuariosView>, val listener: ItemClicadoListener): RecyclerView.Adapter<UsuariosAdapter.ItemUsuariosViewHolder>() {
    class ItemUsuariosViewHolder(val binding: ItemUsuariosBinding): RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemUsuariosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUsuariosBinding.inflate(layoutInflater, parent, false)
        return ItemUsuariosViewHolder(binding)
    }

    override fun getItemCount(): Int = itemUsuariosViews.size

    override fun onBindViewHolder(holder: ItemUsuariosViewHolder, position: Int) {
        val item = itemUsuariosViews[position]
        holder.binding.txtUser.text = item.email
        holder.binding.txtActive.text = if (item.active) "ativo" else "desativado"

        holder.binding.root.setOnClickListener {
            listener.itemClicado(item.email)
        }
    }
}