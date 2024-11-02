package com.example.movieapp.data.models

import com.example.movieapp.domain.models.CastResponse
import com.google.gson.annotations.SerializedName

data class CastResponseApi(
    @SerializedName("cast")
    val cast: List<CastApi>,
    @SerializedName("id")
    val id: Int
)

fun CastResponseApi.toCastResponse(): CastResponse {
    return CastResponse(
        cast = cast.map { it.toCast() },
        id = id
    )
}


