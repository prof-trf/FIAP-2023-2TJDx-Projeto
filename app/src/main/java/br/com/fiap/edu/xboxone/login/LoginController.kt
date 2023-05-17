package br.com.fiap.edu.xboxone.login

import android.annotation.SuppressLint
import android.os.AsyncTask
import br.com.fiap.edu.xboxone.login.contrato.IValidacaoUsuarioView
import java.lang.Exception

/* Classe que controla a tela de senha */
class LoginController {
    private val model: LoginModel = LoginModel()

    /* valida se o usuário digitou um email */
    fun temEmail(email: String): Boolean {
        return email.isNotEmpty() /* isNotEmpty -> valida há conteúdo na variavel senha */
                && email.contains("@") /* contains -> valida há conteúdo na variavel senha contém o caracter @ */
    }

    /* valida se é um usuario valido */
    fun validateUsername(usuario: String, validacaoUsuarioView: IValidacaoUsuarioView) {
        val task = @SuppressLint("StaticFieldLeak")
        object: AsyncTask<String, Void, Boolean>(){
            override fun onPreExecute() {
                validacaoUsuarioView.pesquisandoUsuario()
            }

            override fun doInBackground(vararg params: String?): Boolean {
                val user = params.first() ?: throw Exception("Usuario não preenchido")
                return model.validateUsername(user)
            }

            override fun onPostExecute(result: Boolean?) {
                if(result == true) {
                    validacaoUsuarioView.usuarioLocalizado(usuario)
                } else {
                    validacaoUsuarioView.usuarioNaoLocalizado(Exception("Usuario não localizado"))
                }
            }
        }

        if(usuario.isEmpty()) {
            validacaoUsuarioView.usuarioNaoLocalizado(Exception("Usuario não preenchido"))
        } else {
            task.execute(usuario)
        }
    }
}