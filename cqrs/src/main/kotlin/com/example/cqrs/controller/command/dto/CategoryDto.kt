package com.example.cqrs.controller.command.dto

data class CreateCategoryRequest(
    val categoryName: String
)

data class UpdateCategoryRequest(
    val categoryNo: Long,
    val categoryName: String
)

data class CategoryResponse(
    val categoryNo: Long,
    val categoryName: String
)