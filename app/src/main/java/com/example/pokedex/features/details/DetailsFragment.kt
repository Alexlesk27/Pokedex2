package com.example.pokedex.features.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDetailsBinding
import com.example.pokedex.model.*
import com.example.pokedex.support.setVisible
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel: PokemonDetailsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsViewModel.getPokemon(args.pokemon)
        observePokemonDetails()
    }

    private fun observePokemonDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.pokemonDetail.collect {
                    when (it) {
                        is ListState.Success -> {
                            showLoading(false)
                            pokemonDetails(it.value)
                        }
                        is ListState.Error -> {
                            showLoading(false)
                            showToast(it)
                        }
                        is ListState.Loading -> {
                            showLoading(true)
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun showToast(it: ListState.Error) {
        Toast.makeText(
            requireContext(),
            it.error ?: R.string.pokemon_error_details.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showLoading(state: Boolean) {
        binding.progressBarContainer.setVisible(state)
    }

    private fun pokemonDetails(pokemonDetails: PokemonResult) = with(binding) {
        idPokemon.text = pokemonDetails.id.toString()

        Picasso.get().load(pokemonDetails.sprites.other.home.frontDefault)
            .into(imagePokemonDetails)

        namePokemon.text = pokemonDetails.name.replaceFirstChar { it.uppercase() }
        type.text = pokemonDetails.types[0].type.name.replaceFirstChar { it.uppercase() }

        hpValue.text = pokemonDetails.stats[0].baseStat.toString()
        progressBarHP.progress = pokemonDetails.stats[0].baseStat

        attackValue.text = pokemonDetails.stats[1].baseStat.toString()
        binding.progressBarAttack.progress = pokemonDetails.stats[1].baseStat

        defenseValue.text = pokemonDetails.stats[2].baseStat.toString()
        progressBarDefense.progress = pokemonDetails.stats[2].baseStat

        spAttackValue.text = pokemonDetails.stats[3].baseStat.toString()
        progressBarspAttack.progress = pokemonDetails.stats[3].baseStat

        spDefenseValue.text = pokemonDetails.stats[4].baseStat.toString()
        progressBarspDefense.progress = pokemonDetails.stats[4].baseStat

        speedValue.text = pokemonDetails.stats[5].baseStat.toString()
        progressBarSpeed.progress = pokemonDetails.stats[5].baseStat

    }
}