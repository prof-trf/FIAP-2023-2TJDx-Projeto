package br.com.fiap.edu.xboxone.login

/* Classe que controla a tela de senha */
class LoginController {

    /* valida se o usuário digitou um email */
    fun temEmail(email: String): Boolean {
        return email.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel senha */
                && email.contains("@") /* contains -> valida há conteúdo na variavel senha contém o caracter @ */
    }

    /* valida se é um usuario valido */
    fun validarUsuario(usuario: String): Boolean {
        // eh e-mail
        if (temEmail(usuario)) {     /* valida se o usuário digitou um email */
            return true
        }

        // eh telefone
        val procuraLetra = usuario.find { !it.isDigit() } // find/isDigit -> procura se tem somente letra
        if (usuario.length == 11 // length -> tamanho da string é 11
            && procuraLetra == null) {
            return true
        }

        // eh skype
        if (usuario.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel usuario */
            && usuario.length >= 5
        ) {
            return true
        }

        return false
    }
}