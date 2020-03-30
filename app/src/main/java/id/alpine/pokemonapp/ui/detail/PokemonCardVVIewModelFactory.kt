package id.alpine.pokemonapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PokemonCardVVIewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonCardDetailViewModel::class.java)) {
            return PokemonCardDetailViewModel() as T
        }

        throw IllegalArgumentException()
    }

}