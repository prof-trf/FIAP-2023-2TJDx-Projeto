package br.com.fiap.edu.xboxone.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.fiap.edu.xboxone.core.database.dao.RecomendadoDao
import br.com.fiap.edu.xboxone.core.database.dao.UserDao
import br.com.fiap.edu.xboxone.core.database.entities.Recomendado
import br.com.fiap.edu.xboxone.core.database.entities.User

@Database(entities = [User::class, Recomendado::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getRecomendadoDao(): RecomendadoDao

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
                        .fallbackToDestructiveMigration()
                        //.addMigrations(MIGRATION_2_3, MIGRATION_3_4)
                    .allowMainThreadQueries()
                    .build()
                }
            }

            return instance!!
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN type TEXT NOT NULL DEFAULT 'user'")
            }
        }

        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN active INTEGER NOT NULL DEFAULT 1")
            }
        }
    }
}