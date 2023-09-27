package br.com.fiap.edu.xboxone.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.edu.xboxone.core.database.dao.PeopleDao
import br.com.fiap.edu.xboxone.core.database.dao.UserDao
import br.com.fiap.edu.xboxone.core.database.entities.People
import br.com.fiap.edu.xboxone.core.database.entities.User

@Database(entities = [User::class, People::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getPeopleDao(): PeopleDao

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