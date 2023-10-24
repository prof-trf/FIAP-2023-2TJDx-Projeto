package br.com.fiap.edu.xboxone.crieuma

import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.User
import kotlin.random.Random

class CrieUmaModel {

    private val database = XboxApplication.database
    fun registrarUsuario(usuario: String, senha: String): Boolean {
        val id = Random.nextInt(0, 9999)
        val user = User(id=id, username = usuario, password = senha, type = "user", active = 1)
        database.getUserDao().inserirUsuario(user)

        return true
    }
}