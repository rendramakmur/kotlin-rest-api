package com.trykotlin.restfulapi.model

data class CreateProductRequest(
    val id: String,
    val name: String,
    val price: Long,
    val quantity: Int
)