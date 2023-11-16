package br.com.fiap.edu.xboxone.core.network.dao.invertexto

import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HolidayDao {

    /**
     * https://api.invertexto.com/v1/holidays/2022?token=SEUTOKEN&state=SP
     *
     * {ano} -> (2022, por exemplo) parametro na url para representar qual ano inserido para realizar a busca
     */
    @GET("/v1/holidays/{ano}")
    fun holidays(
        @Path("ano") ano: String,
        @Query("token") token: String = "5330|CMl8YLktFKWRwOxn2FZ52UKg8ZdDRiKd",  // (?token=...)Parametro na url para identificar o token. Não precisa alterar
        @Query("state") state: String // (&state=...) Parametro na url para identificar o estado.
    ): Call<ArrayList<Holiday>> // Objeto que representa o JSON


}