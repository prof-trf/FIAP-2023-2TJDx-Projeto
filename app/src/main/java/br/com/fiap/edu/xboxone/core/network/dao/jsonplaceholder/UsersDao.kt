package br.com.fiap.edu.xboxone.core.network.dao.jsonplaceholder

import br.com.fiap.edu.xboxone.core.network.entities.jsonplaceholder.Users
import retrofit2.Call
import retrofit2.http.GET

interface UsersDao {

    @GET("/users")
    fun getUsers(): Call<ArrayList<Users>>

}