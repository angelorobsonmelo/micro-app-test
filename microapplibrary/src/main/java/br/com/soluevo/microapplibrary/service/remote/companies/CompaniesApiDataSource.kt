package br.com.soluevo.microapplibrary.service.remote.companies

import br.com.soluevo.microapplibrary.domain.Company
import io.reactivex.Observable
import retrofit2.http.GET

interface CompaniesApiDataSource {

    @GET("/companies")
    fun getCompanies(): Observable<List<Company>>
}