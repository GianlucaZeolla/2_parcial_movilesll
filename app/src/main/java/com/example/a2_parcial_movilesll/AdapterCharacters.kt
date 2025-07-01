package com.example.a2_parcial_movilesll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterCharacters(private val character: List<Character>): RecyclerView.Adapter<AdapterCharacters.ViewHolder>(){

    lateinit var onItemClickListener: (Character) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        fun bind(character: Character) {

            view.setOnClickListener {
                onItemClickListener(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return character.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quake = character[position]
        holder.bind(quake)
    }
}