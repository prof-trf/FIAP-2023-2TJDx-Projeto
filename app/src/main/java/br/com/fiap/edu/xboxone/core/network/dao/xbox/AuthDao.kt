package br.com.fiap.edu.xboxone.core.network.dao.xbox

import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyBody
import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthDao {
    @POST("/api/v1/auth/verify")
    fun verifyUser(@Body body: VerifyBody): Call<VerifyResponse>

}