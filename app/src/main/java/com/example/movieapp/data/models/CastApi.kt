package com.example.movieapp.data.models

import com.example.movieapp.domain.models.Cast
import com.google.gson.annotations.SerializedName

data class CastApi(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("cast_id")
    val castId: Int? = null,
    @SerializedName("character")
    val character: String? = null,
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("order")
    val order: Int? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = "https://pixy.org/src/9/94083.png"
)

fun CastApi.toCast(): Cast {
    return Cast(
        adult = adult ?: false,
        castId = castId ?: 0,
        character = character ?: "",
        creditId = creditId ?: "",
        gender = gender ?: 0,
        id = id ?: 0,
        knownForDepartment = knownForDepartment ?: "",
        name = name ?: "",
        order = order ?: 0,
        originalName = originalName ?: "",
        popularity = popularity ?: 0.0,
        profilePath = profilePath ?: "https://pixy.org/src/9/94083.png"
    )
}
