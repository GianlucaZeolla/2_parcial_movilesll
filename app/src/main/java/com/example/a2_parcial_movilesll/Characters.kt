package com.example.a2_parcial_movilesll

import java.io.Serializable


data class CharacterResponse(
    val info: Info,
    val results: List<Character>
) : Serializable

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) : Serializable

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) : Serializable

data class Origin(
    val name: String,
    val url: String
) : Serializable

data class Location(
    val name: String,
    val url: String
) : Serializable

