package br.com.fiap.edu.xboxone.usuarios.contract

import br.com.fiap.edu.xboxone.usuarios.ItemUsuario

interface ListaUsuarioContract {

    fun sucesso(users: List<ItemUsuario>)
}