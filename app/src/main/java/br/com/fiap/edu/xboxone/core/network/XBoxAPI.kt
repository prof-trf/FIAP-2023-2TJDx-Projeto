package br.com.fiap.edu.xboxone.core.network

import br.com.fiap.edu.xboxone.core.network.dao.xbox.AuthDao
import br.com.fiap.edu.xboxone.core.network.dao.xbox.UsersDao


//Criação do conector (quem fará a requisição)
private val retrofit =
//    RetrofitEngine.create("http://192.168.100.64:8080/")
    RetrofitEngine.create("http://de3d-2804-87a8-8cf6-ad00-00-d4.ngrok-free.app/")

//Classe que concentra todos os recursos da API
object XBoxAPI {
    val usersDao: UsersDao by lazy { retrofit.create(UsersDao::class.java) }
    val authDao: AuthDao by lazy { retrofit.create(AuthDao::class.java) }

}