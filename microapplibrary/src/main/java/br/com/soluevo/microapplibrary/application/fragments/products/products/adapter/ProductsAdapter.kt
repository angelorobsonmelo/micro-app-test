package br.com.soluevo.microapplibrary.application.fragments.products.products.adapter

import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.commom.utils.BindingAdapter
import br.com.soluevo.microapplibrary.application.components.product.ProductComponentClickListener
import br.com.soluevo.microapplibrary.databinding.ProductItemBinding
import br.com.soluevo.microapplibrary.domain.Product

class ProductsAdapter(
    private var mProducts: List<Product>,
    private val listener: ProductComponentClickListener
) : BindingAdapter<ProductItemBinding>() {


    override fun getLayoutResId(): Int = R.layout.product_item

    override fun onBindViewHolder(binding: ProductItemBinding, position: Int) {
        binding.run {
            val product = mProducts[position]
            this.product = product

            constraintLayout.setOnClickListener {
                listener.onclick(product, position)
            }

            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = mProducts.size

    fun updateData(it: List<Product>) {
        mProducts = it
        notifyDataSetChanged()
    }

}