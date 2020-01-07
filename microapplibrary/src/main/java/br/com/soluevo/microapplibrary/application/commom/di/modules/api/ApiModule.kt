package br.com.soluevo.microapplibrary.application.commom.di.modules.api


import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    /*@Provides
    @Singleton
    fun provideUserApiDataSource(retrofit: Retrofit): UserApiDataSource {
        return retrofit.create(UserApiDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsApiDataSource(retrofit: Retrofit): EventsApiDataSource {
        return retrofit.create(EventsApiDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoriteApiRemoteDataSource(retrofit: Retrofit): FavoriteApiRemoteDataSource {
        return retrofit.create(FavoriteApiRemoteDataSource::class.java)
    }*/

}