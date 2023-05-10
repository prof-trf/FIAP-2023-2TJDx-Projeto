package br.com.fiap.edu.xboxone.senha

/* Classe que controla a tela de senha */
class SenhaController {

    /* valida se o usuário digitou uma senha */
    fun temSenha(senha: String): Boolean {
        return senha.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel senha */
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