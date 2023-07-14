package com.example.cqrs.exception

data class ExceptionResponse (
    val errorCode: Int,
    val errorMessage: String? = "",
    val stackTrace: String? = ""
)