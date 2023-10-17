package br.com.fiap.edu.xboxone.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.edu.xboxone.core.database.entities.Recomendado

@Dao
interface RecomendadoDao {
    @Query("SELECT * FROM Recomendado WHERE username == :username")
    fun getJogosRecomendados(username: String): List<Recomendado>

    @Insert
    fun inserirRecomendacao(recomendado: Recomendado)
}