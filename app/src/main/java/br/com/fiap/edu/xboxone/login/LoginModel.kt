package br.com.fiap.edu.xboxone.login

import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.network.JsonPlaceholderAPI
import br.com.fiap.edu.xboxone.core.network.XBoxAPI
import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyBody
import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyResponse
import br.com.fiap.edu.xboxone.login.contrato.IUsuarioController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel {

    private val database = XboxApplication.database
    private val authDao = XBoxAPI.authDao

    /* valida se é um usuario valido */
    fun validateUsername2(usuario: String): Boolean {
        val user = database.getUserDao().getUsuario(usuario)
        return user != null
    }

    fun validateUsername(usuario: String, callback: IUsuarioController) {
        authDao.verifyUser(VerifyBody(usuario)).enqueue(
            object: Callback<VerifyResponse> {
                override fun onResponse(
                    call: Call<VerifyResponse>,
                    response: Response<VerifyResponse>
                ) {
                    if(response.code() in 200..299) {
                        callback.usuarioLocalizado()
                    } else {
                        callback.usuarioNaoLocalizado()
                    }
                }

                override fun onFailure(call: Call<VerifyResponse>, t: Throwable) {
                    callback.usuarioNaoLocalizado()
                }
        })
    }
}