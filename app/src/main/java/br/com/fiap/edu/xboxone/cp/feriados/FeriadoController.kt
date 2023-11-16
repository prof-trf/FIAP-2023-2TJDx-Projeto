package br.com.fiap.edu.xboxone.cp.feriados

import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.Holiday

class FeriadoController {

    private val model = FeriadoModel()

    fun pesquisarFeriado(ano: String, estado: String, contract: FeriadoContractView) {
        val validateAno = validateAno(ano)
        var hasError = false
        if (validateAno?.isNotEmpty() == true) {
            //responder erro
            hasError = true
            contract.falhaCarregamentoFeriado(validateAno)
        }

        val validateEstado = validateEstado(estado)
        if (validateEstado?.isNotEmpty() == true) {
            //responder erro
            hasError = true
            contract.falhaCarregamentoFeriado(validateEstado)
        }

        if (!hasError) {

            val task = object : AsyncTask<String, Void, ArrayList<Holiday>?>() {
                private var exception: Exception? = null

                override fun onPreExecute() {
                    contract.localizandoListaFeriados()
                }

                @RequiresApi(Build.VERSION_CODES.N)
                override fun doInBackground(vararg params: String?): ArrayList<Holiday>? {
                    return try {
                        model.pesquisarFeriado(ano, estado)
                    } catch (e: Exception) {
                        exception = e
                        null
                    }
                }

                override fun onPostExecute(result: ArrayList<Holiday>?) {
                    if (exception == null) {
                        contract.feriadoCarregadoComSucesso(result ?: arrayListOf())
                    } else {
                        contract.falhaCarregamentoFeriado(
                            exception?.message ?: "Houve um erro ao gravar usuário"
                        )
                    }
                }
            }

            task.execute()
        }
    }


    private fun validateAno(ano: String): String? {
        if (ano.isEmpty() || ano.isBlank()) {
            return "Ano deve ser preenchido"
        }

        if (ano.length != 4) {
            return "Preencha o campo ano com 4 caracteres números"
        }

        if (ano.any { !it.isDigit() }) {
            return "Preencha o campo ano somente com caracter numérico"
        }

        return null
    }

    private fun validateEstado(estado: String): String? {
        if (estado.isEmpty() || estado.isBlank()) {
            return "Estado deve ser preenchido"
        }

        val estados = arrayListOf(
            "AC",
            "AL",
            "AP",
            "AM",
            "BA",
            "CE",
            "ES",
            "GO",
            "MA",
            "MT",
            "MS",
            "MG",
            "PA",
            "PB",
            "PR",
            "PE",
            "PI",
            "RJ",
            "RN",
            "RS",
            "RO",
            "RR",
            "SC",
            "SP",
            "SE",
            "TO",
            "DF"
        )
        if (!estados.any { it == estado }) {
            return "Preencha o campo estado com a sigla UF correta"
        }

        return null
    }

}