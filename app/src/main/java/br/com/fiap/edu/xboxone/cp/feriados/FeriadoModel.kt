package br.com.fiap.edu.xboxone.cp.feriados

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.fiap.edu.xboxone.core.network.InvertextoAPI
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletableFuture

class FeriadoModel {
    val holidayDao = InvertextoAPI.holidayDao


    @RequiresApi(Build.VERSION_CODES.N)
    fun pesquisarFeriado(ano: String, estado: String): ArrayList<Holiday> {
        val future = CompletableFuture<ArrayList<Holiday>>()

        holidayDao.holidays(ano= ano, state = estado).enqueue(object: Callback<ArrayList<Holiday>> {
            override fun onResponse(
                call: Call<ArrayList<Holiday>>,
                response: Response<ArrayList<Holiday>>
            ) {
                if(response.code() in 200..299) {
                    future.complete(response.body())
                } else {
                    future.complete(arrayListOf())
                }
            }

            override fun onFailure(call: Call<ArrayList<Holiday>>, t: Throwable) {
               future.completeExceptionally(t)
            }
        })


        return future.get()
    }
}