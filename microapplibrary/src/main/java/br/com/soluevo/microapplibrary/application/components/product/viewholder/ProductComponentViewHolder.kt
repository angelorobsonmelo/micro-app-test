package br.com.soluevo.microapplibrary.application.components.product.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.soluevo.microapplibrary.domain.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_component_item.view.*
import kotlinx.android.synthetic.main.product_item.view.*
import java.text.NumberFormat
import java.util.*

class ProductComponentViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

    fun bindItem(
        product: Product
    ) {
        Picasso
            .get()
            .load(product.pictures[0].url)
            .into(itemView.productComponentImageView)

        itemView.productTitleTextView.text = product.title

        itemView.productPriceTextView.text = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            .format(product.price)
    }
}