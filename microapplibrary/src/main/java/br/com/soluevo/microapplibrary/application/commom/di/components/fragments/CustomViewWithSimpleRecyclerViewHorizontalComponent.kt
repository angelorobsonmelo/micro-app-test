package br.com.soluevo.microapplibrary.application.commom.di.components.fragments

import br.com.soluevo.microapplibrary.application.commom.di.modules.generic.GenericModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.SimpleRecyclerViewModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.SympleRecyclerViewHorizontal
import br.com.soluevo.microapplibrary.application.components.companies.CompaniesComponent
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GenericModule::class, SympleRecyclerViewHorizontal::class])
interface CustomViewWithSimpleRecyclerViewHorizontalComponent {

    fun inject(component: CompaniesComponent)


}