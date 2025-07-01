package com.example.a2_parcial_movilesll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
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
    private lateinit var textView_title: TextView


    private lateinit var spinner: Spinner

    private var listCharacter = mutableListOf<Character>()
    private var statusList = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_Characters = findViewById(R.id.recyclerView_Characters)

        adapterCharacters = AdapterCharacters(listCharacter)
        recyclerView_Characters.layoutManager = LinearLayoutManager(this)
        recyclerView_Characters.adapter = adapterCharacters

        textView_title = findViewById(R.id.textView_title)

        adapterCharacters.onItemClickListener = { character ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("character", character)
            startActivity(intent)
        }

        spinner = findViewById(R.id.spinner)

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
                    getStatusList()

                } else {
                    // Manejar error
                }
            }
        }
    }

    private fun getStatusList() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getCharacters()
            val characters = call.body()
            runOnUiThread {
                if (call.isSuccessful && characters != null) {
                    statusList.clear()
                    statusList.addAll(characters.results.map { it.status }.distinct())
                    setSpinner()
                }
            }
        }
    }
    private fun setSpinner() {
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statusList)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedStatus = statusList[position]
                val filtered = listCharacter.filter { it.status == selectedStatus }
                adapterCharacters = AdapterCharacters(filtered)
                recyclerView_Characters.adapter = adapterCharacters

                adapterCharacters.onItemClickListener = { character ->
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("character", character)
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
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