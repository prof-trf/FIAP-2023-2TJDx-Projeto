package br.com.fiap.edu.xboxone.usuarios

import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.network.JsonPlaceholderAPI
import br.com.fiap.edu.xboxone.core.network.entities.AlbumItem
import br.com.fiap.edu.xboxone.core.network.entities.Albums
import br.com.fiap.edu.xboxone.usuarios.contract.ListaUsuarioContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuariosController {

    fun getUsuarios(listaUsuarioContract: ListaUsuarioContract) {
        //getUsuariosDatabase(listaUsuarioContract)
        getUsuariosRemoto(listaUsuarioContract)
    }

    // Busca o usuario na base de dados
    private fun getUsuariosDatabase(listaUsuarioContract: ListaUsuarioContract) {
        val usuarios = XboxApplication.database.getUserDao().getUsuarios()
        listaUsuarioContract.sucesso( usuarios.map {
            ItemUsuario(
                it.username,
                if (it.active == 1) "ativo" else "desativado"
            )
        })
    }

    // Busca o usuario na internet
    private fun getUsuariosRemoto(listaUsuarioContract: ListaUsuarioContract) {
        // Instancia da classe que acessa os dados
        val albumDao = JsonPlaceholderAPI.albumDao

        // Coloca a chama na fila de chamadas para acesso remoto
        albumDao.getAlbums().enqueue(
            object: Callback<ArrayList<AlbumItem>> {

            // Resposta do sucesso
            override fun onResponse(call: Call<ArrayList<AlbumItem>>, response: Response<ArrayList<AlbumItem>>) {
                if(response.code() in 200..299) {
                    val body = response.body() //Json convertido em objeto

                    //Notificando a tela que houve sucesso na chamada e passando a lista
                    listaUsuarioContract.sucesso(
                        body?.map { ItemUsuario(it.title, it.userid.toString()) }
                            ?: arrayListOf()
                    )
                } else {
                    listaUsuarioContract.sucesso(arrayListOf())
                }
            }

            // Resposta da falha
            override fun onFailure(call: Call<ArrayList<AlbumItem>>, t: Throwable) {
            }
        })
    }

}