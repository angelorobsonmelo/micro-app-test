package br.com.soluevo.microapplibrary.application.di.modules.generic

import br.com.soluevo.microapplibrary.application.di.modules.api.ApiModule
import br.com.soluevo.microapplibrary.application.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.di.modules.viewmodel.ViewModelModule

import dagger.Module

@Module(includes = [NetWorkModule::class, ViewModelModule::class, ApiModule::class, ContextModule::class])
class GenericModule