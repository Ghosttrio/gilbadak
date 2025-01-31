package com.ghosttrio.gilbadak.util

interface ServiceRequestConverter<T> {
    fun convert() : T
}