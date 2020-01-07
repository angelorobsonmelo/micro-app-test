package br.com.soluevo.microapplibrary.application.fragments.products.products


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.EventObserver
import br.com.soluevo.microapplibrary.application.commom.di.components.fragments.DaggerFragmentGenericWithRecyclerViewComponent
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.RecyclerViewAnimatedWithDividerModule
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.application.fragments.products.products.adapter.ProductsAdapter
import br.com.soluevo.microapplibrary.databinding.ProductsFragmentBinding
import br.com.soluevo.microapplibrary.domain.Product
import javax.inject.Inject


class ProductsFragment : BindingFragment<ProductsFragmentBinding>() {

    override fun getLayoutResId(): Int = R.layout.products_fragment

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private val mViewModel: ProductsViewModel by lazy {
        ViewModelProviders.of(this, mFactory)[ProductsViewModel::class.java]
    }

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mRecyclerView: RecyclerView

    private var mProducts = listOf<Product>()
    private val mAdapter = ProductsAdapter(mProducts)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpElements()
    }

    private fun setUpElements() {
        setUpDagger()
        setUpDataBinding()
        mViewModel.getProducts()
        initObservables()
    }

    private fun setUpDagger() {
        DaggerFragmentGenericWithRecyclerViewComponent.builder()
            .contextModule(ContextModule(context!!))
            .recyclerViewAnimatedWithDividerModule(
                RecyclerViewAnimatedWithDividerModule(
                    binding.recyclerView,
                    mAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
                )
            )
            .build()
            .inject(this)
    }

    private fun setUpDataBinding() {
        binding.lifecycleOwner = this
    }

    private fun initObservables() {
        mViewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            print(it)
            mAdapter.updateData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.disposables.clear()
    }


}
