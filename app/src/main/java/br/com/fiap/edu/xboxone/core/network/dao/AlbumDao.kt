package br.com.fiap.edu.xboxone.core.network.dao

import br.com.fiap.edu.xboxone.core.network.entities.Albums
import retrofit2.Call
import retrofit2.http.GET

interface AlbumDao {
    @GET("/albums")
    fun getAlbums(): Call<Albums>

}