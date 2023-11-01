package br.com.fiap.edu.xboxone.core.network.dao

import br.com.fiap.edu.xboxone.core.network.entities.AlbumItem
import br.com.fiap.edu.xboxone.core.network.entities.Albums
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.lang.NumberFormatException

// Classe responsavel pelo acesso ao recurso da API
interface AlbumDao {

    @GET("/albums") // Recurso e o verbo a ser chamado
    fun getAlbums(): Call<ArrayList<AlbumItem>> // Metodo que faz a ação de chamar o recurso e devolve o json convertido

}