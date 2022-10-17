package com.example.pokedex.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.model.ListState
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonsResult
import com.example.pokedex.support.setVisible
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
        observerList()
    }

    private fun observerList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.pokemon.collect() {
                    when (it) {
                        is ListState.Success -> {
                            homeAdapter.submitList(it.value)
                            showLoading(false)
                        }
                        is ListState.Error -> {
                            showLoading(false)
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                        is ListState.Loading -> {
                            showLoading(true)
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerview() {
        val recyclerView = binding.pokemonListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        homeAdapter = HomeAdapter(requireContext()){
          goToDetailPokemon(it)
        }
        recyclerView.adapter = homeAdapter
    }

    private fun showLoading(state: Boolean) {
        binding.progressBarContainer.setVisible(state)
    }

    private fun goToDetailPokemon(pokemon: Pokemon){
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(pokemon.name)
        findNavController().navigate(action)
    }
}