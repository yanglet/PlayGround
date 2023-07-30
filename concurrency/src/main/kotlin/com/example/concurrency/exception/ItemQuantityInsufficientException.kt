package com.example.concurrency.exception

class ItemQuantityInsufficientException(override val message: String?) : RuntimeException(message)