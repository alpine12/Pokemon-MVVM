package id.alpine.pokemonapp.webservice

import id.alpine.pokemonapp.model.PokemonCard
import id.alpine.pokemonapp.model.PokemonSet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonTcgService {
    @GET("cards")
    suspend fun getCards(@Query("set") set: String): Response<PokemonCard.PokemonCardResponse>

    @GET("sets")
    suspend fun getSets(): Response<PokemonSet.PokemonSetResponse>
}