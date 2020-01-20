package br.com.soluevo.microapplibrary.application.components.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.soluevo.microapplibrary.R
import br.com.soluevo.microapplibrary.application.components.product.ProductComponentClickListener
import br.com.soluevo.microapplibrary.application.components.product.viewholder.ProductComponentViewHolder
import br.com.soluevo.microapplibrary.domain.Product
import kotlinx.android.synthetic.main.product_component_item.view.*

class ProductComponentAdapter(
    private var itemProducts: List<Product>
) : RecyclerView.Adapter<ProductComponentViewHolder>() {

    private var listener: ProductComponentClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductComponentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_component_item, parent, false)
        return ProductComponentViewHolder(view)
    }

    override fun getItemCount(): Int = itemProducts.size

    override fun onBindViewHolder(holder: ProductComponentViewHolder, position: Int) {
        val product = itemProducts[position]
        holder.bindItem(product)

        holder.itemView.linearLayout.setOnClickListener {
            listener?.onclick(product, position)
        }

        holder.itemView.linearLayout.setOnLongClickListener {
            listener?.onLongClick(product, position)
            true
        }
    }

    fun updateItems(
        products: List<Product>,
        clickListener: ProductComponentClickListener
    ) {
        this.itemProducts = products
        this.listener = clickListener
        notifyDataSetChanged()
    }
}