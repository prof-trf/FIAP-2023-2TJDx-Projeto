package br.com.fiap.edu.xboxone.crieuma

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.User
import br.com.fiap.edu.xboxone.core.network.XboxAPI
import br.com.fiap.edu.xboxone.core.network.entities.xbox.RegisterUserBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture
import kotlin.random.Random

class CrieUmaModel {

    private val database = XboxApplication.database
    private val usersDao = XboxAPI.usersDao

    @RequiresApi(Build.VERSION_CODES.N)
    fun registrarUsuario(usuario: String, senha: String): Boolean {
//       return registrarUsuarioDatabase(usuario, senha)
       return registrarUsuarioRemoto(usuario, senha)
    }

    private fun registrarUsuarioDatabase(usuario: String, senha: String): Boolean {
        val id = Random.nextInt(0, 9999)
        val user = User(id, usuario, senha, "user", 1)
        database.getUserDao().inserirUsuario(user)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun registrarUsuarioRemoto(usuario: String, senha: String): Boolean {
        val future = CompletableFuture<Boolean>()

        val body = RegisterUserBody(usuario, senha, true)
        usersDao.registerUser(body).enqueue(object: Callback<br.com.fiap.edu.xboxone.core.network.entities.xbox.User>{
            override fun onResponse(
                call: Call<br.com.fiap.edu.xboxone.core.network.entities.xbox.User>,
                response: Response<br.com.fiap.edu.xboxone.core.network.entities.xbox.User>
            ) {
                future.complete(response.code() in 200..299)
            }

            override fun onFailure(
                call: Call<br.com.fiap.edu.xboxone.core.network.entities.xbox.User>,
                t: Throwable
            ) {
                future.complete(false)
            }
        })

        return future.get()
    }
}