package com.ghosttrio.gilbadak.util

class GilbadakException(
    private val errorCode: ErrorCode,
    message: String? = errorCode.message,
    cause: Throwable? = null
): RuntimeException(message, cause) {
    constructor(errorCode: ErrorCode) : this(errorCode, errorCode.message)
}