package br.com.fiap.edu.xboxone.core.network.dao

import br.com.fiap.edu.xboxone.core.network.entities.Comments
import retrofit2.Call
import retrofit2.http.GET

interface CommentsDao {

    @GET("/comments")
    fun getComments(): Call<ArrayList<Comments>>

}