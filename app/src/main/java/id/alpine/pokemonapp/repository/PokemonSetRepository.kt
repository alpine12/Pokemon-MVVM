package id.alpine.pokemonapp.repository

import id.alpine.pokemonapp.Debug
import id.alpine.pokemonapp.model.PokemonSet
import id.alpine.pokemonapp.repository.datastore.set.PokemonSetDataStore

class PokemonSetRepository internal constructor() : BaseRepository<PokemonSetDataStore>() {

    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = localDataStore?.getSets()
        Debug().debug("Repository Cache : ${cache?.size}")
        if (cache != null) return cache
        val response = remoteDataStore?.getSets()
        Debug().debug("Repository Response : ${response?.size}")
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy {
            PokemonSetRepository()
        }
    }

}