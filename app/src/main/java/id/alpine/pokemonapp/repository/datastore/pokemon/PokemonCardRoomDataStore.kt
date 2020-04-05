package id.alpine.pokemonapp.repository.datastore.pokemon

import id.alpine.pokemonapp.database.PokemonCardDao
import id.alpine.pokemonapp.model.PokemonCard

class PokemonCardRoomDataStore(private val pokemonCardDao: PokemonCardDao) : PokemonCardDataStore {
    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val response = pokemonCardDao.getAll(set)
        return if (response.isEmpty()) null else response
    }

    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {
        pokemons?.let {
            pokemonCardDao.insertAll(it)
        }
    }

}