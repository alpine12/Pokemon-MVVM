package id.alpine.pokemonapp

import android.app.Application
import id.alpine.pokemonapp.database.AppDatabase
import id.alpine.pokemonapp.repository.PokemonCardRepository
import id.alpine.pokemonapp.repository.PokemonSetRepository
import id.alpine.pokemonapp.repository.datastore.pokemon.PokemonCardRemoteDataStore
import id.alpine.pokemonapp.repository.datastore.pokemon.PokemonCardRoomDataStore
import id.alpine.pokemonapp.repository.datastore.set.PokemonSetRemoteSetDataStore
import id.alpine.pokemonapp.repository.datastore.set.PokemonSetRoomDataStore
import id.alpine.pokemonapp.webservice.RetrofitApp

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val pokemonApiService = RetrofitApp.POKEMON_TCG_SERVICE
        val appDatabase = AppDatabase.getInstance(this)
        PokemonSetRepository.instance.apply {
            init(
                PokemonSetRoomDataStore(appDatabase.pokemonSetDao()),
                PokemonSetRemoteSetDataStore(pokemonApiService)
            )
        }

        PokemonCardRepository.instance.apply {
            init(
                PokemonCardRoomDataStore(appDatabase.pokemonCardDao()),
                PokemonCardRemoteDataStore(pokemonApiService)
            )
        }

    }
}