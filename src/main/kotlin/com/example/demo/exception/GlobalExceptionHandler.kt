package com.example.demo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(e: BusinessException): ResponseEntity<String> {
        val errorCode: ErrorCode = e.errorCode
        val response: String = errorCode.message
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}