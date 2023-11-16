package br.com.fiap.edu.xboxone.core.network

import br.com.fiap.edu.xboxone.core.network.dao.invertexto.HolidayDao
import br.com.fiap.edu.xboxone.core.network.dao.invertexto.NumberInWordsDao


//Criação do conector (quem fará a requisição)
private val retrofit =
    RetrofitEngine.create("https://api.invertexto.com/")

//Classe que concentra todos os recursos da API
object InvertextoAPI {

    //Recurso da API para busca Feriados
    val holidayDao: HolidayDao by lazy { retrofit.create(HolidayDao::class.java) }

    //Recurso da API Numero por Extenso
    val numberInWordsDao: NumberInWordsDao by lazy { retrofit.create(NumberInWordsDao::class.java) }

}