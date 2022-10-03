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

class HomeAdapter(
    var item: List<String>,
    private var context: Context
) : ListAdapter<Pokemon, HomeAdapter.HomeViewHolder>(HomeCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val name = item[position]
        holder.title.text = name
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class HomeViewHolder(binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
    }

    class HomeCallback: DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon
        ): Boolean {
           return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name== newItem.name
        }
    }
}