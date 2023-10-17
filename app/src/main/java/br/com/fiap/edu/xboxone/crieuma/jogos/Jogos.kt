package br.com.fiap.edu.xboxone.crieuma.jogos

import br.com.fiap.edu.xboxone.R
import java.lang.IllegalArgumentException

object Jogos {

    class Jogo(val id: String, val imagem: Int, var recomendado: Boolean = false)

    // Metodo utilizado para listar as imagens das capas dos Jogos
    fun getJogosRecomendado(): Array<Jogo> {
        return arrayOf(
            Jogo("ageofempires",  R.mipmap.ageofempires),
            Jogo("ageofempire2", R.mipmap.ageofempire2),
            Jogo("assassins_creed", R.mipmap.assassins_creed),
            Jogo("callofduty", R.mipmap.callofduty),
            Jogo("deadbydaylight", R.mipmap.deadbydaylight),
            Jogo("f12023", R.mipmap.f12023),
            Jogo("fallout76", R.mipmap.fallout76),
            Jogo("fortnite", R.mipmap.fortnite),
            Jogo("forza4", R.mipmap.forza4),
            Jogo("forza5", R.mipmap.forza5),
            Jogo("minecraft", R.mipmap.minecraft),
            Jogo("gangbeast", R.mipmap.gangbeast),
            Jogo("shadow_warrior", R.mipmap.shadow_warrior),
            Jogo("roblox", R.mipmap.roblox),
            Jogo("overwatch", R.mipmap.overwatch),
        )
    }

    // Metodo utilizado para recuperar através de um nome qual a referencia da imagem de capa do Jogo
    fun converteImagem(nome: String): Int {
        if(nome == "ageofempires") return  R.mipmap.ageofempires
        if(nome == "ageofempire2") return R.mipmap.ageofempire2
        if(nome == "assassins_creed") return R.mipmap.assassins_creed
        if(nome == "callofduty") return R.mipmap.callofduty
        if(nome == "deadbydaylight") return R.mipmap.deadbydaylight
        if(nome == "f12023") return R.mipmap.f12023
        if(nome == "fallout76") return R.mipmap.fallout76
        if(nome == "fortnite") return R.mipmap.fortnite
        if(nome == "forza4") return R.mipmap.forza4
        if(nome == "forza5") return R.mipmap.forza5
        if(nome == "minecraft") return R.mipmap.minecraft
        if(nome == "gangbeast") return R.mipmap.gangbeast
        if(nome == "shadow_warrior") return R.mipmap.shadow_warrior
        if(nome == "roblox") return R.mipmap.roblox
        if(nome == "overwatch") return R.mipmap.overwatch

        throw IllegalArgumentException()
    }
}

