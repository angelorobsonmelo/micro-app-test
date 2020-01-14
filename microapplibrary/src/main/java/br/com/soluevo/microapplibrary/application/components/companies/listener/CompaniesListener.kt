package br.com.soluevo.microapplibrary.application.components.companies.listener

import br.com.soluevo.microapplibrary.domain.Company

interface CompaniesListener {

    fun companies(companies: List<Company>)
}