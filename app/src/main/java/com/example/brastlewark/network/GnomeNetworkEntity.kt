package com.example.brastlewark.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GnomeNetworkEntity(
    @SerializedName(value = "id")
    @Expose
    val id: Int,

    @SerializedName(value = "name")
    @Expose
    val name: String,

    @SerializedName(value = "thumbnail")
    @Expose
    val avatar: String,

    @SerializedName(value = "age")
    @Expose
    val age: Int,

    @SerializedName(value = "weight")
    @Expose
    val weight: Double,

    @SerializedName(value = "height")
    @Expose
    val height: Double,

    @SerializedName(value = "hairColor")
    @Expose
    val hairColor: String? = null,

    @SerializedName(value = "professions")
    @Expose
    val professions: List<String>,

    @SerializedName(value = "friends")
    @Expose
    val friends: List<String>
)

data class CityEntity(
    @SerializedName(value = "Brastlewark")
    @Expose
    val city: List<GnomeNetworkEntity>

)
