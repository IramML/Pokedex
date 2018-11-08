package com.iramml.pokedex.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.iram.check_ins.RecyclerViewMovements.ClickListener
import com.example.iram.check_ins.RecyclerViewMovements.customAdapter
import com.google.gson.Gson
import com.iramml.pokedex.Common.Common
import com.iramml.pokedex.Interfaces.HttpRequest
import com.iramml.pokedex.Model.PokemonResponse
import com.iramml.pokedex.R
import com.iramml.pokedex.Util.Network
import com.squareup.picasso.Picasso

class PokemonDetailActivity : AppCompatActivity() {
    var network:Network?=null
    var adapter:customAdapter?=null
    var layoutManager:RecyclerView.LayoutManager?=null

    var toolbar: Toolbar?=null
    var ivSpritePokemon:ImageView?=null
    var tvName:TextView?=null
    var tvType:TextView?=null
    var listMovements:RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        initToolbar()
        network= Network(this)
        val url=intent.getStringExtra(PokemonsActivity.KEY_URL)
        network?.httpRequest(url, object: HttpRequest{
            override fun httpResponseSuccess(response: String) {
                val gson=Gson()
                var objectResponse=gson.fromJson(response, PokemonResponse::class.java)
                initViews(objectResponse)
                implementRecyclerView(objectResponse)
            }
        })
    }

    private fun initViews(objectResponse: PokemonResponse?) {
        listMovements=findViewById(R.id.listMovements)
        listMovements?.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        listMovements?.layoutManager=layoutManager

        tvName=findViewById(R.id.tvName)
        tvName?.text=objectResponse?.name

        tvType=findViewById(R.id.tvType)
        var type="Type: "

        for (i in 0..(objectResponse?.types?.size!!-1)){
            if(i+1>objectResponse.types.size-1)
                type+=objectResponse.types[i].type.name
            else
                type+="${objectResponse.types[i].type.name}, "
        }
        tvType?.text=type
        ivSpritePokemon=findViewById(R.id.ivSpritePokemon)
        Picasso.get().load("${Common.BASE_URL_SPRITES}PokeAPI/sprites/master/sprites/pokemon/${objectResponse?.id}.png").into(ivSpritePokemon)
    }

    fun initToolbar(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    fun implementRecyclerView(objectResponse:PokemonResponse){
        adapter= customAdapter(objectResponse?.moves, object: ClickListener{
            override fun onClick(view: View, index: Int) {

            }
        })
        listMovements?.adapter=adapter
    }
}
