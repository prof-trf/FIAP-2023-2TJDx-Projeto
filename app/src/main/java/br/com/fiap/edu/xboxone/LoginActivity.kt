package br.com.fiap.edu.xboxone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import br.com.fiap.edu.xboxone.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.edtEmail.addTextChangedListener {
            if(it.toString().isNotEmpty()) {
                binding.txtError.visibility = View.GONE
            }
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }

        binding.btnProximo.setOnClickListener {
            val username = binding.edtEmail.text.toString()
            if(validateUsername(username)) {
                // chamar a proxima activity
                val intent = Intent(this@LoginActivity, SenhaActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)

            } else {
                binding.txtError.visibility = View.VISIBLE
                binding.txtError.text = "Username inválido"
            }
        }
    }

    private fun validateUsername(username: String): Boolean {
        // é um email
        if(username.contains("@")) {
            return true
        }

        // é um telefone
        if(username.length == 11 &&
            username.map { !it.isDigit() }.isEmpty()) {
            return true
        }

        // é um skype
        if(username.length >= 5 && username.isNotEmpty()) {
            return true
        }

        return false
    }
}