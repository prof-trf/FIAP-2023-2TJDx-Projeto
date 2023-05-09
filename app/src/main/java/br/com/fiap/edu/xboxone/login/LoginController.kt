package br.com.fiap.edu.xboxone.login

class LoginController {

    fun temEmail(email: String): Boolean {
        val vazio = email.isEmpty()
        if (vazio) {
            return false
        }

        return email.contains("@")
    }

    fun validateUsername(username: String): Boolean {
        // é um email
        if(temEmail(username)) {
            return true
        }

        // é um telefone
        if(username.length == 11 &&
            username.map { !it.isDigit() }.isEmpty()) {
            return true
        }

        // é um skype
        if(username.length >= 5 && username.isNotEmpty()) {
            return true
        }

        return false
    }

}