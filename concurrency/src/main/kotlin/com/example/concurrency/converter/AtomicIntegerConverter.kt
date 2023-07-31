package com.example.concurrency.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.util.concurrent.atomic.AtomicInteger


@Converter
class AtomicIntegerConverter : AttributeConverter<AtomicInteger, Int> {
    override fun convertToDatabaseColumn(attribute: AtomicInteger): Int {
        return attribute.get()
    }

    override fun convertToEntityAttribute(dbData: Int): AtomicInteger {
        return AtomicInteger(dbData)
    }
}