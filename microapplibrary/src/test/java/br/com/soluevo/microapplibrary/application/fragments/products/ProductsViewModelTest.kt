package br.com.soluevo.microapplibrary.application.fragments.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.soluevo.microapplibrary.application.builders.ProductBuilder
import br.com.soluevo.microapplibrary.application.fragments.products.products.ProductsViewModel
import br.com.soluevo.microapplibrary.domain.Product
import br.com.soluevo.microapplibrary.service.remote.ProductApiDataSource
import br.com.soluevo.utils.SetupRemoteUtils
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.willReturn
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit


@RunWith(Enclosed::class)
class ProductsViewModelTest : SetupRemoteUtils() {

    internal abstract class Contex_setup {

        @Rule
        @JvmField
        val rule = InstantTaskExecutorRule()

        @Rule
        @JvmField
        val mockitoRule = MockitoJUnit.rule()!!

        @InjectMocks
        lateinit var eventsViewModel: ProductsViewModel

        @Mock
        lateinit var mDataSource: ProductApiDataSource

        @Mock
        lateinit var mProductsMock: List<Product>

        @Before
        open fun setUp() {
            val products = ProductBuilder()
                .oneProduct()
                .build()

            val observable = Observable.just(listOf(products))

            given(
                mDataSource.getProducts()
            ).willReturn { observable }

            eventsViewModel.getProducts()
        }

    }

    internal class ContextSuccess : Contex_setup() {


        @Test
        fun `It should call successObserver`() {
            eventsViewModel.successObserver
                .test()
                .assertHasValue()
        }

        @Test
        fun `It should call ss successObserver`() {
            eventsViewModel.errorObserver
                .test()
                .assertNoValue()
        }

        @Test
        fun `It should call sss successObserver`() {
            eventsViewModel.emptyObserver
                .test()
                .assertNoValue()
        }

    }

    internal class Context_Is_Loading : Contex_setup() {

        @Before
        override fun setUp() {
            super.setUp()
        }

        @Test
        fun `It should call isLoadingObserver`() {
            eventsViewModel.isLoadingObserver
                .test()
                .assertHasValue()
        }

    }

    internal class Context_onEmptyData : Contex_setup() {

        @Before
        override fun setUp() {
            val observable = Observable.just(mProductsMock)

            given(
                mDataSource.getProducts()
            ).willReturn { observable }

            eventsViewModel.getProducts()
        }

        @Test
        fun `It should call isEmptyObserver`() {
            eventsViewModel.emptyObserver
                .test()
                .assertHasValue()
        }

        @Test
        fun `It should call ss successObserver`() {
            eventsViewModel.errorObserver
                .test()
                .assertNoValue()
        }

        @Test
        fun `It should call sss successObserver`() {
            eventsViewModel.successObserver
                .test()
                .assertNoValue()
        }
    }


    internal class Context_onError : Contex_setup() {

        @Before
        override fun setUp() {
            val observable = Observable.error<Throwable> { Throwable("fff") }

            `when`<Any>(mDataSource.getProducts())
                .thenReturn(observable)

            eventsViewModel.getProducts()
        }

        @Test
        fun `It should call isEmptyObserver`() {
            eventsViewModel.emptyObserver
                .test()
                .assertNoValue()
        }

        @Test
        fun `It should call s isEmptyObserver`() {
            eventsViewModel.successObserver
                .test()
                .assertNoValue()
        }
    }

}