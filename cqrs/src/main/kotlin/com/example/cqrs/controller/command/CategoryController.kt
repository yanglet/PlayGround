package com.example.cqrs.controller.command

import com.example.cqrs.controller.command.dto.CategoryResponse
import com.example.cqrs.controller.command.dto.CreateCategoryRequest
import com.example.cqrs.controller.command.dto.UpdateCategoryRequest
import com.example.cqrs.service.command.CategoryService
import com.example.cqrs.service.command.dto.CategoryResult
import com.example.cqrs.service.command.dto.CreateCategoryParam
import com.example.cqrs.service.command.dto.UpdateCategoryParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    fun getCategories() =
        ResponseEntity.ok(
            categoryService.getCategories().map { it.toResponse() }
        )

    @PostMapping
    fun createCategory(@RequestBody request: CreateCategoryRequest) =
        ResponseEntity.ok(
            categoryService.createCategory(request.toParam()).toResponse()
        )

    @PatchMapping("/{categoryNo}")
    fun updateCategory(
        @PathVariable categoryNo: Long,
        @RequestBody request: UpdateCategoryRequest
    ) = ResponseEntity.ok(
        categoryService.updateCategory(request.toParam(categoryNo)).toResponse()
    )

    private fun CreateCategoryRequest.toParam() = CreateCategoryParam(
        categoryName = this.categoryName
    )

    private fun UpdateCategoryRequest.toParam(categoryNo: Long) = UpdateCategoryParam(
        categoryNo = categoryNo,
        categoryName = this.categoryName
    )

    private fun CategoryResult.toResponse() = CategoryResponse(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName
    )
}