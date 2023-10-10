package br.com.fiap.edu.xboxone.crieuma

import android.os.AsyncTask
import kotlin.Exception

class CrieUmaController {

    private val model = CrieUmaModel()

    fun registrarUsuario(usuario: String, senha: String, contract: CrieUmaContract) {
        val task = object: AsyncTask<String, Void, Boolean>() {
            private var exception: Exception? = null

            override fun onPreExecute() {
                contract.loadingCadastrandoUsuario()
            }

            override fun doInBackground(vararg params: String?): Boolean {
                return try {
                    val usuario = params[0] ?: throw Exception("usuário não informado")
                    val senha = params[1] ?: throw Exception("senha não informada")
                    model.registrarUsuario(usuario, senha)
                } catch (e: Exception) {
                    exception = e
                    false
                }
            }

            override fun onPostExecute(result: Boolean?) {
                if(result == true) {
                    contract.usuarioCadastradoComSucesso()
                } else {
                    contract.usuarioFalhaNoCadastro(exception?.message ?: "Houve um erro ao gravar usuário")
                }
            }
        }

        task.execute(usuario, senha)
    }

}