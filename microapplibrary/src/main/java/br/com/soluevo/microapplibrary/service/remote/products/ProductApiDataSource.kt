package br.com.soluevo.microapplibrary.service.remote.products

import br.com.soluevo.microapplibrary.domain.Product
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ProductApiDataSource {

    @GET("/products")
    fun getProducts(): Observable<List<Product>>

    @GET("/product/{id}")
    fun getProduct(): Single<Product>
}