package com.example.pokedex.features.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentSearchPokemonsBinding
import com.example.pokedex.features.home.HomeViewModel
import com.example.pokedex.model.Pokemon
import com.example.pokedex.support.removeDotAndDash
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.http.Query

class SearchPokemonFragment : Fragment() {
    private lateinit var binding: FragmentSearchPokemonsBinding
    private lateinit var searchAdapter: SearchAdapter
    private val homeViewModel: HomeViewModel by viewModel()

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

    private fun collectResults(query: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.getPokemon().collectLatest {
                val listFilter = it.filter {
                    it.name.contains(query)
                }
                searchAdapter.submitData(listFilter)
            }
        }
    }

    private fun initSearchView() {
        binding.searchPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    binding.recyclerSearch.isVisible = true
                    collectResults(query.trim())
                }

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    binding.recyclerSearch.isVisible = true
                    binding.textInfo.isVisible = false
                    collectResults(newText)
                }
                return true
            }

        })

    }


}