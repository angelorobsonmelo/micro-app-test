package br.com.soluevo.microapplibrary.application.commom.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.soluevo.microapplibrary.application.commom.di.utils.ViewModelFactory
import br.com.soluevo.microapplibrary.application.commom.di.utils.ViewModelKey
import br.com.soluevo.microapplibrary.application.fragments.products.products.ProductsViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    internal abstract fun productsViewModel(productViewModel: ProductsViewModel): ViewModel

}