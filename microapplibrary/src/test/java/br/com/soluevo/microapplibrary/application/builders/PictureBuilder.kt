package br.com.soluevo.microapplibrary.application.builders

import br.com.soluevo.microapplibrary.application.commom.utils.extensions.randomRange
import br.com.soluevo.microapplibrary.domain.Picture
import kotlin.random.Random

class PictureBuilder {

    private var id = 0
    private var title = ""
    private var url = ""

    fun withId(id: Int): PictureBuilder {
        this.id = id
        return this
    }

    fun withTitle(title: String): PictureBuilder {
        this.title = title
        return this
    }

    fun withUrl(url: String): PictureBuilder {
        this.url = url
        return this
    }

    fun onPicture(): PictureBuilder {
        this.id = 100.randomRange()
        this.title = "Title ${100.randomRange()}"
        this.url = "url ${100.randomRange()}"
        return this
    }

    fun build(): Picture {
        return Picture(id, title, url)
    }
}