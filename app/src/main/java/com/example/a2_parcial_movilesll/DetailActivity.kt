package com.example.a2_parcial_movilesll
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewSpecie: TextView
    private lateinit var textViewGender: TextView
    private lateinit var imageViewCharacter: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val character = intent.getSerializableExtra("character") as? Character

        textViewName = findViewById(R.id.textViewName)
        textViewSpecie = findViewById(R.id.textViewSpecie)
        textViewGender = findViewById(R.id.textViewGender)
        imageViewCharacter = findViewById(R.id.imageViewCharacter)

        textViewName.text = character?.name
        textViewSpecie.text = character?.species
        textViewGender.text = character?.gender
        Picasso.get().load(character?.image).into(imageViewCharacter)


        }
    }
