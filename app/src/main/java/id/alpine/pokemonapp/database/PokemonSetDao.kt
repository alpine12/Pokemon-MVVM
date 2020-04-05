package id.alpine.pokemonapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.alpine.pokemonapp.model.PokemonSet

@Dao
interface PokemonSetDao {

    @Query("SELECT * FROM PokemonSet")
    suspend fun getAll(): MutableList<PokemonSet>

    @Query("DELETE FROM PokemonSet")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(vararg pokemonSet: PokemonSet)
}