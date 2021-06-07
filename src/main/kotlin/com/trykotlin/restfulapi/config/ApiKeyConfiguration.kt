package com.trykotlin.restfulapi.config

import com.trykotlin.restfulapi.entity.ApiKey
import com.trykotlin.restfulapi.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner

class ApiKeyConfiguration(val apiKeyRepository: ApiKeyRepository): ApplicationRunner {
    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if (!apiKeyRepository.existsById(apiKey)) {
            val entity = ApiKey(id = apiKey)
            apiKeyRepository.save(entity)
        }
    }
}