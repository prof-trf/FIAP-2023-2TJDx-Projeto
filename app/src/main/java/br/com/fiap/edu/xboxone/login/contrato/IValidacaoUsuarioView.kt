package br.com.fiap.edu.xboxone.login.contrato

import java.lang.Exception

interface IValidacaoUsuarioView {
    fun pesquisandoUsuario()
    fun usuarioLocalizadoNaBaseDeDados(usuario: String)
    fun erroNaPesquisaDaBaseDedados(error: Exception)
}