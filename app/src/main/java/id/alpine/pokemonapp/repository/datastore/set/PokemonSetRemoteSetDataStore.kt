package id.alpine.pokemonapp.repository.datastore.set

import id.alpine.pokemonapp.model.PokemonSet
import id.alpine.pokemonapp.webservice.PokemonTcgService

class PokemonSetRemoteSetDataStore(private val pokemonTcgService: PokemonTcgService) :
    PokemonSetDataStore {
    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonTcgService.getSets()
        if (response.isSuccessful) return response.body()?.sets

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {}

}