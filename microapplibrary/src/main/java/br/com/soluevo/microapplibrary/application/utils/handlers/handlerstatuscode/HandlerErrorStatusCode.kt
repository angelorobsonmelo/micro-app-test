package br.com.soluevo.microapplibrary.application.utils.handlers.handlerstatuscode


enum class HandlerErrorStatusCode(val value: Int) : MessageStatusCodeError {

    UNAUTHORIZED(401) {
        override fun getMessageFromResourceString(): Int {
           return 1
        }
    },
    INVALID_CREDENTIALS(0) {
        override fun getMessageFromResourceString(): Int {
            return 2
        }
    },
    INTERNAL_SERVER_ERROR(500) {
        override fun getMessageFromResourceString(): Int {
            return 3
        }
    },
    NO_INTERNET_CONNECTION_ERROR(600) {
        override fun getMessageFromResourceString(): Int {
            return 4
        }
    },
    UNKNOWN_ERROR(520) {
        override fun getMessageFromResourceString(): Int {
           return 5
        }
    },

    SOCKET_TIMOUT_ERROR(1000) {
        override fun getMessageFromResourceString(): Int {
            return 6
        }
    },

    BAD_REQUEST(400) {
        override fun getMessageFromResourceString(): Int {
            return 7
        }
    };

    companion object {

        fun fromInt(statusCode: Int): HandlerErrorStatusCode? {
            return values().singleOrNull { it.value == statusCode } ?: UNKNOWN_ERROR
        }

    }

}

