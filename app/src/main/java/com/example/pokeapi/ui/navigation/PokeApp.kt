package com.example.pokeapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pokeapi.ui.ui.DetailsScreen
import com.example.pokeapi.ui.ui.PokemonListScreen
import com.example.pokeapi.ui.ui.SearchScreen
import com.example.pokeapi.viewModel.PokemonViewModel

@Composable
fun PokeApp() {
    val navController = rememberNavController()
    val viewModel = PokemonViewModel()
    NavHost(navController, startDestination = "search") {
        composable("search") {
            SearchScreen(navController)
        }
        composable("details/{pokemonName}") { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
            DetailsScreen(navController,pokemonName,viewModel)
        }
        composable("pokemonList") {
            PokemonListScreen(navController = navController)
        }
    }
}