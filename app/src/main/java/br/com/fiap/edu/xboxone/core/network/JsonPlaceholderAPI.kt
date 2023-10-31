package br.com.fiap.edu.xboxone.core.network

import br.com.fiap.edu.xboxone.core.network.dao.AlbumDao

private val retrofit =
    RetrofitEngine.create("https://jsonplaceholder.typicode.com/")

object JsonPlaceholderAPI {
    val albumDao: AlbumDao by lazy { retrofit.create(AlbumDao::class.java) }
}