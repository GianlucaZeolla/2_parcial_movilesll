package com.example.a2_parcial_movilesll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterCharacters(private val characters: List<Character>) :
    RecyclerView.Adapter<AdapterCharacters.ViewHolder>() {

    lateinit var onItemClickListener: (Character) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.findViewById(R.id.textView_name)
        private val imageView_character: ImageView = view.findViewById(R.id.imageView_character)

        fun bind(character: Character) {
            textViewName.text = character.name
            Picasso.get().load(character.image).into(imageView_character)

            view.setOnClickListener {
                onItemClickListener(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }
}
