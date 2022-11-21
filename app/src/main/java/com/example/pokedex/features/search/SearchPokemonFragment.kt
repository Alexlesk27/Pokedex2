package com.example.pokedex.features.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentSearchPokemonsBinding
import com.example.pokedex.model.ListState
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.http.Query

class SearchPokemonFragment : Fragment() {
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
        initSearchView()
    }

    private fun initRecyclerview() = with(binding) {
        recyclerSearch.layoutManager = LinearLayoutManager(
            activity, LinearLayoutManager.VERTICAL, false
        )
        searchAdapter = SearchAdapter()
        recyclerSearch.adapter = searchAdapter
    }

    private fun observePokemonSearch(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.pokemonSearch.collect {
                when (it) {
                    is ListState.Success -> {
                        val listFilter = it.value.pokemon.filter {
                            it.name.contains(query)
                        }
                        searchAdapter.submitList(listFilter)
                    }
                    else -> {}
                }
            }
        }
    }


    private fun initSearchView() {
        binding.searchPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                observePokemonSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                observePokemonSearch(newText)
                return true
            }
        })
    }
}