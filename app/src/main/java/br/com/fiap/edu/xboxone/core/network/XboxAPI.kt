package br.com.fiap.edu.xboxone.core.network

import br.com.fiap.edu.xboxone.core.network.dao.xbox.UsersDao

//Criação do conector (quem fará a requisição)
private val retrofit =
//    RetrofitEngine.create("https://851b-2804-87a8-8cf6-ad00-00-d4.ngrok-free.app/")
    RetrofitEngine.create("http://192.168.100.64:8080/")

//Classe que concentra todos os recursos da API
object XboxAPI {

    val usersDao: UsersDao by lazy { retrofit.create(UsersDao::class.java) }
}