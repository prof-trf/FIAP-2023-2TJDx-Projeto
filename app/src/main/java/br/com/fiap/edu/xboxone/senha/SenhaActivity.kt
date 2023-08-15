package br.com.fiap.edu.xboxone.senha

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.edu.xboxone.R

/* Declaração da tela de login */
class SenhaActivity : AppCompatActivity(R.layout.activity_senha) {

    companion object {
        private const val USERNAME_PARAMS = "username"

        fun navegar(packageContext: Context, usuario: String): Intent {
            val intent = Intent(packageContext, SenhaActivity::class.java) /* configura a intent, tela onde estou e para qual irá navegar */
            intent.putExtra(USERNAME_PARAMS, usuario) /* adiciona um parametro para tela */
            return intent
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val username = intent.getStringExtra(USERNAME_PARAMS) ?: ""
        val senhaFragment = SenhaFragment.newInstance(username)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frl_corpo, senhaFragment)
            .commit()
    }

}