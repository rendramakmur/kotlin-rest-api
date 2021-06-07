package com.trykotlin.restfulapi.controller

import com.trykotlin.restfulapi.error.NotFoundException
import com.trykotlin.restfulapi.error.UnauthorizedException
import com.trykotlin.restfulapi.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "Bad Request",
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(notFoundException: NotFoundException): WebResponse<String> {
        return WebResponse(
            code = 404,
            status = "Not Found",
            data = "Not found"
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun notFound(unauthorizedException: UnauthorizedException): WebResponse<String> {
        return WebResponse(
            code = 401,
            status = "Unauthorized, please ",
            data = "Please put your Api-Key first."
        )
    }
}