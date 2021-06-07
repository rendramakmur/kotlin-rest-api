package com.trykotlin.restfulapi.services.impl

import com.trykotlin.restfulapi.entity.Product
import com.trykotlin.restfulapi.model.CreateProductRequest
import com.trykotlin.restfulapi.model.ProductResponse
import com.trykotlin.restfulapi.repository.ProductRepository
import com.trykotlin.restfulapi.services.ProductService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(val productRepository: ProductRepository): ProductService {
    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        val product = Product(
            id = createProductRequest.id,
            name = createProductRequest.name,
            price = createProductRequest.price,
            quantity = createProductRequest.quantity,
            createdAt = Date(),
            updatedAt = null
        )

        productRepository.save(product)

        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }
}