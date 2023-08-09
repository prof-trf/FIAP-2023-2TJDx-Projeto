package br.com.fiap.edu.xboxone.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.edu.xboxone.R

/* Declaração da tela de login */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val loginFragment = LoginFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frl_conteudo, loginFragment)
            .commit()
    }
}