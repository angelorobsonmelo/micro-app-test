package br.com.soluevo.microapplibrary.application.fragments.products.productdetail


import android.os.Bundle
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.databinding.ProductDetailFragmentBinding


class ProductDetailFragment : BindingFragment<ProductDetailFragmentBinding>() {

    override fun getLayoutResId(): Int = R.layout.product_detail_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        
    }


}
