package br.com.fiap.edu.xboxone.core.network

import br.com.fiap.edu.xboxone.core.network.dao.AlbumDao

//Criação do conector (quem fará a requisição)
private val retrofit =
    RetrofitEngine.create("https://jsonplaceholder.typicode.com/")

//Classe que concentra todos os recursos da API
object JsonPlaceholderAPI {

    //Recurso da API para busca Albuns
    val albumDao: AlbumDao by lazy { retrofit.create(AlbumDao::class.java) }
}