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

    private fun getUsuarioDatabase(listaUsuarioContract: ListaUsuarioContract) {
        val usuarios = XboxApplication.database.getUserDao().getUsuarios()
        listaUsuarioContract.sucesso( usuarios.map {
            ItemUsuario(
                it.username,
                if (it.active == 1) "ativo" else "desativado"
            )
        })
    }

    private fun getUsuarioDRemoto(listaUsuarioContract: ListaUsuarioContract) {
        val albumDao = JsonPlaceholderAPI.albumDao

        albumDao.getAlbums().enqueue(object: Callback<Albums> {
            override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                val body = response.body()
                listaUsuarioContract.sucesso(
                body?.map { ItemUsuario(it.title, it.id.toString()) }
                        ?: arrayListOf()
                )
            }

            override fun onFailure(call: Call<Albums>, t: Throwable) {
            }
        })
    }

}