package br.com.fiap.edu.xboxone.crieuma

import android.os.AsyncTask
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.Recomendado
import br.com.fiap.edu.xboxone.crieuma.jogos.Jogos
import java.lang.IllegalArgumentException
import kotlin.random.Random

class CrieUmaAssociarJogoController {
    fun associarJogos(usuario: String, jogos: Array<Jogos.Jogo>, contract: CrieUmaContract) {
        val task = object: AsyncTask<Any, Void, Boolean>() {
            private var exception: Exception? = null

            override fun onPreExecute() {
                contract.loadingCadastrandoUsuario()
            }

            override fun doInBackground(vararg params: Any?): Boolean {
                return try {
                   val usuario: String = (params[0] as? String) ?: throw IllegalArgumentException()
                   val jogos: Array<Jogos.Jogo> = (params[1] as? Array<Jogos.Jogo>) ?: throw IllegalArgumentException()
                    val dao = XboxApplication.database.getRecomendadoDao()

                    jogos.filter { it.recomendado }
                        .forEach {
                            val id = Random.nextInt()
                            val recomendado = Recomendado(id, usuario, it.id)
                            dao.inserirRecomendacao(recomendado)
                        }
                    true
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

        task.execute(usuario, jogos)
    }
}