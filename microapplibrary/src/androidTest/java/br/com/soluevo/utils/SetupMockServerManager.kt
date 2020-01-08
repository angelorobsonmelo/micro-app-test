package br.com.soluevo.utils

import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class SetupMockServerManager {

    lateinit var mockServer: MockWebServer

    @Before
    @Throws
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }
}