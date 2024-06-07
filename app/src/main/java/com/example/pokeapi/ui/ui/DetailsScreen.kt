package com.example.pokeapi.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.pokeapi.R
import com.example.pokeapi.model.Pokemon
import com.example.pokeapi.viewModel.PokemonViewModel

@Composable
fun DetailsScreen(navController: NavController, pokemonName: String, viewModel: PokemonViewModel) {
    val pokemon by viewModel.pokemon

    LaunchedEffect(pokemonName) {
        viewModel.fetchPokemonData(pokemonName)
    }

    pokemon?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.pokemon_mosaic),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(colors = listOf(Color(0xAAFFFFFF), Color(0xAAFFFFFF))))
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${it.id}.png"
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = "Image of ${it.name}",
                    modifier = Modifier.size(400.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Name: ${it.name.capitalize()}", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
                Text("ID: ${it.id}", style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                Text("Height: ${it.height}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
                Text("Weight: ${it.weight}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
                Text("Types: ${it.types.joinToString { type -> type.type.name.capitalize() }}", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}