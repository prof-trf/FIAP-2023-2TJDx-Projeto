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
                contract.loadingCadastrandoUsuario() //notificando a tela que está sendo executado alguma coisa
            }

            override fun doInBackground(vararg params: Any?): Boolean {
                return try {
                   val usuario: String = (params[0] as? String) ?: throw IllegalArgumentException()
                   val jogos: Array<Jogos.Jogo> = (params[1] as? Array<Jogos.Jogo>) ?: throw IllegalArgumentException()
                    val dao = XboxApplication.database.getRecomendadoDao() // recuperando instancia de acesso aos dados da tabela

                    jogos.filter { it.recomendado } // filtrando todos os jogos selecionados
                        .forEach {
                            val id = Random.nextInt() // gerando um id dinamico e randomico
                            val recomendado = Recomendado(id, usuario, it.id) // criando a classe que representa a tabela
                            dao.inserirRecomendacao(recomendado) // gravando dados na tabela
                        }
                    true
                } catch (e: Exception) {
                    exception = e
                    false
                }
            }

            override fun onPostExecute(result: Boolean?) {
                if(result == true) {
                    contract.usuarioCadastradoComSucesso() //notificando a tela que houve um sucesso na gravação
                } else {
                    contract.usuarioFalhaNoCadastro(exception?.message ?: "Houve um erro ao gravar usuário") //notificando a tela que houve um erro na gravação
                }
            }
        }

        task.execute(usuario, jogos)
    }
}