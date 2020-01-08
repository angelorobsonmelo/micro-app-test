package br.com.soluevo.microapplibrary.application.fragments.products.products

import br.com.soluevo.utils.FileUtils
import com.squareup.okhttp.mockwebserver.Dispatcher
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.RecordedRequest

class ProductsMockServerDispatcher {

    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/products" -> MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        FileUtils.getJson("json/products/products.json")
                    )
                else -> MockResponse().setResponseCode(404)
            }

        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {

            return MockResponse().setResponseCode(400)

        }
    }
}