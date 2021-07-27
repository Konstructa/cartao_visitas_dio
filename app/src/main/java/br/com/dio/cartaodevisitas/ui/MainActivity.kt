package br.com.dio.cartaodevisitas.ui

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import br.com.dio.cartaodevisitas.App
import br.com.dio.cartaodevisitas.databinding.ActivityMainBinding
import br.com.dio.cartaodevisitas.util.Image


class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repositorio)
    }
    private val adapter by lazy { CartaoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
        setUpPermissions()
        binding.rvCards.adapter = adapter
        getAllCartaoDeVisitas()
    }

    fun insertListeners(){
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddCartaoActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    private fun getAllCartaoDeVisitas() {
        mainViewModel.getAll().observe(this, { cartaoDeVisitas ->
            adapter.submitList(cartaoDeVisitas)
        })
    }

    private fun setUpPermissions() {
        // write permission to access the storage
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }
}