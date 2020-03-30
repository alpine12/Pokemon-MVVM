package id.alpine.pokemonapp.ui.setlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.alpine.pokemonapp.R
import id.alpine.pokemonapp.model.PokemonSet
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_list_item_main.*

class SetListAdapter : RecyclerView.Adapter<SetListAdapter.ViewHolder>() {
    private val pokemonSet = mutableListOf<PokemonSet>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_list_item_main, parent, false)
        )

    override fun getItemCount(): Int = pokemonSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pokemonSet[position])
    }

    fun updateData(newPokemonSet: MutableList<PokemonSet>) {
        pokemonSet.clear()
        pokemonSet.addAll(newPokemonSet)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bindItem(item: PokemonSet) {
            Glide.with(containerView!!)
                .load(item.logo)
                .into(ivSetLogo)

            tvSetName.text = item.name

            containerView?.setOnClickListener {
                val action =
                    SetListFragmentDirections.actionSetListFragmentToPokemonListFragment(item.name)
                itemView.findNavController().navigate(action)
            }
        }

    }

}
