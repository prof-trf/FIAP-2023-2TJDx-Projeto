package br.com.fiap.edu.xboxone.criacaoconta

import android.os.AsyncTask

class CriarContaController {

    private val model = CriarContaModel()

    fun gravarUsuario(email: String, senha: String, criarContaContract: CriarContaContract) {

        val task = object: AsyncTask<String, Void, Boolean>() {
            private var exception: Exception? = null

            override fun onPreExecute() {
                criarContaContract.gravandoInformacao()
            }

            override fun doInBackground(vararg params: String?): Boolean {
                return try {
                    val email = params[0] ?: throw Exception("usuário não informado")
                    val senha = params[1] ?: throw Exception("senha não informada")

                    model.gravarUsuario(email, senha)
                } catch (e: Exception) {
                    exception = e
                    false
                }
            }

            override fun onPostExecute(result: Boolean?) {
                if(result == true) {
                    criarContaContract.usuarioGravadoComSucess()
                } else {
                    criarContaContract.erroAoGravarUsuario(exception?.message ?: "Ocorreu um erro ao tentar gravar o usuário")
                }
            }
        }

        task.execute(email, senha)

    }
}