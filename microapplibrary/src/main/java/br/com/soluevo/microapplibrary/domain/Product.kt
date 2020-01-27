package br.com.soluevo.microapplibrary.domain

import java.io.Serializable

data class Product(
    val id: Int,
    val price: Double,
    val title: String,
    val description: String,
    val pictures: List<Picture>,
    val imagePreviewUrl: String
) : Serializable

data class Picture(
    val id: Int,
    val title: String,
    val url: String
) : Serializable