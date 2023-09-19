package br.com.fiap.edu.xboxone

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.edu.xboxone.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                BaseActivity::class.java
            )
            startActivity(intent)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupBotaoConfigurarConsoleUI()
    }

    private fun setupBotaoConfigurarConsoleUI() {
        binding.btnConfigurar.setOnClickListener {
            Toast.makeText(this, "Em construção", Toast.LENGTH_SHORT).show()
        }
    }
}