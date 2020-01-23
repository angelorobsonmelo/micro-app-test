package br.com.soluevo.microapplibrary.application.fragments.products.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.components.product.ProductComponentClickListener
import br.com.soluevo.microapplibrary.databinding.ProductGridItemBinding
import br.com.soluevo.microapplibrary.domain.Product

class GridAdapter(
    private var mProducts: List<Product>,
    context: Context,
    private val lister: ProductComponentClickListener
) :
    BaseAdapter() {

    private val mLayoutInflater = LayoutInflater.from(context)

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = DataBindingUtil.inflate<ProductGridItemBinding>(
            mLayoutInflater,
            R.layout.product_grid_item,
            p2,
            false
        )

        val product = mProducts[p0]

        binding.product = product
        binding.constraintLayout.setOnClickListener {
            lister.onclick(product, p0)
        }

        return binding.root
    }

    override fun getItem(p0: Int): Any {

        return mProducts[p0]
    }

    override fun getItemId(p0: Int): Long {
        return mProducts[p0].id.toLong()
    }

    override fun getCount(): Int = mProducts.size
}