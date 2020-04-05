package id.alpine.pokemonapp.repository

import id.alpine.pokemonapp.Debug
import id.alpine.pokemonapp.model.PokemonCard
import id.alpine.pokemonapp.repository.datastore.pokemon.PokemonCardDataStore


class PokemonCardRepository internal constructor() : BaseRepository<PokemonCardDataStore>() {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val cache = localDataStore?.getPokemons(set)
        Debug().debug("Repository Card Cache : ${cache?.size}")
        if (cache != null) return cache
        val response = remoteDataStore?.getPokemons(set)
        Debug().debug("Repository Card response : ${response?.size}")
        // localDataStore?.addAll(set, response)
        return response
    }

    companion object {
        val instance by lazy { PokemonCardRepository() }
    }
}