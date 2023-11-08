package br.com.fiap.edu.xboxone.core.network

import br.com.fiap.edu.xboxone.core.network.dao.jsonplaceholder.AlbumDao
import br.com.fiap.edu.xboxone.core.network.dao.jsonplaceholder.CommentsDao
import br.com.fiap.edu.xboxone.core.network.dao.jsonplaceholder.UsersDao

//Criação do conector (quem fará a requisição)
private val retrofit =
    RetrofitEngine.create("https://jsonplaceholder.typicode.com/")

//Classe que concentra todos os recursos da API
object JsonPlaceholderAPI {

    //Recurso da API para busca Albuns
    val albumDao: AlbumDao by lazy { retrofit.create(AlbumDao::class.java) }

    val commentsDao: CommentsDao by lazy { retrofit.create(CommentsDao::class.java) }

    val usersDao: UsersDao by lazy { retrofit.create(UsersDao::class.java) }

}