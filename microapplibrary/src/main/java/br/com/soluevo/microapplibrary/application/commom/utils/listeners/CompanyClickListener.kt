package br.com.soluevo.microapplibrary.application.commom.utils.listeners

import br.com.soluevo.microapplibrary.domain.Company

interface CompanyClickListener {

    fun onClick(company: Company)
    fun onLongClick(company: Company)
}