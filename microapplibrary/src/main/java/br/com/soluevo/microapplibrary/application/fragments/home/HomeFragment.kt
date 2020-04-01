package br.com.soluevo.microapplibrary.application.fragments.home


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.angelorobson.horizontalrecyclerviewimageslibrary.ImageClickListener
import br.com.angelorobson.horizontalrecyclerviewimageslibrary.model.ItemImage
import br.com.soluevo.microapplibrary.NavigationHostMicroAppsActivity
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.EventObserver
import br.com.soluevo.microapplibrary.application.commom.di.components.fragments.DaggerFragmentComponentGeneric
import br.com.soluevo.microapplibrary.application.commom.di.modules.application.ContextModule
import br.com.soluevo.microapplibrary.application.commom.di.modules.network.NetWorkModule
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.application.components.product.ProductComponentClickListener
import br.com.soluevo.microapplibrary.application.fragments.products.products.ProductsViewModel
import br.com.soluevo.microapplibrary.databinding.HomeFragmentBinding
import br.com.soluevo.microapplibrary.domain.Product
import com.example.moeidbannerlibrary.banner.BaseBannerAdapter
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject


class HomeFragment : BindingFragment<HomeFragmentBinding>() {

    override fun getLayoutResId(): Int = R.layout.home_fragment

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    private val mViewModel: ProductsViewModel by lazy {
        ViewModelProviders.of(this, mFactory)[ProductsViewModel::class.java]
    }

    private var mProducts = listOf<Product>()
    private var mMicroAppsActivity: NavigationHostMicroAppsActivity? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setUpElements()
    }

    private fun setUpElements() {
        mMicroAppsActivity = activity as NavigationHostMicroAppsActivity
        setUpDagger()
        setUpDataBinding()
        mViewModel.getProducts()
        initObservables()
        setUpBanner()

        mMicroAppsActivity?.getCompany()?.imageUrl?.apply {
            showToolbarWithDisplayArrowBackAndLogo(this)
        }
    }

    private fun setUpDagger() {
        val url = mMicroAppsActivity?.getUrl()

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
        urls.add("https://i.imgur.com/q2uP1vi.png")
        urls.add("https://i.imgur.com/q2uP1vi.png")
        urls.add("https://i.imgur.com/q2uP1vi.png")
        urls.add("https://i.imgur.com/q2uP1vi.png")
        urls.add("https://i.imgur.com/q2uP1vi.png")
        urls.add("https://i.imgur.com/q2uP1vi.png")

        val webBannerAdapter = BaseBannerAdapter(requireContext(), urls)
        webBannerAdapter.setOnBannerItemClickListener {

        }
        banner.setAdapter(webBannerAdapter)
    }

    private fun initObservables() {
        mViewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            setUpCompanyFilters()
            setUpMostWantedProduct(it)
            setUpTopSallingProduct(it)
        })
    }

    private fun setUpCompanyFilters() {
        val company = mMicroAppsActivity?.getCompany()

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
                goToProductDetail(product)
            }

            override fun onLongClick(product: Product, position: Int) {

            }

        })
    }

    private fun setUpTopSallingProduct(products: List<Product>) {
        val productComponent = topSellingProducts

        topSellingProducts.setProducts(products, object : ProductComponentClickListener {
            override fun onclick(product: Product, position: Int) {
                goToProductDetail(product)
            }

            override fun onLongClick(product: Product, position: Int) {

            }

        })
    }

    private fun goToProductDetail(product: Product) {
        val bundle = bundleOf("PRODUCT" to product)
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.disposables.clear()
    }


}
