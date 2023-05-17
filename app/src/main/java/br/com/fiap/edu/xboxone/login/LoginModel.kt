package br.com.fiap.edu.xboxone.login

class LoginModel {

    /* valida se é um usuario valido */
    fun validateUsername(usuario: String): Boolean {
        Thread.sleep(4000)

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