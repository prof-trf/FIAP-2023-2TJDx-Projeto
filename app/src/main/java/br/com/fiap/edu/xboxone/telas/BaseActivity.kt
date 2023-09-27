package br.com.fiap.edu.xboxone.telas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.fiap.edu.xboxone.R
import br.com.fiap.edu.xboxone.databinding.ActivityBaseBinding

class BaseActivity: AppCompatActivity() {


    private lateinit var binding: ActivityBaseBinding

    /* Classe responsavel pela widget de navegação */
    private var navHostFragment: NavHostFragment? = null

    /* Classe que realiza o controller da navegação */
    private var navController: NavController? = null


    /* Primeiro método a ser executado quando montada a tela */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /* Método executado após a tela ser montada (lido o xml) */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        /* Recupera a componente visual para realizar as injeções de telas */
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        /* faz o controller das injeções das telas */
        navController = navHostFragment?.navController
    }

}