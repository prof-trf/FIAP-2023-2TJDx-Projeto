package br.com.fiap.edu.xboxone

import android.app.Application
import br.com.fiap.edu.xboxone.core.database.AppDatabase

class XboxApplication: Application() {
    companion object {
        lateinit var database: AppDatabase // Referencia para acessar o banco de dados
    }

    override fun onCreate() {
        super.onCreate()

        database = AppDatabase.setup(this)  // Iniciando o banco de dados
    }
}