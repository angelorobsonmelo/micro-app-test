package br.com.soluevo.microapplibrary.application.commom.di.components.fragments

import br.com.soluevo.microapplibrary.application.commom.di.modules.generic.GenericModule

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GenericModule::class])
interface ActivityComponentGeneric {

//    fun inject(signInActivity: SignInActivity)
}