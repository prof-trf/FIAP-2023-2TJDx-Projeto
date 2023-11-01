package br.com.fiap.edu.xboxone.login

import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.network.JsonPlaceholderAPI

class LoginModel {

    private val database = XboxApplication.database

    /* valida se é um usuario valido */
    fun validateUsername(usuario: String): Boolean {
        val user = database.getUserDao().getUsuario(usuario)
        return user != null
    }
}