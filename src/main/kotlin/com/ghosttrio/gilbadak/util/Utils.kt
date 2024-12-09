package com.ghosttrio.gilbadak.util

object Utils {
    fun <T> T?.throwIfPresent(exception: Throwable) {
        // let은 null이 아닌 경우에만 실행된다.
        // 결국 T 타입으로 들어오는 객체가 null이 아니면 Throw
        this?.let {
            throw exception
        }
    }
}