package br.com.fiap.edu.xboxone.core.network.dao.xbox

import br.com.fiap.edu.xboxone.core.network.entities.xbox.RegisterUserBody
import br.com.fiap.edu.xboxone.core.network.entities.xbox.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersDao {

    @GET("/api/v1/user")
    fun getAllUsers(): Call<ArrayList<User>>

    @POST("/api/v1/user")
    fun registerUser(@Body body: RegisterUserBody): Call<User>
}