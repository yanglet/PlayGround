package com.example.cqrs.service.command.dto

data class CreateCategoryParam(
    val categoryName: String
)

data class UpdateCategoryParam(
    val categoryNo: Long,
    val categoryName: String
)

data class CategoryResult(
    val categoryNo: Long,
    val categoryName: String
)