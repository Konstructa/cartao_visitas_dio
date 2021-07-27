package br.com.dio.cartaodevisitas.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CartaoDeVisitasRepositorio (private val dao: CartaoDeVisitasDao) {

    fun insert(cartaoDeVisitas: CartaoDeVisitas) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(cartaoDeVisitas)
        }
    }

    fun getAll() = dao.getAll()
}
