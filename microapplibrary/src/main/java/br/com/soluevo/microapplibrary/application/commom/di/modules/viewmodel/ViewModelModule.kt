package br.com.soluevo.microapplibrary.application.commom.di.modules.viewmodel

import androidx.lifecycle.ViewModelProvider
import br.com.soluevo.microapplibrary.application.commom.di.utils.ViewModelFactory

import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

   /* @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    internal abstract fun eventsViewModel(eventsViewModel: EventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    internal abstract fun eventViewModel(eventViewModel: EventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    internal abstract fun spreadViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventFormViewModel::class)
    internal abstract fun eventFormViewModel(eventFormViewModel: EventFormViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    internal abstract fun favoriteViewModel(eventFormViewModel: FavoriteViewModel): ViewModel*/

}