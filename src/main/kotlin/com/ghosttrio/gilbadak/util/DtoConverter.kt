package com.ghosttrio.gilbadak.util

interface DtoConverter<S, T> {
    fun convert(source: S): T
}