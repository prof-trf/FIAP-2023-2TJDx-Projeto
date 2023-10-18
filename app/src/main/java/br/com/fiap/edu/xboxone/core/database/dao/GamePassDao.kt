package br.com.fiap.edu.xboxone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.edu.xboxone.core.database.entities.GamePass

@Dao
interface GamePassDao {

    @Query("SELECT * FROM GamePass as g WHERE g.username == :username")
    fun getGames(username: String): List<GamePass>

    @Insert
    fun inserirRecomendado(gamePass: GamePass)
}