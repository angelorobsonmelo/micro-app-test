package br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SympleRecyclerViewHorizontal(
    private val recyclerView: RecyclerView,
    private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {

    @Provides
    @Singleton
    fun provideLayoutManager(@Named("ApplicationContext") context: Context) =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


    @Provides
    @Singleton
    fun provideRecyclerView(
        layoutManager: LinearLayoutManager
    ): RecyclerView {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        return recyclerView
    }

}