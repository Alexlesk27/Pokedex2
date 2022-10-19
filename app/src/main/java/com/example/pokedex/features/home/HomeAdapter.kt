package com.example.pokedex.features.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

class HomeAdapter(
    private var context: Context,
    val onclick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, HomeAdapter.HomeViewHolder>(HomeCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.nameTextView.text = pokemon.name
            binding.image.setOnClickListener {
                onclick(pokemon)
            }
        }
    }

    class HomeCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(
            oldItem: Pokemon, newItem: Pokemon
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }
    }
}