package com.example.cqrs.controller.command

import com.example.cqrs.controller.command.dto.CreateSellerRequest
import com.example.cqrs.controller.command.dto.SellerResponse
import com.example.cqrs.controller.command.dto.UpdateSellerRequest
import com.example.cqrs.service.command.SellerService
import com.example.cqrs.service.command.dto.CreateSellerParam
import com.example.cqrs.service.command.dto.SellerResult
import com.example.cqrs.service.command.dto.UpdateSellerParam
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/sellers")
class SellerController(
    private val sellerService: SellerService
) {
    @PostMapping
    fun createSeller(@RequestBody request: CreateSellerRequest) =
        ResponseEntity.ok(
            sellerService.createSeller(request.toParam()).toResponse()
        )

    @PutMapping("/{sellerNo}")
    fun updateSeller(
        @PathVariable sellerNo: Long,
        @RequestBody request: UpdateSellerRequest
    ) = ResponseEntity.ok(
        sellerService.updateSeller(request.toParam(sellerNo)).toResponse()
    )

    private fun CreateSellerRequest.toParam() = CreateSellerParam(
        email = this.email,
        name = this.name
    )

    private fun UpdateSellerRequest.toParam(sellerNo: Long) = UpdateSellerParam(
        sellerNo = sellerNo,
        email = this.email,
        name = this.name
    )

    private fun SellerResult.toResponse() = SellerResponse(
        sellerNo = this.sellerNo,
        email = this.email,
        name = this.name
    )
}