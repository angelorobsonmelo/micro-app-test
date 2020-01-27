package br.com.soluevo.microapplibrary.application.fragments.products.productdetail


import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
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

        binding.product = mProduct

        setUpElemens()
    }

    private fun setUpElemens() {
        setupSliderAdapter()
        setUpButtonsSizeClickListener()
    }

    private fun setUpButtonsSizeClickListener() {
        seventeenButton.setOnClickListener {
            binding.sizeTextView.text = "TAMANHO: 17"
            setStateWhenClickInSeventeenButton()
        }

        nineteenButton.setOnClickListener {
            binding.sizeTextView.text = "TAMANHO: 19"
            setStateWhenClickInNineteenButton()
        }

        twentyOneButton.setOnClickListener {
            binding.sizeTextView.text = "TAMANHO: 21"
            setStateWhenClickInTwentyOneButton()
        }
    }

    private fun setStateWhenClickInSeventeenButton() {
        seventeenButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )

        seventeenButton.setBackgroundResource(R.drawable.border_button_size_selected)

        nineteenButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.light_gray
            )
        )

        nineteenButton.setBackgroundResource(R.drawable.border_button_size_unselected)

        twentyOneButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.light_gray
            )
        )

        twentyOneButton.setBackgroundResource(R.drawable.border_button_size_unselected)
    }

    private fun setStateWhenClickInNineteenButton() {
        nineteenButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )

        nineteenButton.setBackgroundResource(R.drawable.border_button_size_selected)

        seventeenButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.light_gray
            )
        )

        seventeenButton.setBackgroundResource(R.drawable.border_button_size_unselected)

        twentyOneButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.light_gray
            )
        )

        twentyOneButton.setBackgroundResource(R.drawable.border_button_size_unselected)
    }

    private fun setStateWhenClickInTwentyOneButton() {
        twentyOneButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )

        twentyOneButton.setBackgroundResource(R.drawable.border_button_size_selected)

        seventeenButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.light_gray
            )
        )

        seventeenButton.setBackgroundResource(R.drawable.border_button_size_unselected)

        nineteenButton.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.light_gray
            )
        )

        nineteenButton.setBackgroundResource(R.drawable.border_button_size_unselected)
    }

    private fun setupSliderAdapter() {
        val picturesMock = ArrayList<Picture>()
        for (i in 0..4) {
            picturesMock.add(Picture(i, "ff", mProduct?.pictures?.first()?.url!!))
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
