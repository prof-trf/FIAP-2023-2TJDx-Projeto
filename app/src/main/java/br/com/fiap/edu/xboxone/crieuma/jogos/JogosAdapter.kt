package br.com.fiap.edu.xboxone.crieuma.jogos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.edu.xboxone.databinding.ItemJogoASelecionarBinding

// Classe responsavel por desenha a lista bem como todos os itens
class JogosAdapter(private val jogos: Array<Jogos.Jogo>):
    RecyclerView.Adapter<JogosAdapter.JogosViewHolder>() {

    //Classe que representa o item da view
    class JogosViewHolder(val binding: ItemJogoASelecionarBinding):
        RecyclerView.ViewHolder(binding.root)

    // Metodo que cria a referencia de um item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogosViewHolder { // crie a referencia para o item

        val layoutInflater = LayoutInflater.from(parent.context) // motor usado para ler o layout xml
        val binding = ItemJogoASelecionarBinding.inflate(layoutInflater, parent, false) // realiza a leitura do layout xml
        return JogosViewHolder(binding) // cria a instancia do item da lista
    }

    // Metodo que indica quantos itens terá a listagem
    override fun getItemCount(): Int { // quantos itens
        return jogos.size
    }

    // Metodo que configura a informação que cada item exibirá
    override fun onBindViewHolder(holder: JogosViewHolder, position: Int) { // como será configurado o item
        val jogo = jogos[position] // recupera a informação para o item da posição (position)

        val binding = holder.binding // pega a referencia dos componentes de tela do item

        // Faz a configuração visual do item da tela com os dados recuperados
        binding.imgCapa.setImageResource(jogo.imagem)
        binding.stcRecomendado.setOnCheckedChangeListener { _, checked ->
            jogo.recomendado = checked
        }
    }

}