package br.com.soluevo.microapplibrary.domain

import java.io.Serializable

data class CompanyFilter(
    val id: Int,
    val name: String,
    val imageUrl: String
): Serializable