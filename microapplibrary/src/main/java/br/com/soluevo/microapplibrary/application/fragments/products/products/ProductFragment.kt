package br.com.soluevo.microapplibrary.application.fragments.products.products


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.soluevo.microapplibrary.NavigationHostMicroAppsActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.activities.filter.FilterActivity
import br.com.soluevo.microapplibrary.application.commom.EventObserver
import br.com.soluevo.microapplibrary.application.commom.di.components.fragments.DaggerFragmentGenericWithRecyclerViewComponent
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.RecyclerViewAnimatedWithDividerModule
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.application.commom.utils.Constants
import br.com.soluevo.microapplibrary.application.components.product.ProductComponentClickListener
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
    private val mAdapter = ProductsAdapter(mProducts, object : ProductComponentClickListener {
        override fun onclick(product: Product, position: Int) {
            goToPrductDetail(product)
        }

        override fun onLongClick(product: Product, position: Int) {

        }

    })

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

        listProductButton.setImageResource(R.drawable.ic_list_unselected)
        listProductGridButton.setImageResource(R.drawable.ic_grid_selected)
    }

    private fun setUpListButtonClicked() {
        productsListRecyclerView.visibility = View.VISIBLE
        productGridView.visibility = View.GONE

        listProductButton.setImageResource(R.drawable.ic_list_selected)
        listProductGridButton.setImageResource(R.drawable.ic_grid_unselected)
    }

    private fun buttonListRedIcon() {
        listProductButton.setImageResource(R.drawable.ic_list_selected)
    }

    private fun setUpDagger() {
        val ac = activity as NavigationHostMicroAppsActivity
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
            setUpGridAdapter(it)
        })
    }

    private fun setUpGridAdapter(it: List<Product>) {
        productGridView.adapter =
            GridAdapter(it, requireContext(), object : ProductComponentClickListener {
                override fun onclick(product: Product, position: Int) {
                    goToPrductDetail(product)
                }

                override fun onLongClick(product: Product, position: Int) {

                }

            })
    }

    private fun goToPrductDetail(product: Product) {
        val bundle = bundleOf(
            "PRODUCT" to product
        )

        findNavController().navigate(
            R.id.action_productFragment_to_productDetailFragment,
            bundle
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter_micro_app, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                val ac = activity as NavigationHostMicroAppsActivity

                val bundle = Bundle()
                val intent = Intent(context, FilterActivity::class.java).apply {

                    bundle.putSerializable(
                        Constants.CompanyThemeConstant.EXTRA_COMPANY,
                        ac.getCompany()
                    )
                    putExtras(bundle)
                }

                startActivityForResult(intent, 550)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.disposables.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        print(resultCode)
    }

}
