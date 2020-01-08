package br.com.soluevo.microapplibrary.application.fragments.products.products

import br.com.soluevo.microapplibrary.application.commom.EventLiveData
import br.com.soluevo.microapplibrary.application.commom.utils.BaseViewModel
import br.com.soluevo.microapplibrary.domain.Product
import br.com.soluevo.microapplibrary.service.remote.ProductApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val apiDataSource: ProductApiDataSource
) : BaseViewModel<List<Product>>() {

    val disposables = CompositeDisposable()

    fun getProducts() {
        val disposable = apiDataSource.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingStarted() }
            .doAfterTerminate { loadingFinished() }
            .subscribe(
                {
                    if (it.size > 0) {
                        successObserver.value = EventLiveData(it)
                        return@subscribe
                    }

                    emptyObserver.value = EventLiveData(true)
                },
                {
                    errorObserver.value = EventLiveData(it.localizedMessage)
                }
            )

        disposables.add(disposable)
    }

}