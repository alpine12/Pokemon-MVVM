package id.alpine.pokemonapp.repository.datastore.pokemon

import id.alpine.pokemonapp.model.PokemonCard

class PokemonCardLocalDataStore : PokemonCardDataStore {
    private val caches = mutableMapOf<String, MutableList<PokemonCard>?>()

    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? =
        if (caches.containsKey(set)) caches[set] else null


    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {
        caches[set] = pokemons
    }

}