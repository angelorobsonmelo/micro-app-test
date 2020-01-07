package br.com.soluevo.microapplibrary.application.commom.di.components.fragments

import br.com.soluevo.microapplibrary.application.commom.di.modules.generic.GenericModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.RecyclerViewAnimatedWithDividerModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GenericModule::class, RecyclerViewAnimatedWithDividerModule::class])
interface FragmentGenericWithRecyclerViewComponent {

   /* fun inject(eventsFragment: EventsFragment)
    fun inject(favoriteFragment: FavoriteFragment)*/

}