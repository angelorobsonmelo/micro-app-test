package br.com.soluevo.utils

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.BeforeClass

abstract class SetupRemoteUtils {


    companion object {

        @BeforeClass
        @JvmStatic
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass
        @JvmStatic
        @Throws(Exception::class)
        fun tearDownClass() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }


}