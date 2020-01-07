package br.com.soluevo.microapplibrary.application.commom.di.components.fragments

import br.com.soluevo.microapplibrary.application.commom.di.modules.generic.GenericModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.SimpleRecyclerViewModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GenericModule::class, SimpleRecyclerViewModule::class])
interface FragmentWithSimpleRecyclerViewComponentGeneric {

//    fun inject(eventActivity: EventActivity)
//    fun inject(eventFormFragment: EventFormFragment)


}