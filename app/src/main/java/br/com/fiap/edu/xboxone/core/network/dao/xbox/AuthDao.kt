package br.com.fiap.edu.xboxone.core.network.dao.xbox

import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyUser
import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyUserBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthDao {

    @POST("/api/v1/auth/verify")
    fun verifyUser(@Body body: VerifyUserBody): Call<VerifyUser>

}