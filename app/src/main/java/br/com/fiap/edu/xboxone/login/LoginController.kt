package br.com.fiap.edu.xboxone.login

import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import br.com.fiap.edu.xboxone.login.contrato.IUsuarioController
import br.com.fiap.edu.xboxone.login.contrato.IValidacaoUsuarioView
import java.lang.Exception
import java.util.concurrent.CompletableFuture

/* Classe que controla a tela de senha */
class LoginController {
    private val model = LoginModel()

    /* valida se o usuário digitou um email */
    fun temEmail(email: String): Boolean {
        return email.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel senha */
                && email.contains("@") /* contains -> valida há conteúdo na variavel senha contém o caracter @ */
    }

    /* valida se é um usuario valido */
    fun validateUsername(usuario: String, validacaoUsuarioView: IValidacaoUsuarioView) {
        // eh e-mail
        if (!temEmail(usuario)) {     /* valida se o usuário digitou um email */
            validacaoUsuarioView.erroNaPesquisaDaBaseDedados(Exception("usuário não informado"))
        }

        val task = object: AsyncTask<String, Void, Boolean>() {
            override fun onPreExecute() {
                validacaoUsuarioView.pesquisandoUsuario()
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun doInBackground(vararg params: String?): Boolean {
                val usuario = params.first() ?: throw Exception("usuário não informado")
                val future = CompletableFuture<Boolean>()

                model.validateUsername(usuario, object: IUsuarioController {
                    override fun usuarioLocalizado() {
                        future.complete(true)
                    }

                    override fun usuarioNaoLocalizado() {
                        future.complete(false)
                    }
                })

                return future.get()
            }

            override fun onPostExecute(result: Boolean?) {
                if(result == true) {
                    validacaoUsuarioView.usuarioLocalizadoNaBaseDeDados(usuario)
                } else {
                    validacaoUsuarioView.erroNaPesquisaDaBaseDedados(Exception("Digite um e-mail ou telefone válido"))
                }
            }
        }

        task.execute(usuario)
    }

}