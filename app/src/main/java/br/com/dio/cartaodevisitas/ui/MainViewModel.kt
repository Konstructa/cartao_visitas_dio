package br.com.dio.cartaodevisitas.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dio.cartaodevisitas.data.CartaoDeVisitas
import br.com.dio.cartaodevisitas.data.CartaoDeVisitasRepositorio


class MainViewModel(private val cartaoDeVisitasRepositorio: CartaoDeVisitasRepositorio): ViewModel() {

    fun insert(cartaoDeVisitas: CartaoDeVisitas) {
        cartaoDeVisitasRepositorio.insert(cartaoDeVisitas)
    }

    fun getAll(): LiveData<List<CartaoDeVisitas>> {
        return cartaoDeVisitasRepositorio.getAll()
    }

}

class MainViewModelFactory(private val repositorio: CartaoDeVisitasRepositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}