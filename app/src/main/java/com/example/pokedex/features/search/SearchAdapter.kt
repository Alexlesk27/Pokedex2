package com.example.pokedex.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemSearchBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.support.IMAGE_EXTENSION
import com.example.pokedex.support.IMAGE_URL
import com.example.pokedex.support.POKEMON_URL_REPLACE
import com.squareup.picasso.Picasso

class SearchAdapter(
    var onClick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, SearchAdapter.SearchViewHolder>(SearchCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            val idUrl = pokemon.url
                .replace(POKEMON_URL_REPLACE, "")
                .replace("/", "")
            Picasso.get().load(
                IMAGE_URL + idUrl + IMAGE_EXTENSION
            )
                .into(binding.thumbnail)

            binding.title.text = pokemon.name.replaceFirstChar { it.uppercase() }

            binding.card.setOnClickListener {
                onClick(pokemon)
            }
        }
    }

    class SearchCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(
            oldItem: Pokemon, newItem: Pokemon
        ): Boolean {

            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }
}