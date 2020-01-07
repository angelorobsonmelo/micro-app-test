package br.com.soluevo.microapplibrary.application.di.modules.application

import br.com.soluevo.microapplibrary.application.di.modules.network.NetWorkModule
import dagger.Module

@Module(includes = [ApplicationAbstractModule::class, NetWorkModule::class])
class ApplicationModule {

    /*@Provides
    @Singleton
    fun provideSessionLocalDataSourceImpl(@Named("ApplicationContext") context: Context): SessionLocalDataSourceImpl {
        return SessionLocalDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideSessionUseCase(sessionLocalDataSource: SessionLocalDataSource): SessionUseCase {
        return SessionUseCase(sessionLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideEventStateDataSource(@Named("ApplicationContext") context: Context): EventLocalDataSource {
        return EventLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideAuthApiDataSource(retrofit: Retrofit): AuthApiDataSource {
        return retrofit.create(AuthApiDataSource::class.java)
    }*/


}