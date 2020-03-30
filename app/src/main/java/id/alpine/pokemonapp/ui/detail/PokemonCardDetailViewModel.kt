package id.alpine.pokemonapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.alpine.pokemonapp.model.PokemonCard

class PokemonCardDetailViewModel() : ViewModel() {

    private var mViewState = MutableLiveData<PokemonCardDetailViewState>().apply {
        value = PokemonCardDetailViewState(null)
    }

    val viewState: LiveData<PokemonCardDetailViewState> get() = mViewState

    fun setData(pokemonCard: PokemonCard) {
        mViewState.value = mViewState.value?.copy(data = pokemonCard)
    }

}