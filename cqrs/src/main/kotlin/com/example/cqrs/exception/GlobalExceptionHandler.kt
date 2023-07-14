package com.example.cqrs.exception

import com.example.cqrs.service.command.exception.CategoryNotFoundException
import com.example.cqrs.service.command.exception.HotdealItemNotFoundException
import com.example.cqrs.service.command.exception.SellerNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [SellerNotFoundException::class])
    fun sellerNotFoundExceptionHandler(e: SellerNotFoundException) =
        ResponseEntity.ok(
            ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                e.message,
                e.stackTraceToString()
            )
        )

    @ExceptionHandler(value = [CategoryNotFoundException::class])
    fun categoryNotFoundExceptionHandler(e: CategoryNotFoundException) =
        ResponseEntity.ok(
            ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                e.message,
                e.stackTraceToString()
            )
        )

    @ExceptionHandler(value = [HotdealItemNotFoundException::class])
    fun HotdealNotFoundExceptionHandler(e: HotdealItemNotFoundException) =
        ResponseEntity.ok(
            ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                e.message,
                e.stackTraceToString()
            )
        )

    @ExceptionHandler(value = [Exception::class])
    fun defaultExceptionHandler(e: Exception) =
        ResponseEntity.ok(
            ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.message,
                e.stackTraceToString()
            )
        )
}