package br.com.soluevo.microapplibrary.domain

data class Product(
    val id: Int,
    val price: Double,
    val title: String,
    val description: String,
    val pictures: List<Picture>
)

data class Picture(
    val id: Int,
    val title: String,
    val url: String
)