package br.com.fiap.edu.xboxone.usuarios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.databinding.ItemUsuariosBinding

interface ItemUsuarioListener {
    fun usuarioSelecionado(email: String)
}

class UsuariosAdapter(
    val usuarios: List<ItemUsuario>,
    val itemUsuarioListener: ItemUsuarioListener
): RecyclerView.Adapter<UsuariosAdapter.UsuariosViewHolder>() {

    class UsuariosViewHolder(val binding: ItemUsuariosBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUsuariosBinding.inflate(layoutInflater, parent, false)
        return UsuariosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val usuario = usuarios[position]

        holder.binding.txtEmail.text = usuario.email
        holder.binding.txtAcesso.text = usuario.active

        holder.binding.root.setOnClickListener {
            itemUsuarioListener.usuarioSelecionado(usuario.email)
        }
    }
}