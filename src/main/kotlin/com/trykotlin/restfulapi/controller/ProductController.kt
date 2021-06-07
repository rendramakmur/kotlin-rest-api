package com.trykotlin.restfulapi.controller

import com.trykotlin.restfulapi.model.CreateProductRequest
import com.trykotlin.restfulapi.model.ProductResponse
import com.trykotlin.restfulapi.model.WebResponse
import com.trykotlin.restfulapi.services.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
        value = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)

        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

}