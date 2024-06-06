package com.example.pokeapi.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.model.Pokemon
import com.example.pokeapi.service.RetrofitInstance
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    var pokemon: MutableState<Pokemon?> = mutableStateOf(null)
        private set

    fun fetchPokemonData(name: String) {
        viewModelScope.launch {
            try {
                pokemon.value = RetrofitInstance.api.getPokemon(name)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}