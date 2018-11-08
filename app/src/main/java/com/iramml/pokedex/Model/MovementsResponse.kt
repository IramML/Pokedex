package com.iramml.pokedex.Model

data class MovementsResponse(val name:String, val type:Type, val target:Target, val flavor_text_entries:ArrayList<FlavorTextEntries>)
data class Target(val name: String)
data class FlavorTextEntries(val flavor_text:String, val language:Languaje)
data class Languaje(val name:String)