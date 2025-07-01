package com.example.a2_parcial_movilesll

import okhttp3.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): retrofit2.Response<CharacterResponse>

}

