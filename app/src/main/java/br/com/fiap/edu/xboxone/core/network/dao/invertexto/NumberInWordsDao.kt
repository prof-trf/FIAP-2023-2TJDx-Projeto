package br.com.fiap.edu.xboxone.core.network.dao.invertexto

import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.NumberToWords
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NumberInWordsDao {

    /**
     * https://api.invertexto.com/v1/number-to-words?token=SEUTOKEN&number=1200.50&language=pt&currency=BRL
     *
     * {ano} -> (2022, por exemplo) parametro na url para representar qual ano inserido para realizar a busca
     */
    @GET("/v1/number-to-words")
    fun numberToWords(
        @Query("token") token: String = "5330|CMl8YLktFKWRwOxn2FZ52UKg8ZdDRiKd",  // (?token=...)Parametro na url para identificar o token. Não precisa alterar
        @Query("number") number: String, // (&number=...)Parametro na url para identificar o numero. Número a ser escrito (casa decimal separada por ponto).
        @Query("language") language: String = "pt", // (&number=...)Parametro na url para identificar o idioma. Idioma (valor padrão: pt).
        @Query("currency") currency: String = "BRL", // (&currency=...)Parametro na url para identificar a moeda. Moeda (deixe em branco para escrever número inteiro, sem a moeda).
    ): Call<NumberToWords> // Objeto que representa o JSON

}