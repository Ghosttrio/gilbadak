package com.ghosttrio.gilbadak.util

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

data class GilbadakResponse<T> (
    val data: T?,
    val message: String?,
    val responseCode: HttpStatusCode
) {
    companion object {

        fun <T> empty() : GilbadakResponse<T> = GilbadakResponse(null, "Response Data가 없습니다.", HttpStatus.OK)

        fun <T> ok(data: T) : GilbadakResponse<T> = GilbadakResponse(data, null, HttpStatus.OK)

        fun <T> ok(data: T, message: String) : GilbadakResponse<T> = GilbadakResponse(data, message, HttpStatus.OK)

        fun <T> fail(message: String, errorCode: HttpStatusCode) : GilbadakResponse<T> = GilbadakResponse(null, message, errorCode)

    }
}