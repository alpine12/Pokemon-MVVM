package id.alpine.pokemonapp.repository.datastore.set

import id.alpine.pokemonapp.model.PokemonSet

interface PokemonSetDataStore {
    suspend fun getSets(): MutableList<PokemonSet>?
    suspend fun addAll(sets: MutableList<PokemonSet>?)
}