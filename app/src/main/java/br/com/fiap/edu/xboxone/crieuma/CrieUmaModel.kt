package br.com.fiap.edu.xboxone.crieuma

import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.User
import br.com.fiap.edu.xboxone.core.network.XBoxAPI
import br.com.fiap.edu.xboxone.core.network.entities.xbox.UserBody
import br.com.fiap.edu.xboxone.core.network.entities.xbox.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class CrieUmaModel {

    private val database = XboxApplication.database
    private val userDao = XBoxAPI.usersDao

    fun registrarUsuarioLocal(usuario: String, senha: String): Boolean {
        val id = Random.nextInt(0, 9999)
        val user = User(id, usuario, senha, "user", 1)
        database.getUserDao().inserirUsuario(user)

        return true
    }

    fun registrarUsuarioRemoto(usuario: String, senha: String): Boolean {
        val body = UserBody(email = usuario, password = senha, active = true)
        userDao.registerUser(body).enqueue(object: Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {}
            override fun onFailure(call: Call<Users>, t: Throwable) {}
        })
        return true
    }
}