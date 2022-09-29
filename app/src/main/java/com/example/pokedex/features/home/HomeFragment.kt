package com.example.pokedex.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentHomeBinding
import com.example.pokedex.model.ListState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewmodel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerList()
    }

    private fun observerList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.home.collect() {
                    when (it) {
                        is ListState.Success -> {
                            iniReciclerview(it)
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    private fun iniReciclerview(it: ListState.Success<List<String>>) {
        val recyclerView = binding.pokemonListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
        homeAdapter = HomeAdapter(it.value, requireContext())
        recyclerView.adapter = homeAdapter
    }
}