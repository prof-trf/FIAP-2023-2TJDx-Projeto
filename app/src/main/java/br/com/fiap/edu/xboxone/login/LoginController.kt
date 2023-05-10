package br.com.fiap.edu.xboxone.login

class LoginController {

    fun temEmail(email: String): Boolean {
        return email.isNotEmpty() && email.contains("@")
    }

    fun validarUsuario(usuario: String): Boolean {
        // eh e-mail
        if (temEmail(usuario)) {
            return true
        }

        // eh telefone
        val procuraLetra = usuario.find { !it.isDigit() }
        if (usuario.length == 11 && procuraLetra == null) {
            return true
        }

        // eh skype
        if (usuario.isNotEmpty() && usuario.length >= 5) {
            return true
        }

        return false
    }
}