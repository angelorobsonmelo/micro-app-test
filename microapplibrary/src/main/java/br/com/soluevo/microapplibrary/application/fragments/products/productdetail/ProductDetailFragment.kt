package br.com.soluevo.microapplibrary.application.fragments.products.productdetail


import android.graphics.Color
import android.os.Bundle
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.utils.BindingFragment
import br.com.soluevo.microapplibrary.application.fragments.products.productdetail.adapter.SliderAdapterExample
import br.com.soluevo.microapplibrary.databinding.ProductDetailFragmentBinding
import br.com.soluevo.microapplibrary.domain.Picture
import br.com.soluevo.microapplibrary.domain.Product
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.product_detail_fragment.*


class ProductDetailFragment : BindingFragment<ProductDetailFragmentBinding>() {

    override fun getLayoutResId(): Int = R.layout.product_detail_fragment

    var mProduct: Product? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.apply {
            mProduct = getSerializable("PRODUCT") as Product
        }

        setUpElemens()
    }

    private fun setUpElemens() {
        setupSliderAdapter()
    }

    private fun setupSliderAdapter() {
        val picturesMock = ArrayList<Picture>()
        for (i in 0..4) {
            picturesMock.add(Picture(i, "ff", "fff"))
        }

        mProduct?.pictures?.apply {
            val sliderView = imageSlider
            val mSliderAdapter = SliderAdapterExample(picturesMock)

            sliderView.sliderAdapter = mSliderAdapter
            sliderView.setIndicatorAnimation(IndicatorAnimations.NONE)
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            sliderView.indicatorSelectedColor = Color.GRAY
            sliderView.indicatorUnselectedColor = Color.RED
            sliderView.setOnIndicatorClickListener { position ->
                sliderView.currentPagePosition = position
            }
        }


    }


}
