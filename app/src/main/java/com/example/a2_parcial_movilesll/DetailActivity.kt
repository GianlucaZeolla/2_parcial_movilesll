package com.example.a2_parcial_movilesll
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewSpecie: TextView
    private lateinit var textViewGender: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val character = intent.getSerializableExtra("character") as? Character

        textViewName = findViewById(R.id.textViewName)
        textViewSpecie = findViewById(R.id.textViewSpecie)
        textViewGender = findViewById(R.id.textViewGender)

        textViewName.text = character?.name
        textViewSpecie.text = character?.species
        textViewGender.text = character?.gender

    }
}