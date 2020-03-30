package id.alpine.pokemonapp.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.alpine.pokemonapp.repository.PokemonCardRepository
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val pokemonRepository: PokemonCardRepository
) : ViewModel() {
    private val mViewState = MutableLiveData<PokemonListViewState>().apply {
        value = PokemonListViewState(loading = true)
    }
    val viewState: LiveData<PokemonListViewState>
        get() = mViewState

    fun getPokemons(set: String) = viewModelScope.launch {
        try {
            val data = pokemonRepository.getPokemons(set)
            id.alpine.pokemonapp.Debug().debug("List Pokemon viewmodel -> $data")
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }
}