package com.example.iram.check_ins.RecyclerViewMain

import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.iramml.pokedex.Model.PokemonsResponse
import com.iramml.pokedex.Model.Results
import com.iramml.pokedex.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.template_list_pokemons.view.*


class customAdapter(items: ArrayList<Results>, var listener: ClickListener, var longClickListener: LongClickListener) : RecyclerView.Adapter<customAdapter.ViewHolder>() {
    var itemsSelected: ArrayList<Int>
    var viewHolder: ViewHolder?=null
    var items: ArrayList<Results>?=null
    init {
        this.items=items
        itemsSelected = ArrayList()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.template_list_pokemons, viewGroup, false)
        viewHolder = ViewHolder(view, listener, longClickListener)
        return viewHolder!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var item=items?.get(i)
        viewHolder.tvName?.text = item?.name
        if(!item?.url_sprite.isNullOrEmpty())
            Picasso.get().load(item?.url_sprite).into(viewHolder.ivSpritePokemon)
    }
    override fun getItemCount(): Int {
        return items?.count()!!
    }
    class ViewHolder(itemView: View, listener:ClickListener, longClickListener:LongClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {
        var tvName: TextView?=null
        var ivSpritePokemon:ImageView?=null
        var listener: ClickListener?=null
        var longClickListener: LongClickListener?=null

        init {
            tvName = itemView.tvName
            ivSpritePokemon=itemView.ivSpritePokemon
            this.listener=listener
            this.longClickListener=longClickListener
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
        override fun onClick(view: View?) {
            this.listener?.onClick(view!!, adapterPosition)
        }
        override fun onLongClick(view: View?): Boolean {
            this.longClickListener?.longClick(view!!, adapterPosition)
            return false
        }
    }
}