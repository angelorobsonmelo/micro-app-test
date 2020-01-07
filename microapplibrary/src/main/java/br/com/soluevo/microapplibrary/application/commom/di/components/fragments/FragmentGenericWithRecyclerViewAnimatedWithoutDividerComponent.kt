package br.com.soluevo.microapplibrary.application.commom.di.components.fragments

import br.com.soluevo.microapplibrary.application.commom.di.modules.generic.GenericModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.RecyclerViewAnimatedModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GenericModule::class, RecyclerViewAnimatedModule::class])
interface FragmentGenericWithRecyclerViewAnimatedWithoutDividerComponent {

//    fun inject(favoriteFragment: FavoriteFragment)

}