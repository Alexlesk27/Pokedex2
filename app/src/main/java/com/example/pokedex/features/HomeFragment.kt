package com.example.pokedex.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private val  homeViewModel: HomeViewmodel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerList()

    }

    private fun observerList() {
        homeViewModel.home.observe(viewLifecycleOwner, Observer {
            initRecyclerview(it)

        })
    }

    private fun initRecyclerview(it: List<String>) {
        val recyclerView = binding.homeRecylerview
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            GridLayoutManager.VERTICAL, false
        )
        homeAdapter = HomeAdapter(it, requireContext())
        recyclerView.adapter = homeAdapter
    }


}