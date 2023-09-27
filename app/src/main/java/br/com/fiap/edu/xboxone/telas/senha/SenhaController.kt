package br.com.fiap.edu.xboxone.telas.senha

/* Classe que controla a tela de senha */
class SenhaController {

    /* valida se o usuário digitou uma senha */
    fun temSenha(senha: String): Boolean {
        return senha.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel senha */
    }

    fun validateSenha(senha: String): Boolean {
        return senha == "rm1234"
    }
}