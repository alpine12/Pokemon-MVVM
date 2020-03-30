package id.alpine.pokemonapp.ui.pokemonlist

import id.alpine.pokemonapp.model.PokemonCard

data class PokemonListViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonCard>? = null
)