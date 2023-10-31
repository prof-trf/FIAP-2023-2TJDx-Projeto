package br.com.fiap.edu.xboxone.core.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Criação do conector (quem fará a requisição)
internal object RetrofitEngine {
    fun create(baseUrl: String): Retrofit {
        //  Configura o Log para podermos observar como funciona por dentro, olhar o LOGCAT
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        // Adiciona o LogInteceptor dentro do motor de requisições
        val client: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .build()

        // Classe responsavel por transforma JSON em Objeto
        val converterFactory = GsonConverterFactory.create(GsonBuilder().create())


        // Conector que faz as requisições e conversões acontecerem
        return Retrofit.Builder()
            .baseUrl(baseUrl) // URL base do recurso
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
    }

}