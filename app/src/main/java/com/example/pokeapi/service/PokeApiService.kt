package com.example.pokeapi.service

import com.example.pokeapi.model.Pokemon
import com.example.pokeapi.model.PokemonListResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon

    @GET("pokemon?limit=1000")
    suspend fun getPokemonList(): PokemonListResult
}