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
    private lateinit var textViewLocation: TextView
    private lateinit var imageViewCharacter: ImageView
    private lateinit var textViewEpisodes: TextView
    private lateinit var textViewCreated: TextView
    private lateinit var textViewStatus: TextView
    private lateinit var textViewOrigin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val character = intent.getSerializableExtra("character") as? Character

        textViewName = findViewById(R.id.textViewName)
        textViewSpecie = findViewById(R.id.textViewSpecie)
        textViewGender = findViewById(R.id.textViewGender)
        textViewLocation = findViewById(R.id.textViewLocation)
        imageViewCharacter = findViewById(R.id.imageViewCharacter)
        textViewEpisodes = findViewById(R.id.textViewEpisodes)
        textViewStatus = findViewById(R.id.textViewStatus)
        textViewOrigin = findViewById(R.id.textViewOrigin)

        textViewName.text = character?.name
        textViewSpecie.text = "Species: ${character?.species}"
        textViewGender.text = "Gender: ${character?.gender}"
        textViewLocation.text = "Location: ${character?.location?.name}"
        textViewEpisodes.text = "Episodes: ${character?.episode?.size}"
        textViewStatus.text = "Status: ${character?.status}"
        textViewOrigin.text = "Origin: ${character?.origin?.name}"

        Picasso.get().load(character?.image).into(imageViewCharacter)




        }
    }
