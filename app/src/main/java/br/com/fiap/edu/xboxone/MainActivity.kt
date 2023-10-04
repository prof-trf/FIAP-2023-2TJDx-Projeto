package br.com.fiap.edu.xboxone

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.edu.xboxone.core.database.entities.Product
import br.com.fiap.edu.xboxone.core.database.entities.User
import br.com.fiap.edu.xboxone.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var click = 0

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
            //Toast.makeText(this, "Em construção", Toast.LENGTH_SHORT).show()
//            Handler(Looper.getMainLooper()).post {
//                val id = System.currentTimeMillis().toInt()
//                val user = User(id, "Thiago-${click++}", "Filadelfo")
//                XboxApplication.database.getUserDao().inserirUsuario(user)
//            }
            Handler(Looper.getMainLooper()).post {
                val id = Random.nextInt(1, 100)
                val user = User(id, "Thiago-$id", "Filadelfo")
                XboxApplication.database.getUserDao().inserirUsuario(user)

                val product = Product(id, "Prod-$id")
                XboxApplication.database.getProductDao().inserirProduct(product)
            }
        }
    }
}