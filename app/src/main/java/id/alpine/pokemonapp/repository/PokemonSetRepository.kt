package id.alpine.pokemonapp.repository

import id.alpine.pokemonapp.model.PokemonSet
import id.alpine.pokemonapp.repository.datastore.set.PokemonSetDataStore

class PokemonSetRepository internal constructor() : BaseRepository<PokemonSetDataStore>() {

    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = localDataStore?.getSets()
        if (cache != null) return cache
        val response = remoteDataStore?.getSets()
//        val response = RetrofitApp.POKEMON_TCG_SERVICE.getSets().body()?.sets
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy {
            PokemonSetRepository()
        }
    }

}