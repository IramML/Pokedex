package com.iramml.pokedex.Model

data class PokemonResponse(val name:String, val id:String, val moves:ArrayList<Moves>, val types:ArrayList<Types>)
data class Moves(val move:Move)
data class Move(val name:String, val url:String)
data class Types(val type:Type)
data class Type(val name:String)