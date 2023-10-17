package br.com.fiap.edu.xboxone.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.edu.xboxone.core.database.entities.Recomendado

@Dao //Notação para representar a classe de acesso aos dados (Data Access Object)
interface RecomendadoDao {
    @Query("SELECT * FROM Recomendado WHERE username == :username") //script SQL para fazer a listagem de todos os jogos que serão recomendado para o usuário (username)
    fun getJogosRecomendados(username: String): List<Recomendado>

    @Insert  //Notacao muito importante para dizer para o android que é um metodo de gravação de registro
    fun inserirRecomendacao(recomendado: Recomendado) //Metodo usado para realizar a gravação dos dados na base de dados


    // Notacoes para serem usadas conforme a necessidade
    //@Delete -> Notacao muito importante para dizer deseja remover um registro
    //@Update -> Notacao muito importante para dizer deseja atualizar um registro

}