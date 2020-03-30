package id.alpine.pokemonapp.ui.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import id.alpine.pokemonapp.Debug
import id.alpine.pokemonapp.R
import id.alpine.pokemonapp.model.PokemonCard
import id.alpine.pokemonapp.repository.PokemonCardRepository
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

/**
 * A simple [Fragment] subclass.
 */
class PokemonListFragment : Fragment() {

    private lateinit var vm: PokemonListViewModel
    private lateinit var adapter: PokemonListAdapter

    private val args: PokemonListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PokemonListAdapter()
        rvCard.adapter = adapter

        val factory = PokemonListViewModelFactory(PokemonCardRepository.instance)
        vm = ViewModelProvider(this, factory).get(PokemonListViewModel::class.java).apply {
            viewState.observe(
                viewLifecycleOwner, Observer(this@PokemonListFragment::handleState)
            )
            if (viewState.value?.data == null) getPokemons(args.setName)
            srlCard.setOnRefreshListener { getPokemons(args.setName) }
        }
    }

    fun handleState(viewState: PokemonListViewState?) {
        viewState?.let { data ->
            toggleLoading(data.loading)
            data.data?.let { it -> showData(it) }
            data.error?.let { it -> showError(it) }
        }
    }

    private fun showData(data: MutableList<PokemonCard>) {
        Debug().debug("List Fragment -> ${data.size}")
        adapter.updateData(data)
        tvCardError.visibility = View.GONE
        rvCard.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvCardError.text = error.message
        tvCardError.visibility = View.VISIBLE
        rvCard.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlCard.isRefreshing = loading
    }

}




