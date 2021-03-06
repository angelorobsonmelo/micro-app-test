package br.com.soluevo.microapplibrary.application.commom.di.components.fragments

import br.com.soluevo.microapplibrary.application.commom.di.modules.generic.GenericModule
import br.com.soluevo.microapplibrary.application.fragments.home.HomeFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GenericModule::class])
interface FragmentComponentGeneric {

    fun inject(homeFragment: HomeFragment)
}