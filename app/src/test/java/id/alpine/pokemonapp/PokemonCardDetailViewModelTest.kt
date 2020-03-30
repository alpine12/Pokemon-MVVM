package id.alpine.pokemonapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.alpine.pokemonapp.model.PokemonCard
import id.alpine.pokemonapp.ui.detail.PokemonCardDetailViewModel
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonCardDetailViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    var pokemonCardDetailViewModel: PokemonCardDetailViewModel? = null

    @Before
    fun init() {
        pokemonCardDetailViewModel =
            PokemonCardDetailViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldNullWhenFirstInitialized() {
        val state = pokemonCardDetailViewModel!!.viewState.value!!
        assertNull(state.data)
    }

    @Test
    fun shouldNotNullAfterSetData() {
        pokemonCardDetailViewModel?.setData(PokemonCard("", "", "", ""))
        val state = pokemonCardDetailViewModel!!.viewState.value!!
        assertNotNull(state.data)
    }
}