package br.com.fiap.edu.xboxone.crieuma.recomendado

import android.os.AsyncTask
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.GamePass
import br.com.fiap.edu.xboxone.crieuma.CrieUmaContract
import kotlin.Exception
import kotlin.random.Random

class JogosRecomendadosController {

    private val database = XboxApplication.database
    fun registrarGamePass(username: String,  jogos: List<Recomendados>, contract: CrieUmaContract) {
        val task = object: AsyncTask<Void, Void, Boolean>() {
            private var exception: Exception? = null

            override fun onPreExecute() {
                contract.loadingCadastrandoUsuario()
            }

            override fun doInBackground(vararg params: Void): Boolean {
                return try {
                    val dao = database.getGamePassDao()
                    jogos.forEach { jogo ->
                        val id = Random.nextInt()
                        dao.inserirRecomendado(GamePass(id, username, jogo.id))
                    }
                    return true
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

        task.execute()
    }

}