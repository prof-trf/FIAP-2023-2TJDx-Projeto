package br.com.fiap.edu.xboxone.crieuma.recomendado

import br.com.fiap.edu.xboxone.R
import java.lang.IllegalArgumentException

object JogosSuportados {

    fun getJogos(): Map<String, Int> = mapOf(
        "ageofempire2" to R.mipmap.ageofempire2,
        "ageofempires" to R.mipmap.ageofempires,
        "assassins_creed" to R.mipmap.assassins_creed,
        "callofduty" to R.mipmap.callofduty,
        "deadbydaylight" to R.mipmap.deadbydaylight,
        "f12023" to R.mipmap.f12023,
        "fallout76" to R.mipmap.fallout76,
        "fortnite" to R.mipmap.fortnite,
        "forza4" to R.mipmap.forza4,
        "forza5" to R.mipmap.forza5,
        "gangbeast" to R.mipmap.gangbeast,
        "minecraft" to R.mipmap.minecraft,
        "overwatch" to R.mipmap.overwatch,
        "roblox" to R.mipmap.roblox,
        "shadow_warrior" to R.mipmap.shadow_warrior
    )

    fun getImageJogo(nomeJogo: String): Int {
        val jogos = getJogos()
        return jogos[nomeJogo] ?: throw IllegalArgumentException()
    }



}