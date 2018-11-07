package com.iramml.pokedex.Model

data class PokemonsResponse (val results:ArrayList<Results>)
data class Results(val name:String, val url:String, var url_sprite: String)