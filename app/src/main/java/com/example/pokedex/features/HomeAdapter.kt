package com.example.pokedex.features

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemPokemonBinding
import com.example.pokedex.model.Pokemon

class HomeAdapter(
    var item : List<String> ,
    private var context: Context
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
      val layoutInflater =LayoutInflater.from(context)
        val binding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
      val name = item[position]
        holder.titulo .text = name

    }

    override fun getItemCount(): Int {
       return item.size
    }

    inner class HomeViewHolder( binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
         val titulo = binding.title

    }
    fun submitList(list: List<String>) {
        item
        notifyDataSetChanged()
    }
}