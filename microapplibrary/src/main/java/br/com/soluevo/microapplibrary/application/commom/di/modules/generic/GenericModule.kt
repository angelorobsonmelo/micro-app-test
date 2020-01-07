package br.com.soluevo.microapplibrary.application.commom.di.modules.generic

import br.com.soluevo.microapplibrary.application.commom.di.modules.api.ApiModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.viewmodel.ViewModelModule

import dagger.Module

@Module(includes = [NetWorkModule::class, ViewModelModule::class, ApiModule::class, ContextModule::class])
class GenericModule