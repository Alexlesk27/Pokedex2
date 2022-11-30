package com.example.pokedex.features.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentSearchPokemonsBinding
import com.example.pokedex.model.ListState
import com.example.pokedex.model.Pokemon
import kotlinx.coroutines.launch
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchPokemonFragment() : Fragment() {
    private lateinit var binding: FragmentSearchPokemonsBinding
    private lateinit var searchAdapter: SearchAdapter
    private val searchViewModel: SearchPokemonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
        observePokemonSearchFilter()
        initSearchView()
    }

    private fun initRecyclerview() = with(binding) {
        recyclerSearch.layoutManager = LinearLayoutManager(
            activity, LinearLayoutManager.VERTICAL, false
        )
        searchAdapter = SearchAdapter() {
            goDetailsSearch(it)
        }
        recyclerSearch.adapter = searchAdapter
    }

    private fun observePokemonSearchFilter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.pokemonSearch.collect {
                    when (it) {
                        is ListState.Success -> {
                            searchViewModel.listPokemon = it.value.pokemon
                            searchAdapter.submitList(it.value.pokemon)
                            binding.progressBarContainer.isVisible = false
                        }
                        is ListState.Loading -> {
                            binding.progressBarContainer.isVisible = true
                        }
                        is ListState.Error -> {

                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun initSearchView() = with(binding) {
        searchPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                UIUtil.hideKeyboard(context, searchPokemon)
                filterPokemon(searchViewModel.listPokemon)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterPokemon(searchViewModel.listPokemon)
                return true
            }
        })
    }

    private fun filterPokemon(
        pokemons: List<Pokemon>
    ) {
        val query = binding.searchPokemon.query

        if (query.isNotEmpty()) {
            val listFiltered = pokemons.filter {
                it.name.startsWith(query)
            }
            searchAdapter.submitList(listFiltered)
            searchAdapter.notifyDataSetChanged()
        } else {
            searchAdapter.submitList(searchViewModel.listPokemon)
            searchAdapter.notifyDataSetChanged()
        }
    }

    private fun goDetailsSearch(pokemon: Pokemon) {
        val action =
            SearchPokemonFragmentDirections.actionSearchPokemonFragmentToDetailsFragment(pokemon.name)
        findNavController().navigate(action)
    }
}