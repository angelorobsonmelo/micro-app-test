package br.com.soluevo.microapplibrary.application.fragments.products.products


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.angelorobson.horizontalrecyclerviewimageslibrary.ImageClickListener
import br.com.angelorobson.horizontalrecyclerviewimageslibrary.model.ItemImage
import br.com.soluevo.microapplibrary.NavigationHostActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.EventObserver
import br.com.soluevo.microapplibrary.application.commom.di.components.fragments.DaggerFragmentComponentGeneric
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.application.commom.utils.listeners.OnBackPressedListener
import br.com.soluevo.microapplibrary.application.components.product.ProductComponentClickListener
import br.com.soluevo.microapplibrary.application.fragments.products.products.adapter.ProductsAdapter
import br.com.soluevo.microapplibrary.databinding.ProductsFragmentBinding
import br.com.soluevo.microapplibrary.domain.Product
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import kotlinx.android.synthetic.main.products_fragment.*
import javax.inject.Inject


class ProductsFragment : BindingFragment<ProductsFragmentBinding>() {

    override fun getLayoutResId(): Int = R.layout.products_fragment

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private val mViewModel: ProductsViewModel by lazy {
        ViewModelProviders.of(this, mFactory)[ProductsViewModel::class.java]
    }

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
        setUpBanner()
    }

    private fun setUpDagger() {
        val url = mActivity?.getUrl()

        mActivity?.onBackPressedListener(object : OnBackPressedListener {
            override fun onBackPressedClicked() {
                mActivity?.finishActivity()
            }

        })

        DaggerFragmentComponentGeneric.builder()
            .contextModule(ContextModule(requireContext()))
            .netWorkModule(NetWorkModule(url!!))
            .build()
            .inject(this)
    }

    private fun setUpDataBinding() {
        binding.lifecycleOwner = this
    }


    private fun setUpBanner() {
        val banner = Banner

        val urls: MutableList<String> = ArrayList()
        urls.add("http://img0.imgtn.bdimg.com/it/u=1352823040,1166166164&fm=27&gp=0.jpg")
        urls.add("http://img3.imgtn.bdimg.com/it/u=2293177440,3125900197&fm=27&gp=0.jpg")
        urls.add("http://img3.imgtn.bdimg.com/it/u=3967183915,4078698000&fm=27&gp=0.jpg")
        urls.add("http://img0.imgtn.bdimg.com/it/u=3184221534,2238244948&fm=27&gp=0.jpg")
        urls.add("http://img4.imgtn.bdimg.com/it/u=1794621527,1964098559&fm=27&gp=0.jpg")
        urls.add("http://img4.imgtn.bdimg.com/it/u=1243617734,335916716&fm=27&gp=0.jpg")

        val webBannerAdapter = BaseBannerAdapter(requireContext(), urls)
        webBannerAdapter.setOnBannerItemClickListener {

        }
        banner.setAdapter(webBannerAdapter)
    }

    private fun initObservables() {
        mViewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            mAdapter.updateData(it)
            setUpCompanyFilters()
            setUpMostWantedProduct(it)
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

    private fun setUpMostWantedProduct(products: List<Product>) {
        val productComponent = mostWantedProduct

        productComponent.setProducts(products, object : ProductComponentClickListener {
            override fun onclick(product: Product, position: Int) {

            }

            override fun onLongClick(product: Product, position: Int) {

            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.disposables.clear()
    }


}
