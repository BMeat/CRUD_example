package com.example.demo.exception

class BusinessException(val errorCode: ErrorCode, message: String? = null) : RuntimeException(message) {
    constructor(errorCode: ErrorCode) : this(errorCode, errorCode.message)
}