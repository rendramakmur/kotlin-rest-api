package com.trykotlin.restfulapi.services.impl

import com.trykotlin.restfulapi.entity.Product
import com.trykotlin.restfulapi.error.NotFoundException
import com.trykotlin.restfulapi.model.CreateProductRequest
import com.trykotlin.restfulapi.model.ListProductRequest
import com.trykotlin.restfulapi.model.ProductResponse
import com.trykotlin.restfulapi.model.UpdateProductRequest
import com.trykotlin.restfulapi.repository.ProductRepository
import com.trykotlin.restfulapi.services.ProductService
import com.trykotlin.restfulapi.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
    ): ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product(
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductOrThrowNotFound(id)

        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        validationUtil.validate(updateProductRequest)

        val product = findProductOrThrowNotFound(id)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductOrThrowNotFound(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        println(page.get().toList())
        println(page.get().collect(Collectors.toList()))
        val products: List<Product> = page.get().collect(Collectors.toList())
        println(products.map { convertProductToProductResponse(it) })
        return products.map { convertProductToProductResponse(it) }
    }

    private fun findProductOrThrowNotFound(id: String): Product {
        val product = productRepository.findByIdOrNull(id)

        if (product == null) {
            throw NotFoundException()
        } else {
            return product
        }
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
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