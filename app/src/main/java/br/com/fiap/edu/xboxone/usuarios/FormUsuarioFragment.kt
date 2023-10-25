package br.com.fiap.edu.xboxone.usuarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.XboxApplication

class FormUsuarioFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = arguments?.getString("usuario")

        if(email == null || email.isEmpty()) {
            //tela de cadastro
        } else {
            // tela de edicao
            val user = XboxApplication.database.getUserDao().getUsuario(email)
            println(user)
        }
    }

}