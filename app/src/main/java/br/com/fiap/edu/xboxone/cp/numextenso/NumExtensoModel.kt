package br.com.fiap.edu.xboxone.cp.numextenso

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.fiap.edu.xboxone.core.network.InvertextoAPI
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.NumberToWords
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture

class NumExtensoModel {

    private val numberInWordsDao = InvertextoAPI.numberInWordsDao

    @RequiresApi(Build.VERSION_CODES.N)
    fun gerandoNumeroPorExtenso(numero: String): NumberToWords {
        val future = CompletableFuture<NumberToWords>()
        val number = numero.replace(",", ".") //Número a ser escrito (casa decimal separada por ponto).


        numberInWordsDao.numberToWords(number = number).enqueue(object: Callback<NumberToWords> {
            override fun onResponse(
                call: Call<NumberToWords>,
                response: Response<NumberToWords>
            ) {
                if(response.code() in 200..299) {
                    future.complete(response.body())
                } else {
                    future.complete(NumberToWords("Não foi possível gerar o número por extenso"))
                }
            }

            override fun onFailure(call: Call<NumberToWords>, t: Throwable) {
                future.completeExceptionally(t)
            }
        })


        return future.get()
    }

}