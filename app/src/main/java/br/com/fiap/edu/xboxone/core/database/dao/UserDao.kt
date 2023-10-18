package br.com.fiap.edu.xboxone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.edu.xboxone.core.database.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getUsuarios(): List<User>

    @Query("SELECT * FROM User as u WHERE u.username == :username")
    fun getUsuario(username: String): User

    @Query("SELECT * FROM User as u WHERE u.username == :username AND u.password == :password")
    fun validateUser(username: String, password: String): User

    @Insert
    fun inserirUsuario(user: User)



}