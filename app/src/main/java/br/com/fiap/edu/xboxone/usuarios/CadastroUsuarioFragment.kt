package br.com.fiap.edu.xboxone.usuarios

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.fiap.edu.xboxone.XboxApplication
import br.com.fiap.edu.xboxone.core.database.entities.User

class CadastroUsuarioFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val usuario = arguments?.getString("usuario")
        if(usuario == null) {
            // cadastro
        } else {
            val user = XboxApplication.database.getUserDao().getUsuario(usuario)

            println(user)
            // edição

        }
    }

}