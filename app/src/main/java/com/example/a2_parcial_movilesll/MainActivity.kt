package com.example.a2_parcial_movilesll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView_Characters: RecyclerView
    private lateinit var adapterCharacters: AdapterCharacters

    private var listCharacter = mutableListOf<Character>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_Characters = findViewById(R.id.recyclerView_Characters)

        adapterCharacters = AdapterCharacters(listCharacter)
        recyclerView_Characters.layoutManager = LinearLayoutManager(this)
        recyclerView_Characters.adapter = adapterCharacters

        adapterCharacters.onItemClickListener = { character ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("character", character)
            startActivity(intent)
        }

        getCharacters()

        recyclerView_Characters.layoutManager = LinearLayoutManager(this)
        recyclerView_Characters.adapter = adapterCharacters
        recyclerView_Characters.setHasFixedSize(true)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView_Characters.context,
            LinearLayoutManager.VERTICAL
        )
        recyclerView_Characters.addItemDecoration(dividerItemDecoration)



    }

    private fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getCharacters()
            val characters = call.body()
            runOnUiThread {
                if (call.isSuccessful && characters != null) {
                    listCharacter.clear()
                    listCharacter.addAll(characters.results)
                    adapterCharacters.notifyDataSetChanged()

                } else {
                    // Manejar error
                }
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}