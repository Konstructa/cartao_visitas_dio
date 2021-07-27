package br.com.dio.cartaodevisitas.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartaoDeVisitasDao {

    @Query("SELECT * FROM CartaoDeVisitas")
    fun getAll(): LiveData<List<CartaoDeVisitas>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartaoDeVisitas: CartaoDeVisitas)
}