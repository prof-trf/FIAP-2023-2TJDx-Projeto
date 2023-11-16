package br.com.fiap.edu.xboxone.cp.numextenso

import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import br.com.fiap.edu.xboxone.core.network.entities.invertexto.NumberToWords


class NumExtensoController {

    private val model  = NumExtensoModel()

    fun gerarNumeroPorExtenso(numero: String, contract: NumExtensoContractView) {
        val validateNumero = validateNumero(numero)
        var hasError = false
        if (validateNumero?.isNotEmpty() == true) {
            //responder erro
            hasError = true
            contract.falhaCarregamentoNumExtenso(validateNumero)
        }

        if (!hasError) {

            val task = object : AsyncTask<String, Void, NumberToWords?>() {
                private var exception: Exception? = null

                override fun onPreExecute() {
                    contract.gerandoNumExtenso()
                }

                @RequiresApi(Build.VERSION_CODES.N)
                override fun doInBackground(vararg params: String?): NumberToWords? {
                    return try {
                        model.gerandoNumeroPorExtenso(numero)
                    } catch (e: Exception) {
                        exception = e
                        null
                    }
                }

                override fun onPostExecute(result: NumberToWords?) {
                    if (exception == null) {
                        contract.numExtensoCarregadoComSucesso(result ?: NumberToWords("Não foi possível gerar o número por extenso"))
                    } else {
                        contract.falhaCarregamentoNumExtenso(
                            exception?.message ?: "Houve um erro ao gravar usuário"
                        )
                    }
                }
            }

            task.execute()
        }
    }


    private fun validateNumero(numero: String): String? {
        if (numero.isEmpty() || numero.isBlank()) {
            return "Número deve ser preenchido"
        }

        val number = numero.replace(".", "").replace(",", "")
        if (!number.any { it.isDigit() }) {
            return "Você deve digitar apenas números"
        }

        return null
    }

}