package br.com.fiap.edu.xboxone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.fiap.edu.xboxone.core.database.entities.Product
import br.com.fiap.edu.xboxone.core.database.entities.User

@Dao
interface ProductDao {

    //methods
    @Insert
    fun inserirProduct(product: Product)
}