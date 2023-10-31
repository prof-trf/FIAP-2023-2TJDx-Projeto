package br.com.fiap.edu.xboxone.core.network.dao

import br.com.fiap.edu.xboxone.core.network.entities.Albums
import retrofit2.Call
import retrofit2.http.GET

// Classe responsavel pelo acesso ao recurso da API
interface AlbumDao {
    @GET("/albums") // Recurso e o verbo a ser chamado
    fun getAlbums(): Call<Albums> // Metodo que faz a ação de chamar o recurso e devolve o json convertido

}