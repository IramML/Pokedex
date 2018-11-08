package com.example.iram.check_ins.RecyclerViewMovements

import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.iramml.pokedex.Model.Moves
import com.iramml.pokedex.Model.PokemonsResponse
import com.iramml.pokedex.Model.Results
import com.iramml.pokedex.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.template_list_movements.view.*
import kotlinx.android.synthetic.main.template_list_pokemons.view.*


class customAdapter(items: ArrayList<Moves>, var listener: ClickListener) : RecyclerView.Adapter<customAdapter.ViewHolder>() {
    var itemsSelected: ArrayList<Int>
    var viewHolder: ViewHolder?=null
    var items: ArrayList<Moves>?=null
    init {
        this.items=items
        itemsSelected = ArrayList()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.template_list_movements, viewGroup, false)
        viewHolder = ViewHolder(view, listener)
        return viewHolder!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var item=items?.get(i)?.move
        viewHolder.tvNameMovement?.text= item?.name?.replace('-', ' ')

    }
    override fun getItemCount(): Int {
        return items?.count()!!
    }
    class ViewHolder(itemView: View, listener:ClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var tvNameMovement: TextView?=null
        var listener: ClickListener?=null

        init {
            tvNameMovement = itemView.tvNameMovement
            this.listener=listener
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            this.listener?.onClick(view!!, adapterPosition)
        }
    }
}