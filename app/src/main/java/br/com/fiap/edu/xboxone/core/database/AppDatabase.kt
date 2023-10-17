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

@Database( //Notação que representa um banco de dados
    entities = [User::class, Recomendado::class],  // Lista de tabelas do banco de dados
    version = 1,  // Versão do banco de dados, quando atualizado devemos usar o addMigrations
    exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao // referencia para acessar os dados da tabela Usuario
    abstract fun getRecomendadoDao(): RecomendadoDao  // referencia para acessar os dados da tabela Recomendado

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

            // Metodo para realizar a configuração do banco de dados no Android
        fun setup(context: Context): AppDatabase {
            if(instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database" // Nome do banco de dados
                    )
                        .fallbackToDestructiveMigration()    // Metodo usado para sempre renovar a base de dados caso vc queira limpar sempre que mudar versão
                        //.addMigrations(MIGRATION_2_3, MIGRATION_3_4) // Metodo usado para sempre realizar as utualizações (versão de banco de dados)
                    .allowMainThreadQueries()
                    .build()
                }
            }

            return instance!!
        }

        // Script para realizar migrações de versão de base de dados
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