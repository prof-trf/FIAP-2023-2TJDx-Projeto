package br.com.fiap.edu.xboxone.login.contrato

import java.lang.Exception

interface IValidacaoUsuarioView {
    fun pesquisandoUsuario() /* Loading */
    fun usuarioLocalizado(usuario: String) /* validou o usuario no sistema */
    fun usuarioNaoLocalizado(erro: Exception) /* nao validou o usuario no sistema */
}