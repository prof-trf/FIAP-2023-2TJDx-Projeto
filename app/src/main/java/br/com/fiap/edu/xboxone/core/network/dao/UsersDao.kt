package br.com.fiap.edu.xboxone.core.network.dao

import br.com.fiap.edu.xboxone.core.network.entities.Users
import retrofit2.Call
import retrofit2.http.GET

interface UsersDao {

    @GET("/users")
    fun getUsers(): Call<ArrayList<Users>>

}