package com.trykotlin.restfulapi.repository

import com.trykotlin.restfulapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
}