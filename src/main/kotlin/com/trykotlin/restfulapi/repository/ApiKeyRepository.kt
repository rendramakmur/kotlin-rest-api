package com.trykotlin.restfulapi.repository

import com.trykotlin.restfulapi.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository: JpaRepository<ApiKey, String> {
}