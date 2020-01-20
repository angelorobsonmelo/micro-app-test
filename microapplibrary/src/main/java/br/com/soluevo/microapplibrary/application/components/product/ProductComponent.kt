package br.com.soluevo.microapplibrary.application.components.product

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.components.product.adapter.ProductComponentAdapter
import br.com.soluevo.microapplibrary.domain.Product
import kotlinx.android.synthetic.main.product_component.view.*

class ProductComponent(context: Context, var attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val mAdapter = ProductComponentAdapter(mutableListOf())

    init {
        init()
    }

    private fun init() {
        val view = LayoutInflater.from(context).inflate(R.layout.product_component, null)
        addView(view)

        setUpRecyclerView(view)
    }

    private fun setUpRecyclerView(view: View) {
        val recyclerView = view.productComponentRecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapter
        }
    }

    fun setProducts(products: List<Product>, clickListener: ProductComponentClickListener) {
        mAdapter.updateItems(products, clickListener)
    }

}