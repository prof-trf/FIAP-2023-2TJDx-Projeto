package br.com.fiap.edu.xboxone.login

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.network.JsonPlaceholderAPI
import br.com.fiap.edu.xboxone.core.network.XboxAPI
import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyUser
import br.com.fiap.edu.xboxone.core.network.entities.xbox.VerifyUserBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture

class LoginModel {

    private val database = XboxApplication.database
    private val authDao = XboxAPI.authDao

    /* valida se é um usuario valido */
    @RequiresApi(Build.VERSION_CODES.N)
    fun validateUsername(usuario: String): Boolean {
//       return validateUsernameDatabase(usuario)
        return validateUsernameRemote(usuario)
    }

    private fun validateUsernameDatabase(usuario: String): Boolean {
        val user = database.getUserDao().getUsuario(usuario)
        return user != null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun validateUsernameRemote(usuario: String): Boolean {
        val future = CompletableFuture<Boolean>()

        val body = VerifyUserBody(usuario)
        authDao.verifyUser(body).enqueue(object: Callback<VerifyUser> {
            override fun onResponse(call: Call<VerifyUser>, response: Response<VerifyUser>) {
                future.complete(response.code() in 200..299)
            }

            override fun onFailure(call: Call<VerifyUser>, t: Throwable) {
                future.complete(false)
            }
        })

        return future.get()
    }
}