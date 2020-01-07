package br.com.soluevo.microapplibrary.application.utils.listeners

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import br.com.soluevo.microapplibrary.application.utils.ActivityBase

abstract class BindingActivity<T : ViewDataBinding> : ActivityBase() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<T>(this, getLayoutResId()).apply {
            binding = this
        }
    }

}