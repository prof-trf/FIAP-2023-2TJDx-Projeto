package br.com.fiap.edu.xboxone.criacaoconta

import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.User

class CriarContaModel {

    private val database = XboxApplication.database

    fun gravarUsuario(email: String, senha: String): Boolean {
        val user = User(email, senha)
        database.getUserDao().inserirUsuario(user)
        return true
    }
}