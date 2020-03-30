package id.alpine.pokemonapp.repository

import id.alpine.pokemonapp.Debug
import id.alpine.pokemonapp.model.PokemonCard
import id.alpine.pokemonapp.repository.datastore.pokemon.PokemonCardDataStore


class PokemonCardRepository internal constructor() : BaseRepository<PokemonCardDataStore>() {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val cache = localDataStore?.getPokemons(set)
        if (cache != null) return cache
        val response = remoteDataStore?.getPokemons(set)
        Debug().debug(" Card Repo -> ${response.toString()}")
        localDataStore?.addAll(set, response)
        return response
    }

    companion object {
        val instance by lazy { PokemonCardRepository() }
    }
}