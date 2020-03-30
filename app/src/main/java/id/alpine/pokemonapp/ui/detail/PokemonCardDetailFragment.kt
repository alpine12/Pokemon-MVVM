package id.alpine.pokemonapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import id.alpine.pokemonapp.R
import id.alpine.pokemonapp.model.PokemonCard
import kotlinx.android.synthetic.main.fragment_pokemon_card_detail.*

/**
 * A simple [Fragment] subclass.
 */
class PokemonCardDetailFragment : Fragment() {

    private lateinit var vm: PokemonCardDetailViewModel

    private val args: PokemonCardDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_card_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonCard = args.pokemonCard
        vm = ViewModelProvider(this).get(PokemonCardDetailViewModel::class.java).apply {
            viewState.observe(
                viewLifecycleOwner,
                Observer(this@PokemonCardDetailFragment::handleState)
            )
            pokemonCard?.let { setData(it) }
        }
    }

    fun handleState(viewState: PokemonCardDetailViewState) {
        viewState.data?.let { showDetail(it) }
    }

    private fun showDetail(data: PokemonCard) {

        Glide.with(this)
            .load(data.image)
            .into(ivLogo)

        tvName.text = data.name
        tvRarity.text = data.rarity
        tvSeries.text = data.series
    }
}

