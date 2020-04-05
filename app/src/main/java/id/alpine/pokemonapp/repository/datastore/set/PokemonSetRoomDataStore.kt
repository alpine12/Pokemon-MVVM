package id.alpine.pokemonapp.repository.datastore.set

import id.alpine.pokemonapp.database.PokemonSetDao
import id.alpine.pokemonapp.model.PokemonSet

class PokemonSetRoomDataStore(private val pokemonSetDao: PokemonSetDao) : PokemonSetDataStore {
    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonSetDao.getAll()
        return if (response.isEmpty()) return null else response

    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        sets?.let {
            pokemonSetDao.insertAll(*it.toTypedArray())
        }
    }

}