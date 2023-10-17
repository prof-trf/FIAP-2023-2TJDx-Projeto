package br.com.fiap.edu.xboxone.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.ItemImageHomeBinding

class GamePassAdapter(val images: Array<Int>): RecyclerView.Adapter<GamePassAdapter.GamePassViewHolder>() {

    class GamePassViewHolder(val binding: ItemImageHomeBinding):
            RecyclerView.ViewHolder(binding.root)

//    private val images = arrayListOf(
//            R.mipmap.minecraft,
//            R.mipmap.forza4,
//            R.mipmap.gangbeast,
//            R.mipmap.fortnite,
//            R.mipmap.assassins_creed
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamePassViewHolder {
        // como vou criar os itens
        val binding = ItemImageHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamePassViewHolder(binding)
    }

    override fun getItemCount(): Int {
        // quantos tem
        return images.size
    }

    override fun onBindViewHolder(holder: GamePassViewHolder, position: Int) {
        // como vou configurar cada item
        val image = images[position]
        holder.binding.imgCapa.setImageResource(image)
    }
}