package br.com.soluevo.microapplibrary.application.fragments.products.products


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.angelorobson.horizontalrecyclerviewimageslibrary.ImageClickListener
import br.com.angelorobson.horizontalrecyclerviewimageslibrary.model.ItemImage
import br.com.soluevo.microapplibrary.NavigationHostActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.EventObserver
import br.com.soluevo.microapplibrary.application.commom.di.components.fragments.DaggerFragmentGenericWithRecyclerViewComponent
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.recyclerview.RecyclerViewAnimatedWithDividerModule
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.application.commom.utils.listeners.OnBackPressedListener
import br.com.soluevo.microapplibrary.application.fragments.products.products.adapter.ProductsAdapter
import br.com.soluevo.microapplibrary.databinding.ProductsFragmentBinding
import br.com.soluevo.microapplibrary.domain.Product
import kotlinx.android.synthetic.main.products_fragment.*
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
    private var mActivity: NavigationHostActivity? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpElements()
    }

    private fun setUpElements() {
        mActivity = activity as NavigationHostActivity
        setUpDagger()
        setUpDataBinding()
        mViewModel.getProducts()
        initObservables()
        showToolbarWithDisplayArrowBack("Voltar")
    }

    private fun setUpDagger() {
        val url = mActivity?.getUrl()

        mActivity?.onBackPressedListener(object : OnBackPressedListener {
            override fun onBackPressedClicked() {
                mActivity?.finishActivity()
            }

        })

        DaggerFragmentGenericWithRecyclerViewComponent.builder()
            .contextModule(ContextModule(requireContext()))
            .netWorkModule(NetWorkModule(url!!))
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
            mAdapter.updateData(it)
            setUpCompanyFilters()
        })
    }

    private fun setUpCompanyFilters() {
        val company = mActivity?.getCompany()

        val filters = company?.companyFilters?.map { filter ->
            ItemImage(filter.id, filter.name, filter.imageUrl)
        }

        filters?.apply {
            horizontalRecyclerViewImages.setImages(this, object : ImageClickListener {
                override fun onclick(image: ItemImage, position: Int) {

                }

                override fun onLongClick(image: ItemImage, position: Int) {

                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.disposables.clear()
    }


}
