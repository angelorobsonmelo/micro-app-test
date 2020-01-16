package br.com.soluevo.microapplibrary.domain

import java.io.Serializable

data class Company(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val theme: Theme,
    val companyFilters: List<CompanyFilter>
): Serializable