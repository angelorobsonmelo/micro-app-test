package br.com.soluevo.microapplibrary.application.components.product

import br.com.soluevo.microapplibrary.domain.Product

interface ProductComponentClickListener {

    fun onclick(product: Product, position: Int)
    fun onLongClick(product: Product, position: Int)
}