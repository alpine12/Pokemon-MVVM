package id.alpine.pokemonapp.ui.setlist

import id.alpine.pokemonapp.model.PokemonSet

data class SetListViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonSet>? = null
)