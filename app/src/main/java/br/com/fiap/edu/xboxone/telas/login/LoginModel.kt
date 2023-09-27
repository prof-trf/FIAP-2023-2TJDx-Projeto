package br.com.fiap.edu.xboxone.telas.login

class LoginModel {

    /* valida se é um usuario valido */
    fun validateUsername(usuario: String): Boolean {
        val user = usuario.lowercase()

        val temRM = user.contains("rm")
        val ehOMeuRM = user == "rm1234@fiap.com.br"

        return temRM && ehOMeuRM
    }
}