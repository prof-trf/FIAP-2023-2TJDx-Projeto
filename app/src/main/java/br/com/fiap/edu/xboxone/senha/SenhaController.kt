package br.com.fiap.edu.xboxone.senha

import br.com.fiap.edu.xboxone.XboxApplication

/* Classe que controla a tela de senha */
class SenhaController {

    private val database = XboxApplication.database

    /* valida se o usuário digitou uma senha */
    fun temSenha(senha: String): Boolean {
        return senha.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel senha */
    }

    fun validateUser(username: String, password: String): Boolean {
       return database.getUserDao().isValidateUsuario(username, password) != null
    }
}