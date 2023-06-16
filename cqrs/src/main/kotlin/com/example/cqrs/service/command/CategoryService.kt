package com.example.cqrs.service.command

import com.example.cqrs.repository.command.Category
import com.example.cqrs.repository.command.CategoryRepository
import com.example.cqrs.service.command.dto.CreateCategoryParam
import com.example.cqrs.service.command.dto.CategoryResult
import com.example.cqrs.service.command.dto.UpdateCategoryParam
import com.example.cqrs.service.command.exception.CategoryNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun createCategory(param: CreateCategoryParam): CategoryResult =
        categoryRepository.save(
            Category(categoryName = param.categoryName)
        ).toDto()

    fun updateCategory(param: UpdateCategoryParam): CategoryResult {
        val category = categoryRepository.findByIdOrNull(param.categoryNo)

        category?.run {
            this.categoryName = param.categoryName
        } ?: throw CategoryNotFoundException()

        return category.toDto()
    }

    private fun Category.toDto() = CategoryResult(
        categoryNo = this.categoryNo,
        categoryName = this.categoryName
    )
}