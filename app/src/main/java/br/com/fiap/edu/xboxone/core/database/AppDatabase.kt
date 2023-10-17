package br.com.fiap.edu.xboxone.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.fiap.edu.xboxone.core.database.dao.PeopleDao
import br.com.fiap.edu.xboxone.core.database.dao.ProductDao
import br.com.fiap.edu.xboxone.core.database.dao.UserDao
import br.com.fiap.edu.xboxone.core.database.entities.People
import br.com.fiap.edu.xboxone.core.database.entities.Product
import br.com.fiap.edu.xboxone.core.database.entities.User

@Database(entities = [User::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

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
                    ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .allowMainThreadQueries()
                    .build()
                }
            }

            return instance!!
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN type TEXT NOT NULL DEFAULT 'staff'")
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN active INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}