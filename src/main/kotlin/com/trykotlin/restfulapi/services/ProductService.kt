package com.trykotlin.restfulapi.services

import com.trykotlin.restfulapi.model.CreateProductRequest
import com.trykotlin.restfulapi.model.ProductResponse

interface ProductService {

   fun create(createProductRequest: CreateProductRequest): ProductResponse

}