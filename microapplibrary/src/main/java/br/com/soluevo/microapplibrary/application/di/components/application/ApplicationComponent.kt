package br.com.soluevo.microapplibrary.application.di.components.application

import android.app.Application
import br.com.soluevo.microapplibrary.application.di.modules.application.ApplicationModule
import br.com.soluevo.microapplibrary.application.di.modules.application.ContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ContextModule::class])
interface ApplicationComponent {

    fun inject(application: Application)
//    fun getSessionUseCase(): SessionUseCase
//    fun getEventLocalDataSource(): EventLocalDataSource
//    fun getAuthApiDataSource(): AuthApiDataSource

}