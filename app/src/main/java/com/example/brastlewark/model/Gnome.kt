package com.example.brastlewark.model

import java.io.Serializable

data class Gnome(
    val id: Int,
    val name: String,
    val avatar: String,
    val age: Int,
    val weight: Double,
    val height: Double,
    val hairColor: String,
    var professions: List<String>,
    var friends: List<String>
): Serializable
