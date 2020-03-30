package id.alpine.pokemonapp

import android.app.Application
import id.alpine.pokemonapp.repository.PokemonCardRepository
import id.alpine.pokemonapp.repository.PokemonSetRepository
import id.alpine.pokemonapp.repository.datastore.pokemon.PokemonCardLocalDataStore
import id.alpine.pokemonapp.repository.datastore.pokemon.PokemonCardRemoteDataStore
import id.alpine.pokemonapp.repository.datastore.set.PokemonSetLocalSetDataStore
import id.alpine.pokemonapp.repository.datastore.set.PokemonSetRemoteSetDataStore
import id.alpine.pokemonapp.webservice.RetrofitApp

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val pokemonApiService = RetrofitApp.POKEMON_TCG_SERVICE
        PokemonSetRepository.instance.apply {
            init(PokemonSetLocalSetDataStore(), PokemonSetRemoteSetDataStore(pokemonApiService))
        }

        PokemonCardRepository.instance.apply {
            init(PokemonCardLocalDataStore(), PokemonCardRemoteDataStore(pokemonApiService))
        }

    }
}