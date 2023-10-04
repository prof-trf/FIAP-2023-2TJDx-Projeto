package br.com.fiap.edu.xboxone.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.edu.xboxone.core.database.dao.PeopleDao
import br.com.fiap.edu.xboxone.core.database.dao.ProductDao
import br.com.fiap.edu.xboxone.core.database.dao.UserDao
import br.com.fiap.edu.xboxone.core.database.entities.People
import br.com.fiap.edu.xboxone.core.database.entities.Product
import br.com.fiap.edu.xboxone.core.database.entities.User

@Database(entities = [User::class, Product::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getProductDao(): ProductDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun setup(context: Context): AppDatabase {
            if(instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                    .allowMainThreadQueries()
                    .build()
                }
            }

            return instance!!
        }
    }
}