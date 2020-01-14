package br.com.soluevo.microapplibrary.service.remote.products

import br.com.soluevo.microapplibrary.domain.Product
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductApiDataSource {

    @GET("/products")
    fun getProducts(): Observable<List<Product>>
}