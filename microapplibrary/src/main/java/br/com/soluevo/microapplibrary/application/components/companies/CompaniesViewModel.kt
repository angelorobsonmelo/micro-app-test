package br.com.soluevo.microapplibrary.application.components.companies

import br.com.soluevo.microapplibrary.application.commom.EventLiveData
import br.com.soluevo.microapplibrary.application.commom.utils.BaseViewModel
import br.com.soluevo.microapplibrary.domain.Company
import br.com.soluevo.microapplibrary.service.remote.companies.CompaniesApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CompaniesViewModel @Inject constructor(
    private val apiDataSource: CompaniesApiDataSource
) : BaseViewModel<List<Company>>() {

    val disposables = CompositeDisposable()

    fun getCompanies() {
        val disposable = apiDataSource.getCompanies()
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