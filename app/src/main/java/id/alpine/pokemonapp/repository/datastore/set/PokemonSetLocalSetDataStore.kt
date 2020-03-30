package id.alpine.pokemonapp.repository.datastore.set

import id.alpine.pokemonapp.model.PokemonSet

class PokemonSetLocalSetDataStore :
    PokemonSetDataStore {
    private var caches = mutableListOf<PokemonSet>()

    override suspend fun getSets(): MutableList<PokemonSet>? =
        if (caches.isNotEmpty()) caches else null

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        sets?.let {
            caches = it
        }
    }


}