package id.alpine.pokemonapp.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApp {
    private const val BASE_URL = "https://api.pokemontcg.io/v1/"


    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    val log = OkHttpClient.Builder().addInterceptor(logging).build()


    private val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(log)
        .build()

    val POKEMON_TCG_SERVICE: PokemonTcgService = client.create(PokemonTcgService::class.java)
}

