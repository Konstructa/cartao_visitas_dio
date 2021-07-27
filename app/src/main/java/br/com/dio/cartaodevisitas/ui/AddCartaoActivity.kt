package br.com.dio.cartaodevisitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.com.dio.cartaodevisitas.App
import br.com.dio.cartaodevisitas.R
import br.com.dio.cartaodevisitas.data.CartaoDeVisitas
import br.com.dio.cartaodevisitas.databinding.ActivityAddCartaoBinding


class AddCartaoActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddCartaoBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners(){
        binding.btnClose.setOnClickListener{
            finish()
        }
        binding.btnConfirm.setOnClickListener{
            val businessCard = CartaoDeVisitas(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}