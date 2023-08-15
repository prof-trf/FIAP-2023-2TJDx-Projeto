package br.com.fiap.edu.xboxone.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding
import br.com.fiap.edu.xboxone.login.contrato.IValidacaoUsuarioView
import br.com.fiap.edu.xboxone.senha.SenhaActivity
import java.lang.Exception

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
            .add(R.id.frl_corpo, loginFragment)
            .commit()
    }
}