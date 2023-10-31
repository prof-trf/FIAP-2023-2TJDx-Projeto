package br.com.fiap.edu.xboxone.usuarios

import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.network.JsonPlaceholderAPI
import br.com.fiap.edu.xboxone.core.network.entities.Albums
import br.com.fiap.edu.xboxone.usuarios.contract.ListaUsuarioContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuariosController {

    fun getUsuarios(listaUsuarioContract: ListaUsuarioContract) {
        //getUsuarioDatabase(listaUsuarioContract)
        getUsuarioDRemoto(listaUsuarioContract)
    }

    // Busca o usuario na base de dados
    private fun getUsuarioDatabase(listaUsuarioContract: ListaUsuarioContract) {
        val usuarios = XboxApplication.database.getUserDao().getUsuarios()
        listaUsuarioContract.sucesso( usuarios.map {
            ItemUsuario(
                it.username,
                if (it.active == 1) "ativo" else "desativado"
            )
        })
    }

    // Busca o usuario na internet
    private fun getUsuarioDRemoto(listaUsuarioContract: ListaUsuarioContract) {
        // Instancia da classe que acessa os dados
        val albumDao = JsonPlaceholderAPI.albumDao

        // Coloca a chama na fila de chamadas para acesso remoto
        albumDao.getAlbums().enqueue(object: Callback<Albums> {

            // Resposta do sucesso
            override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                val body = response.body() //Json convertido em objeto

                //Notificando a tela que houve sucesso na chamada e passando a lista
                listaUsuarioContract.sucesso(
                body?.map { ItemUsuario(it.title, it.id.toString()) }
                        ?: arrayListOf()
                )
            }

            // Resposta da falha
            override fun onFailure(call: Call<Albums>, t: Throwable) {
            }
        })
    }

}