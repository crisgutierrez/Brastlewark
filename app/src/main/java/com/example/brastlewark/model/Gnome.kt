package com.example.brastlewark.model

data class Gnome(
    val id: Int,
    val name: String,
    val avatar: String,
    val age: Int,
    val weight: Double,
    val height: Double,
    val hairColor: String?,
    val professions: List<String>,
    val friends: List<String>
)