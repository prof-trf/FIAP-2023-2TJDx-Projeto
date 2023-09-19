package br.com.fiap.edu.xboxone.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.ItemImageHomeBinding

class PopularAdapter: RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    class PopularViewHolder(val binding: ItemImageHomeBinding): RecyclerView.ViewHolder(binding.root)

    private val images = arrayListOf(
            R.mipmap.ageofempire2,
            R.mipmap.fallout76,
            R.mipmap.f12023,
            R.mipmap.forza5,
            R.mipmap.deadbydaylight
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = ItemImageHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val image = images[position]
        holder.binding.imgCapa.setImageResource(image)
    }
}