package br.com.soluevo.microapplibrary.application.builders

import br.com.soluevo.microapplibrary.application.commom.utils.extensions.randomRange
import br.com.soluevo.microapplibrary.domain.Picture
import br.com.soluevo.microapplibrary.domain.Product

class ProductBuilder {

    private var id = 0
    private var price = 10.0
    private var title = ""
    private var description = ""
    private var pictures = mutableListOf<Picture>()

    fun withId(id: Int): ProductBuilder {
        this.id = id
        return this
    }

    fun withPrice(price: Double): ProductBuilder {
        this.price = price
        return this
    }

    fun withTitle(title: String): ProductBuilder {
        this.title = title
        return this
    }

    fun withDescription(description: String): ProductBuilder {
        this.description = description
        return this
    }

    fun withPicture(picture: Picture): ProductBuilder {
        this.pictures = mutableListOf(picture)
        return this
    }

    fun oneProduct(): ProductBuilder {
        val picture = PictureBuilder()
            .onPicture()
            .build()

        id = 100.randomRange()
        title = "title ${100.randomRange()}"
        description = "description ${100.randomRange()}"
        price = 100.randomRange().toDouble()
        pictures = mutableListOf(picture)

        return this
    }

    fun build(): Product {
        return Product(id, price, title, description, pictures)
    }


}