package br.com.fiap.edu.xboxone.senha

class SenhaController {

    fun temSenha(senha: String): Boolean {
        return senha.isNotEmpty()
    }

    fun validateSenha(senha: String): Boolean {
        // senha deve ter no minimo 8 caracteres
        val temTamanho = senha.length >= 8

        // um caracter numerico
        val temNumero = senha.any { it.isDigit() }

        val ehASenha = senha == "EU SOU A LENDA 2023"

        return temTamanho && temNumero && ehASenha
    }
}