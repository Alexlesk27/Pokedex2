package com.example.pokedex.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.model.Pokemon
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
        collectResults()
    }

    private fun initRecyclerview() = with(binding) {
        pokemonListRecyclerView.layoutManager = GridLayoutManager(
            activity,
            2
        )
        homeAdapter = HomeAdapter(requireContext()) {
            goToDetailPokemon(it)
        }
        pokemonListRecyclerView.adapter = homeAdapter.withLoadStateFooter(
            footer = SampleLoadStateAdapter { homeAdapter.retry() }
        )
        homeAdapter.addLoadStateListener { loadState ->
            errorMsg.isVisible = loadState.source.refresh is LoadState.Error
            pokemonListRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        }
    }

    private fun collectResults() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.getPokemon().collectLatest {
                homeAdapter.submitData(it)
            }
        }
    }

    private fun goToDetailPokemon(pokemon: Pokemon) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(pokemon.name)
        findNavController().navigate(action)
    }
}