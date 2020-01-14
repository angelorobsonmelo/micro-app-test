package br.com.soluevo.microapplibrary.application.commom.di.modules.api


import br.com.soluevo.microapplibrary.service.remote.companies.CompaniesApiDataSource
import br.com.soluevo.microapplibrary.service.remote.products.ProductApiDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun productApiDataSource(retrofit: Retrofit): ProductApiDataSource {
        return retrofit.create(ProductApiDataSource::class.java)
    }

    @Provides
    @Singleton
    fun companiesApiDataSource(retrofit: Retrofit): CompaniesApiDataSource {
        return retrofit.create(CompaniesApiDataSource::class.java)
    }

}