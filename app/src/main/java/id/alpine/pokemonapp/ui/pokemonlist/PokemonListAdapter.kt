package id.alpine.pokemonapp.ui.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.alpine.pokemonapp.Debug
import id.alpine.pokemonapp.R
import id.alpine.pokemonapp.model.PokemonCard
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_list_item_pokemon.*

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    private val pokemonCards = mutableListOf<PokemonCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_item_pokemon, parent, false)
    )

    override fun getItemCount() = pokemonCards.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonCards[position])
    }

    fun updateData(newPokemonCards: MutableList<PokemonCard>) {
        Debug().debug(" Adapter List -> ${newPokemonCards[1].name}")
        pokemonCards.clear()
        pokemonCards.addAll(newPokemonCards)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindItem(pokemonCard: PokemonCard) {
            Glide.with(containerView!!)
                .load(pokemonCard.image)
                .into(ivCardLogo)

            tvCardName.text = pokemonCard.name
            tvCardRarity.text = pokemonCard.rarity

            containerView?.setOnClickListener { view ->
                val action = PokemonListFragmentDirections
                    .actionPokemonListFragmentToPokemonCardDetailFragment(
                        pokemonCard,
                        pokemonCard.name!!
                    )
                view.findNavController().navigate(action)
            }
        }
    }
}