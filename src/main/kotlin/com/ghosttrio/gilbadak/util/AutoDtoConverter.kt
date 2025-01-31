package com.ghosttrio.gilbadak.util

import java.lang.reflect.Field

class AutoDtoConverter<S, T>(private val targetClass: Class<T>) : DtoConverter<S, T> {

    override fun convert(source: S): T {
        if (source == null) {
            throw RuntimeException()
        }

        return try {
            // 새로운 인스턴스 생성
            val target = targetClass.getDeclaredConstructor().newInstance()

            // source 객체의 필드를 순회
            val sourceFields = source.javaClass.declaredFields
            for (sourceField in sourceFields) {
                sourceField.isAccessible = true // 필드 접근을 허용

                try {
                    // target 객체에서 동일한 이름의 필드 찾기
                    val targetField: Field = targetClass.getDeclaredField(sourceField.name)
                    targetField.isAccessible = true // 필드 접근을 허용

                    // 값을 가져와서 target 객체에 설정
                    val value = sourceField.get(source)
                    targetField.set(target, value)
                } catch (e: NoSuchFieldException) {
                    // 필드가 target에 없으면 무시하거나 로깅할 수 있음
                } catch (e: IllegalAccessException) {
                    // 필드에 접근할 수 없을 때 예외 처리
                }
            }

            target
        } catch (e: Exception) {
            throw RuntimeException("Error during DTO conversion", e)
        }
    }
}
