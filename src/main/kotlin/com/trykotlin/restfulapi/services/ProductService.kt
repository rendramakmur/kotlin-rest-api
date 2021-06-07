package com.trykotlin.restfulapi.services

import com.trykotlin.restfulapi.model.CreateProductRequest
import com.trykotlin.restfulapi.model.ListProductRequest
import com.trykotlin.restfulapi.model.ProductResponse
import com.trykotlin.restfulapi.model.UpdateProductRequest

interface ProductService {

   fun create(createProductRequest: CreateProductRequest): ProductResponse

   fun get(id: String): ProductResponse

   fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse

   fun delete(id: String)

   fun list(listProductRequest: ListProductRequest): List<ProductResponse>
}