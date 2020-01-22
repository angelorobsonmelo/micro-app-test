package br.com.soluevo.microapplibrary.application.fragments.products.products


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.soluevo.microapplibrary.NavigationHostActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.EventObserver
import br.com.soluevo.microapplibrary.application.commom.di.components.fragments.DaggerFragmentGenericWithRecyclerViewComponent
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.RecyclerViewAnimatedWithDividerModule
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.application.fragments.products.products.adapter.GridAdapter
import br.com.soluevo.microapplibrary.application.fragments.products.products.adapter.ProductsAdapter
import br.com.soluevo.microapplibrary.databinding.ProductFragmentBinding
import br.com.soluevo.microapplibrary.domain.Product
import kotlinx.android.synthetic.main.product_fragment.*
import javax.inject.Inject


class ProductFragment : BindingFragment<ProductFragmentBinding>() {


    override fun getLayoutResId(): Int = R.layout.product_fragment

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private val mViewModel: ProductsViewModel by lazy {
        ViewModelProviders.of(this, mFactory)[ProductsViewModel::class.java]
    }

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mRecyclerView: RecyclerView

    private var mProducts = listOf<Product>()
    private val mAdapter = ProductsAdapter(mProducts)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setUpElements()
    }

    private fun setUpElements() {
        setUpDagger()
        setUpDataBinding()
        initObservables()
        mViewModel.getProducts()

        buttonListRedIcon()
        initButtonsListClickListener()
    }

    private fun initButtonsListClickListener() {
        listProductButton.setOnClickListener {
            setUpListButtonClicked()
        }

        listProductGridButton.setOnClickListener {
            setUpListGridButtonClicked()
        }

    }

    private fun setUpListGridButtonClicked() {
        productsListRecyclerView.visibility = View.GONE
        productGridView.visibility = View.VISIBLE

        listProductButton.setImageResource(R.drawable.ic_products)
        listProductGridButton.setImageResource(R.drawable.ic_grid_large)
    }

    private fun setUpListButtonClicked() {
        productsListRecyclerView.visibility = View.VISIBLE
        productGridView.visibility = View.GONE

        listProductButton.setImageResource(R.drawable.ic_format_list_red)
        listProductGridButton.setImageResource(R.drawable.ic_grid_four_columns)
    }

    private fun buttonListRedIcon() {
        listProductButton.setImageResource(R.drawable.ic_format_list_red)
    }

    private fun buttonGridRedIcon() {
        listProductButton.setImageResource(R.drawable.ic_grid_large)
    }

    private fun setUpDagger() {
        val ac = activity as NavigationHostActivity
        val url = ac.getUrl()

        DaggerFragmentGenericWithRecyclerViewComponent.builder()
            .contextModule(ContextModule(requireContext()))
            .netWorkModule(NetWorkModule(url))
            .recyclerViewAnimatedWithDividerModule(
                RecyclerViewAnimatedWithDividerModule(
                    binding.productsListRecyclerView,
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
            mAdapter.updateData(it)
            productGridView.adapter = GridAdapter(it, requireContext())
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.disposables.clear()
    }

}
