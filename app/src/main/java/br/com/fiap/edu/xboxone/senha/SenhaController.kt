package br.com.fiap.edu.xboxone.senha

import br.com.fiap.edu.xboxone.XboxApplication

/* Classe que controla a tela de senha */
class SenhaController {

    val database = XboxApplication.database

    /* valida se o usuário digitou uma senha */
    fun temSenha(senha: String): Boolean {
        return senha.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel senha */
    }

    fun validateSenha(usuario: String, senha: String): Boolean {
        val user = database.getUserDao().validateUser(usuario, senha)
        return user != null
    }
}