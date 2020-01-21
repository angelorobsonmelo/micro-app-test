package br.com.soluevo.microapplibrary.application.commom.di.components.fragments

import br.com.soluevo.microapplibrary.application.commom.di.modules.generic.GenericModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.RecyclerViewAnimatedWithDividerModule
import br.com.soluevo.microapplibrary.application.fragments.home.HomeFragment
import br.com.soluevo.microapplibrary.application.fragments.products.products.ProductFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GenericModule::class, RecyclerViewAnimatedWithDividerModule::class])
interface FragmentGenericWithRecyclerViewComponent {

    fun inject(fragment: HomeFragment)
    fun inject(productFragment: ProductFragment)

}